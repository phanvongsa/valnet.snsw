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
 * Calls unlink_property stored procedure
 * @author matkinson
 *
 */

@Component
public class StoredProcUnlinkProperty extends StoredProcedure {

	transient Logger logger = Logger.getLogger(getClass());

	private static final String SQL = "vn_web_services.unlink_property";

	@Autowired
	public StoredProcUnlinkProperty(DataSource ds) {
		
		/*
		 logger.info("*** unlinkProperty instantiate");
		*/
		
		setDataSource(ds);
		setFunction(true);
		setSql(SQL);
		declareParameter(new SqlOutParameter("p_status_code", Types.VARCHAR));
		declareParameter(new SqlParameter("p_xref",           Types.VARCHAR));
		declareParameter(new SqlParameter("p_prop_id",        Types.VARCHAR));
		declareParameter(new SqlOutParameter("p_response",    Types.VARCHAR));		
		compile();

	}

	public Map execute(RequestControl model) {
		
		logger.info("*** Stored proc unlinkProperty execute " + model.getXRef() + " " +
					model.getPropId());

		Map inputs = new HashMap();
		inputs.put("p_xref", model.getXRef());
		inputs.put("p_prop_id", model.getPropId());
		
		return super.execute(inputs);
	}

}
