/**
 * 
 */
package com.zhaomengyu.cms.entity;

import java.io.Serializable;

/**
 * @author zhaomengyu
 *
 */
public class Voted implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 397856207659606813L;
	private    String  optionKey;
    private    Integer voteNum;
    private    String  optionTitle;
    private    Integer voteNumTotal;
	/**
	 * 
	 */
	public Voted() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param optionKey
	 * @param voteNum
	 * @param optionTitle
	 * @param voteNumTotal
	 */
	public Voted(String optionKey, Integer voteNum, String optionTitle, Integer voteNumTotal) {
		super();
		this.optionKey = optionKey;
		this.voteNum = voteNum;
		this.optionTitle = optionTitle;
		this.voteNumTotal = voteNumTotal;
	}
	public String getOptionKey() {
		return optionKey;
	}
	public void setOptionKey(String optionKey) {
		this.optionKey = optionKey;
	}
	public Integer getVoteNum() {
		return voteNum;
	}
	public void setVoteNum(Integer voteNum) {
		this.voteNum = voteNum;
	}
	public String getOptionTitle() {
		return optionTitle;
	}
	public void setOptionTitle(String optionTitle) {
		this.optionTitle = optionTitle;
	}
	public Integer getVoteNumTotal() {
		return voteNumTotal;
	}
	public void setVoteNumTotal(Integer voteNumTotal) {
		this.voteNumTotal = voteNumTotal;
	}
	@Override
	public String toString() {
		return "Voted [optionKey=" + optionKey + ", voteNum=" + voteNum + ", optionTitle=" + optionTitle
				+ ", voteNumTotal=" + voteNumTotal + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((optionKey == null) ? 0 : optionKey.hashCode());
		result = prime * result + ((optionTitle == null) ? 0 : optionTitle.hashCode());
		result = prime * result + ((voteNum == null) ? 0 : voteNum.hashCode());
		result = prime * result + ((voteNumTotal == null) ? 0 : voteNumTotal.hashCode());
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
		Voted other = (Voted) obj;
		if (optionKey == null) {
			if (other.optionKey != null)
				return false;
		} else if (!optionKey.equals(other.optionKey))
			return false;
		if (optionTitle == null) {
			if (other.optionTitle != null)
				return false;
		} else if (!optionTitle.equals(other.optionTitle))
			return false;
		if (voteNum == null) {
			if (other.voteNum != null)
				return false;
		} else if (!voteNum.equals(other.voteNum))
			return false;
		if (voteNumTotal == null) {
			if (other.voteNumTotal != null)
				return false;
		} else if (!voteNumTotal.equals(other.voteNumTotal))
			return false;
		return true;
	}
    
}
