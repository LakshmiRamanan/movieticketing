package com.movieticketing.bo;

import com.movieticketing.common.LoginDetails;
import com.movieticketing.model.Login;
import com.movieticketing.model.ResultBean;

public interface LoginBO {

	Login getLoginCredentials(String userName);

	ResultBean validateLogin(LoginDetails login);
	
}
