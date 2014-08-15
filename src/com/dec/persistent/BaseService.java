package com.dec.persistent;

import java.io.Serializable;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 
 * @author NanZihao
 * 
 * @param <T>
 *            实体对象
 */
public interface BaseService<T> {
	/**
	 * 根据主键id取出实体对象
	 * 
	 * @param id
	 *            主键
	 * @return 实体对象
	 */
	public T findById(Serializable id);

	/**
	 * 新增实体对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void save(T entity);

	/**
	 * 根据主键删除对象
	 * 
	 * @param ids
	 *            对象的主键
	 */
	public void delete(Serializable[] ids);

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 */
	public void update(T entity);


	/**
	 * 查询
	 * 
	 * @param page
	 *            分页对象
	 * @param qlString
	 *            hql语句
	 * @param parameter
	 *            hql语句中的参数
	 * @return 分页对象
	 */
	public Page<T> find(Page<T> page, String qlString, Object... parameter);

	/**
	 * Criteria查询
	 * 
	 * @param page
	 *            分页对象
	 * @param detachedCriteria
	 *            离线检索对象
	 * @return
	 */
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria);
	
}
