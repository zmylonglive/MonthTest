/**
 * 
 */
package com.zhaomengyu.cms.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zhaomengyu.cms.entity.Voted;
import com.zhaomengyu.cms.entity.VotedBig;
import com.zhaomengyu.cms.service.VoteService;

/**
 * @author zhaomengyu
 *
 */
@Controller
@RequestMapping("vote")
public class voteController {

	@Autowired
	VoteService vs;
	
	/**
	 * 查询票
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public String voted(HttpServletRequest request) {
		List<VotedBig> list = vs.list();
		request.setAttribute("list", list);
		return "vote/list";
	}
	
	/**
	 * 进入添加页面
	 * @param request
	 * @return
	 */
	@GetMapping("push")
	public String push(HttpServletRequest request) {
		return "vote/add";
	}
	
	/**
	 * 进行操作
	 * @param request
	 * @param votebig
	 * @return
	 */
	@PostMapping("push")
	@ResponseBody
	public boolean push(HttpServletRequest request,VotedBig votebig) {
		return vs.publish(votebig)>0;
	}
	
	@GetMapping("getVote")
	public String getVote(HttpServletRequest request,int arId) {
		VotedBig av = vs.findById(arId);
		request.setAttribute("voteArticle", av);
		Gson gson = new Gson();
		
		LinkedHashMap<String,String> map = gson.fromJson(av.getContent(), LinkedHashMap.class);
		
		LinkedHashMap<String, Voted> lmap = new LinkedHashMap<String,Voted>();
		Set<Entry<String,String>> entrySet = map.entrySet();
		
		List<Voted> voteStatics = vs.getVoteStatics(arId);
		//计算多少人投票
		int totalNum = 0;
		for (Voted vote : voteStatics) {
			totalNum += vote.getVoteNum();
		}
		
		//生成新的map集合
		for (Map.Entry<String, String> entry : entrySet) {
			Voted voteStatic = new Voted();
			voteStatic.setOptionKey(entry.getKey());
			voteStatic.setVoteNumTotal(totalNum);;
			voteStatic.setOptionTitle(entry.getValue());;
			lmap.put(entry.getKey(), voteStatic);
		}
		
		
		
		//每一项的结果
		for (Voted voteStatic : voteStatics) {
			Voted showStatic = lmap.get(voteStatic.getOptionKey());
			showStatic.setVoteNum(voteStatic.getVoteNum());
		}
		
		
		request.setAttribute("lmap", lmap);
		return "/vote/detail";
	}
	
	@PostMapping("vote")
	@ResponseBody
	public Boolean push(HttpServletRequest request,Integer articleId,Character option) {
		return vs.vote(articleId,option)>0;
	}
	
	
	
	
}
