package com.movieticketing.bo.impl;

import com.movieticketing.bo.LoginBO;
import com.movieticketing.dao.LoginDao;
import com.movieticketing.model.Login;
import com.movieticketing.model.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginBOImpl implements LoginBO {

    @Autowired
	LoginDao loginDao;

	public Login getLoginCredentials(String userName) {
        return loginDao.getLoginCredentials(userName);
	}

    public ResultBean validateLogin(Login login) {
        ResultBean rb = new ResultBean();
        Login lgin = loginDao.validateLogin(login);
        if(lgin!=null) {
            List<Login> rows = new ArrayList<Login>();
            rows.add(lgin);
            rb.setRows(rows);
            rb.setStatus(200);
        }
        else{
            rb.setStatus(400);
        }
        return rb;
    }

}
