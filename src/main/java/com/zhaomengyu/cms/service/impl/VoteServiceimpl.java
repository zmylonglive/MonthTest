/**
 * 
 */
package com.zhaomengyu.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhaomengyu.cms.dao.VoteMapper;
import com.zhaomengyu.cms.entity.Voted;
import com.zhaomengyu.cms.entity.VotedBig;
import com.zhaomengyu.cms.service.VoteService;

/**
 * @author zhaomengyu
 *
 */
@Service
public class VoteServiceimpl implements VoteService{

	
	@Autowired
	VoteMapper voteMapper;
	
	
	@Override
	public List<VotedBig> list() {
		// TODO Auto-generated method stub
		return voteMapper.list();
	}

	
	@Override
	public int publish(VotedBig votebig) {
		// TODO Auto-generated method stub
		return voteMapper.publish(votebig);
	}
	
	
	
	@Override
	public VotedBig findById(int arId) {
		// TODO Auto-generated method stub
		return voteMapper.findById(arId);
	}

	
	@Override
	public List<Voted> getVoteStatics(int arId) {
		// TODO Auto-generated method stub
		return voteMapper.getVoteStatics(arId);
	}

	
	@Override
	public int vote(Integer articleId, Character option) {
		// TODO Auto-generated method stub
		return voteMapper.vote(articleId,option);
	}

}
