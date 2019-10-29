/**
 * 
 */
package com.zhaomengyu.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.zhaomengyu.cms.entity.Comment;

/**
 * @author zhaomengyu
 *
 */
public interface CommentMapper {

	//获取文章列表
	List<Comment> getComList(Integer articleId);
	
	
	//发表评论(评论数量使用触发器增加)
	@Insert("insert into cms_comment (articleId,userId,content,created) values (#{articleId},#{userId},#{content},now())")
	int addComment(@Param("content")String content,@Param("articleId")Integer articleId,@Param("userId")Integer userId);
	
	
	//删除评论（评论数量使用触发器删除）
	@Delete("delete from cms_comment where id = #{value}")
	int delComment(Integer comId);
}
