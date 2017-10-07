package cn.blake.test;

import java.util.Collection;

import util.SessionFactoryUtil;
import cn.blake.shoa.domain.Privilege;
import cn.blake.shoa.domain.User;
import cn.blake.shoa.service.PrivilegeService;

public class PrivilegeServiceTest2 {
	private static PrivilegeService privilegeService = SessionFactoryUtil.getCtx().getBean(PrivilegeService.class);
    public static void main(String[] args) {
		User user = new  User();
		user.setUsername("blake");
		user.setUid(43);
		Collection<Privilege> privileges = privilegeService.getPrivilegesByUid(user.getUid(),user.getUsername());
		for (Privilege privilege : privileges) {
			System.out.println(privilege);
		}
	}
}
