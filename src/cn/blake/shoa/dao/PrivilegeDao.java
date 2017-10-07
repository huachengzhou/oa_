package cn.blake.shoa.dao;

import java.util.Collection;

import cn.blake.shoa.dao.base.BaseDao;
import cn.blake.shoa.domain.Privilege;

public interface PrivilegeDao extends BaseDao<Privilege> {
	public Collection<Privilege> getPrivilegesByRid(Integer rid);

	public Collection<Privilege> getMenuitemsByUid(Integer uid, String username);

	/**
	 * �����û�ID�õ����û��Ĺ���Ȩ��
	 * 
	 * @param uid
	 * @return
	 */
	public Collection<Privilege> getFunctionsByUid(Integer uid);
}
