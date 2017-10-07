package cn.blake.shoa.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate4.HibernateTemplate;

public class BaseDaoImpl<T> implements BaseDao<T> {
	@SuppressWarnings({ "rawtypes" })
	private Class classt;
	/**
	 * 获取到T代表的持久化类的数据字典--->当前的实体bean的标示符的名称
	 */
	private ClassMetadata classMetadata;

	@SuppressWarnings("rawtypes")
	public BaseDaoImpl() {
		/**
		 * 1、this可以代表子类或者本类
		 * 2、不能把BaseDaoImpl在spring容器中实例化，因为如果在spring容器中实例化，则就不是泛型了
		 * 3、所以根据以上两点可以得出this应该代表子类
		 * 4、this.getClass().getGenericSuperclass()代表当前的类：就是泛型
		 * 5、注意：不能把BaseDaoImpl让spring容器实例化
		 */
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 因为将来T代表的是某一个持久化类，而该类型是class
		this.classt = (Class) type.getActualTypeArguments()[0];
	}

	/**
	 * 当调用完成构造函数之后，立刻执行该init方法
	 */
	@PostConstruct
	public void init() {
		classMetadata = hibernateTemplate.getSessionFactory().getClassMetadata(this.classt);
	}

	@Resource(name = "hibernateTemplate")
	public HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	public Collection<T> getAllEntry() {
		return (Collection<T>) hibernateTemplate.find("from " + this.classt.getName());
	}

	@SuppressWarnings("unchecked")
	public T getEntryById(Serializable id) {
		/* 得到持久化类的数据字典 */
		ClassMetadata classMetadata = hibernateTemplate.getSessionFactory().getClassMetadata(this.classt);
		return (T) hibernateTemplate
				.find("from " + classt.getName() + " where " + classMetadata.getIdentifierPropertyName() + " =? ", id)
				.get(0);
	}

	public void saveEntry(T t) {
		hibernateTemplate.save(t);
	}

	public void deleteEntry(Serializable id) {
		T t = getEntryById(id);
		hibernateTemplate.delete(t);
	}

	public void updateEntry(T t) {
		hibernateTemplate.evict(t);
		hibernateTemplate.clear();
		hibernateTemplate.update(t);
		hibernateTemplate.flush();
	}
	public void updateEntry(T t,Serializable id) {
		T t2 = getEntryById(id);
		hibernateTemplate.evict(t);/*使t成为托管状态,然后就可以操作t2了*/
		/*或者*/
		/*两个对象合并       hibernate3以上可以这样*/
//		hibernateTemplate.getSessionFactory().openSession().merge(t);
		hibernateTemplate.update(t2);
	}

	public Set<T> qetEntryByIds(Serializable[] ids) {
		StringBuffer buffer = new StringBuffer();
		/**
		 * 1、如果数组只有一个元素 2、如果数组中有两个或者两个以上的元素 from Person where pid in(1,2,3,4)
		 */
		buffer.append("from " + classt.getName());
		buffer.append(" where " + classMetadata.getIdentifierPropertyName());
		buffer.append(" in(");
		for (int i = 0; i < ids.length; i++) {
			if (i == (ids.length - 1)) {
				buffer.append(ids[i]);
			} else {
				buffer.append(ids[i] + ",");
			}
		}
		buffer.append(")");
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) hibernateTemplate.find(buffer.toString());
		return new HashSet<T>(list);
	}

	public T login(String username, String password) {
		String hql = "from "+classt.getName() +" u where u.username = :name and u.password = :pass";
		Query query = hibernateTemplate.getSessionFactory().openSession().createQuery(hql);
		query.setString("name",username).setString("pass",password);
		@SuppressWarnings("unchecked")
		T t2 =(T) query.uniqueResult();
		// from User u where u.username = :name and u.password = :pass
		return t2;
	}
	public T login(String username){
		String hql = "from "+classt.getName() +" u where u.username = :name";
		Query query = hibernateTemplate.getSessionFactory().openSession().createQuery(hql);
		query.setString("name",username);
		@SuppressWarnings("unchecked")
		T t =(T) query.uniqueResult();
		return t;
	}

	public Set<T> qetEntryByIds(String ids) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from "+this.classt.getName());
		stringBuffer.append(" where "+this.classMetadata.getIdentifierPropertyName());
		stringBuffer.append(" in(");
		stringBuffer.append(ids);
		stringBuffer.append(")");
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) hibernateTemplate.find(stringBuffer.toString());
		return new HashSet<T>(list);
	}

}
/*hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO)*/
