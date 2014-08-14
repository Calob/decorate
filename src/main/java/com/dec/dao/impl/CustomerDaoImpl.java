package com.dec.dao.impl;

import org.springframework.stereotype.Component;

import com.dec.dao.CustomerDao;
import com.dec.model.Customer;
import com.dec.persistent.BaseDaoImpl;

@Component
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
	
}
