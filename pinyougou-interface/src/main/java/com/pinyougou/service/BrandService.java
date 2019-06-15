package com.pinyougou.service;

import com.pinyougou.pojo.Brand;
import com.pinyougou.pojo.PageResult;

import java.util.List;
import java.io.Serializable;
/**
 * BrandService 服务接口
 * @date 2019-06-06 21:09:47
 * @version 1.0
 */
public interface BrandService {

	/** 添加方法 */
	void save(Brand brand);

	/** 修改方法 */
	void update(Brand brand);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	Brand findOne(Serializable id);

	/** 查询全部 */
	List<Brand> findAll(Brand brand);

	List<Brand>findAll();

	/** 多条件分页查询 */
	PageResult findByPage(Brand brand, int page, int rows);

}