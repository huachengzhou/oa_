package cn.blake.shoa.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.blake.shoa.dao.RoleDao;
import cn.blake.shoa.domain.Role;
import cn.blake.shoa.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;

	public Collection<Role> getAllRole() {
		return roleDao.getAllEntry();
	}

	public Role getRoleById(Serializable id) {
		return roleDao.getEntryById(id);
	}

	@Transactional(readOnly = false)
	public void updateRole(Role role) {
		 roleDao.updateEntry(role,role.getRid());
	}

	@Transactional(readOnly = false)
	public void deleteRole(Serializable id) {
		roleDao.deleteEntry(id);
	}

	@Transactional(readOnly = false)
	public void saveRole(Role role) {
		roleDao.saveEntry(role);
	}


	public Set<Role> getEntrysByIDS(Serializable[] ids) {
		return roleDao.qetEntryByIds(ids);
	}

	public Collection<Role> getRolesByUid(Integer uid) {
		return roleDao.getRoles(uid);
	}

	public Set<Role> getEntrysByIDS(String ids) {
		return roleDao.qetEntryByIds(ids);
	}

	public Collection<Role> getRoles(Integer uid) {
		return roleDao.getRoles(uid);
	}
	@Transactional(readOnly = false)
	public void updateRole_(Role role) {
		roleDao.updateEntry(role);
	}
}
