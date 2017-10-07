package cn.blake.shoa.service;

import java.io.InputStream;
import java.util.Collection;

import org.activiti.engine.repository.ProcessDefinition;

/**
 * ���̶������
 * @author Blake.Zhou
 *
 */
public interface PDManager {
	public Collection<ProcessDefinition> getLasterVersion();
	public void deploy(InputStream inputStream) throws Exception;
	public void removePD(String key);
	public InputStream getImgManager(String deploymentId);
}
