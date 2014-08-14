package com.dec.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dec.model.Customer;
import com.dec.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 根据顾客Id返回顾客信息详情
	 * 
	 * @param cusId
	 *            顾客Id
	 * @return 顾客详细信息
	 */
	@RequestMapping(value="/cusInfoDetail", method=RequestMethod.POST)
	public ModelAndView customerInfoDetail(Model model, HttpServletRequest request)
	{
		String customerName = request.getParameter("customerName");
		System.out.println("test successful!" + customerName);
		Customer customer = customerService.findById(customerName);
		
		model.addAttribute("customerId", customer.getCustomerId());
		model.addAttribute("customerName", customer.getCustomerName());
		model.addAttribute("idNumber", customer.getIdNumber());
		model.addAttribute("email", customer.getEmail());
		model.addAttribute("lastupdate", customer.getLastupdate());
		
		System.out.println("test successful!");
		return new ModelAndView("customer/dashboard");
	}
	
//	/**
//	 * 根据顾客Id返回顾客信息详情
//	 * 
//	 * @param cusId
//	 *            顾客Id
//	 * @return 顾客详细信息
//	 */
//	@RequestMapping(value="cusInfoDetail", method=RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> customerInfoDetail(HttpServletRequest request)
//	{
//		Map<String, Object> modelMap = new HashMap<>();
//		
//		String customerName = request.getParameter("customerName");
//		Customer customer = customerService.findById(customerName);
//		
//		modelMap.put("customerId", customer.getCustomerId());
//		modelMap.put("customerName", customer.getCustomerName());
//		modelMap.put("idNumber", customer.getIdNumber());
//		modelMap.put("email", customer.getEmail());
//		modelMap.put("lastupdate", customer.getLastupdate());
//		
//		System.out.println("test successful!");
//		return modelMap;
//	}
	
}
