package com.dec.service.impl;

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

}
