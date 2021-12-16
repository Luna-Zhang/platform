package com.money.more.merchant.auth.config;


import com.money.more.merchant.auth.oauth.ShiroFilter;
import com.money.more.merchant.auth.oauth.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Describe：shiro配置
 * @Author：luna
 * @Date：2021/11/29:14:09
 */
@Configuration
public class ShiroConfig {


    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new ShiroFilter());
        filterFactoryBean.setFilters(filters);

        Map<String, String> filterMap = new HashMap<>();
        // swagger
        filterMap.put("/doc.html", "anon");
        filterMap.put("/swagger-resources", "anon");
        filterMap.put("/v2/**", "anon");

        filterMap.put("/register","anon"); // 注册
        filterMap.put("/login","anon"); // 登录
        filterMap.put("/forgetPassword","anon"); // 忘记密码
        filterMap.put("/getVerifyCode","anon"); // 获取验证码


        filterMap.put("/**", "oauth2"); // 对所有用户认证
        filterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return filterFactoryBean;
    }

    /**
     * 加入注解的使用，不加入这个注解不生效
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher());

        return userRealm;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

}
