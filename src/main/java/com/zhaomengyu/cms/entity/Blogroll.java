/**
 * 
 */
package com.zhaomengyu.cms.entity;

/**
 * @author zhaomengyu
 *
 */
public class Blogroll {

	private Integer id;
	private String text;
	private String url;
	private String created;
	/**
	 * 
	 */
	public Blogroll() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param text
	 * @param url
	 * @param created
	 */
	public Blogroll(Integer id, String text, String url, String created) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.created = created;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
	
	
	@Override
	public String toString() {
		return "Blogroll [id=" + id + ", text=" + text + ", url=" + url + ", created=" + created + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blogroll other = (Blogroll) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
