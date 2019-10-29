/**
 * 
 */
package com.zhaomengyu.cms.entity;

import java.io.Serializable;

/**
 * 用于存储图片的描述和图片地址
 * @author zhaomengyu
 *
 */
public class ImageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6249401930681931667L;
	String desc;
	String picUrl;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * @param desc
	 * @param picUrl
	 */
	public ImageBean(String desc, String picUrl) {
		super();
		this.desc = desc;
		this.picUrl = picUrl;
	}
	/**
	 * 
	 */
	public ImageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((picUrl == null) ? 0 : picUrl.hashCode());
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
		ImageBean other = (ImageBean) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (picUrl == null) {
			if (other.picUrl != null)
				return false;
		} else if (!picUrl.equals(other.picUrl))
			return false;
		return true;
	}
	
}
