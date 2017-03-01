package cn._51app.stats.domain;

import java.io.Serializable;

public class AppDownload implements Serializable {
	/**
	 * tengh 商城点击下载统计 下午7:32:20
	 */
	private static final long serialVersionUID = 3058880009956779117L;
	
	private int appId;
	
	private int channel;
	
	private int sys;
	
	private int appCode;
	
	private int type;
	
	private int top;
	
	private int appModel;
	
	
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
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
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getAppModel() {
		return appModel;
	}
	public void setAppModel(int appModel) {
		this.appModel = appModel;
	}


}
