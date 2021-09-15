package com.tcs.eas.rest.apis.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.eas.rest.apis.model.Customer;


/**
 * 
 * @author 44745
 *
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	
}
