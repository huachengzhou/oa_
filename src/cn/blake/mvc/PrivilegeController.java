package cn.blake.mvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.blake.activiti.SpringJDBC;
import cn.blake.activiti.SpringJDBCImpl;
import cn.blake.shoa.domain.Privilege;
import cn.blake.shoa.domain.Role;
import cn.blake.shoa.domain.User;
import cn.blake.shoa.service.PrivilegeService;
import cn.blake.shoa.service.RoleService;

/**
 * Ȩ�޿�����
 * 
 * @author Blake.Zhou
 * 
 */
@Controller
public class PrivilegeController {
	private static final Log logger = LogFactory
			.getLog(PrivilegeController.class);
	private SpringJDBC springJDBC = new SpringJDBCImpl();
	
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/showPrivilegeByRid")
	public @ResponseBody
	Object showPrivilegeByRid(@RequestParam String rid) {
		logger.info("showPrivilegeByRid() " + rid);
		Collection<Privilege> privileges = privilegeService
				.getPrivilegesByRid(Integer.parseInt(rid));
		return privileges;
	}

	@RequestMapping(value = "/savePrivilege__", method = RequestMethod.POST)
	public @ResponseBody
	String savePrivilege(@RequestParam String rid, @RequestParam String str) {
		Role role = roleService.getRoleById(Integer.parseInt(rid));
		Set<Privilege> privileges = privilegeService.getPrivileges(str);
		System.out.println(privileges);
		role.setPrivileges(privileges);
		
		roleService.updateRole_(role);
		
		String[] strings = str.split(",");
		List<String> sList = new ArrayList<String>();
		for (String string : strings) {
			sList.add(string);
		}
		/*�õ����ݿ��н�ɫ��Ȩ��*/
//		List<String> idData = springJDBC.getIList(Integer.parseInt(rid));
//		try {
//			if (sList.size()>idData.size()) {//����
//				sList.removeAll(idData);
//				for (String string : sList) {
//					springJDBC.insert(Integer.parseInt(string),Integer.parseInt(rid));
//				}
//			}else {
//				idData.removeAll(sList);
//				for (String string : sList) {
//					springJDBC.insert(Integer.parseInt(string),Integer.parseInt(rid));
//				}
//			}
//		} catch (Exception e) {
//		}
		logger.info("rid:" + role + " str:" + str);
		
		if (privileges != null) {
			return "success ";
		}
		return "";
	}

	@RequestMapping(value = "/showMenuitemTreeByUid")
	public @ResponseBody
	Object showMenuitemTreeByUidBlakeObject(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		logger.info("Ȩ�������� " + user);
		Collection<Privilege> privileges = new ArrayList<Privilege>();
		if (user != null) {
			/*��Ϊ:cn.blake.shoa.dao.impl.PrivilegeDaoImpl �Ѿ������ж�*/
			privileges =  privilegeService.getPrivilegesByUid(user.getUid(),user.getUsername());
			/**
			 * ����Ա
			 */
//			if (user.getUsername().equals("blake")) {
//				privileges = privilegeService.getPrivilegesByUid(user.getUid(),user.getUsername());
//			} else {
//				privileges = privilegeService.getPrivilegesByUid(user.getUid());
//			}
			System.out.println("size:" + privileges.size());
		}
		return new ArrayList<Privilege>(privileges);
	}

}
