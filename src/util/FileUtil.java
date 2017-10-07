package util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
/**
 * 
 * @author Blake.Zhou
 *
 */
public class FileUtil {
	/**
	 * @param file
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String saveFile(MultipartFile file,HttpServletRequest request){
		String url = "";
		if (!file.isEmpty()) {// �ļ���Ϊnull
			String path = "";
			try {
//				path = request.getServletContext().getRealPath("/document/");// �ϴ�·��
				path = request.getRealPath("/document/");
			} catch (Exception e) {
			}
			String fileName = file.getOriginalFilename();// �ļ���
			File filepath = new File(path, fileName);
			// �ж�·���Ƿ��������������򴴽�һ��
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// ���ϴ��ļ����浽һ��Ŀ���ļ���
			try {
				url = path + File.separator + fileName;
				file.transferTo(new File(path + File.separator + fileName));
			} catch (Exception e) {
				
			}
		}
		return url;
	}
}
