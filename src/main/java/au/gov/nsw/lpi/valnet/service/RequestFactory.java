package au.gov.nsw.lpi.valnet.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import au.gov.nsw.lpi.valnet.domain.RequestControl;

/**
 * Create and populate a request object 
 * @author matkinson
 *
 */


public class RequestFactory {

	public static RequestControl getRequestControl (HttpServletRequest r) {
		
		RequestControl rc = new RequestControl();
								
		if (r.getParameterMap().containsKey("action")) {
			rc.setAction(r.getParameter("action"));
		}		
		if (r.getParameterMap().containsKey("xRef")) {
			rc.setXRef(r.getParameter("xRef"));
		}		
		if (r.getParameterMap().containsKey("customerToken")) {
			rc.setCustomerToken(r.getParameter("customerToken"));
		}
		if (r.getParameterMap().containsKey("customerId")) {
			rc.setCustomerId(r.getParameter("customerId"));
		}
		if (r.getParameterMap().containsKey("propId")) {
			rc.setPropId(r.getParameter("propId"));
		}
		if (r.getParameterMap().containsKey("firstName")) {
			rc.setFirstName(r.getParameter("firstName"));
		}
		if (r.getParameterMap().containsKey("lastName")) {
			rc.setLastName(r.getParameter("lastName"));
		}
		
		return rc;
	}
	
}
