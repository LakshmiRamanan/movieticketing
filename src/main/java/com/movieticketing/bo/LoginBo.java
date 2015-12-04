package com.movieticketing.bo;

import com.movieticketing.model.Login;
import com.movieticketing.model.ResultBean;
import org.springframework.stereotype.Component;

public interface LoginBo {

	Login getLoginCredentials(String userName);

	ResultBean validateLogin(Login login);
	
}
