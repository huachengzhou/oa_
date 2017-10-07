package cn.blake.shoa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.blake.shoa.dao.LoginDao;
import cn.blake.shoa.dao.base.BaseDaoImpl;
import cn.blake.shoa.domain.User;
@Repository
public class LoginDaoImpl extends BaseDaoImpl<User> implements LoginDao {

	public User loginUser(String username, String password) {
		return login(username, password);
	}

	public User loginUser(String username) {
		return login(username);
	}

}
