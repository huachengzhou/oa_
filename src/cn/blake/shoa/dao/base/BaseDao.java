package cn.blake.shoa.dao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * 
 * @author Blake.Zhou
 * @see Dao interface(common)
 * @param <T>
 */
public interface BaseDao<T> {
	/**
	 * @see �õ�T��������е�ʵ�����
	 * @return
	 */
	public Collection<T> getAllEntry();

	/**
	 * @see Serializable�����Ϳ��Խ������еĻ������ͺ�String����
	 * @param id
	 * @return
	 */
	public T getEntryById(Serializable id);

	public void saveEntry(T t);

	public void deleteEntry(Serializable id);

	public void updateEntry(T t, Serializable id);

	public void updateEntry(T t);

	/**
	 * @see ��������������������id����Set
	 * @param ids
	 * @return
	 */
	public Set<T> qetEntryByIds(Serializable[] ids);

	public Set<T> qetEntryByIds(String ids);

	public T login(String username, String password);

	public T login(String username);
}
