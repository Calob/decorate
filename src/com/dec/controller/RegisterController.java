package com.dec.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dec.classes.CheckRegisterData;
import com.dec.model.Customer;
import com.dec.service.CustomerService;
import com.dec.classes.SetPassSalt;

@Controller
@RequestMapping("/guest")
public class RegisterController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("register")
	@ResponseBody
	public String register(Model model,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password")	  String password,
			@RequestParam(value = "password_2")	  String password2,
			@RequestParam(value = "email")		  String email,
			@RequestParam(value = "identityNumber")	  String idNumber) throws Exception
	{
		boolean isValid = false;
		try {
			isValid = CheckRegisterData.isValid(username, password, password, email, idNumber);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(!isValid)
			return "failure";
		
		Customer c1 = customerService.getCustomerByName(username);
		Customer c2 = customerService.getCustomerByEmail(email);
		Customer c3 = customerService.getCustomerByIdentity(idNumber);
		
		if(c1!=null)
			return "error_username";
		if(c2!=null)
			return "error_email";
		if(c3!=null)
			return "error_idNumber";
		
		
		Customer customer = new Customer();
		
		SetPassSalt setPassSalt = new SetPassSalt(); // define a MD5
		String salt = setPassSalt.CreateSalt(); // create a salt and set into
												// merchant
		// use MD5 encrypt the password with salt
		String passwordMD5 = setPassSalt.CreateMD5(password, salt);
		// use MD5 encrypt the user name and email
		String confirmation = setPassSalt.CreateMD5(username, email,
				password);
		customer.setCustomerName(username);
		customer.setEmail(email);
		customer.setIdNumber(idNumber);
		customer.setPassword(passwordMD5);
		customer.setSalt(salt);
		customer.setConfirmation(confirmation);
		
		customerService.save(customer);
		
		return "success";
	}
}
