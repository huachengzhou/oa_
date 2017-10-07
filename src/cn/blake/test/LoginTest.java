package cn.blake.test;

import util.MyMD5Util;
import util.SessionFactoryUtil;
import cn.blake.shoa.domain.User;
import cn.blake.shoa.service.LoginService;

public class LoginTest {
	static LoginService loginService = SessionFactoryUtil.getCtx().getBean(LoginService.class);
    public static void main(String[] args) throws Exception {
    	isit();
	}
    public static void isit() throws Exception{
    	User user = loginService.loginUser("blake");
    	boolean flag = MyMD5Util.validPassword("123456",user.getPassword());
    	if (flag) {
			System.out.println("³É¹¦!");
		}
    }
}
