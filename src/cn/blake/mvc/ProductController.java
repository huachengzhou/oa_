package cn.blake.mvc;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ProductController {
	private static final Log logger = LogFactory.getLog(ProductController.class);

	@RequestMapping(value = "/product_save")
	public String product_save(HttpServletRequest request, Model model, @RequestParam("file") MultipartFile file) {
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setPrice(Double.parseDouble(request.getParameter("price")));
		product.setDescription(request.getParameter("description"));
		product.setImage(file);
		if (!file.isEmpty()) {// �ļ���Ϊnull
			String path = request.getServletContext().getRealPath("/image/");// �ϴ�·��
			String fileName = file.getOriginalFilename();// �ļ���
			File filepath = new File(path, fileName);
			logger.debug("file:"+file + " fileName:" + fileName + " filepath:" + filepath + " product:" + product);
			// �ж�·���Ƿ��������������򴴽�һ��
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// ���ϴ��ļ����浽һ��Ŀ���ļ���
			try {
				file.transferTo(new File(path + File.separator + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("product", product);
		return "test_/productDetails";
	}

	/**
	 * @see ��ת���ϴ��ļ�ҳ��
	 * @param mv
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/product_input")
	public String product_input(Model model) {
		model.addAttribute("product", new Product());
		logger.info("start!");
		System.out.println("/product_input()");
		return "test_/productForm";
	}
	@RequestMapping(value="/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,@RequestParam("fileName") String fileName,Model model){
		String path = request.getServletContext().getRealPath("/image/");
		File file = new File(path+File.separator+fileName);
		HttpHeaders headers = new HttpHeaders();
		logger.debug("�ļ���ʼ����!");
		//���������ʾ���ļ�������
		try {
			String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
			//֪ͨ����������صķ�ʽ��ͼƬ
			headers.setContentDispositionFormData("attachment",downloadFileName);
			//������������(������ļ�����)
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			logger.debug("�ɹ�!");
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
