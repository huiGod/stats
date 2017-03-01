package cn._51app.stats.domain;

import java.io.Serializable;

public class AppSearch implements Serializable{

	/**
	 * tengh
	 * 上午10:58:45
	 */
	private static final long serialVersionUID = 1L;

	private String key;
	
	private String deviceModel;
	
	private int sys;
	
	private int appCode;
	
	private int result;
	
	private int type;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	

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

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
