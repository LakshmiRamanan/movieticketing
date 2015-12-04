package com.movieticketing.common;

import com.movieticketing.bo.LoginBo;
import com.movieticketing.model.Login;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by nagal_000 on 11/30/2015.
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("config/applicationContext.xml");

        LoginBo bo = (LoginBo) appContext.getBean("loginBo");
        Login login = bo.getLoginCredentials("admin");
        System.out.println("Login:" + login);
    }
}
