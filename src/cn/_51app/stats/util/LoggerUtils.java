package cn._51app.stats.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn._51app.stats.domain.Adevice;
import cn._51app.stats.domain.Advertisement;
import cn._51app.stats.domain.AppBanner;
import cn._51app.stats.domain.AppDownload;
import cn._51app.stats.domain.AppSearch;
import cn._51app.stats.domain.AppSpecialAndCat;
import cn._51app.stats.domain.Device;
import cn._51app.stats.domain.LifeStyleClick;
import cn._51app.stats.domain.StatsModel;

/**
 * 数据日志记录
 * 
 * @author jonny
 */
public class LoggerUtils {
	private static Pattern loggerParser = Pattern
			.compile("\u0000([^:]*):([^\u0000]*)\u0000");
	//Ios用户统计
	private static Log logger = LogFactory.getLog("deviceLogger");
	//Android用户统计
	private static Log loggerAndroid = LogFactory.getLog("deviceAndroidLogger");
	//模块统计
	private static Log loggerModel = LogFactory.getLog("loggerModelLogger");
	//应用商城点击和下载量统计
	private static Log loggerAppDownload = LogFactory.getLog("loggerAppDownloadLogger");
	//应用商城广告栏的点击统计
	private static Log loggerAppBanner = LogFactory.getLog("loggerBannerLogger");
	//应用商城搜索关键字的统计
	private static Log loggerAppSearch = LogFactory.getLog("loggerSearchLogger");
	//应用商城专题和分类的统计
	private static Log loggerAppSpecialAndCat = LogFactory.getLog("loggerAppSpecialAndCatLogger");
	//生活圈资源点击数
	private static Log loggerLifeStyleClick = LogFactory.getLog("loggerLifeStyleClickLogger");
	//生活圈资源点击数
	private static Log loggerAdvertisementClick = LogFactory.getLog("loggerAdvertisementClickLogger");
	/**
	 * 用户日志
	 * 
	 * @param mallLog
	 */
	public static void logDeviceAction(Device device) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("\u0000");
			sb.append("uuid");
			sb.append(":");
			if(device!=null){
				sb.append(device.getUuid());
			}
			sb.append("\u0000");

			sb.append("\u0000");
			sb.append("cid");
			sb.append(":");
			sb.append(device.getCid());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("model");
			sb.append(":");
			sb.append(device.getModel());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("ios");
			sb.append(":");
			sb.append(device.getIos());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("version");
			sb.append(":");
			sb.append(device.getVersion());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("type");
			sb.append(":");
			sb.append(device.getType());
			sb.append("\u0000");
			
			String message = URLEncoder.encode(sb.toString(), "utf-8");
			// message = sb.toString();
			// System.out.println("message:" + message);
			logger.info(message);
		} catch (UnsupportedEncodingException ex) {
			java.util.logging.Logger.getLogger(LoggerUtils.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}
	
	public static void logAndroidDeviceAction(Adevice adevice) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("\u0000");
			sb.append("imei");
			sb.append(":");
			sb.append(adevice.getImei());
			sb.append("\u0000");

			sb.append("\u0000");
			sb.append("cid");
			sb.append(":");
			sb.append(adevice.getCid());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("model");
			sb.append(":");
			sb.append(adevice.getModel());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("android");
			sb.append(":");
			sb.append(adevice.getAndroid());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("version");
			sb.append(":");
			sb.append(adevice.getVersion());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("type");
			sb.append(":");
			sb.append(adevice.getType());
			sb.append("\u0000");
			
			String message = URLEncoder.encode(sb.toString(), "utf-8");
			// message = sb.toString();
			// System.out.println("message:" + message);
			loggerAndroid.info(message);
		} catch (UnsupportedEncodingException ex) {
			java.util.logging.Logger.getLogger(LoggerUtils.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * tengh 上午9:56:50
	 * TODO 记录统计模块日志
	 * @param statsModel
	 */
	public static void logModel(StatsModel statsModel) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("\u0000");
			sb.append("stop_time");
			sb.append(":");
			sb.append(statsModel.getStopTime());
			sb.append("\u0000");

			sb.append("\u0000");
			sb.append("app_model");
			sb.append(":");
			sb.append(statsModel.getAppModel());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("device_model");
			sb.append(":");
			sb.append(statsModel.getDeviceModel());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("sys");
			sb.append(":");
			sb.append(statsModel.getSys());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("app_code");
			sb.append(":");
			sb.append(statsModel.getAppCode());
			sb.append("\u0000");
			System.out.println("message:" + sb.toString());
			String message = URLEncoder.encode(sb.toString(), "utf-8");
			// message = sb.toString();
			// System.out.println("message:" + message);
			loggerModel.info(message);
		} catch (UnsupportedEncodingException ex) {
			java.util.logging.Logger.getLogger(LoggerUtils.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * tengh 下午8:04:55
	 * TODO 应用点击、下载量的统计
	 * @param appDownload
	 */
	public static void logAppDownload(AppDownload appDownload) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("\u0000");
			sb.append("appId");
			sb.append(":");
			sb.append(appDownload.getAppId());
			sb.append("\u0000");

			sb.append("\u0000");
			sb.append("channel");
			sb.append(":");
			sb.append(appDownload.getChannel());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("sys");
			sb.append(":");
			sb.append(appDownload.getSys());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("appCode");
			sb.append(":");
			sb.append(appDownload.getAppCode());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("type");
			sb.append(":");
			sb.append(appDownload.getType());
			sb.append("\u0000");

			sb.append("\u0000");
			sb.append("top");
			sb.append(":");
			sb.append(appDownload.getTop());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("appModel");
			sb.append(":");
			sb.append(appDownload.getAppModel());
			sb.append("\u0000");
			
			System.out.println("message:" + sb.toString());
			String message = URLEncoder.encode(sb.toString(), "utf-8");
			// message = sb.toString();
			// System.out.println("message:" + message);
			loggerAppDownload.info(message);
		} catch (UnsupportedEncodingException ex) {
			java.util.logging.Logger.getLogger(LoggerUtils.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * tengh 上午9:52:11
	 * TODO 统计应用商城广告栏的点击量
	 * @param appBanner
	 */
	public static void logAppBanner(AppBanner appBanner) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("\u0000");
			sb.append("deviceModel");
			sb.append(":");
			sb.append(appBanner.getDeviceModel());
			sb.append("\u0000");

			sb.append("\u0000");
			sb.append("sys");
			sb.append(":");
			sb.append(appBanner.getSys());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("type");
			sb.append(":");
			sb.append(appBanner.getType());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("aboutId");
			sb.append(":");
			sb.append(appBanner.getAboutId());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("top");
			sb.append(":");
			sb.append(appBanner.getTop());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("appCode");
			sb.append(":");
			sb.append(appBanner.getAppCode());
			sb.append("\u0000");
			
			System.out.println("message:" + sb.toString());
			String message = URLEncoder.encode(sb.toString(), "utf-8");
			// message = sb.toString();
			// System.out.println("message:" + message);
			loggerAppBanner.info(message);
		} catch (UnsupportedEncodingException ex) {
			java.util.logging.Logger.getLogger(LoggerUtils.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * tengh 上午11:02:01
	 * TODO 应用搜索关键字的统计
	 * @param appSearch
	 */
	public static void logAppSearch(AppSearch appSearch) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("\u0000");
			sb.append("key");
			sb.append(":");
			sb.append(appSearch.getKey());
			sb.append("\u0000");

			sb.append("\u0000");
			sb.append("deviceModel");
			sb.append(":");
			sb.append(appSearch.getDeviceModel());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("sys");
			sb.append(":");
			sb.append(appSearch.getSys());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("appCode");
			sb.append(":");
			sb.append(appSearch.getAppCode());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("result");
			sb.append(":");
			sb.append(appSearch.getResult());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("type");
			sb.append(":");
			sb.append(appSearch.getType());
			sb.append("\u0000");
			
			System.out.println("message:" + sb.toString());
			String message = URLEncoder.encode(sb.toString(), "utf-8");
			// message = sb.toString();
			// System.out.println("message:" + message);
			loggerAppSearch.info(message);
		} catch (UnsupportedEncodingException ex) {
			java.util.logging.Logger.getLogger(LoggerUtils.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * tengh 下午2:05:01
	 * TODO  
	 * @param appSpecialAndCat
	 */
	public static void logAppSpecialAndCat(AppSpecialAndCat appSpecialAndCat) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("\u0000");
			sb.append("deviceModel");
			sb.append(":");
			sb.append(appSpecialAndCat.getDeviceModel());
			sb.append("\u0000");

			sb.append("\u0000");
			sb.append("sys");
			sb.append(":");
			sb.append(appSpecialAndCat.getSys());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("appCode");
			sb.append(":");
			sb.append(appSpecialAndCat.getAppCode());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("type");
			sb.append(":");
			sb.append(appSpecialAndCat.getType());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("aboutId");
			sb.append(":");
			sb.append(appSpecialAndCat.getAboutId());
			sb.append("\u0000");
			
			System.out.println("message:" + sb.toString());
			String message = URLEncoder.encode(sb.toString(), "utf-8");
			// message = sb.toString();
			// System.out.println("message:" + message);
			loggerAppSpecialAndCat.info(message);
		} catch (UnsupportedEncodingException ex) {
			java.util.logging.Logger.getLogger(LoggerUtils.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}
	
	public static void loglifeStyleClick(LifeStyleClick lifeStyleClick) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("\u0000");
			sb.append("deviceModel");
			sb.append(":");
			sb.append(lifeStyleClick.getDeviceModel());
			sb.append("\u0000");

			sb.append("\u0000");
			sb.append("sys");
			sb.append(":");
			sb.append(lifeStyleClick.getSys());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("url");
			sb.append(":");
			sb.append(lifeStyleClick.getUrl());
			sb.append("\u0000");
			
			System.out.println("message:" + sb.toString());
			String message = URLEncoder.encode(sb.toString(), "utf-8");
			// message = sb.toString();
			// System.out.println("message:" + message);
			loggerLifeStyleClick.info(message);
		} catch (UnsupportedEncodingException ex) {
			java.util.logging.Logger.getLogger(LoggerUtils.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * tengh 2015年9月16日 上午9:52:22
	 * @param advertisement
	 * TODO 广告点击统计
	 */
	public static void advertisementClick(Advertisement advertisement) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("\u0000");
			sb.append("appCode");
			sb.append(":");
			sb.append(advertisement.getAppCode());
			sb.append("\u0000");

			sb.append("\u0000");
			sb.append("appModel");
			sb.append(":");
			sb.append(advertisement.getAppModel());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("sys");
			sb.append(":");
			sb.append(advertisement.getSys());
			sb.append("\u0000");
			
			sb.append("\u0000");
			sb.append("adId");
			sb.append(":");
			sb.append(advertisement.getAdId());
			sb.append("\u0000");
			
			System.out.println("message:" + sb.toString());
			String message = URLEncoder.encode(sb.toString(), "utf-8");
			// message = sb.toString();
			// System.out.println("message:" + message);
			loggerAdvertisementClick.info(message);
		} catch (UnsupportedEncodingException ex) {
			java.util.logging.Logger.getLogger(LoggerUtils.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * 日志数据还原
	 * 
	 * @param logdata
	 * @return
	 */
	public static Map<String, Object> restoreLogger(String logdata) {
		Map<String, Object> logDataMap = new HashMap<String, Object>();
		try {
			System.out.println(URLDecoder.decode(logdata, "utf-8"));
			URLDecoder.decode(logdata, "utf-8");
			// data = logdata;
			Matcher matcher = loggerParser.matcher(logdata);
			while (matcher.find()) {
				System.out.println(matcher.group(1) + ":" + matcher.group(2));
				logDataMap.put(matcher.group(1), matcher.group(2));
			}
		} catch (UnsupportedEncodingException ex) {
			java.util.logging.Logger.getLogger(LoggerUtils.class.getName())
					.log(Level.SEVERE, null, ex);
		}
		return logDataMap;
	}
	
	
	
	public static void main(String[] args) {
//		restoreLogger("appId%3A912%00%00userId%3A5857937%00%00token%3A3e5ad319000f42f68a087bb5fac55c45%00%00type%3A4%00%00asVersionId%3A2.0.3%00%00osversion%3A8.1.2%00%00model%3AiPhone6%2C2%00%00isjail%3A1%00%00network%3A2%00");
//		restoreLogger("2015-08-24 10:48:18%00key%3A%E9%99%8C%E9%99%8C%00%00deviceModel%3AiPhone6%2C2%00%00sys%3A1%00%00getAppCode%3A1%00%00result%3A1%00");
		restoreLogger("%00appCode%3A1%00%00appModel%3A901%00%00sys%3A1%00%00adId%3A1098463168%00");
	}


}
