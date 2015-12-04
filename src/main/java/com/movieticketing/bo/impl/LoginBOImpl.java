package com.movieticketing.bo.impl;

import com.movieticketing.bo.LoginBo;
import com.movieticketing.dao.LoginDao;
import com.movieticketing.model.Login;
import com.movieticketing.model.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public class LoginBOImpl implements LoginBo {

    @Autowired
	LoginDao loginDao;

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

	public Login getLoginCredentials(String userName) {
		return loginDao.getLoginCredentials(userName);
	}

    public ResultBean validateLogin(Login login) {
        ResultBean rb = new ResultBean();
        Boolean status = loginDao.validateLogin(login);
        if(status) {
            rb.setStatus(200);
        }
        else{
            rb.setStatus(400);
        }
        return rb;
    }

}
