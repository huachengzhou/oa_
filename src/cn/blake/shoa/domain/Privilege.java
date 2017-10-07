package cn.blake.shoa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

/**
 * @see Ȩ��
 * @author Blake.Zhou
 * 
 */
@Entity
@SuppressWarnings("serial")
public class Privilege implements Serializable {
	/**
	 * ����...
	 */
	private Integer id;
	/**
	 * ���ڵ�ID...
	 */
	private Integer pid;
	/**
	 * �ڵ������...
	 */
	private String name;
	/**
	 * "1"Ϊ�˵� "2"Ϊ����...
	 */
	private String flag;
	private Boolean checked;
	/**
	 * �Ƿ��Ǹ��ڵ�
	 */
	private Boolean isParent;
	/**
	 * ��ת��������
	 */
	private String url;
	/**
	 * ��ת����frame������
	 */
	private String target;
	/**
	 * ͼƬ��·��
	 */
	private String icon;
	private Set<Role> roles = new HashSet<Role>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Privilege() {
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", pid=" + pid + ", name=" + name
				+ ", flag=" + flag + ", checked=" + checked + ", isParent="
				+ isParent + ", url=" + url + ", target=" + target + ", icon="
				+ icon + "]";
	}
}
