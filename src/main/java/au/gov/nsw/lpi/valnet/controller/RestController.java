package au.gov.nsw.lpi.valnet.controller;

import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import au.gov.nsw.lpi.valnet.domain.RequestControl;
import au.gov.nsw.lpi.valnet.service.DatabaseManager;
import au.gov.nsw.lpi.valnet.service.RequestFactory;

/*
 * simple controller to support Rest requests
 * 
 * @author matkinson
 * 
 */
@Controller
public class RestController {
	
	static private final String LINK_CUSTOMER   = "linkxref";
	static private final String LINK_PROPERTY   = "linkprop";
	static private final String GET_PROPERTIES  = "getprops";
	static private final String UNLINK_PROPERTY = "unlinkpr";
	
	transient Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public void webService(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		RequestControl rc = RequestFactory.getRequestControl(request);
		
       	logger.info("action =  " + rc.getAction());        	

    	if (Arrays.asList(LINK_CUSTOMER, LINK_PROPERTY, GET_PROPERTIES, UNLINK_PROPERTY).contains(rc.getAction())) {

    		if (LINK_CUSTOMER.equals(rc.getAction())) {

  				databaseManager.linkCustomer(rc);    				
   			
   			} else if (LINK_PROPERTY.equals(rc.getAction())) {
    				
     			databaseManager.linkProperty(rc);
   				     				
   			} else if (GET_PROPERTIES.equals(rc.getAction())) {
    				
       			databaseManager.getProperties(rc);	
    				
   			} else if (UNLINK_PROPERTY.equals(rc.getAction())) {
    				
  				databaseManager.unlinkProperty(rc);

   			}
    		
    		logger.info("status code: " + rc.getStatusCode() + " returning: " + rc.getResponse());	    			    	
    		
			response.setStatus(rc.getStatusCodeInt());
   			response.addHeader("Content-Type", "application/json;charset=UTF-8");
   			PrintWriter pw = response.getWriter();
   			pw.write(rc.getResponse());
			
		} else {
			
//				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//   			response.addHeader("Content-Type", "application/json;charset=UTF-8");
//   			PrintWriter pw = response.getWriter();
//   			pw.write("Invalid request");
		

        	logger.error("user authentication error");
        	response.setContentType("application/json");
        	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        	PrintWriter out = response.getWriter();
        	out.print("{\r\n" + 
        			" \"errors\": [\r\n" + 
        			"  { \"code\": \"request_param\",\r\n" + 
        			"    \"message\": \"Invalid request parameters\" \r\n" + 
        			"  }\r\n" + 
        			" ]\r\n" + 
        			"}");
        	out.flush();
        	
        
			
			
		}	
	}
	
	@Autowired
	DatabaseManager databaseManager;
	
}
