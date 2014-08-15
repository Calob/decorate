package com.dec.service;

import com.dec.model.Customer;
import com.dec.persistent.BaseService;

/**
 * An interface.<br>
 * Used by Controller to deal with logical. Implements by CustomerServiceImpl.
 * */
public interface CustomerService extends BaseService<Customer>{

	/**Get the customer by name.
	 * @param String customer name
	 * @return customer
	 * */
	public Customer getCustomerByName(String username);

	/**Get customer by customer_id.
	 * @param int customer ID
	 * @return user
	 * */
	public Customer getCustomerById(int id);

	/**Get salt by name.
	 * @param String customer name
	 * @return the customer salt
	 * */
	public String getSaltByName(String name);

	/**Change the customer password.
	 * @param User
	 * */
	public Customer getCustomerByIdentity(String identity);
	
	public Customer getCustomerByEmail(String email);
	
}
