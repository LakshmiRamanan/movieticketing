package com.movieticketing.dao.impl;

import com.movieticketing.dao.LoginDao;
import com.movieticketing.model.Login;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao {

	public Login getLoginCredentials(String userName) {
		List results = getHibernateTemplate().find("from Login where userId like ?" , userName);
		return (Login) results.get(0);
	}

	public Login validateLogin(Login login) {
		List results = getHibernateTemplate().find("from Login where userId = ? and password = ?" , login.getUserId(), login.getPassword() );
		if(results.size()==1) {
			Login lgin = (Login) results.get(0);
			lgin.setPassword("*****");
			return lgin;
		}
		else
			return null;
	}

}
