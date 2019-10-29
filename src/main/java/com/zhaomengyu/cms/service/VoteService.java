/**
 * 
 */
package com.zhaomengyu.cms.service;

import java.util.List;

import com.zhaomengyu.cms.entity.Voted;
import com.zhaomengyu.cms.entity.VotedBig;

/**
 * @author zhaomengyu
 *
 */
public interface VoteService {

	/**
	 * @return
	 */
	List<VotedBig> list();

	/**
	 * @param votebig
	 * @return
	 */
	int publish(VotedBig votebig);

	/**
	 * @param arId
	 * @return
	 */
	VotedBig findById(int arId);

	/**
	 * @param arId
	 * @return
	 */
	List<Voted> getVoteStatics(int arId);

	/**
	 * @param articleId
	 * @param option
	 * @return
	 */
	int vote(Integer articleId, Character option);

}
