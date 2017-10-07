package cn.blake.mvc;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import util.MyMD5Util;
import cn.blake.shoa.domain.User;
import cn.blake.shoa.service.LoginService;

@Controller
public class LoginController {
	private static final Log logger = LogFactory.getLog(LoginController.class);
	@Autowired
	private LoginService loginService;

	/**
	 * @see ��½
	 * @param username
	 * @param password
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(@RequestParam String username,@RequestParam String password,HttpServletRequest request,Model model) {
		boolean flag = false;
		User user = null;
		try {
		user = loginService.loginUser(username);// ȡ��User
			if (user != null) {
				flag = MyMD5Util.validPassword(password, user.getPassword());// ���бȽ�(���ݿⱣ�����md5��ʽ)
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			logger.info("��½�ɹ�!");
			request.getSession().setAttribute("user",user);
			return "frame/index";
		} else {
			logger.info("��½ʧ��!");
			model.addAttribute("error","��½ʧ��!");
			model.addAttribute("user", new User());
			return "system/login/login";
		}
	}

	/**
	 * @see ��½ת��
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login_")
	public String login_(Model model) {
		logger.info("login_() ");
		model.addAttribute("user", new User());
		return "system/login/login";
	}
	
	/**
	 * @see �˳���½
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request){
		logger.info("logout() ");
		request.getSession().removeAttribute("user");
		return "system/login/logout";
	}
}
