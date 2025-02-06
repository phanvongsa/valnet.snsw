package au.gov.nsw.lpi.valnet.service;

import java.io.Serializable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import au.gov.nsw.lpi.valnet.domain.RequestControl;
import au.gov.nsw.lpi.valnet.storedproc.StoredProcLinkCustomer;
import au.gov.nsw.lpi.valnet.storedproc.StoredProcGetProperties;
import au.gov.nsw.lpi.valnet.storedproc.StoredProcLinkProperty;
import au.gov.nsw.lpi.valnet.storedproc.StoredProcUnlinkProperty;

/**
 * Call stored procedures to support web services 
 * @author matkinson
 *
 */

@Component
public class DatabaseManager implements Serializable {
	
	static private final String SC_500 = "500";
	
	transient Logger logger = Logger.getLogger(getClass());


	/**
	 * Link a customer
	 * @param model
	 * @return
	 */
	public void linkCustomer(RequestControl model) {

		Map results;

		logger.info("*** in linkCustomer");
		
		try {
			results = this.storedProcLinkCustomer.execute(model);
		} catch (Exception e) {
			model.setStatusCode(SC_500);
			model.setResponse("service unavailable");
			return;
		}

		model.setResponse(results.get("p_response").toString());
		model.setStatusCode(results.get("p_status_code").toString());
	
		return;
		
	}
	
	
	/**
	 * Link a customer to a property
	 * @param model
	 * @return
	 */
	public void linkProperty(RequestControl model) {

		Boolean reply = false;
		Map results;

		logger.info("*** in linkProperty");
		
		try {
			results = this.storedProcLinkProperty.execute(model);
		} catch (Exception e) {
			model.setStatusCode(SC_500);
			model.setResponse("service unavailable");
			return;
		}
		
		model.setResponse(results.get("p_response").toString());
		model.setStatusCode(results.get("p_status_code").toString());
		
		return;
		
	}
	
	
	/**
	 * Get property summary
	 * @param model
	 * @return
	 */
	public void getProperties(RequestControl model) {

		Map results;

		logger.info("*** in getProperties");
		
		try {
			results = this.storedProcGetProperties.execute(model);
		} catch (Exception e) {
			model.setStatusCode(SC_500);
			model.setResponse("service unavailable");
			return;
		}
		
		model.setResponse(results.get("p_response").toString());
		model.setStatusCode(results.get("p_status_code").toString());
		
		return;
		
	}
	
	
	/**
	 * Unlink a customer from a property
	 * @param model
	 * @return
	 */
	public void unlinkProperty(RequestControl model) {

		Map results;

		logger.info("*** in unlinkProperty");
		
		try {
			results = this.storedProcUnlinkProperty.execute(model);
		} catch (Exception e) {
			model.setStatusCode(SC_500);
			model.setResponse("service unavailable");
			return;
		}
		
		model.setResponse(results.get("p_response").toString());
		model.setStatusCode(results.get("p_status_code").toString());
		
		return;
		
	}	
	
	@Autowired
	private StoredProcLinkCustomer storedProcLinkCustomer;
	
	@Autowired
	private StoredProcLinkProperty storedProcLinkProperty;
	
	@Autowired
	private StoredProcGetProperties storedProcGetProperties;

	@Autowired
	private StoredProcUnlinkProperty storedProcUnlinkProperty;
	
}
