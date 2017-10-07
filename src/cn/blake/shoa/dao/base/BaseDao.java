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
	 * @see 得到T代表的所有的实体对象
	 * @return
	 */
	public Collection<T> getAllEntry();

	/**
	 * @see Serializable该类型可以接受所有的基本类型和String类型
	 * @param id
	 * @return
	 */
	public T getEntryById(Serializable id);

	public void saveEntry(T t);

	public void deleteEntry(Serializable id);

	public void updateEntry(T t, Serializable id);

	public void updateEntry(T t);

	/**
	 * @see 根据两个或者两个以上id返回Set
	 * @param ids
	 * @return
	 */
	public Set<T> qetEntryByIds(Serializable[] ids);

	public Set<T> qetEntryByIds(String ids);

	public T login(String username, String password);

	public T login(String username);
}
