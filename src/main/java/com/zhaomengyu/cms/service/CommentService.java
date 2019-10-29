/**
 * 
 */
package com.zhaomengyu.cms.service;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.zhaomengyu.cms.entity.Comment;

/**
 * @author zhaomengyu
 *
 */
public interface CommentService {

	//获取评论列表（文章ID，页码）
	PageInfo<Comment> getComList(Integer articleId,Integer page);
	
	/**
	 * 发表评论
	 * @param content        评论内容
	 * @param articleId      文章ID
	 * @param userId         用户ID
	 * @return
	 */
	int addComment(String content,Integer articleId,Integer userId);
	
	/**
	 * 删除评论
	 * @param comId
	 * @return
	 */
	Integer delComment(Integer comId);
	
	
}
