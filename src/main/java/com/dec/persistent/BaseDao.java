package com.dec.persistent;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * DAO����
 * 
 * @author NanZihao
 * 
 * @param <T>
 *            ʵ��
 */
public interface BaseDao<T> {
	/**
	 * ֱ�ӻ�ȡHibernateTemplate���󣬸ö�����ֲ�BaseDao���ܲ����ȱ�ݡ�
	 * 
	 * @return
	 */
	public HibernateTemplate getHibernateTemplate();

	/**
	 * ͬʱ�ṩJdbcTemplate���󣬸ö���������HibernateTemplate����
	 * 
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate();

	/**
	 * ��ȡ��ǰ���õ�Session����
	 * 
	 * @return
	 */
	public Session getSession();

	/**
	 * ����ID��ȡʵ�����.
	 * 
	 * @param id
	 *            ��¼ID
	 * @return ʵ�����
	 */
	public T get(Serializable id);

	/**
	 * ����ID��ȡʵ�����.
	 * 
	 * @param id
	 *            ��¼ID
	 * @return ʵ�����
	 */
	public T load(Serializable id);

	/**
	 * ����ID�����ȡʵ����󼯺�.
	 * 
	 * @param ids
	 *            ID��������
	 * 
	 * @return ʵ����󼯺�
	 */
	public List<T> get(Serializable[] ids);

	/**
	 * ����������������ֵ��ȡʵ�����.
	 * 
	 * @param propertyName
	 *            ��������
	 * @param value
	 *            ����ֵ
	 * @return ʵ�����
	 */
	public T get(String propertyName, Object value);

	/**
	 * ����������������ֵ��ȡʵ�����
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> getListByProperty(String propertyName, Object value);

	/**
	 * ����������������ֵ��ȡʵ����󼯺�.
	 * 
	 * @param propertyName
	 *            ��������
	 * @param value
	 *            ����ֵ
	 * @return ʵ����󼯺�
	 */
	public List<T> getList(String propertyName, Object value);

	/**
	 * ��ȡ����ʵ���������.
	 * 
	 * @return ʵ���������
	 */
	public Long getTotalCount();

	/**
	 * �������������޸�ǰ������ֵ�ж������ݿ����Ƿ�Ψһ(�����޸ĵ�ֵ��ԭ��ֵ�����ֱ�ӷ���true).
	 * 
	 * @param propertyName
	 *            ��������
	 * @param oldValue
	 *            �޸�ǰ������ֵ
	 * @param oldValue
	 *            �޸ĺ������ֵ
	 * @return boolean
	 */
	public boolean isUnique(String propertyName, Object oldValue,
			Object newValue);

	/**
	 * �����������ж������Ƿ��Ѵ���.
	 * 
	 * @param propertyName
	 *            ��������
	 * @param value
	 *            ֵ
	 * @return boolean
	 */
	public boolean isExist(String propertyName, Object value);

	/**
	 * ����ʵ�����.
	 * 
	 * @param entity
	 *            ����
	 * @return ID
	 */
	public void save(T entity);

	/**
	 * ����ʵ�����.
	 * 
	 * @param entity
	 *            ����
	 */
	public void update(T entity);

	/**
	 * ɾ��ʵ�����.
	 * 
	 * @param entity
	 *            ����
	 * @return
	 */
	public void delete(T entity);

	/**
	 * ����IDɾ��ʵ�����.
	 * 
	 * @param id
	 *            ��¼ID
	 */
	public void delete(Serializable id);

	/**
	 * ����ID����ɾ��ʵ�����,���ｨ��ʹ��sql���ִ��ɾ��������ɾ�����ʵ�����ʱ������sql���Ч�ʸ���
	 * 
	 * @param ids
	 *            ID����
	 */
	public void delete(Serializable[] ids);

	/**
	 * ˢ��session.
	 * 
	 */
	public void flush();

	/**
	 * ���Session.
	 * 
	 */
	public void clear();

	/**
	 * ���ĳһ����.
	 * 
	 * @param object
	 *            ��Ҫ����Ķ���
	 */
	public void evict(Object object);

	/**
	 * ����Page������в�ѯ(�ṩ��ҳ�����ҡ�������).
	 * 
	 * @param page
	 *            Page����
	 * @return Page����
	 */
	public Page<T> findByPage(Page<T> page);

	/**
	 * ����Page��DetachedCriteria������в�ѯ(�ṩ��ҳ�����ҡ�������).
	 * 
	 * @param page
	 *            Page����
	 * @param detachedCriteria
	 *            DetachedCriteria����
	 * @return
	 */
	public Page<T> findByPage(Page<T> page, DetachedCriteria detachedCriteria);

	/**
	 * ����Page����hql���Ͳ������з�ҳ��ѯ
	 * 
	 * @param page
	 *            Page����
	 * @param qlString
	 *            hql���
	 * @param parameter
	 *            ������
	 * @return ����Page����
	 */
	public Page<T> find(Page<T> page, String qlString, Object... parameter);

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param page
	 * @return
	 */
	public Page<T> find(Page<T> page);

	/**
	 * ʹ�ü�����׼�����ҳ��ѯ
	 * 
	 * @param page
	 * @param detachedCriteria
	 * @return
	 */
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria);

	/**
	 * ʹ�ü�����׼�����ҳ��ѯ
	 * 
	 * @param page
	 * @param detachedCriteria
	 * @param resultTransformer
	 * @return
	 */
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria,
			ResultTransformer resultTransformer);

	/**
	 * ʹ�ü�����׼�����ѯ��¼��
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	public long count(DetachedCriteria detachedCriteria);

	/**
	 * ������Ự�޹صļ�����׼����
	 * 
	 * @param criterions
	 *            Restrictions.eq("name", value);
	 * @return
	 */
	public DetachedCriteria createDetachedCriteria(Criterion... criterions);
	
}
