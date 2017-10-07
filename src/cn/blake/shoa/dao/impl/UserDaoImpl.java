package cn.blake.shoa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.blake.shoa.dao.UserDao;
import cn.blake.shoa.dao.base.BaseDaoImpl;
import cn.blake.shoa.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

}
