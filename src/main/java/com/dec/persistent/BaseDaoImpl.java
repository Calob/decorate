package com.dec.persistent;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.dec.common.config.Global;
import com.dec.common.utils.StringUtils;

/**
 * BaseDao实现类，提供基本的CRUD操作（查询包括分页）。
 * 
 * @author NanZihao
 * 
 * @param <T>
 */
// 测试时需要@Transactional，与OpenSessionInViewInterceptor有关
 @Transactional(rollbackFor = RuntimeException.class)
// 修改为abstract，防止Base类被实例化
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	/**
	 * 通过反射, 获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
	 * 
	 * @param clazz
	 * @param index
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Class<Object> getSuperClassGenricType(final Class clazz,
			final int index) {
		// 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		// 返回表示此类型实际类型参数的 Type 对象的数组。
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Class<T> entityClass;

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		// getClass() 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的超类的 Class。
		this.setEntityClass((Class<T>) getSuperClassGenricType(getClass(), 0));
	}

	@Override
	public void clear() {
		getSession().clear();
	}

	@Override
	public void delete(Serializable id) {
		Assert.notNull(id, "id is required");
		T entity = load(id);
		getSession().delete(entity);
	}

	@Override
	public void delete(Serializable[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (Serializable id : ids) {
			T entity = load(id);
			getSession().delete(entity);
		}
	}

	@Override
	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}

	@Override
	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getSession().evict(object);
	}

	@Override
	public Page<T> findByPage(Page<T> page) {
		if (page == null) {
			page = new Page<T>();
		}
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(entityClass);
		return findByPage(page, detachedCriteria);
	}

	@Override
	public Page<T> findByPage(Page<T> page, DetachedCriteria detachedCriteria) {
		return null;
	}

	@Override
	public void flush() {
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Serializable id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Override
	public Session getSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	@Override
	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName();
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public boolean isExist(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		T object = get(propertyName, value);
		return (object != null);
	}

	@Override
	public boolean isUnique(String propertyName, Object oldValue,
			Object newValue) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(newValue, "newValue is required");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		if (newValue instanceof String) {
			if (oldValue != null
					&& StringUtils.equalsIgnoreCase((String) oldValue,
							(String) newValue)) {
				return true;
			}
		}
		T object = get(propertyName, newValue);
		return (object == null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(Serializable id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().load(entityClass, id);
	}

	@Override
	public void save(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().save(entity);
	}

	private void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void update(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> get(Serializable[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "from " + entityClass.getName()
				+ " as model where model.id in(:ids)";
		return getSession().createQuery(hql).setParameterList("ids", ids)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName + " = ?";
		return (T) getSession().createQuery(hql).setParameter(0, value)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName + " = ?";
		return getSession().createQuery(hql).setParameter(0, value).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<T> find(Page<T> page, String qlString, Object... parameter) {
		// get count
		if (!page.isDisabled() && !page.isNotCount()) {
			String countQlString = "select count(*) "
					+ removeSelect(removeOrders(qlString));
			// page.setCount(Long.valueOf(createQuery(countQlString,
			// parameter).uniqueResult().toString()));
			Query query = createQuery(countQlString, parameter);
			List<Object> list = query.list();
			if (list.size() > 0) {
				page.setCount(Long.valueOf(list.get(0).toString()));
			} else {
				page.setCount(list.size());
			}
			if (page.getCount() < 1) {
				return page;
			}
		}
		// order by
		String ql = qlString;
		if (StringUtils.isNotBlank(page.getOrderBy())) {
			ql += " order by " + page.getOrderBy();
		}
		Query query = createQuery(ql, parameter);
		// set page
		if (!page.isDisabled()) {
			// 设置分页的当前页码 pageNo
			query.setFirstResult(page.getFirstResult());
			// 设置页数大小 pageSize
			query.setMaxResults(page.getMaxResults());
		}
		page.setList(query.list());
		return page;
	}

	/**
	 * 去除qlString的select子句。
	 * 
	 * @param hql
	 * @return
	 */
	private String removeSelect(String qlString) {
		int beginPos = qlString.toLowerCase().indexOf("from");
		System.out.println(beginPos);
		return qlString.substring(beginPos);
	}

	/**
	 * 创建 QL 查询对象
	 * 
	 * @param qlString
	 * @param parameter
	 * @return
	 */
	public Query createQuery(String qlString, Object... parameter) {
		Query query = getSession().createQuery(qlString);
		setParameter(query, parameter);
		return query;
	}

	/**
	 * 设置查询参数
	 * 
	 * @param query
	 * @param parameter
	 */
	private void setParameter(Query query, Object... parameter) {
		if (parameter != null) {
			for (int i = 0; i < parameter.length; i++) {
				query.setParameter(i, parameter[i]);
			}
		}
	}

	/**
	 * 去除hql的orderBy子句。
	 * 
	 * @param hql
	 * @return
	 */
	private String removeOrders(String qlString) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(qlString);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName + " = ?";
		return getSession().createQuery(hql).setParameter(0, value).list();
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	public Page<T> find(Page<T> page) {
		return find(page, createDetachedCriteria());
	}

	/**
	 * 使用检索标准对象分页查询
	 * 
	 * @param page
	 * @param detachedCriteria
	 * @param resultTransformer
	 * @return
	 */
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria) {
		return find(page, detachedCriteria, Criteria.DISTINCT_ROOT_ENTITY);
	}

	/**
	 * 使用检索标准对象分页查询
	 * 
	 * @param page
	 * @param detachedCriteria
	 * @param resultTransformer
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria,
			ResultTransformer resultTransformer) {
		// get count
		if (!page.isDisabled() && !page.isNotCount()) {
			page.setCount(count(detachedCriteria));
			if (page.getCount() < 1) {
				return page;
			}
		}
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		criteria.setResultTransformer(resultTransformer);
		// set page
		if (!page.isDisabled()) {
			criteria.setFirstResult(page.getFirstResult());
			criteria.setMaxResults(page.getMaxResults());
		}
		// order by
		if (StringUtils.isNotBlank(page.getOrderBy())) {
			for (String order : StringUtils.split(page.getOrderBy(), ",")) {
				String[] o = StringUtils.split(order, " ");
				if (o.length == 1) {
					criteria.addOrder(Order.asc(o[0]));
				} else if (o.length == 2) {
					if ("DESC".equals(o[1].toUpperCase())) {
						criteria.addOrder(Order.desc(o[0]));
					} else {
						criteria.addOrder(Order.asc(o[0]));
					}
				}
			}
		}
		page.setList(criteria.list());
		return page;
	}

	/**
	 * 使用检索标准对象查询记录数
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public long count(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		long totalCount = 0;
		try {
			// Get orders
			Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
			field.setAccessible(true);
			List orderEntrys = (List) field.get(criteria);
			// Remove orders
			field.set(criteria, new ArrayList());
			// Get count
			criteria.setProjection(Projections.rowCount());
			totalCount = Long.valueOf(criteria.uniqueResult().toString());
			// Clean count
			criteria.setProjection(null);
			// Restore orders
			field.set(criteria, orderEntrys);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	/**
	 * 创建与会话无关的检索标准对象
	 * 
	 * @param criterions
	 *            Restrictions.eq("name", value);
	 * @return
	 */
	public DetachedCriteria createDetachedCriteria(Criterion... criterions) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		for (Criterion c : criterions) {
			dc.add(c);
		}
		return dc;
	}

}
