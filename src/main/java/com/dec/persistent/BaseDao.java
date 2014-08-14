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
 * DAO基类
 * 
 * @author NanZihao
 * 
 * @param <T>
 *            实体
 */
public interface BaseDao<T> {
	/**
	 * 直接获取HibernateTemplate对象，该对象可弥补BaseDao功能不足的缺陷。
	 * 
	 * @return
	 */
	public HibernateTemplate getHibernateTemplate();

	/**
	 * 同时提供JdbcTemplate对象，该对象类似于HibernateTemplate对象。
	 * 
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate();

	/**
	 * 获取当前可用的Session对象
	 * 
	 * @return
	 */
	public Session getSession();

	/**
	 * 根据ID获取实体对象.
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T get(Serializable id);

	/**
	 * 根据ID获取实体对象.
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T load(Serializable id);

	/**
	 * 根据ID数组获取实体对象集合.
	 * 
	 * @param ids
	 *            ID对象数组
	 * 
	 * @return 实体对象集合
	 */
	public List<T> get(Serializable[] ids);

	/**
	 * 根据属性名和属性值获取实体对象.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象
	 */
	public T get(String propertyName, Object value);

	/**
	 * 根据属性名和属性值获取实体对象集
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> getListByProperty(String propertyName, Object value);

	/**
	 * 根据属性名和属性值获取实体对象集合.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象集合
	 */
	public List<T> getList(String propertyName, Object value);

	/**
	 * 获取所有实体对象总数.
	 * 
	 * @return 实体对象总数
	 */
	public Long getTotalCount();

	/**
	 * 根据属性名、修改前后属性值判断在数据库中是否唯一(若新修改的值与原来值相等则直接返回true).
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param oldValue
	 *            修改前的属性值
	 * @param oldValue
	 *            修改后的属性值
	 * @return boolean
	 */
	public boolean isUnique(String propertyName, Object oldValue,
			Object newValue);

	/**
	 * 根据属性名判断数据是否已存在.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            值
	 * @return boolean
	 */
	public boolean isExist(String propertyName, Object value);

	/**
	 * 保存实体对象.
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	public void save(T entity);

	/**
	 * 更新实体对象.
	 * 
	 * @param entity
	 *            对象
	 */
	public void update(T entity);

	/**
	 * 删除实体对象.
	 * 
	 * @param entity
	 *            对象
	 * @return
	 */
	public void delete(T entity);

	/**
	 * 根据ID删除实体对象.
	 * 
	 * @param id
	 *            记录ID
	 */
	public void delete(Serializable id);

	/**
	 * 根据ID数组删除实体对象,这里建议使用sql语句执行删除操作。删除多个实体对象时，采用sql语句效率更高
	 * 
	 * @param ids
	 *            ID数组
	 */
	public void delete(Serializable[] ids);

	/**
	 * 刷新session.
	 * 
	 */
	public void flush();

	/**
	 * 清除Session.
	 * 
	 */
	public void clear();

	/**
	 * 清除某一对象.
	 * 
	 * @param object
	 *            需要清除的对象
	 */
	public void evict(Object object);

	/**
	 * 根据Page对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param page
	 *            Page对象
	 * @return Page对象
	 */
	public Page<T> findByPage(Page<T> page);

	/**
	 * 根据Page和DetachedCriteria对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param page
	 *            Page对象
	 * @param detachedCriteria
	 *            DetachedCriteria对象
	 * @return
	 */
	public Page<T> findByPage(Page<T> page, DetachedCriteria detachedCriteria);

	/**
	 * 根据Page对象，hql语句和参数进行分页查询
	 * 
	 * @param page
	 *            Page对象
	 * @param qlString
	 *            hql语句
	 * @param parameter
	 *            参数集
	 * @return 返回Page对象
	 */
	public Page<T> find(Page<T> page, String qlString, Object... parameter);

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	public Page<T> find(Page<T> page);

	/**
	 * 使用检索标准对象分页查询
	 * 
	 * @param page
	 * @param detachedCriteria
	 * @return
	 */
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria);

	/**
	 * 使用检索标准对象分页查询
	 * 
	 * @param page
	 * @param detachedCriteria
	 * @param resultTransformer
	 * @return
	 */
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria,
			ResultTransformer resultTransformer);

	/**
	 * 使用检索标准对象查询记录数
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	public long count(DetachedCriteria detachedCriteria);

	/**
	 * 创建与会话无关的检索标准对象
	 * 
	 * @param criterions
	 *            Restrictions.eq("name", value);
	 * @return
	 */
	public DetachedCriteria createDetachedCriteria(Criterion... criterions);
	
}
