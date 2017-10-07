package cn.blake.email;

import java.util.Date;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * �ع������ʼ�����
 */
public class EmailMimeMessage {
	/**
	 * 
	 * @param session
	 * @param sendMail
	 * @param email
	 * @param title
	 * @param theme
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage getMimeMessageUtil(Session session, String sendMail, String email,String title,String theme,String content) throws Exception {
		// 1. ����һ���ʼ�
		MimeMessage message = new MimeMessage(session);
		
		// 2. From: ������
		message.setFrom(new InternetAddress(sendMail,title, "UTF-8"));
		
		// 3. To: �ռ��ˣ��������Ӷ���ռ��ˡ����͡����ͣ�
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email, "UTF-8"));
		// 4. Subject: �ʼ�����
		message.setSubject(theme, "UTF-8");
		
		// 5. Content: �ʼ����ģ�����ʹ��html��ǩ��
		message.setContent(content, "text/html;charset=UTF-8");
		
		// 6. ���÷���ʱ��
		message.setSentDate(new Date());
		
		// 7. ��������
		message.saveChanges();
		return message;
	}
}
