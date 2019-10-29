package com.zhaoemngyu.cms.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.zhaomengyu.cms.comon.ArticleType;
import com.zhaomengyu.cms.dao.ArticleMapper;
import com.zhaomengyu.cms.entity.Article;
import com.zhaomengyu.cms.entity.Term;
import com.zhaomengyu.cms.service.ArticleService;
import com.zhaomengyu.utils.FileUtils;
import com.zhaomengyu.utils.StringUtils;

public class TestArticle  extends BaseTest{
	
	@Autowired
	ArticleService arService;
	
	@Autowired
	ArticleMapper am;
	
	@Test
	public void testList() {
		int chnId= 3;
		PageInfo<Article> list = arService.list(3, 0, 1);
		if(list!=null && list.getList() != null) {
			list.getList().forEach(article->{
				System.out.println("article is " + article );
			});
		}
		
	} 
	
	
	@Test
	public void testGetAarticle() {
		
		System.out.println(" 33  文章是 ： " +  arService.findById(33).toString());
		
		System.out.println(" 34  文章是 ： " + arService.findById(34).toString());
		
	} 
	
	@Test
	public void testAddAarticle() throws Exception {
		
		Article article1 = new Article();
		article1.setArticleType(ArticleType.HTML);
		article1.setTitle("测试html 文章");
		arService.add(article1);
		
		
		Article article2 = new Article();
		article2.setArticleType(ArticleType.IMAGE);
		article2.setTitle("测试html 文章");
		arService.add(article2);
		
	} 
	
	@Test
	public void testImport() throws Exception{
		File file = new File("d:\\aaa");
		//获取子目录
		String[] list = file.list();
		for (int i = 0; i < list.length; i++) {
			if(list[i].endsWith(".txt")) {
				String content = FileUtils.readFileByLine("D:/\\aaa\\"+list[i]);
				Article article = new Article();
				article.setContent(content);
				article.setTitle(list[i].substring(0,list[i].lastIndexOf('.')));
				article.setChannelId(4);
				article.setCategoryId(20);
				arService.add(article);
			}
		}
	}
	
	
	@Test
	public void testImportTag() throws IOException {
		
		List<String> list = FileUtils.readFile("d:\\aaa\\tags.txt");
		for (String term : list) {
			String uniqueTerm = StringUtils.toUniqueTerm(term);
			Term termBean = new Term(term,uniqueTerm);
			am.addTerm(termBean);
		}
		
	}
	
	

}
