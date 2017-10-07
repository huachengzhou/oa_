package cn.blake.mvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.blake.shoa.domain.Role;
import cn.blake.shoa.domain.User;
import cn.blake.shoa.service.RoleService;
import cn.blake.shoa.service.UserService;

@Controller
public class RoleController {
	private static final Log logger = LogFactory.getLog(RoleController.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	/**
	 * @see ��ʾ��λ�б�
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/showAllRole")
	public ModelAndView showAllRole(ModelAndView mv) {
		Collection<Role> roles = roleService.getAllRole();
		logger.info("showAllRole() ");
		mv.addObject("roles", roles);
		mv.setViewName("system/role/list");
		return mv;
	}

	/**
	 * ת����Ӹ�λ
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addRole_")
	public String saveUI(Model model) {
		logger.info("saveUI() ת����Ӹ�λҳ��");
		model.addAttribute("role", new Role());
		return "system/role/add";
	}

	/**
	 * @see ��Ӹ�λ
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addRole")
	public String add(@ModelAttribute Role role, Model model) {
		logger.info("addRole() ��Ӳ�����Ϣ");
		try {
			roleService.saveRole(role);
		} catch (Exception e) {
			logger.debug("Role:" + role + " " + e.getMessage());
		}
		return "redirect:/showAllRole";
	}

	/**
	 * @see ɾ����λ
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "/removeRole")
	public String removeRole(@RequestParam Integer rid) {
		try {
			roleService.deleteRole(rid);
			logger.info("rid:" + rid);
		} catch (Exception e) {
			logger.debug("rid:" + rid + " " + e.getMessage());
		}
		return "redirect:/showAllRole";
	}

	/**
	 * @see ת����λ�޸�ҳ��
	 * @param rid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateRole_")
	public String updateRole_(@RequestParam Integer rid, Model model) {
		Role role = roleService.getRoleById(rid);
		model.addAttribute("role", role);
		return "system/role/update";
	}

	/**
	 * @see ��λ �޸�
	 * @param model
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/updateRole")
	public String updateRole(Model model, @ModelAttribute Role role) {
		if (role != null) {
			try {
				roleService.updateRole(role);
			} catch (Exception e) {
				logger.debug("role:" + role + " " + e.getMessage());
			}
		}
		return "redirect:/showAllRole";
	}

	/**
	 * @see �ص���ҳ
	 * @return
	 */
	@RequestMapping(value = "/goBackRole")
	public String goBackRole() {
		logger.info("goBackRole() �ص�������ҳ");
		return "redirect:/showAllRole";
	}

	/**
	 * @see ���ؽ�ɫ��
	 * @return
	 */
	@RequestMapping(value = "/showTreeRoleObject", method = RequestMethod.POST)
	public @ResponseBody
	Object showTreeRoleObject(@RequestParam String uid) {
		Collection<Role> roles = roleService.getRolesByUid(Integer
				.parseInt(uid));
		ArrayList<Role> list = new ArrayList<Role>(roles);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("showTreeRoleObject() ���ؽ�ɫ�� " + roles);
		logger.info("�ѽ��O�ÿ��� ");
		return list;
	}
	/**
	 * �첽�ύ����  �����û��ͽ�ɫ֮��Ĺ�ϵ
	 * @param uid
	 * @param checkedStr
	 */
	@RequestMapping(value="/userRoleController")
	public ModelAndView userRoleController(@RequestParam String uid,@RequestParam String checkedStr,ModelAndView mv){
		System.out.println("uid:"+uid+"checks:"+checkedStr);
		User user = userService.getUserById(Integer.parseInt(uid));
		Set<Role> roles = roleService.getEntrysByIDS(checkedStr);
		user.setRoles(roles);
		userService.updateUser(user);
		logger.info("�ѽ��O�ÿ��� " +user);
		mv.setViewName("redirect:/showAllUser");
		return mv;
	}

}
