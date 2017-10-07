package cn.blake.mvc;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.blake.shoa.domain.FormTemplate;
import cn.blake.shoa.service.FormTemplateService;
import cn.blake.shoa.service.PDManager;

@Controller
public class FormTemplateController {
	private static final Log logger = LogFactory.getLog(FormTemplateController.class);
	
	@Autowired
	private FormTemplateService formTemplateService;
	@Autowired
	private PDManager pDManager;
	
	/**
	 * @see ��ʾ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showFormTemplate")
	public String showFormTemplate(Model model){
		logger.info("showFormTemplate()");
		Collection<FormTemplate> formTemplates = formTemplateService.getAllFormTemplate();
		model.addAttribute("formTemplates",formTemplates);
		return "workflow/formTemplate/list";
	}
	
	/**
	 * ת�� Save page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/formTemplateAddUI")
	public String formTemplateAddUI(Model model){
		Collection<ProcessDefinition> processDefinitions = pDManager.getLasterVersion();
		model.addAttribute("formTemplate",new FormTemplate());
		model.addAttribute("processDefinitions",processDefinitions);
		logger.info("formTemplateAddUI()");
		return "workflow/formTemplate/addUI";
	}
	
	/**
	 * Save
	 * @param request
	 * @param model
	 * @param file
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/formTemplateSave")
	public String formTemplateSave(HttpServletRequest request, Model model, @RequestParam("file") MultipartFile file){
		FormTemplate formTemplate = new FormTemplate();
		formTemplate.setName(request.getParameter("name"));
		formTemplate.setProcessKey(request.getParameter("processKey"));
		System.out.println("-->"+formTemplate.getName()+" "+formTemplate.getProcessKey());
		String url = "";
		if (!file.isEmpty()) {// �ļ���Ϊnull
			String path = "";
			path = request.getRealPath("/document/");
//			path = request.getServletContext().getRealPath("/document/");// �ϴ�·��
			String fileName = file.getOriginalFilename();// �ļ���
			File filepath = new File(path, fileName);
			// �ж�·���Ƿ��������������򴴽�һ��
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// ���ϴ��ļ����浽һ��Ŀ���ļ���
			try {
				url = path + File.separator + fileName;
				formTemplate.setUrl(url);
				formTemplateService.saveFormTemplate(formTemplate);
				file.transferTo(new File(path + File.separator + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/showFormTemplate";
	}
	
	@RequestMapping(value="/downloadFormTemplate")
	public ResponseEntity<byte[]> downloadFormTemplate(@RequestParam Integer ftid){
		FormTemplate formTemplate = null;
		HttpHeaders headers = new HttpHeaders();
		File file = null;
		try {
			formTemplate = formTemplateService.getFormTemplate(ftid);
			String path = formTemplate.getUrl();
			file = new File(path);
			/*window������,linux���뻻*/
			String fileName = path.substring(path.lastIndexOf("\\")+1, path.length());
			//���������ʾ���ļ�������
			String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
			headers.setContentDispositionFormData("attachment",downloadFileName);
			//������������(������ļ�����)
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@RequestMapping(value="/removeDocument")
	public String removeDocument(@RequestParam Integer ftid){
		formTemplateService.removeDocument(ftid);
		return "redirect:/showFormTemplate";
	}
}
