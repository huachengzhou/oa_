package cn.blake.shoa.dao;

import cn.blake.shoa.dao.base.BaseDao;
import cn.blake.shoa.domain.User;

public interface LoginDao extends BaseDao<User> {
    public User loginUser(String username, String password);
    public User loginUser(String username);
}
