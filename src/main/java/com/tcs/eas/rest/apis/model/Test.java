package com.tcs.eas.rest.apis.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel
public class Test implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5849886989719086232L;
	private String fname;
	private String lname;
	
	public Test() {
		
	}
	public Test(String fname, String lname) {
		super();
		this.fname = fname;
		this.lname = lname;
	}
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	
}
