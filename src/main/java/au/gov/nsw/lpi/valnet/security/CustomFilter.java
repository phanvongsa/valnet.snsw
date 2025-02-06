package au.gov.nsw.lpi.valnet.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import au.gov.nsw.lpi.valnet.domain.AppConfig;

public class CustomFilter extends GenericFilterBean {
	
	@Autowired
	private AppConfig appConfig;
	
	transient Logger logger = Logger.getLogger(getClass());

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String username = req.getHeader("username");
        String password = req.getHeader("password");

        String usernameEnv = appConfig.getAppUsername();
        String passwordEnv = appConfig.getAppPassword();
        
        logger.info(" attempt to authenticate user " + username);
        //logger.info(usernameEnv + " " + passwordEnv);
        
        if (username != null && password != null && username.equals(usernameEnv) && password.equals(passwordEnv)) {
        	logger.info("user " + username + " authentication success");
        	chain.doFilter(req, res);
        } else {
        	logger.error("user authentication error");
        	res.setContentType("application/json");
        	res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        	PrintWriter out = res.getWriter();
        	out.print("{\r\n" + 
        			" \"errors\": [\r\n" + 
        			"  { \"code\": \"authentication\",\r\n" + 
        			"    \"message\": \"Invalid credentials\" \r\n" + 
        			"  }\r\n" + 
        			" ]\r\n" + 
        			"}");
        	out.flush();
        	//res.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid credentials");
        }	
		
	}
}