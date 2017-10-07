package cn.blake.mvc;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.blake.shoa.InterceptorAndException.AppWideExceptionHandler;
import cn.blake.shoa.domain.Department;
import cn.blake.shoa.service.DepartmentService;

/**
 * @author Blake.Zhou
 * @see ���� MVC
 */
@Controller
public class DepartmentController {

	private static final Log logger = LogFactory
			.getLog(DepartmentController.class);
	@Autowired
	private DepartmentService departmentService;

	/**
	 * @see ��ʾ�����б�
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/showAllDepartment")
	public ModelAndView showAllDepartment(ModelAndView mv) {
		logger.info("showAllDepartment() ��ʾ�����б�");
		Collection<Department> departments = departmentService
				.getAllDepartment();
		logger.debug("showAllDepartment() data:" + departments);
		mv.addObject("departments", departments);
		mv.setViewName("system/department/list");
		return mv;
	}

	/**
	 * @see ת����Ӳ���ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add_")
	public String saveUI(Model model) {
		logger.info("saveUI() ת����Ӳ���ҳ��");
		model.addAttribute("dept", new Department());
		return "system/department/add";
	}

	/**
	 * @see ��Ӳ�����Ϣ
	 */
	@RequestMapping(value = "/add")
	public String add(@ModelAttribute Department department, Model model) {
		logger.info("add() ��Ӳ�����Ϣ");
		try {
			departmentService.DepartmentSave(department);
		} catch (Exception e) {
			logger.debug("Department:" + department + " " + e.getMessage());
		}
		return "redirect:/showAllDepartment";
	}

	/**
	 * @see ɾ��������Ϣ
	 * @param did
	 * @return
	 */
	@RequestMapping(value = "/removeDepartment")
	public String removeDepartment(@RequestParam Integer did) {
		logger.info("removeDepartment() ɾ��������Ϣ id:" + did);
		try {
			departmentService.deleteDepartment(did);
		} catch (Exception e) {
			logger.debug("id:" + did + " ;" + e.getMessage());
		}
		return "redirect:/showAllDepartment";
	}

	/**
	 * @see ת���޸Ĳ���ҳ��
	 * @param did
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateDepartment_")
	public String updateDepartment_(@RequestParam Integer did, Model model) {
		logger.info("updateDepartment_() ת���޸Ĳ���ҳ��");
		Department department = departmentService.getDepartmentById(did);
		model.addAttribute("department", department);
		logger.debug("Department " + department);
		return "system/department/update";
	}

	/**
	 * @see �޸Ĳ�����Ϣ
	 * @param model
	 * @param department
	 * @return
	 */
	@RequestMapping(value = "/updateDepartment")
	public String updateDepartment(Model model,
			@ModelAttribute Department department) {
		logger.info("updateDepartment() �޸Ĳ�����Ϣ Department:" + department);
		try {
			departmentService.updateDepartment(department);
		} catch (Exception e) {
			logger.debug(department + " " + e.getMessage());
		}
		return "redirect:/showAllDepartment";
	}

	/**
	 * @see �ص�������ҳ
	 * @return
	 */
	@RequestMapping(value = "/goBack")
	public String goBack() {
		logger.info("goBack() �ص�������ҳ");
		return "redirect:/showAllDepartment";
	}

	/**
	 * @see ͳһ����ÿ������������쳣
	 * @return
	 */
	@ExceptionHandler(AppWideExceptionHandler.class)
	public String handleDuplicateException() {
		return "exception/error";
	}
}
