package cn._51app.stats.action;

import org.apache.commons.lang.StringUtils;

import cn._51app.stats.base.BaseAction;
import cn._51app.stats.domain.ActiveIdfa;
import cn._51app.stats.domain.Adevice;
import cn._51app.stats.domain.Advertisement;
import cn._51app.stats.domain.AppBanner;
import cn._51app.stats.domain.AppDownload;
import cn._51app.stats.domain.AppSearch;
import cn._51app.stats.domain.AppSpecialAndCat;
import cn._51app.stats.domain.Device;
import cn._51app.stats.domain.IdfaVo;
import cn._51app.stats.domain.LifeStyleClick;
import cn._51app.stats.domain.StatsModel;
import cn._51app.stats.service.LogAndroidService;
import cn._51app.stats.service.LogService;
import cn._51app.stats.service.StatsService;
import cn._51app.stats.util.Config;
import cn._51app.stats.util.LoggerUtils;

/**
 * 
 * @author jonny
 * 
 */
public class LogAction extends BaseAction {

	private static final long serialVersionUID = 8358037341369941549L;

	private Device device;
	
	private IdfaVo idfaVo;
	
	private Adevice adevice;
	
	private StatsModel statsModel;
	
	private AppDownload appDownload;
	
	private AppBanner appBanner;
	
	private AppSearch appSearch;
	
	private Advertisement advertisement;
	
	private AppSpecialAndCat appSpecialAndCat;
	
	private LifeStyleClick lifeStyleClick;
	
	private ActiveIdfa activeIdfa;
	
	private int cacheType;
	
	private LogService logService;
	
	private StatsService statsService;
	
	private LogAndroidService logAndroidService;
	
	private String appid;
	
	private String idfa;
	
	private String os;
	
	private Integer channel;
	
	private String ip;
	
	private String callback;
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public IdfaVo getIdfaVo() {
		return idfaVo;
	}

	public void setIdfaVo(IdfaVo idfaVo) {
		this.idfaVo = idfaVo;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public int getCacheType() {
		return cacheType;
	}

	public void setCacheType(int cacheType) {
		this.cacheType = cacheType;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	
	public Adevice getAdevice() {
		return adevice;
	}

	public void setAdevice(Adevice adevice) {
		this.adevice = adevice;
	}

	public LogAndroidService getLogAndroidService() {
		return logAndroidService;
	}

	public void setLogAndroidService(LogAndroidService logAndroidService) {
		this.logAndroidService = logAndroidService;
	}
	

	public StatsModel getStatsModel() {
		return statsModel;
	}

	public void setStatsModel(StatsModel statsModel) {
		this.statsModel = statsModel;
	}

	public AppDownload getAppDownload() {
		return appDownload;
	}

	public void setAppDownload(AppDownload appDownload) {
		this.appDownload = appDownload;
	}
	

	public AppBanner getAppBanner() {
		return appBanner;
	}

	public void setAppBanner(AppBanner appBanner) {
		this.appBanner = appBanner;
	}

	public AppSearch getAppSearch() {
		return appSearch;
	}

	public void setAppSearch(AppSearch appSearch) {
		this.appSearch = appSearch;
	}
	

	public AppSpecialAndCat getAppSpecialAndCat() {
		return appSpecialAndCat;
	}

	public void setAppSpecialAndCat(AppSpecialAndCat appSpecialAndCat) {
		this.appSpecialAndCat = appSpecialAndCat;
	}

	public StatsService getStatsService() {
		return statsService;
	}

	public void setStatsService(StatsService statsService) {
		this.statsService = statsService;
	}
	
	
	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public LifeStyleClick getLifeStyleClick() {
		return lifeStyleClick;
	}

	public void setLifeStyleClick(LifeStyleClick lifeStyleClick) {
		this.lifeStyleClick = lifeStyleClick;
	}

	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	
	
	public ActiveIdfa getActiveIdfa() {
		return activeIdfa;
	}

	public void setActiveIdfa(ActiveIdfa activeIdfa) {
		this.activeIdfa = activeIdfa;
	}

	/**
	 * tengh 2016年2月27日 下午4:26:02
	 * @return
	 * TODO cpa来源分发统计
	 */
	public String idfa() {
		try {
			if(StringUtils.isBlank(ip)){
				ip = getIpAddr(getRequest());
			}
			logService.saveDevice(appid,idfa,os,channel,ip,callback);
			responseWrite("{\"success\":\"true\"}");
		} catch (Exception e) {
			responseWrite("{\"success\":\"false\"}");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 统计设备日志
	 * 
	 * @return3
	 */
	public String device() {
		try {
			logService.saveDevice(device);
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			responseWrite("{\"msg\":\"fail\"}");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * tengh 2015年9月8日 下午5:27:42
	 * @return 更新后台统计数据
	 * TODO
	 */
	public String createStatsLog() {
		try {
			String ip = getIpAddr(getRequest());
//			if (ip == null || !Config.WHITE_IP.contains(ip)) {
//				responseWrite("{\"msg\":\"IP_DENIED:" + ip + "\"}");
//				return null;
//			}
			statsService.createStatsLog();
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			responseWrite("{\"msg\":\"fail\"}");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * tengh 下午5:21:58
	 * TODO
	 * @return
	 */
	public String deviceAndroid(){
		try {
			logAndroidService.saveAndroidDevice(adevice);
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			responseWrite("{\"msg\":\"fail\"}");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * tengh 上午9:54:21
	 * TODO 统计模块
	 * @return
	 */
	public String model(){
		try {
			LoggerUtils.logModel(statsModel);
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			responseWrite("{\"msg\":\"fail\"}");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * tengh 下午7:48:00
	 * TODO 应用下载点击统计
	 * @return
	 */
	public String appDownload(){
		try {
			LoggerUtils.logAppDownload(appDownload);
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			responseWrite("{\"msg\":\"fail\"}");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * tengh 上午10:54:14
	 * TODO 首页大图广告位统计
	 * @return
	 */
	public String appBanner(){
		try {
			LoggerUtils.logAppBanner(appBanner);
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			responseWrite("{\"msg\":\"fail\"}");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * tengh 上午10:57:25
	 * TODO 应用搜索的统计
	 * @return
	 */
	public String appSearch(){
		try {
			LoggerUtils.logAppSearch(appSearch);
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			responseWrite("{\"msg\":\"fail\"}");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * tengh 下午1:47:24
	 * TODO 专题和分类的点击统计
	 * @return
	 */
	public String appSpecialAndCat(){
		try {
			LoggerUtils.logAppSpecialAndCat(appSpecialAndCat);
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			responseWrite("{\"msg\":\"fail\"}");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * tengh 2015年9月11日 下午3:34:52
	 * @return 生活圈资源点击数
	 * TODO
	 */
	public String lifeStyleClick(){
		try {
			LoggerUtils.loglifeStyleClick(lifeStyleClick);
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			responseWrite("{\"msg\":\"fail\"}");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * tengh 2015年9月16日 上午9:49:10
	 * @return 广告点击统计
	 * TODO
	 */
	public String advertisementClick(){
		try {
			LoggerUtils.advertisementClick(advertisement);
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			responseWrite("{\"msg\":\"fail\"}");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * tengh 2016年4月26日 下午2:05:37
	 * @return
	 * TODO idfa的统计
	 */
	public String idfaStats(){
		try {
			String ip = getIpAddr(getRequest());
			idfaVo.setRemoteIp(ip);
			statsService.insertIdfa(idfaVo);
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			e.printStackTrace();
			responseWrite("{\"msg\":\"fail\"}");
		}
		return null;
	}
	
	/**
	 * tengh 2016年4月26日 下午2:16:58
	 * @return
	 * TODO 验证idfa的有效性
	 */
	public String activeIdfa(){
		try {
			boolean result=statsService.checkIdfa(activeIdfa);
			if(!result){
				responseWrite("{\"msg\":\"success\"}");
			}else{
				responseWrite("{\"msg\":\"fail\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseWrite("{\"msg\":\"fail\"}");
		}
		return null;
	}
	
	/**
	 * 删除缓存
	 * 
	 * @return
	 */
	public String deleteCache() {
		try {
			String ip = getIpAddr(getRequest());
			if (ip == null || !Config.WHITE_IP.contains(ip)) {
				responseWrite("{\"msg\":\"IP_DENIED:" + ip + "\"}");
				return null;
			}
			switch (cacheType) {
			case 1:
				logService.deleteChannelCache();
				break;
			case 2:
				logService.deleteModelCache();
				break;
			case 3:
				logService.deleteIosVersionCache();
				break;
			case 4:
				break;
			default:
				break;
			}
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			responseWrite("{\"msg\":\"fail\"}");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除安卓缓存
	 * 
	 * @return
	 */
	public String deleteAndroidCache() {
		try {
			String ip = getIpAddr(getRequest());
			if (ip == null || !Config.WHITE_IP.contains(ip)) {
				responseWrite("{\"msg\":\"IP_DENIED:" + ip + "\"}");
				return null;
			}
			switch (cacheType) {
			case 1:
				logAndroidService.deleteChannelCache();
				break;
			case 2:
				logAndroidService.deleteModelCache();
				break;
			case 3:
				logAndroidService.deleteAndroidVersionCache();
				break;
			case 4:
				break;
			default:
				break;
			}
			responseWrite("{\"msg\":\"success\"}");
		} catch (Exception e) {
			responseWrite("{\"msg\":\"fail\"}");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
