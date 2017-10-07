package cn.blake.test;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import util.SessionFactoryUtil;
import cn.blake.shoa.domain.Privilege;
import cn.blake.shoa.domain.Role;
import cn.blake.shoa.service.RoleService;


public class RoleTest{
	RoleService roleService = SessionFactoryUtil.getCtx().getBean(RoleService.class);
	Session session = SessionFactoryUtil.getSessionFactory().openSession();
	public static void main(String[] args) {
		RoleTest roleTest = new RoleTest();
		roleTest.getRolesByUid();
		
		roleTest.session.close();
	}
	public void getRolesByUid(){
		Collection<Role> collection = roleService.getRolesByUid(61);
		for (Role role : collection) {
			System.out.println(role);
		}
	}
	public void testSaveRole(){
		/**
		 * �û���ɫ����λ��ɫ�����Ž�ɫ
		 */
		Role role1 = new Role();
		role1.setName("�û���ɫ");
		role1.setDescription("ӵ���û�ģ���crud�Ĳ���");
		//������ɫ��Ȩ��֮��Ĺ���
		@SuppressWarnings("unchecked")
		List<Privilege> privileges1 = session.createQuery("from Privilege where id in(38,39,40,41)").list();
		//role1.setPrivileges(new HashSet<Privilege>(privileges1));
		roleService.saveRole(role1);
		
		Role role2 = new Role();
		role2.setName("���Ž�ɫ");
		role2.setDescription("ӵ�в���ģ���crud�Ĳ���");
		//������ɫ��Ȩ��֮��Ĺ���
		List<Privilege> privileges2 = session.createQuery("from Privilege where id in(42,44,45,46)").list();
		//role2.setPrivileges(new HashSet<Privilege>(privileges2));
		roleService.saveRole(role2);
		
		Role role3 = new Role();
		role3.setName("��λ��ɫ");
		role3.setDescription("ӵ�и�λģ���crud�Ĳ���");
		//������ɫ��Ȩ��֮��Ĺ���
		List<Privilege> privileges3 = session.createQuery("from Privilege where id in(43,47,48,49)").list();
		//role3.setPrivileges(new HashSet<Privilege>(privileges3));
		roleService.saveRole(role3);
		
	}
}
