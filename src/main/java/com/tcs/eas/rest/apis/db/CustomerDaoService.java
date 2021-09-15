package com.tcs.eas.rest.apis.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcs.eas.rest.apis.model.Customer;

/**
 * 
 * @author 44745
 *
 */
@Component
public class CustomerDaoService {

	@Autowired
	CustomerRepository customerRepository;

	

	/**
	 * 
	 * @param book
	 * @return
	 */
	public Customer save(Customer customer) {
		//Address address = customer.getAddress();
		//Address addressEntity = new AddressEntity(address.getStreet(), address.getCity(), address.getPostcode(), address.getState(), address.getCountry());
		//Customer customer = new Customer(customer.getFirstname(),customer.getLastname(),customer.getMobile(),customer.getEmail(),addressEntity);
		customerRepository.save(customer);
		return customer;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Customer> findAll(){
		return customerRepository.findAll();
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Customer getCustomerById(Integer id) {
		Customer c = customerRepository.findById(id).get();
		return c;
	}
}
