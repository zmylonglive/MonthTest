/**
 * 
 */
package com.zhaomengyu.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhaomengyu.cms.comon.ResultMsg;
import com.zhaomengyu.cms.entity.Blogroll;
import com.zhaomengyu.cms.service.BolgrollService;

/**
 * @author zhaomengyu
 *
 */
@Controller
@RequestMapping("blogroll")
public class BlogrollController {

	@Autowired
	BolgrollService bs;
	
	/**
	 * 友情链接
	 * @param request
	 * @return
	 */
	@GetMapping("friendship")
	public String friendShip(HttpServletRequest request) {
		List<Blogroll> listBlogroll = bs.list();
		request.setAttribute("listBlogroll", listBlogroll);
		return "blogro/list";
	}
	
	/**
	 * 进入添加页面
	 * @return
	 */
	@GetMapping("add")
	public String jia() {
		return "blogro/add";
	}
	
	@PostMapping("add")
	@ResponseBody
	public ResultMsg jia(Blogroll blogroll) {
		int i = bs.add(blogroll);
		if(i>0) {
			return new ResultMsg(1, "成功", null);
		}else {
			return new ResultMsg(2, "失败", null);
		}
	}
	
	
}
