/**
 * 
 */
package com.zhaomengyu.cms.service;

import java.util.List;

import com.zhaomengyu.cms.entity.Cat;

/**
 * @author zhaomengyu
 *
 */
public interface CatService {

	/**
	 * 
	 * 根据频道去获取下边的分类
	 */
	
	List<Cat> getListByChnId(Integer id);
}
