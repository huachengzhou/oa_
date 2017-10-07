package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ���ڴ���Util_
 * 
 * @author Blake.Zhou
 *
 */
public class CalendarUtil {
	/**
	 * @see ��ȡ����ʱ��
	 * @return
	 */
	public static String time() {
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		// ��õ�ǰ�·�
		String month = String.valueOf(date.get(Calendar.MONTH) + 1);
		// ��õ�ǰ��
		String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		// ��õ�ǰʱ��
		String hour = String.valueOf(date.get(Calendar.HOUR));
		// ��õ�ǰ����
		String minute = String.valueOf(date.get(Calendar.MINUTE));
		// ��õ�ǰ����
		String second = String.valueOf(date.get(Calendar.SECOND));
		// ��ӡ����ǰ����
		String dateTime = year + "�� " + month + "�� " + day + "�� " + hour + ":" + minute + ":" + second;
		return dateTime;
	}
	/**
	 * @see ��ȡ�����ֵ��ʱ���ַ���
	 * @param millis
	 * @return
	 */
	public static String time(long millis) {
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(millis);
		String year = String.valueOf(date.get(Calendar.YEAR));
		// ��õ�ǰ�·�
		String month = String.valueOf(date.get(Calendar.MONTH) + 1);
		// ��õ�ǰ��
		String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
		// ��õ�ǰʱ��
		String hour = String.valueOf(date.get(Calendar.HOUR));
		// ��õ�ǰ����
		String minute = String.valueOf(date.get(Calendar.MINUTE));
		// ��õ�ǰ����
		String second = String.valueOf(date.get(Calendar.SECOND));
		// ��ӡ����ǰ����
		String dateTime = year + "�� " + month + "�� " + day + "�� " + hour + ":" + minute + ":" + second;
		return dateTime;
	}

	/**
	 * @see ���Ӷ����ջ��߼��ٶ�����,��ȡ�����ַ���
	 * @param dayNumber
	 * @return
	 */
	public static String time(int dayNumber) {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_YEAR, dayNumber);
		String dateTime = date.get(Calendar.YEAR) + "�� " + (date.get(Calendar.MONTH) + 1) + "�� " + date.get(Calendar.DAY_OF_MONTH)
				+ "�� " + date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE) + ":" + date.get(Calendar.SECOND);
		return dateTime;
	}
	/**
	 * @see ��������ֵ������Ȼͨ�����ӻ��߼��ٶ���������ȡ�����ַ���
	 * @param dayNumber
	 * @param millis value
	 * @return
	 */
	public static String time(int dayNumber,long millis) {
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(millis);
		date.add(Calendar.DAY_OF_YEAR, dayNumber);
		String dateTime = date.get(Calendar.YEAR) + "�� " + (date.get(Calendar.MONTH) + 1) + "�� " + date.get(Calendar.DAY_OF_MONTH)
		+ "�� " + date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE) + ":" + date.get(Calendar.SECOND);
		return dateTime;
	}

	/**
	 * @see ���Ӷ�������߼��ٶ�����,����ȡ�����ַ���
	 * @param yearNumber
	 * @return
	 */
	public static String time_(int yearNumber) {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.YEAR, yearNumber);
		String dateTime = date.get(Calendar.YEAR) + "�� " + (date.get(Calendar.MONTH) + 1) + "�� " + date.get(Calendar.DAY_OF_MONTH)
				+ "�� " + date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE) + ":" + date.get(Calendar.SECOND);
		return dateTime;
	}
	/**
	 * @see  ���ݴ���ֵ:���Ӷ�������߼��ٶ�����,����ȡ�����ַ���
	 * @param yearNumber
	 * @param millis
	 * @return
	 */
	public static String time_(int yearNumber,long millis) {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.YEAR, yearNumber);
		String dateTime = date.get(Calendar.YEAR) + "�� " + (date.get(Calendar.MONTH) + 1) + "�� " + date.get(Calendar.DAY_OF_MONTH)
		+ "�� " + date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE) + ":" + date.get(Calendar.SECOND);
		return dateTime;
	}
	/**
	 * @see yyyy-MM-dd HH:mm:ss
	 * @param text
	 * @return
	 */
	public static Date formate(String text){
    	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = null;
    	try {
			date = dateformat.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
    }

}
