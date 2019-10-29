package com.zhaomengyu.cms.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zhaomengyu.cms.comon.ConstClass;
import com.zhaomengyu.cms.entity.Article;
import com.zhaomengyu.cms.entity.User;
import com.zhaomengyu.cms.service.ArticleService;
import com.zhaomengyu.cms.service.UserService;
import com.zhaomengyu.cms.web.PageUtils;

/**
 * @author zhaomengyu
 *
 */
@Controller
@RequestMapping("user")
public class UserController {

	
	@Autowired
	UserService userService;
	
	@Autowired
	ArticleService as;
	
	@GetMapping("register")//只接受get的请求
	//@PostMapping//只能接受post方法的请求
	//@RequestMapping(value = "register",method=RequestMethod.get)
	public String register() {
		return "user/register";
	}
	
	@RequestMapping("index")
	public String index() {
		return "user/index";
	}
	
	/**
	 * 判断用户是否已经被占用
	 */
	@RequestMapping("checkExist")
	@ResponseBody
	public boolean checkExist(String username) {
		return !userService.checkUserExist(username);
	}
	
	@PostMapping("register")//只接受post的请求
	public String register(HttpServletRequest request,
			@Validated User user,BindingResult errorResult) {
		if(errorResult.hasErrors()) {
			return "user/register";
		}
		
		int result = userService.register(user);
		if(result>0) {
			return "redirect:login";
		}else {
			request.setAttribute("errorMsg", "系统错误，请稍后重试");
			return "user/register";
		}
	}
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(ConstClass.SESSION_USER_KEY);
		return "user/login";
	}
	
	@PostMapping("login")
	public String login(HttpServletRequest request,
			@Validated User user,
			BindingResult errorResult) {
		if(errorResult.hasErrors()) {
			return "login";
		}
		
		//登录
		User loginUser = userService.login(user);
		if(loginUser== null) {
			request.setAttribute("errorMsg", "用户名密码错误");
			return "user/login";
		}else {
			if(loginUser.getLocked() == 0) { //判断用户是否被冻结
				
				request.getSession().setAttribute(ConstClass.SESSION_USER_KEY, loginUser);
				if(loginUser.getRole() == ConstClass.USER_ROLE_GENERAL) {
					return "redirect:home"; //普通用户重定向到主页
				}else if(loginUser.getRole() == ConstClass.USER_ROLE_ADMIN){
					return "redirect:../admin/index";//管理员重定向到管理页面
				}else {
					//其他情况
					return "user/login";//其他情况
				}
			}else {
				request.setAttribute("errorMsg", "该用户已被冻结！");
				return "user/login";
			}
		}
	}
	
	
	
	/**
	 * 进入个人中心（普通注册用户）
	 */
	@RequestMapping("home")
	public String home(HttpServletRequest request) {
		return "my/home";
	}
	
	/**
	 * 删除用户自己的文章
	 */
	@RequestMapping("delArticle")
	@ResponseBody
	public boolean delArticle(Integer id) {
		return as.remove(id)>0;
	}
	
	/**
	 * 进入个人中心 获取我的文章
	 */
	@RequestMapping("myarticlelist")
	public String myarticles(HttpServletRequest request,@RequestParam(defaultValue="1")Integer page) {
		User loginUser = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		PageInfo<Article> pageArticles = as.listArticleByUserId(loginUser.getId(), page);
		
		List<Article> list = pageArticles.getList();
		for (Article article : list) {
			System.out.println(article);
		}
		String pageStr = PageUtils.pageLoad(pageArticles.getPageNum(), pageArticles.getPages(), "/user/myarticlelist", 5);
		request.setAttribute("pageStr", pageStr);
		request.setAttribute("pageArticle", pageArticles);
		return "my/list";
	}
	/**
	 * 进入上传头像页面
	 * @return
	 */
	@GetMapping("portrait")
	public String portrait() {
		return "my/portrait";
	}
	
	/**
	 * 上传头像动作
	 * @param request
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("portrait")
	public String portrait(HttpServletRequest request,MultipartFile file) throws Exception {
		User user = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		processFile(file,user);
		int i = userService.addPortrait(user);
		return "redirect:home";
	}
	
	private void processFile(MultipartFile file,User user) throws Exception, Exception {
		//原来的文件名称
		System.out.println("file.isEmpty() :"+file.isEmpty());
		System.out.println("ffile.name:"+file.getOriginalFilename());
		
		
		if(file.isEmpty() || "".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0) {
			user.setPicture("");
			return ;
		}
		
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String path = "d:/pic/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = path + "/" + UUID.randomUUID().toString() + suffixName;
		File distFile = new File(destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		user.setPicture(destFileName.substring(7));
	}
	
	
}
