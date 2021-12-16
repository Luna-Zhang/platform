package com.money.more.merchant.auth.config;

import com.money.more.common.exception.GlobalException;
import com.money.more.common.utils.PasswordUtils;
import com.money.more.data.entity.UserInfo;
import com.money.more.data.mapper.UserSaltInfoMapper;
import com.money.more.data.service.UserSaltInfoService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: luna
 * @Date: 2018/11/28 14:26
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    private static final Logger log = LoggerFactory.getLogger(CredentialsMatcher.class);

    @Autowired
    private UserSaltInfoService userSaltInfoService;


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        // 获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(utoken.getPassword());
        // 获得数据库中的密码
        String dbPassword = (String) info.getCredentials();

        UserInfo user= (UserInfo)info.getPrincipals().getPrimaryPrincipal();
        String salt = userSaltInfoService.getSalt(user.getUserId());
        if (null == salt) {
            log.info(">>>>> 当前用户：[{}] 盐值为空", user.getUserId());
            throw new GlobalException("error_10011");
        }
        return PasswordUtils.match(inPassword,salt,dbPassword);
    }
}
