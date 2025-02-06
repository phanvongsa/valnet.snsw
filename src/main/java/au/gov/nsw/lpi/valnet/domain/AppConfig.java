package au.gov.nsw.lpi.valnet.domain;

import java.io.Serializable;

public class AppConfig implements Serializable {

	private String appUsername;
	private String appPassword;
	
	public AppConfig () {}
	
	public String getAppUsername() {
		return appUsername;
	}
	
	public void setAppUsername(String appUsername) {
		this.appUsername = appUsername;
	}
	
	public String getAppPassword() {
		return appPassword;
	}
	
	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}
	
}
