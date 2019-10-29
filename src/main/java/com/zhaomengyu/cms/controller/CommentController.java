/**
 * 
 */
package com.zhaomengyu.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhaomengyu.cms.comon.ConstClass;
import com.zhaomengyu.cms.comon.ResultMsg;
import com.zhaomengyu.cms.entity.Comment;
import com.zhaomengyu.cms.entity.User;
import com.zhaomengyu.cms.service.CommentService;
import com.zhaomengyu.cms.web.PageUtils;

/**
 * @author zhaomengyu
 *
 */
@Controller
@RequestMapping("comment")
public class CommentController {

	@Autowired
	CommentService cs;
	
	/**
	 * 获取评论
	 * @param model
	 * @param articleId
	 * @param page
	 * @return
	 */
	@RequestMapping("getComList")
	public String getComList(Model model,Integer articleId,
			@RequestParam(defaultValue="1")Integer page) {
		PageInfo<Comment> commenPage = cs.getComList(articleId, page);
		String pageStr = PageUtils.pageLoad(commenPage.getPageNum(), commenPage.getPages(), "/comment/getComList?articleId="+articleId, 5);
		
		model.addAttribute("commenPage", commenPage);
		model.addAttribute("pageUtil", pageStr);
		return "my/comment/list";
	}
	
	@RequestMapping("addComment")
	@ResponseBody
	public ResultMsg addComment(HttpServletRequest request,String content,Integer articleId) {
		User loginUser = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		
		if(loginUser == null) {
			return new ResultMsg(-1, "您尚未登录，不能评论！", null);
		}
		
		int res = cs.addComment(content, articleId, loginUser.getId());
		
		if(res > 0) {
			return new ResultMsg(1, "发表成功！", null);
		}else {
			return new ResultMsg(0, "发表失败！", null);
		}
	}
	
	@RequestMapping("delComment")
	@ResponseBody
	public ResultMsg delComment(HttpServletRequest request,Integer comId) {
		User loginUser = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		
		if(loginUser == null) {
			return new ResultMsg(0, "您尚未登录，不能评论！！！", null);
		}
		
		Integer res = cs.delComment(comId);
		if(res > 0) {
			return new ResultMsg(1, "删除成功！", null);
		}else {
			return new ResultMsg(9, "删除失败！", null);
		}
	}
	
	
	
	
	
	
}
