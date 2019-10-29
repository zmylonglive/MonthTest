/**
 * 
 */
package com.zhaomengyu.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaomengyu.cms.dao.CommentMapper;
import com.zhaomengyu.cms.entity.Comment;
import com.zhaomengyu.cms.service.CommentService;

/**
 * @author zhaomengyu
 *
 */
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentMapper cm;

	//获取评论列表（文章ID）
	@Override
	public PageInfo<Comment> getComList(Integer articleId, Integer page) {
		PageHelper.startPage(page,10);
		List<Comment> comments = cm.getComList(articleId);
		
		return new PageInfo<Comment>(comments);
		
	}

	//发表评论
	@Override
	public int addComment(String content, Integer articleId, Integer userId) {
		int res = cm.addComment(content, articleId, userId);
		return res;
	}

	//删除评论
	@Override
	public Integer delComment(Integer comId) {
		// TODO Auto-generated method stub
		return cm.delComment(comId);
	}
	
}
