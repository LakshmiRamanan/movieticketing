package com.movieticketing.dao.impl;

import com.movieticketing.dao.LoginDao;
import com.movieticketing.model.Login;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import java.util.List;

public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao {

	public Login getLoginCredentials(String userName) {
		List results = getHibernateTemplate().find("from Login where userName like ?" , userName);
		return (Login) results.get(0);
	}

	public Boolean validateLogin(Login login) {
		List results = getHibernateTemplate().find("from Login where userName = ? and password = ?" , login.getUserName(), login.getPassword() );
		if(results.size()==1)
			return true;
		else
			return false;
	}

}
