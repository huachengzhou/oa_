package cn.blake.shoa.dao.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.blake.shoa.dao.PrivilegeDao;
import cn.blake.shoa.dao.base.BaseDaoImpl;
import cn.blake.shoa.domain.Privilege;
@Repository
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements PrivilegeDao {

	public Collection<Privilege> getPrivilegesByRid(Integer rid) {
		Collection<Privilege> allPrivilege = this.getAllEntry();
		@SuppressWarnings("unchecked")
		Collection<Privilege> rolePrivilege = (Collection<Privilege>) this.hibernateTemplate.find("from Privilege p inner join fetch p.roles r where r.rid=?",rid);
		for (Privilege privilege : allPrivilege) {
			for (Privilege privilege2 : rolePrivilege) {
				if (privilege.getId().intValue()==privilege2.getId().intValue()) {
					privilege.setChecked(true);
					break;
				}
			}
		}
		return allPrivilege;
	}

	@SuppressWarnings("unchecked")
	public Collection<Privilege> getMenuitemsByUid(Integer uid, String username) {
		List<Privilege> privileges = null;
		if("blake".equals(username)){
			privileges = (List<Privilege>) hibernateTemplate.find("from Privilege where flag='1'");
		}else{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("from Privilege p inner join fetch p.roles r inner join fetch r.users u");
			stringBuffer.append(" where u.uid=?");
			stringBuffer.append(" and flag='1'");
			privileges = (List<Privilege>) hibernateTemplate.find(stringBuffer.toString(),uid);
		}
		return new HashSet<Privilege>(privileges);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Privilege> getFunctionsByUid(Integer uid) {
		List<Privilege> privileges = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Privilege p inner join fetch p.roles r inner join fetch r.users u");
		stringBuffer.append(" where u.uid=?");
		stringBuffer.append(" and flag='1'");
		privileges = (List<Privilege>) hibernateTemplate.find(stringBuffer.toString(),uid);
		return new HashSet<Privilege>(privileges);
	}

}
