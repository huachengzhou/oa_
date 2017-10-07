package cn.blake.email;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

/**
 * �ع����Ͳ���
 */
public class EmailSend {
	/**
	 * smtp.qq.comQQ������
	 * smtp.163.com163������
	 */

	/**
	 * �������� SMTP��������ַ��������hostName��qq and 163()
	 * 
	 * @param hostName
	 * @return
	 */
	private static Properties getProperties(String hostName) {
		String hostServlet = "smtp." + hostName + ".com";
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp"); // ʹ�õ�Э�飨JavaMail�淶Ҫ��
		props.setProperty("mail.smtp.host", hostServlet); // �����˵������ SMTP��������ַ
		props.setProperty("mail.smtp.auth", "true"); // ��Ҫ������֤
		final String smtpPort = "465";
		props.setProperty("mail.smtp.port", smtpPort);
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", smtpPort);
		return props;
	}

	/**
	 * @see ��ȡ�Ự(��var=qq)
	 * @param hostName
	 * @return
	 */
	public static Session getSession(String hostName) {
		return Session.getDefaultInstance(EmailSend.getProperties(hostName));
	}

	/**
	 * @see ���δ��뷢����:������,����(key:name,password)
	 * @param myEmailAccount
	 * @param myEmailPassword
	 * @return
	 */
	public static Map<String, String> getMap(String myEmailAccount, String myEmailPassword) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", myEmailAccount);
		map.put("password", myEmailPassword);
		return map;
	}

	public static void sendTransport(Map<String, String> map, Session session, MimeMessage message) throws Exception {
		Map<String, String> m = map;
		String myEmailAccount = m.get("name");
		String myEmailPassword = m.get("password");
		Transport transport = session.getTransport();
		// 5ʹ�� �����˺� �� ���� �����ʼ�������, ������֤����������� message �еķ���������һ��, ���򱨴�
		transport.connect(myEmailAccount, myEmailPassword);
		// 6. �����ʼ�, �������е��ռ���ַ, message.getAllRecipients()
		// ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���,������, ������
		transport.sendMessage(message, message.getAllRecipients());
		// 7. �ر�����
		transport.close();
	}

}
