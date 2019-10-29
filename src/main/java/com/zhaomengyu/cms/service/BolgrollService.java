/**
 * 
 */
package com.zhaomengyu.cms.service;

import java.util.List;

import com.zhaomengyu.cms.entity.Blogroll;

/**
 * @author zhaomengyu
 *
 */
public interface BolgrollService {

	/**
	 * @return
	 */
	List<Blogroll> list();

	/**
	 * @param blogroll
	 * @return
	 */
	int add(Blogroll blogroll);

}
