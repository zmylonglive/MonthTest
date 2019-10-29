/**
 * 
 */
package com.zhaomengyu.cms.entity;

/**
 * @author zhaomengyu
 *
 */
public class VotedBig {

	
	 private  Integer id;
	    /**
	     * 标题
	     */
	 private  String title;
	    /**
	     * 投票的选项 的字符串
	     */
	 private  String content;
		/**
		 * 
		 */
		public VotedBig() {
			super();
			// TODO Auto-generated constructor stub
		}
		/**
		 * @param id
		 * @param title
		 * @param content
		 */
		public VotedBig(Integer id, String title, String content) {
			super();
			this.id = id;
			this.title = title;
			this.content = content;
		}
		@Override
		public String toString() {
			return "VotedBig [id=" + id + ", title=" + title + ", content=" + content + "]";
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	 
	 
}
