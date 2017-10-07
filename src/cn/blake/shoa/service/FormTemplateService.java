package cn.blake.shoa.service;

import java.util.Collection;

import cn.blake.shoa.domain.FormTemplate;

public interface FormTemplateService {
    public Collection<FormTemplate> getAllFormTemplate();
	
	public void saveFormTemplate(FormTemplate formTemplate);
	
	public FormTemplate getFormTemplate(Integer ftid) throws Exception ;
	public void removeDocument(Integer ftid);
}
