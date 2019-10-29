/**
 * 
 */
package com.zhaomengyu.cms.controller;

import javax.crypto.AEADBadTagException;
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
import com.zhaomengyu.cms.entity.Article;
import com.zhaomengyu.cms.entity.User;
import com.zhaomengyu.cms.service.AdminService;
import com.zhaomengyu.cms.service.ArticleService;
import com.zhaomengyu.cms.web.PageUtils;

/**
 * @author zhaomengyu
 *
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	ArticleService articelService;
	
	@Autowired
	AdminService as;
	
	@RequestMapping("index")
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping("manArticle")
	public String adminArticle(HttpServletRequest request,
			@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="0")Integer status) {
		PageInfo<Article> pageInfo = articelService.getAdminArticles(page,status);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("status", status);
		String pageStr = PageUtils.pageLoad(pageInfo.getPageNum(), pageInfo.getPages(), "/admin/manArticle?status="+status, 10);
		request.setAttribute("page", pageStr);
		return "admin/article/list";
	}
	
	@RequestMapping("getArticle")
	public String getArticle(HttpServletRequest request,Integer id) {
		Article article = articelService.findById(id);
		request.setAttribute("article", article);
		return "admin/article/detail";
	}
	
	/**
	 * 审核文章
	 * @param request
	 * @param articleId  文章的id
	 * @param status 审核后的状态 1审核通过 2不通过
	 * @return
	 */
	@RequestMapping("checkArticle")
	@ResponseBody
	public ResultMsg checkArticle(HttpServletRequest request,Integer articleId,int status) {
		User login = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		if(login == null) {
			return new ResultMsg(2, "对不起，您尚未登录，不能审核文章", null);
		}
		if(login.getRole()!= ConstClass.USER_ROLE_ADMIN){
			return new ResultMsg(3, "对不起，您没有权限审核文章", null);
		}
		Article article = articelService.findById(articleId);
		if(article == null) {
			return new ResultMsg(4, "没有这篇文章！！", null);
		}
		if(article.getStatus()==status) {
			return new ResultMsg(5, "这篇文章的状态就是您要的审核状态，无需此操作！！", null);
		}
		int result = articelService.updateStatus(articleId, status);
		if(result>0) {
			return new ResultMsg(1, "恭喜，审核成功！！", null);
		}else {
			return new ResultMsg(5, "很遗憾，操作失败，请与管理员联系或稍后再试！！", null);
		}
	}
	
	
	@RequestMapping("sethot")
	@ResponseBody
	public ResultMsg sethot(HttpServletRequest request,Integer articleId,int status) {
		User login = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		if(login == null) {
			return new ResultMsg(2, "对不起，您尚未登录，不能修改热门状态", null);
		}
		if(login.getRole() != ConstClass.USER_ROLE_ADMIN) {
			return new ResultMsg(3, "对不起，您没有权限修改热门状态", null);
		}
		Article article = articelService.findById(articleId);
		if(article == null) {
			return new ResultMsg(4, "没有这篇文章！！", null);
		}
		if(article.getHot() == status) {
			return new ResultMsg(5, "这篇文章的状态就是您要修改的状态，无需再操作！！", null);
		}
		int result = articelService.updateHot(articleId, status);
		if(result>0) {
			return new ResultMsg(1, "恭喜，审核成功！！", null);
		}else {
			return new ResultMsg(5, "很遗憾，操作失败,请与管理员联系或稍后重试！", null);
		}
	}
	
	/**
	 * 管理员管理用户
	 * @param model
	 * @param pageNum 分页页码
	 * @param name 模糊查询名称
	 * @return
	 */
	@RequestMapping("managerUser")
	public String managerUser(Model model,@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="")String name) {
		PageInfo<User> userList = as.UserList(page, name);
		String pageStr = PageUtils.pageLoad(userList.getPageNum(), userList.getPages(), "/admin/managerUser?name="+name, 10);
		
		model.addAttribute("name", name);
		model.addAttribute("userList", userList);
		model.addAttribute("pageUtil", pageStr);
		
		return "admin/user/list";
	}
	
	/**
	 * 修改用户状态
	 * @param id
	 * @param locked
	 * @return
	 */
	@RequestMapping("modifyUserStatus")
	@ResponseBody
	public boolean modifyUserStatus(Integer id,Integer locked) {
		System.out.println("修改状态为：" + locked);
		int res = as.modifyUserStatus(id, locked);
		return res > 0;
	}
	
	
	
	
}
