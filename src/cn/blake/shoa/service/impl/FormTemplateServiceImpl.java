package cn.blake.shoa.service.impl;

import java.io.File;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.blake.shoa.dao.FormTemplateDao;
import cn.blake.shoa.domain.FormTemplate;
import cn.blake.shoa.service.FormTemplateService;
@Service
public class FormTemplateServiceImpl implements FormTemplateService {
	
	@Autowired
	private FormTemplateDao formTemplateDao;
	
	public Collection<FormTemplate> getAllFormTemplate() {
		return formTemplateDao.getAllEntry();
	}

	@Transactional(readOnly=false)
	public void saveFormTemplate(FormTemplate formTemplate) {
		formTemplateDao.saveEntry(formTemplate);
	}

	public FormTemplate getFormTemplate(Integer ftid) throws Exception {
		FormTemplate formTemplate = formTemplateDao.getEntryById(ftid);
		return formTemplate;
	}
	@Transactional(readOnly=false)
	public void removeDocument(Integer ftid) {
		FormTemplate formTemplate = formTemplateDao.getEntryById(ftid);
		File file = null;
		formTemplateDao.deleteEntry(ftid);
		try {
			String path = formTemplate.getUrl();
			file = new File(path);
			file.delete();
			try {
				path = path.replaceAll("\\","\\");
				file = new File(path);
				file.delete();
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}
	}

}
