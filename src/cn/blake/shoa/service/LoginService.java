package cn.blake.shoa.service;

import cn.blake.shoa.domain.User;

public interface LoginService {
	public User loginUser(String username, String password);

	public User loginUser(String username);
}
