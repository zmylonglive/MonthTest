/**
 * 
 */
package com.zhaomengyu.cms.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.zhaomengyu.cms.comon.ArticleType;
import com.zhaomengyu.cms.comon.CmsAssertJson;
import com.zhaomengyu.cms.comon.CmsException;
import com.zhaomengyu.cms.comon.ConstClass;
import com.zhaomengyu.cms.comon.ResultMsg;
import com.zhaomengyu.cms.entity.Article;
import com.zhaomengyu.cms.entity.Cat;
import com.zhaomengyu.cms.entity.Channel;
import com.zhaomengyu.cms.entity.ImageBean;
import com.zhaomengyu.cms.entity.User;
import com.zhaomengyu.cms.service.ArticleService;
import com.zhaomengyu.cms.service.CatService;
import com.zhaomengyu.cms.service.ChannelService;

/**
 * @author zhaomengyu
 *
 */
@Controller
@RequestMapping("article")
public class ArticleController {

	@Autowired
	ArticleService artService;
	
	
	@Autowired
	ChannelService chanService;
	
	@Autowired
	CatService catService;
	
	
	/**
	 * 显示一篇具体的文章
	 */
	
	@RequestMapping("show")
	public String show(HttpServletRequest request,Integer id) {
		CmsAssertJson.Assert(id!=0, "文章id不能让等于0");
		
		Article article = artService.findById(id);
		if(article.getArticleType() == ArticleType.HTML) {
			
			System.out.println("article is " + article);
			request.setAttribute("article", article);
			return "article/detail";
		}else {
			System.out.println("图片文章");
			Gson gson = new Gson();
			article.setImgList(gson.fromJson(article.getContent(), List.class));
			request.setAttribute("article", article);
			return "article/slieimgarticle";
		}
	}
	
	/**
	 * 跳转到添加的页面
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(HttpServletRequest request) {
		List<Channel> allChnls = chanService.getAllChnls();
		request.setAttribute("channels", allChnls);
		return "article/publish";
	}
	@RequestMapping(value="addimg",method=RequestMethod.GET)
	public String addimg(HttpServletRequest request) {
		List<Channel> allChnls = chanService.getAllChnls();
		request.setAttribute("channels", allChnls);
		return "article/publishimg";
	}
	
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "addimg",method=RequestMethod.POST)
	public String addimg(HttpServletRequest request,Article article, 
			@RequestParam("file") MultipartFile file,//标题图片
			@RequestParam("imgs") MultipartFile[] imgs,// 文章中图片
			@RequestParam("imgsdesc") String[]  imgsdesc// 文章中图片的描述
			) throws Exception {
		
		
		article.setArticleType(ArticleType.IMAGE);
		
		processFile(file,article);
		List<ImageBean> imgBeans =  new ArrayList<ImageBean>();
		
		for (int i = 0; i < imgs.length; i++) {
			String picUrl = processFile(imgs[i]);//
			if(!"".equals(picUrl)) {
				ImageBean imageBean = new ImageBean(imgsdesc[i],picUrl);
				imgBeans.add(imageBean);
			}
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(imgBeans);// 文章的内容
		article.setContent(json);//
		
		
		//获取作者
		User loginUser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		article.setUserId(loginUser.getId());
		
		artService.add(article);
		
		return "article/publish";
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="add",method=RequestMethod.POST)
	public boolean add(HttpServletRequest request,Article article,MultipartFile file) throws Exception  {
		processFile(file,article);
		
		
		
		//获取作者
		User loginUser = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		article.setUserId(loginUser.getId());
		
		System.out.println("article is  " + article);
		
		return artService.add(article)>0;
	}
	
	
	/**
	 * 跳转到修改的页面
	 */
	@RequestMapping(value="update",method=RequestMethod.GET)
	public String update(HttpServletRequest request,Integer id) {
		List<Channel> allChnls = chanService.getAllChnls();
		Article article = artService.findById(id);
		
		request.setAttribute("article", article);
		request.setAttribute("content1", article.getContent());
		request.setAttribute("channels", allChnls);
		return "my/update";
	}
	
	/**
	 * 处理文章的附件上传
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	private void processFile(MultipartFile file,Article article) throws IllegalStateException, IOException {
		//原来的文件名称
		if(file.isEmpty() || "".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0) {
			article.setPicture("");
			return;
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
		article.setPicture(destFileName.substring(7));
	}
	
	/**
	 * 处理每一个图片集合中的文件
	 * @param file
	 * @param article
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private String processFile(MultipartFile file) throws IllegalStateException, IOException {

		// 原来的文件名称
		System.out.println("file.isEmpty() :" + file.isEmpty()  );
		System.out.println("file.name :" + file.getOriginalFilename());
		
		if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
			return "";
		}
			
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
		String path = "d:/pic/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
		File distFile = new File( destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		return destFileName.substring(7);
		
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public boolean update(HttpServletRequest request,Article article,MultipartFile file) throws Exception {
		processFile(file, article);
		//获取作者
		
		User loginUser = (User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		article.setUserId(loginUser.getId());
		int result = artService.update(article);
		return result > 0;
	}
	/**
	 * 根据频道获取相应的分类 用户发布文章或者修改文章的下拉框
	 */
	
	@RequestMapping(value="listCatByChnl",method=RequestMethod.GET)
	@ResponseBody
	//public List<Cat> getCatByChnl(int chnlId){
	public ResultMsg getCatByChnl(int chnlId) {
		List<Cat> chnlList = catService.getListByChnId(chnlId);
		return new ResultMsg(1, "获取数据成功", chnlList);
	}
	
	
	/**
	 *  断言处理
	 * @param expression
	 * @param msg
	 */
	private void Assert(boolean expression,String msg) {
		if(!expression)
			throw new CmsException(msg);
	}

	
	
	/**
	 * 增加文章的点击次数
	 */
	@RequestMapping(value="addHits",method=RequestMethod.POST)
	@ResponseBody
	public boolean addHits(Integer id) {
		
		return artService.addHits(id)>0;
	}
	
	
	
}
