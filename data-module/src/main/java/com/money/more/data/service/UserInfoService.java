package com.money.more.data.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.money.more.common.contants.BasicParamConstant;
import com.money.more.common.contants.DbFieldConstant;
import com.money.more.common.contants.RedisConstant;
import com.money.more.common.enums.StatusEnum;
import com.money.more.common.exception.GlobalException;
import com.money.more.common.utils.DateUtils;
import com.money.more.common.utils.PasswordUtils;
import com.money.more.common.utils.UUIDGenerateUtil;
import com.money.more.data.entity.UserInfo;
import com.money.more.data.entity.UserSaltInfo;
import com.money.more.data.mapper.UserInfoMapper;
import com.money.more.data.model.UserBean;
import com.money.more.data.redis.RedisService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author luna
 * @since 2021-11-24
 */
@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {
    private static final Logger log = LoggerFactory.getLogger(UserRoleInfoService.class);

    @Autowired
    private UserInfoMapper entityMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserSaltInfoService userSaltInfoService;

    /**
     * 用户登录
     *
     * @param bean
     */
    public Map<String, Object> login(UserBean bean) {
        String mobile = bean.getMobile();
        String password = bean.getPassword();
        List<UserInfo> userInfos = this.findByParam(DbFieldConstant.MOBILE, mobile);
        if (CollectionUtils.isEmpty(userInfos)) {
            throw new GlobalException("error_10002");
        }
        // 校验用户状态
        UserInfo userInfo = userInfos.get(0);
        if (Integer.valueOf(userInfo.getStatus()) == StatusEnum.DISABLE.getStatus()) {
            throw new GlobalException("error_10003");
        }
        // 交给shiro管理
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(mobile, bean.getPassword());
        Subject subject = SecurityUtils.getSubject();

        subject.login(usernamePasswordToken);

        String token = (String) subject.getSession().getId();
        // token存入redis
        //
        //
        // redisService.set(RedisConstant.TOKEN_PREFIX + token, userInfo.getUserId(), RedisConstant.EXPIRES_A_DAY);

        Map<String, Object> result = Maps.newHashMap();
        result.put(BasicParamConstant.TOKEN, token);

        return result;
    }


    /**
     * 用户注册
     *
     * @param bean
     */
    public void register(UserBean bean) {
        String mobile = bean.getMobile();
        String inCode = bean.getVerifyCode();
        String password = bean.getPassword();
        String passwordAgain = bean.getPasswordAgain();
        // 校验验证码
        checkVerifyCode(mobile, inCode);
        // 查询当前手机号是否存在
        List<UserInfo> userInfoList = this.findByParam(DbFieldConstant.MOBILE, mobile);
        if (CollectionUtils.isNotEmpty(userInfoList)) {
            log.info("当前手机号已存在：手机号：[{}]", mobile);
            throw new GlobalException("error_10007");
        }
        // 校验密码是否一致
        String salt = UUIDGenerateUtil.uuid(); // 获取随机盐
        String encryptPasswordAgain = encryptPassword(password, passwordAgain, salt);

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(bean, userInfo);
        userInfo.setPassWord(encryptPasswordAgain);

        // 保存用户
        String userId = UUIDGenerateUtil.uuid();
        userInfo.setUserId(userId);
        userInfo.setAmount(BigDecimal.ZERO);
        userInfo.setCreateBy("system");
        userInfo.setLastUpdateBy("system");
        this.add(userInfo);

        // 盐值保存
        userSaltInfoService.addUserSaltInfo(userId, salt);
    }

    /**
     * 获取验证码
     *
     * @param mobile 手机号
     */
    public void getVerifyCode(String mobile) {
        // todo 从公共表获取次数值
        Integer dataCount = 5;
        // 从redis获取当天的当前手机号次数
        String date = DateUtils.dateToDay();
        String key = RedisConstant.SMS_CODE_COUNT.concat(date).concat(":").concat(mobile);
        String redisCount = (String) redisService.get(key);
        redisCount = null == redisCount ? "0" : redisCount;

        Integer executeCount = Integer.valueOf(redisCount) + 1;
        if (executeCount > dataCount) {
            log.info("当前手机号：[{}], 已超次数：[{}]", mobile, redisCount);
            throw new GlobalException("error_10010");
        }
        // 随机生成四位验证码
        Integer code = UUIDGenerateUtil.randomNumber4();
        redisService.set(RedisConstant.VERIFY_CODE_PREFIX + mobile, String.valueOf(code), RedisConstant.EXPIRES_THREE_MINUTE);
        redisService.set(key, String.valueOf(executeCount), RedisConstant.EXPIRES_A_DAY);
        log.info("当前手机号：[{}],验证码为：[{}]", mobile, code);
    }

    /**
     * 忘记密码
     * @param userBean
     */
    public void forgetPassword (UserBean userBean) {
        String mobile = userBean.getMobile();
        String inCode = userBean.getVerifyCode();
        String password = userBean.getPassword();
        String passwordAgain = userBean.getPasswordAgain();
        checkVerifyCode(mobile, inCode); // 校验验证码

        // 查询当前手机号是否存在
        List<UserInfo> userInfoList = this.findByParam(DbFieldConstant.MOBILE, mobile);
        if (CollectionUtils.isEmpty(userInfoList)) {
            log.info("当前手机号已存在：手机号：[{}]", mobile);
            throw new GlobalException("error_10002");
        }
        UserInfo userInfo = userInfoList.get(0);
        String salt = userSaltInfoService.getSalt(userInfo.getUserId());
        // 校验密码
        String encryptPassword = encryptPassword(password, passwordAgain, salt);

        userInfo.setPassWord(encryptPassword);
        entityMapper.updateByPrimaryKeySelective(userInfo);
    }

    /**
     * 修改密码
     * @param userBean
     */
    public void updatePassword (UserBean userBean) {
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        // 校验老密码和数据库是否一致
        String salt = userSaltInfoService.getSalt(userInfo.getUserId());
        String oldPassword = userBean.getPassword();
        String dbPassWord = userInfo.getPassWord();
        if (!dbPassWord.equals(PasswordUtils.encrypt(oldPassword, salt))) {
            log.info(">>>>> 旧密码不匹配，请重新输入 <<<<<");
            throw new GlobalException("error_10012");
        }
        userInfo.setPassWord(PasswordUtils.encrypt(userBean.getPasswordAgain(), salt));
        userInfo.setLastUpdateBy(String.valueOf(userInfo.getId()));
        entityMapper.updateByPrimaryKeySelective(userInfo);
    }






    /**
     * 校验密码
     * @param password
     * @param passwordAgain
     * @param salt
     * @return
     */
    public String encryptPassword (String password, String passwordAgain, String salt) {
        if (!password.equals(passwordAgain)) {
            throw new GlobalException("error_10008");
        }
        String encryptPassword = PasswordUtils.encrypt(password, salt);
        String encryptPasswordAgain = PasswordUtils.encrypt(passwordAgain, salt);
        if (!encryptPassword.equals(encryptPasswordAgain)) {
            throw new GlobalException("error_10008");
        }
        return encryptPasswordAgain;
    }

    /**
     * 校验手机验证码
     * @param mobile 手机号
     * @param inCode 输入的验证码
     * @return
     */
    public void checkVerifyCode (String mobile, String inCode) {
        String redisCode = (String) redisService.get(RedisConstant.VERIFY_CODE_PREFIX + mobile);
        if (null == redisCode) {
            log.info(">>>>> redis验证码为空!！");
            throw new GlobalException("error_10009");
        }
        if (!redisCode.equals(inCode)) {
            log.info("验证码不一致：手机号：[{}], redisCode：[{}], code：[{}]", mobile, redisCode, inCode);
            throw new GlobalException("error_10006");
        }
    }



    /**
     * 新增
     *
     * @param entity 实体
     * @return int
     */
    public void add(UserInfo entity) {
        int insert = entityMapper.insertSelective(entity);
        if (insert <= 0) {
            throw new GlobalException("注册失败");
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @return int
     */
    public void deleteById(Integer id) {
        int only = entityMapper.deleteByPrimaryKey(Long.valueOf(id));
        if (only <= 0) {
            // TODO 异常
        }
    }

    /**
     * 根据id修改
     *
     * @param entity
     * @return int
     */
    public void edit(UserInfo entity) {
        int i = entityMapper.updateByPrimaryKey(entity);
        if (i <= 0) {
            // TODO 异常
        }
    }

    /**
     * 通过查询参数获取单个BaseDict对象
     *
     * @param id id
     * @return UserInfo
     */
    public UserInfo getOneBaseDictByParams(Integer id) {
        return entityMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    /**
     * 分页查询
     *
     * @param entity
     * @return IPage<UserInfo>
     * TODO 分页查询的当前页和条数需要修改
     */
    public IPage<UserInfo> getBaseDictListPage(UserInfo entity) {
        IPage<UserInfo> page = new Page<>(1, 10);
        QueryWrapper<UserInfo> param = new QueryWrapper<>();
        return entityMapper.selectPage(page, param);
    }

    /**
     * 用户Id查询
     * @param name 动态查询
     * @param param 参数
     * @return List<UserInfo>
     */
    public List<UserInfo> findByParam (String name, String param) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper();
        queryWrapper.eq(name, param);
        return entityMapper.selectList(queryWrapper);
    }

}