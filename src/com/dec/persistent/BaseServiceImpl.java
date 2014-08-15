package com.dec.persistent;

import java.io.Serializable;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author NanZihao
 * 
 * @param <T>
 */
//测试时需要@Transactional，与OpenSessionInViewInterceptor有关
@Transactional
//修改为abstract，防止Base类被实例化
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	/**
	 * 修改BaseDao的注入方法，修改为接口注入。
	 * 
	 * @return
	 */
	public abstract BaseDao<T> getBaseDao();

	@Override
	public void save(T entity) {
		getBaseDao().save(entity);
	}

	/**
	 * 批量删除采用hibernate方式，但效率较低，建议重写使用jdbc的方式进行批量删除
	 */
	@Override
	public void delete(Serializable[] ids) {
		getBaseDao().delete(ids);
	}

	@Override
	public void update(T entity) {
		getBaseDao().update(entity);
	}
	

	@Override
	public Page<T> find(Page<T> page, String qlString, Object... parameter) {
		return getBaseDao().find(page, qlString, parameter);
	}

	@Override
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria) {
		return getBaseDao().find(page, detachedCriteria);
	}

	@Override
	public T findById(Serializable id) {
		//将load修改为get
		return getBaseDao().get(id);
	}

}
