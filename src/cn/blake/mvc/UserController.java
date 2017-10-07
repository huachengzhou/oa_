package cn.blake.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import util.MyMD5Util;
import util.UTF_8Utils;
import cn.blake.shoa.InterceptorAndException.PrivilegeInfo;
import cn.blake.shoa.domain.Department;
import cn.blake.shoa.domain.Role;
import cn.blake.shoa.domain.User;
import cn.blake.shoa.service.DepartmentService;
import cn.blake.shoa.service.RoleService;
import cn.blake.shoa.service.UserService;

/**
 * @see Controller
 * @author Blake.Zhou
 * 
 */
@Controller
public class UserController {
	private static final Log logger = LogFactory.getLog(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private DepartmentService departmentService;

	@PrivilegeInfo(name="�û���ѯ")
	@RequestMapping(value = "/showAllUser")
	public ModelAndView showAllUser(ModelAndView mv) {
		Collection<User> users = userService.getAllUser();
		logger.info("showAllUser()");
		mv.addObject("users", users);
		mv.setViewName("system/user/list");
		return mv;
	}

	/**
	 * @see ת�����ҳ��
	 * @param model
	 * @return
	 */
	@PrivilegeInfo(name="�û�����")
	@RequestMapping(value = "/addUser_")
	public String addUser_(Model model) {
		logger.info("addUser_()");
		Collection<Department> departments = departmentService
				.getAllDepartment();
		Collection<Role> roles = roleService.getAllRole();

		List<String> sexList = new ArrayList<String>(Arrays.asList("Ů", "��"));

		model.addAttribute("user", new User());
		model.addAttribute("departments", departments);
		model.addAttribute("sexList", sexList);
		model.addAttribute("roles", roles);
		return "system/user/add";
	}

	@RequestMapping(value = "/addUser")
	public String addUser(@ModelAttribute User user, Model model,
			@RequestParam Integer did, HttpServletRequest request) {
		Department department = departmentService.getDepartmentById(did);
		user.setDepartment(department);
		logger.info("addUser() rids:" + request.getParameter("rids") + " ;"
				+ user);
		String[] rids_ = request.getParameter("rids").split(",");
		try {
			user.setSex(UTF_8Utils.getUTF_8(user.getSex()));
			user.setName(UTF_8Utils.getUTF_8(user.getName()));
			Integer[] rids_Int = new Integer[rids_.length];
			for (int i = 0; i < rids_Int.length; i++) {
				rids_Int[i] = Integer.parseInt(rids_[i]);
			}
			Set<Role> roles = roleService.getEntrysByIDS(rids_Int);
			user.setRoles(roles);
			user.setPassword(MyMD5Util.getEncryptedPwd(user.getPassword()));// MD5����
		} catch (Exception e) {
			System.out.println("û��ֵ!........" + e.getMessage());
		}
		userService.saveUser(user);
		System.out.println(user);
		return "redirect:/showAllUser";
	}

	@RequestMapping(value = "/removeUser")
	public String removeUser(@RequestParam Integer uid) {
		logger.info("removeUser()");
		userService.deleteUser(uid);
		return "redirect:/showAllUser";
	}
	@PrivilegeInfo(name="�û��޸�")
	@RequestMapping(value = "/updateUser_")
	public String updateUser_(@RequestParam Integer uid, Model model) {
		User user = userService.getUserById(uid);
		Collection<Department> departments = departmentService
				.getAllDepartment();
		Collection<Role> roles = roleService.getAllRole();
		logger.info("updateUser_()");
		/* ��һЩ����,��roles�а���user�е�Set<Role>ȥ��,ͬ������Ҳ��һ�� */
		// -------------
		roles.removeAll(user.getRoles());
		departments.remove(user.getDepartment());/* ��������֮��ҳ��Ͳ������ظ����� */
		// .................
		model.addAttribute("user", user);
		model.addAttribute("departments", departments);
		model.addAttribute("roles", roles);
		return "system/user/update";
	}

	@RequestMapping(value = "/updateUser")
	public String updateUser(Model model, @ModelAttribute User user,
			HttpServletRequest request) {
		String did = request.getParameter("did");
		Department department = departmentService.getDepartmentById(Integer
				.parseInt(did));
		user.setDepartment(department);
		try {
			user.setSex(UTF_8Utils.getUTF_8(user.getSex()));
			user.setName(UTF_8Utils.getUTF_8(user.getName()));
			String[] rids_ = request.getParameter("rids").split(",");
			Integer[] rids_Int = new Integer[rids_.length];
			for (int i = 0; i < rids_Int.length; i++) {
				rids_Int[i] = Integer.parseInt(rids_[i]);
			}
			Set<Role> roles = roleService.getEntrysByIDS(rids_Int);
			user.setRoles(roles);
			if (!(user.getPassword().length() == 56)) {/* ��������56��ô����û���޸�,Ȼ��ȡ�� */
				user.setPassword(MyMD5Util.getEncryptedPwd(user.getPassword()));// ����
			}
			logger.info("updateUser() " + user + " rids "
					+ request.getParameter("rids"));
		} catch (Exception e) {
			System.out.println("�쳣��! " + e.getMessage());
		}
		System.out.println(user);
		userService.updateUser(user);
		return "redirect:/showAllUser";
	}

	/**
	 * @see �ص���ҳ
	 * @return
	 */
	@RequestMapping(value = "/goBackUser")
	public String goBackUser() {
		return "redirect:/showAllUser";
	}


}
