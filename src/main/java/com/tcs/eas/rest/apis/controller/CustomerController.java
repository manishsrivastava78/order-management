package com.tcs.eas.rest.apis.controller;



import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcs.eas.rest.apis.db.CustomerDaoService;
import com.tcs.eas.rest.apis.exception.CustomerNotFound;
import com.tcs.eas.rest.apis.log.LoggingService;
import com.tcs.eas.rest.apis.model.Customer;
import com.tcs.eas.rest.apis.utility.Utility;

/**
 * 
 * @author 44745
 *
 */
@RestController
@RequestMapping("/apis/v1")
public class CustomerController {
	
	@Autowired
	LoggingService loggingService;
	/**
	 * 
	 */
	@Autowired
	CustomerDaoService customerDaoService;
	
	/**
	 * 
	 * @param book
	 * @return
	 */
	@PostMapping("/customers")
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody Customer customer,@RequestHeader Map<String, String> headers) {
		Customer savedCustomer = customerDaoService.save(customer);
		//loggingService.writeProcessLog("POST","Customer","createCustomer",customer);
		//loggingService.clearMDC();
		//CustomerEntity ce = test();
		loggingService.writeProcessLog("POST", "customers", "createCustomer", customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{customerid}").buildAndExpand(savedCustomer.getCustomerid()).toUri();
		return ResponseEntity.created(location).headers(Utility.getCustomResponseHeaders(headers)).build();
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers(@RequestHeader Map<String, String> headers) {
		List<Customer> customers = customerDaoService.findAll();
		loggingService.writeProcessLog("GET", "customers", "getCustomer", customers);
		return  ResponseEntity.ok().headers(Utility.getCustomResponseHeaders(headers)).body(customers);
	}
	
	@GetMapping("/customers/{customerid}")
	public ResponseEntity<Customer> getCustomer(@PathVariable int customerid,@RequestHeader Map<String, String> headers) {
		Customer customer = customerDaoService.getCustomerById(customerid);
		if(customer == null) {
			throw new CustomerNotFound("Customer id="+customerid);
		}
		loggingService.writeProcessLog("GET", "customers", "getCustomer by id", customer);
		return ResponseEntity.ok().headers(Utility.getCustomResponseHeaders(headers)).body(customer);
	}
	
	
}
