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
		// user = fllObjectUser(user);/*���˻���ֱ����BaseFaoImple����ȽϺ�*/
		userDao.updateEntry(user, user.getUid());
		/**
		 * �����������֣�һ����ֱ�����session,��һ����������po�ڵ���get����load
		 * method���session�־û�ʵ��,Ȼ��ɱ��,������evict method
		 */
	}

	public User fllObjectUser(User user) {/*
										 * ����쳣����
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
