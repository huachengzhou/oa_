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
	 * ��ȡ��T����ĳ־û���������ֵ�--->��ǰ��ʵ��bean�ı�ʾ��������
	 */
	private ClassMetadata classMetadata;

	@SuppressWarnings("rawtypes")
	public BaseDaoImpl() {
		/**
		 * 1��this���Դ���������߱���
		 * 2�����ܰ�BaseDaoImpl��spring������ʵ��������Ϊ�����spring������ʵ��������Ͳ��Ƿ�����
		 * 3�����Ը�������������Եó�thisӦ�ô�������
		 * 4��this.getClass().getGenericSuperclass()����ǰ���ࣺ���Ƿ���
		 * 5��ע�⣺���ܰ�BaseDaoImpl��spring����ʵ����
		 */
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		// ��Ϊ����T�������ĳһ���־û��࣬����������class
		this.classt = (Class) type.getActualTypeArguments()[0];
	}

	/**
	 * ��������ɹ��캯��֮������ִ�и�init����
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
		/* �õ��־û���������ֵ� */
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
		hibernateTemplate.evict(t);/*ʹt��Ϊ�й�״̬,Ȼ��Ϳ��Բ���t2��*/
		/*����*/
		/*��������ϲ�       hibernate3���Ͽ�������*/
//		hibernateTemplate.getSessionFactory().openSession().merge(t);
		hibernateTemplate.update(t2);
	}

	public Set<T> qetEntryByIds(Serializable[] ids) {
		StringBuffer buffer = new StringBuffer();
		/**
		 * 1���������ֻ��һ��Ԫ�� 2����������������������������ϵ�Ԫ�� from Person where pid in(1,2,3,4)
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
