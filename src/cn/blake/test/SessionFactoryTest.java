package cn.blake.test;

import org.hibernate.Session;
import org.junit.Test;

import util.SessionFactoryUtil;

public class SessionFactoryTest {
	Session session = SessionFactoryUtil.getSessionFactory().openSession();
	@Test
	public void sessionTest(){
		System.out.println(session);
	}
}
