package com.money.more.merchant.auth.oauth;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.money.more.common.contants.DbFieldConstant;
import com.money.more.common.contants.RedisConstant;
import com.money.more.common.enums.StatusEnum;
import com.money.more.data.entity.UserInfo;
import com.money.more.data.redis.RedisService;
import com.money.more.data.service.UserInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Describe：自定义shiro认证
 * @Author：luna
 * @Date：2021/11/29:12:58
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RedisService redisService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String mobile = token.getUsername();
        List<UserInfo> userInfoList = userInfoService.findByParam(DbFieldConstant.MOBILE, mobile);
        if (CollectionUtils.isEmpty(userInfoList)) {
            logger.info(">>>> 当前用户不存在：[{}]", mobile);
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        // 这里验证authenticationToken和simpleAuthenticationInfo的信息
        UserInfo userInfo = userInfoList.get(0);
        if (Integer.valueOf(userInfo.getStatus()) == StatusEnum.DISABLE.getStatus()) {
            throw new LockedAccountException("error_10003");
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userInfo, userInfo.getPassWord(), getName());
        return simpleAuthenticationInfo;
    }

//    @Override
//    public boolean supports(AuthenticationToken token) {
//        return token instanceof OAuth2Token;
//    }
}
