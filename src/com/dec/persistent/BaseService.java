package com.dec.persistent;

import java.io.Serializable;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 
 * @author NanZihao
 * 
 * @param <T>
 *            ʵ�����
 */
public interface BaseService<T> {
	/**
	 * ��������idȡ��ʵ�����
	 * 
	 * @param id
	 *            ����
	 * @return ʵ�����
	 */
	public T findById(Serializable id);

	/**
	 * ����ʵ�����
	 * 
	 * @param entity
	 *            ʵ�����
	 */
	public void save(T entity);

	/**
	 * ��������ɾ������
	 * 
	 * @param ids
	 *            ���������
	 */
	public void delete(Serializable[] ids);

	/**
	 * ����ʵ�����
	 * 
	 * @param entity
	 */
	public void update(T entity);


	/**
	 * ��ѯ
	 * 
	 * @param page
	 *            ��ҳ����
	 * @param qlString
	 *            hql���
	 * @param parameter
	 *            hql����еĲ���
	 * @return ��ҳ����
	 */
	public Page<T> find(Page<T> page, String qlString, Object... parameter);

	/**
	 * Criteria��ѯ
	 * 
	 * @param page
	 *            ��ҳ����
	 * @param detachedCriteria
	 *            ���߼�������
	 * @return
	 */
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria);
	
}
