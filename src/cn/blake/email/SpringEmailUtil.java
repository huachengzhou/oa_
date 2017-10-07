package cn.blake.email;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
/**
 * 
 * @author Blake.Zhou
 * ������� Util(����xml��ʽ)
 */
public class SpringEmailUtil {
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:config/beans.xml");
	/**
	 * 
	 * @param to  ��������
	 * @param content  ����
	 * @param theme  ����
	 */
	public static void emailUtil(String[] to,String content,String theme){
		mailSender_(mailMessage_(to, content, theme));
	}
    private static SimpleMailMessage mailMessage_(String[] to,String content,String theme){
    	SimpleMailMessage mailMessage = (SimpleMailMessage) ctx.getBean("mailMessage");
    	mailMessage.setTo(to);
    	mailMessage.setText(content);
    	mailMessage.setSubject(theme);
    	return mailMessage;
    }
    private static void mailSender_(SimpleMailMessage mailMessage){
    	JavaMailSenderImpl mailSender = (JavaMailSenderImpl) ctx.getBean("mailSender");
    	mailSender.send(mailMessage);
    }
}
