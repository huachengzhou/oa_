package cn.blake.shoa.service;

import java.io.Serializable;
import java.util.Collection;

import cn.blake.shoa.domain.User;

/**
 * @see User ҵ���
 * @author Blake.Zhou
 * 
 */
public interface UserService {
	/**
	 * ��ѯ���е��û�
	 * 
	 * @return
	 */
	public Collection<User> getAllUser();

	/**
	 * �����û�
	 */
	public void saveUser(User user);

	/**
	 * �޸��û�
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * @see �����쳣
	 * @param user
	 * @param id
	 */

	/**
	 * ɾ���û�
	 */
	public void deleteUser(Serializable id);

	/**
	 * ����id��ѯuser
	 */
	public User getUserById(Serializable id);

}
