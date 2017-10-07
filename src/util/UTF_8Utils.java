package util;

import java.io.UnsupportedEncodingException;
/**
 * 
 * @author Blake.Zhou
 *
 */
public class UTF_8Utils {
	public static String getUTF_8(String str) {
		try {
			/**
			 * ��������
			 */
			byte[] buf = str.getBytes("ISO8859-1");
			/**
			 * ����
			 */
			str = new String(buf, "UTF-8").toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
