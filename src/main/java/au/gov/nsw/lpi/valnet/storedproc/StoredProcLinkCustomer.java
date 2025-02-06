package au.gov.nsw.lpi.valnet.storedproc;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

import au.gov.nsw.lpi.valnet.domain.RequestControl;

/**
 * Calls get_customer_id stored procedure
 * @author matkinson
 *
 */

@Component
public class StoredProcLinkCustomer extends StoredProcedure {

	transient Logger logger = Logger.getLogger(getClass());

	private static final String SQL = "vn_web_services.link_customer";

	@Autowired
	public StoredProcLinkCustomer(DataSource ds) {
		
		/*
		 logger.info("*** getCustomerId instantiate");
		*/
		
		setDataSource(ds);
		setFunction(true);
		setSql(SQL);
		declareParameter(new SqlOutParameter("p_status_code", Types.VARCHAR));
		declareParameter(new SqlParameter("p_token",          Types.VARCHAR));
		declareParameter(new SqlParameter("p_xref",           Types.VARCHAR));
		declareParameter(new SqlParameter("p_firstname",      Types.VARCHAR));
		declareParameter(new SqlParameter("p_lastname",       Types.VARCHAR));
		declareParameter(new SqlOutParameter("p_response",    Types.VARCHAR));		
		compile();

	}

	public Map execute(RequestControl model) {
		
		logger.info("*** Stored proc linkCustomer execute " + model.getCustomerToken() + " " +
					model.getXRef() + " " + model.getFirstName() + " " + model.getLastName());

		Map inputs = new HashMap();
		inputs.put("p_token", model.getCustomerToken());
		inputs.put("p_xref", model.getXRef());
		inputs.put("p_firstname", model.getFirstName());
		inputs.put("p_lastname", model.getLastName());
		
		return super.execute(inputs);
	}

}
