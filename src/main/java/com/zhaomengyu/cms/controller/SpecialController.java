/**
 * 
 */
package com.zhaomengyu.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhaomengyu.cms.comon.ResultMsg;
import com.zhaomengyu.cms.entity.Special;
import com.zhaomengyu.cms.service.SpecialService;
import com.zhaomengyu.utils.StringUtils;

/**
 * @author zhaomengyu
 *
 */
@Controller
@RequestMapping("special")
public class SpecialController {

	
	@Autowired
	SpecialService specialService;
	
	
	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		List<Special> specialList = specialService.list();
		request.setAttribute("specialList", specialList);
		return "admin/special/list";
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(HttpServletRequest request) {
		return "admin/special/add";
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg add(HttpServletRequest request,Special special) {
		if(StringUtils.isEmpty(special.getDigest())) {
			return new ResultMsg(3, "摘要不能为空", null);
		}
		int result = specialService.add(special);
		if(result > 0) {
			return new ResultMsg(1, "添加成功", null);
		}else {
			return new ResultMsg(2, "添加失败，请与管理员联系！", null);
		}
	}
	
	
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String update(HttpServletRequest request,Integer id) {
		Special special = specialService.findById(id);
		request.setAttribute("special", special);
		return "admin/special/update";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg update(HttpServletRequest request,Special special) {
		int result = specialService.update(special);
		if(result > 0) {
			return new ResultMsg(1, "修改成功", null);
		}else {
			return new ResultMsg(2, "修改失败，请与管理员联系！", null);
		}
	}
	
	
	
	//specId:${special.id},articleId:$("#articleId")
	@RequestMapping(value="addArticle",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg addArticle(HttpServletRequest request,Integer specId,Integer articleId) {
		int result = specialService.addArticle(specId, articleId);
		if(result > 0) {
			return new ResultMsg(1, "添加成功", null);
		}else {
			return new ResultMsg(2, "添加失败，请与管理员练习！", null);
		}
	}
	
	@RequestMapping(value="removeArticle",method=RequestMethod.POST)
	@ResponseBody
	public ResultMsg removeArticle(HttpServletRequest request,Integer specId,Integer articleId) {
		int result = specialService.removeArticle(specId, articleId);
		if(result > 0) {
			return new ResultMsg(1, "移除成功", null);
		}else {
			return new ResultMsg(2, "移除失败，请与管理员联系！", null);
		}
	}
	
	@RequestMapping("detail")
	public String detail(HttpServletRequest request,Integer id) {
		Special special = specialService.findById(id);
		request.setAttribute("special", special);
		return "admin/special/detail";
	}
	
	
}
