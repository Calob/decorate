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
//����ʱ��Ҫ@Transactional����OpenSessionInViewInterceptor�й�
@Transactional
//�޸�Ϊabstract����ֹBase�౻ʵ����
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	/**
	 * �޸�BaseDao��ע�뷽�����޸�Ϊ�ӿ�ע�롣
	 * 
	 * @return
	 */
	public abstract BaseDao<T> getBaseDao();

	@Override
	public void save(T entity) {
		getBaseDao().save(entity);
	}

	/**
	 * ����ɾ������hibernate��ʽ����Ч�ʽϵͣ�������дʹ��jdbc�ķ�ʽ��������ɾ��
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
		//��load�޸�Ϊget
		return getBaseDao().get(id);
	}

}
