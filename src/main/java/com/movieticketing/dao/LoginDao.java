package com.movieticketing.dao;

import com.movieticketing.model.Login;
import org.springframework.stereotype.Component;

public interface LoginDao {

	Login getLoginCredentials(String userName);

	Login validateLogin(Login login);
}
