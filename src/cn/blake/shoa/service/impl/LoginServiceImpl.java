package cn.blake.shoa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.blake.shoa.dao.LoginDao;
import cn.blake.shoa.domain.User;
import cn.blake.shoa.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginDao loginDao;

	public User loginUser(String username, String password) {
		return loginDao.loginUser(username, password);
	}

	public User loginUser(String username) {
		return loginDao.loginUser(username);
	}

}
