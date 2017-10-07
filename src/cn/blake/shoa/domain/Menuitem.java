package cn.blake.shoa.domain;

import java.io.Serializable;
/**
 *  @see �˵�
 * @author Blake.Zhou
 *
 */
@SuppressWarnings("serial")
public class Menuitem implements Serializable {
	/**
	 * ����������Ψһ��ʾ
	 */
	private Integer mid;
	/**
	 * ���ڵ�
	 */
	private Integer pid;
	/**
	 * ��������
	 */
	private String name;
	/**
	 * �Ƿ�Ϊ���ڵ�
	 */
	private Boolean isParent;
	/**
	 * ͼ���·��
	 */
	private String icon;
	
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
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
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Menuitem() {
	}
	public Menuitem(Integer pid, String name, Boolean isParent, String icon) {
		this.pid = pid;
		this.name = name;
		this.isParent = isParent;
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "Menuitem [mid=" + mid + ", pid=" + pid + ", name=" + name
				+ ", isParent=" + isParent + ", icon=" + icon + "]";
	}
}
