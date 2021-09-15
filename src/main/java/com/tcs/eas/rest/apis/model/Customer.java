package com.tcs.eas.rest.apis.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModel;

/**
 * 
 * @author 44745
 *
 */
@ApiModel
@Entity(name = "Customer")
public class Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6820844333408498994L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerid;
	
	@NotNull(message="firstname field is missing")
	@Size(min=2,message="minimum 2 characters are required for firstname")
	private String firstname;
	
	@NotNull(message="lastname field is missing")
	@Size(min=2,message="minimum 2 characters are required for lastname")
	private String lastname;
	
	@NotNull(message="mobile field is missing")
	@Size(min=2,message="minimum 2 characters are required for mobile")
	private String mobile;
	
	@NotNull(message="email field is missing")
	@Size(min=2,message="minimum 2 characters are required for email")
	private String email;
	
	@NotNull(message="street field is missing")
	@Size(min=2,message="minimum 2 characters are required for street")
	private String street;
	
	@NotNull(message="city field is missing")
	@Size(min=2,message="minimum 2 characters are required for city")
	private String city;
	
	@NotNull(message="postcode field is missing")
	@Size(min=2,message="minimum 2 characters are required for postcode")
	private String postcode;
	
	@NotNull(message="state field is missing")
	@Size(min=2,message="minimum 2 characters are required for state")
	private String state;
	
	@NotNull(message="country field is missing")
	@Size(min=2,message="minimum 2 characters are required for country")
	private String country;	
	
	public Customer() {
		
	}

	
	public Customer(
			@NotNull(message = "firstname field is missing") @Size(min = 2, message = "minimum 2 characters are required for firstname") String firstname,
			@NotNull(message = "lastname field is missing") @Size(min = 2, message = "minimum 2 characters are required for lastname") String lastname,
			@NotNull(message = "mobile field is missing") @Size(min = 2, message = "minimum 2 characters are required for mobile") String mobile,
			@NotNull(message = "email field is missing") @Size(min = 2, message = "minimum 2 characters are required for email") String email,
			@NotNull(message = "street field is missing") @Size(min = 2, message = "minimum 2 characters are required for street") String street,
			@NotNull(message = "city field is missing") @Size(min = 2, message = "minimum 2 characters are required for city") String city,
			@NotNull(message = "postcode field is missing") @Size(min = 2, message = "minimum 2 characters are required for postcode") String postcode,
			@NotNull(message = "state field is missing") @Size(min = 2, message = "minimum 2 characters are required for state") String state,
			@NotNull(message = "country field is missing") @Size(min = 2, message = "minimum 2 characters are required for country") String country) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.email = email;
		this.street = street;
		this.city = city;
		this.postcode = postcode;
		this.state = state;
		this.country = country;
	}


	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}


	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}


	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * @return the customerid
	 */
	public int getCustomerid() {
		return customerid;
	}
	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
