package cn.blake.shoa.service;

import java.io.Serializable;
import java.util.Collection;

import cn.blake.shoa.domain.Department;

/**
 * 
 * @author Blake.Zhou
 * @see ���� ҵ���
 */
public interface DepartmentService {
	/**
	 * @see ��ʾ�����б�
	 * @return
	 */
	public Collection<Department> getAllDepartment();

	/**
	 * @see �����ز���
	 * @param department
	 */
	public void DepartmentSave(Department department);

	/**
	 * @see ɾ������
	 * @param id
	 */
	public void deleteDepartment(Integer id);

	/**
	 * @see ����id ��ȡ��ز���
	 * @param id
	 * @return
	 */
	public Department getDepartmentById(Serializable id);

	/**
	 * @see �޸���ز���
	 * @param department
	 */
	public void updateDepartment(Department department);
}
