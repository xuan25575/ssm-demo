package com.training.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloShiro {
    private static final Logger  logger = LoggerFactory.getLogger(HelloShiro.class);

    public static void main(String[] args) {
        DefaultSecurityManager defaultSecurityManager =   new DefaultSecurityManager();
        IniRealm  iniRealm = new IniRealm("classpath:shiro.ini");
        defaultSecurityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("shiro","123456");

        try{
            subject.login(token);
        }catch (AuthenticationException e ){
            logger.info("登录失败");
            return;
        }
        logger.info("登录成功，hello "+ subject.getPrincipal());
        // 注销
        subject.logout();

    }
}
