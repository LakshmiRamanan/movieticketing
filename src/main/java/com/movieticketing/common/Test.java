package com.movieticketing.common;

import com.movieticketing.bo.LoginBO;
import com.movieticketing.model.Login;
import com.movieticketing.model.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by nagal_000 on 11/30/2015.
 */
public class Test {

    @Autowired
    LoginBO loginBO;

    public static void main(String[] args) {
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("config/applicationContext.xml");

       // LoginBO bo = (LoginBO) appContext.getBean("loginBo");
        Login lg = new Login();
        lg.setUserId("ani@bazinga.com");
        lg.setPassword("ani123");
        ResultBean rb = new Test().loginBO.validateLogin(lg);
        System.out.println("Login:" + rb);
    }
}
