package cn.blake.shoa.service.impl;

import cn.blake.shoa.dao.UserDao;
import cn.blake.shoa.domain.User;
import cn.blake.shoa.service.UserService;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private SessionFactory sessionFactory;

	public Collection<User> getAllUser() {
		return userDao.getAllEntry();
	}

	@Transactional(readOnly = false)
	public void saveUser(User user) {
		userDao.saveEntry(user);
	}

	@Transactional(readOnly = false)
	public void updateUser(User user) {
		// user = fllObjectUser(user);/*算了还是直接在BaseFaoImple处理比较好*/
		userDao.updateEntry(user, user.getUid());
		/**
		 * 还有另外两种，一种是直接清空session,另一种是先利用po在调用get或者load
		 * method获得session持久化实体,然后杀掉,即调用evict method
		 */
	}

	public User fllObjectUser(User user) {/*
										 * 解决异常问题
										 * org.hibernate.NonUniqueObjectException
										 * : a different object with the same
										 * identifier value was already
										 * associated with the session
										 */
		User user2 = (User) sessionFactory.openSession().load(User.class,
				user.getUid());
		sessionFactory.openSession().evict(user2);
		return user2;
	}

	@Transactional(readOnly = false)
	public void deleteUser(Serializable id) {
		userDao.deleteEntry(id);
	}

	public User getUserById(Serializable id) {
		return userDao.getEntryById(id);
	}

}
