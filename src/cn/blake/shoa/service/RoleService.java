package cn.blake.shoa.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import cn.blake.shoa.domain.Role;

/**
 * @see ��λҵ���
 * @author Blake.Zhou
 *
 */
public interface RoleService {
	/**
	 * @see ��ȡ���и�λ
	 * @return
	 */
	public Collection<Role> getAllRole();

	/**
	 * @see ��ȡ��λ
	 * @param id
	 * @return
	 */
	public Role getRoleById(Serializable id);

	/**
	 * @see �޸ĸ�λ
	 * @param role
	 */
	public void updateRole(Role role);

	/**
	 * @see ɾ����λ
	 * @param id
	 */
	public void deleteRole(Serializable id);

	/**
	 * @see ��Ӹ�λ
	 * @param role
	 */
	public void saveRole(Role role);
	
	public Set<Role> getEntrysByIDS(Serializable[] ids);
	public Set<Role> getEntrysByIDS(String ids);
	public Collection<Role> getRolesByUid(Integer uid);
	public Collection<Role> getRoles(Integer uid);
	public void updateRole_(Role role);
}
