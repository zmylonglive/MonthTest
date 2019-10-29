/**
 * 
 */
package com.zhaomengyu.cms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.zhaomengyu.cms.entity.Article;
import com.zhaomengyu.cms.entity.Blogroll;
import com.zhaomengyu.cms.entity.Cat;
import com.zhaomengyu.cms.entity.Channel;
import com.zhaomengyu.cms.entity.Special;
import com.zhaomengyu.cms.service.ArticleService;
import com.zhaomengyu.cms.service.BolgrollService;
import com.zhaomengyu.cms.service.CatService;
import com.zhaomengyu.cms.service.ChannelService;
import com.zhaomengyu.cms.service.SpecialService;
import com.zhaomengyu.cms.web.PageUtils;

/**
 * @author zhaomengyu
 *
 */
@Controller
public class IndexController {

	@Autowired
	ChannelService chnlService;
	
	@Autowired
	CatService catService;
	
	@Autowired
	ArticleService as;
	
	@Autowired
	SpecialService specialService;
	
	 @Autowired
	 BolgrollService bolgrollService; //文章的分类
	
	@RequestMapping({"index","/"})
	public String index(HttpServletRequest request,
			@RequestParam(defaultValue="0")Integer chnId,
			@RequestParam(defaultValue="0")Integer catId,
			@RequestParam(defaultValue="1")Integer page) throws InterruptedException {
		
		Thread t1 = new Thread() {
		
			public void run() {
				List<Channel> channels = chnlService.getAllChnls();
				request.setAttribute("chnls", channels);
			};
		};
		
		
		
		
		//获取所有的频道
		
		Thread t2;
		List<Channel> channels = chnlService.getAllChnls();
		if(chnId!=0) {
			//获取该栏目下的所有分类
			t2 = new Thread() {
				
				public void run() {
					
					List<Cat> catygories = catService.getListByChnId(chnId);
					request.setAttribute("catygories", catygories);
					//获取该栏目下的文章
					PageInfo<Article> articleList = as.list(chnId, catId, page);
					request.setAttribute("articles", articleList);
					PageUtils.page(request, "/index?chnId="+chnId+"&catId", 1, articleList.getList(), (long)articleList.getSize(), articleList.getPageNum());
				}
			};
		}else {
			//获取热门
			//获取热门文章
			t2 = new Thread() {
				
			
				public void run() {
					
					PageInfo<Article> articleList = as.hostList(page);
					request.setAttribute("articles", articleList);
					PageUtils.page(request, "/index", 10, articleList.getList(), (long) articleList.getSize(), articleList.getPageNum());
				}
			};
		}
		
		Thread t3 = new Thread() {
			
			public void run() {
				System.out.println(" ====================线程已经开始");
				//获取最新文章
				List<Article> lastList = as.last(5);
				request.setAttribute("lastList", lastList);
				System.out.println(" =================线程即将结束");
			}
		};
		
		List<Special> specials = new ArrayList<Special>();
		//获取专题文章
		List<Special> list = specialService.list();
		for (Special special : list) {
			Special newSpecial = specialService.findById(special.getId());
			specials.add(newSpecial);
		}
		
		Thread t4 = new Thread() {
			
			@Override
			public void run() {
				List<Blogroll> listblogroll = bolgrollService.list();
				request.setAttribute("listblogroll", listblogroll);
			}
		};
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		
		System.out.println(" 主线程即将结束");
		
		request.setAttribute("chnls", channels);
		request.setAttribute("chnId", chnId);
		request.setAttribute("catId", catId);
		
		request.setAttribute("specials", specials);
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
