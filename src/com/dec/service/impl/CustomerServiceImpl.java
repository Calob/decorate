package com.dec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dec.dao.CustomerDao;
import com.dec.model.Customer;
import com.dec.persistent.BaseDao;
import com.dec.persistent.BaseServiceImpl;
import com.dec.service.CustomerService;

@Component
@Transactional
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService{

	@Autowired
	private CustomerDao baseDao;
	
	@Override
	public BaseDao<Customer> getBaseDao() {
		return this.baseDao;
	}

	private Customer getCustomerByProperty( String property , String value ) {
		@SuppressWarnings("unchecked")
		List<Customer> customers = getBaseDao().getHibernateTemplate().findByNamedParam(
				"from Customer as c where c."+property+"=:a", "a", value);
		if (customers != null && customers.size() > 0) {
			return getBaseDao().get(customers.get(0).getCustomerId());
		}
		return null;
	}
	
	@Override
	public Customer getCustomerByName(String username) {
		return getCustomerByProperty("customerName", username);
	}
	
	@Override
	public Customer getCustomerByIdentity(String idNumber) {
		return getCustomerByProperty("idNumber", idNumber);
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return getCustomerByProperty("email", email);
	}

	@Override
	public Customer getCustomerById(int id) {
		return getBaseDao().get(id);
	}

	@Override
	public String getSaltByName(String name) {
		@SuppressWarnings("unchecked")
		List<Customer> customer = getBaseDao().getHibernateTemplate().findByNamedParam(
				"from Customer as c where c.customerName=:name", "name", name);
		if (customer != null && customer.size() > 0) {
			return customer.get(0).getSalt();
		}
		return new String();
	}
}
