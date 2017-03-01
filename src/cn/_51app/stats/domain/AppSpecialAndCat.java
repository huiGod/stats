package cn._51app.stats.domain;

import java.io.Serializable;

public class AppSpecialAndCat implements Serializable{
	
	/**
	 * tengh
	 * 下午2:01:41
	 */
	private static final long serialVersionUID = -5296615584187062737L;

	private String deviceModel;
	
	private int sys;
	
	private int appCode;
	
	private int type;
	
	private int aboutId;

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public int getSys() {
		return sys;
	}

	public void setSys(int sys) {
		this.sys = sys;
	}

	public int getAppCode() {
		return appCode;
	}

	public void setAppCode(int appCode) {
		this.appCode = appCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAboutId() { 
		return aboutId;
	} 

	public void setAboutId(int aboutId) {
		this.aboutId = aboutId;
	}
	
	
}
