package au.gov.nsw.lpi.valnet.domain;


import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * Model to support web services
 * @author matkinson
 *
 */

@Component
public class RequestControl implements Serializable {

	static private final String EMPTY_STRING = "";
	
	private String customerId;
	private String xRef;
	private String propId;
	private String firstName;
	private String lastName;
	private String customerToken;
	private String action;
	private String response;
	private String statusCode;
	
	public RequestControl() {
		reset();
	}
	
	public String getXRef() {
		return xRef;
	}

	public void setXRef(String xRef) {
		this.xRef = xRef;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}
	
	public String getPropId() {
		return propId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCustomerToken() {
		return customerToken;
	}

	public void setCustomerToken(String customerToken) {
		this.customerToken = customerToken;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public int getStatusCodeInt() {
		
		int sc;
		
	    try  
	    {   
	    	sc = Integer.parseInt(statusCode);   
	    }   
	    catch(NumberFormatException nfe)   
	    {   
	    	return 500;   
	    }
	    
	    return sc;
	}
	
	public void reset() {
		this.xRef = EMPTY_STRING;
		this.customerId = EMPTY_STRING;
		this.propId = EMPTY_STRING;
		this.firstName = EMPTY_STRING;
		this.lastName = EMPTY_STRING;
		this.customerToken = EMPTY_STRING;
		this.action = EMPTY_STRING;
		this.response = EMPTY_STRING;
		this.statusCode = EMPTY_STRING;
	}
		
}
