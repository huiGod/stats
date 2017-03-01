package cn._51app.stats.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.HttpClientUtils;

import cn._51app.stats.base.BaseOcs;
import cn._51app.stats.dao.LogDao;
import cn._51app.stats.dao.StatsDao;
import cn._51app.stats.domain.ActiveIdfa;
import cn._51app.stats.domain.IdfaVo;
import cn._51app.stats.util.HttpUtils;
import net.sf.json.JSONObject;

public class StatsServiceImpl extends BaseOcs implements StatsService {
	private StatsDao statsDao;
	private LogDao logDao;
	private static Pattern loggerParser=Pattern.compile("\u0000([^:]*):([^\u0000]*)\u0000");
//	private static String DIR="E:/data/log/log4j/";
	private static String DIR="/data/log/log4j/";
	//停留时长和模块点击次数
	private static String modelPath=DIR+"model/";
	//应用点击和下载次数
	private static String appDownloadPath=DIR+"appDownload/";
	//应用商城广告栏的点击量
	private static String appBannerPath=DIR+"appBanner/";
	//商城搜索关键字的统计
	private static String appSearchPath=DIR+"appSearch/";
	//商城分类和专题点击统计
	private static String appSpecialAndCat=DIR+"appSpecialAndCat/";
	//生活圈点击次数
	private static String lifeStyleClick=DIR+"lifeStyleClick/";
	//广告点击次数
	private static String advertisementClick=DIR+"advertisementClick/";
	public StatsDao getStatsDao() {
		return statsDao;
	}
	public void setStatsDao(StatsDao statsDao) {
		this.statsDao = statsDao;
	}
	
	public LogDao getLogDao() {
		return logDao;
	}
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}
	@Override
	public void createStatsLog() throws Exception {
		this.createModelSql(modelPath+"model.log."+getPostDay());
		this.createModelSql(appDownloadPath+"appDownload.log."+getPostDay());
		this.createModelSql(appBannerPath+"appBanner.log."+getPostDay());
		this.createModelSql(appSearchPath+"appSearch.log."+getPostDay());
		this.createModelSql(appSpecialAndCat+"appSpecialAndCat.log."+getPostDay());
		this.createModelSql(lifeStyleClick+"lifeStyleClick.log."+getPostDay());
		this.createModelSql(advertisementClick+"advertisementClick.log."+getPostDay());
	}
	/**
	 * tengh 2015年9月8日 下午8:15:14
	 * TODO 读取日志文件
	 */
	@SuppressWarnings("null")
	private void createModelSql(String path) {
		BufferedReader reader=null;
		FileWriter fileWriter=null;
		try {
			reader=new BufferedReader(new FileReader(path));
			File fs=new File(path.replace(".log", "")+".sql");
			if(!fs.exists()){
				fs.createNewFile();
			}
			fileWriter=new FileWriter(fs,false);
			String line=null;
			while((line=reader.readLine())!=null){
				fileWriter.write(parseLineToSql(line));
				fileWriter.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fileWriter!=null){
				try {
					fileWriter.close();
				} catch (IOException e) {
					fileWriter=null;
				}
			}
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					reader=null;
				}
			}
		}
	}
	
	/**
	 * tengh 2015年9月9日 上午11:05:49
	 * @param line
	 * @return 将日志文件转换成sql文件
	 * TODO
	 */
	private String parseLineToSql(String line) {
		StringBuffer sb=new StringBuffer();
		Map<String, Object> bean=new HashMap<String,Object>();
		if(line!=null && !line.isEmpty()){
			try {
				String createtime=line.substring(0,10);
				line=line.substring(19);
				String data=URLDecoder.decode(line,"UTF-8");
				Matcher matcher=loggerParser.matcher(data);
				while(matcher.find()){
					bean.put(matcher.group(1), matcher.group(2));
				}
				if(data.contains("stop_time")){//停留时长和模块点击次数
					int stopTime=Integer.parseInt(bean.get("stop_time").toString());
					String appModel=bean.get("app_model").toString();
					String deviceModel=bean.get("device_model").toString();
					String sys=bean.get("sys").toString();
					String appCode=bean.get("app_code").toString();
					
					sb.append("INSERT INTO `s_stats` (`count`,`stopTime`,`appModel`,`deviceModel`,`time`,`sys`,`app_code`) VALUES(");
					sb.append("1,");
					sb.append(stopTime+",");
					sb.append(appModel+",");
					sb.append(getAppModel(deviceModel,sys)+",");
					sb.append("'"+createtime+"',");
					sb.append(sys+",");
					sb.append(appCode+") ");
					sb.append("ON DUPLICATE KEY UPDATE ");
					if(stopTime==0){
						sb.append("`count`=`count`+1");
					}else{
						sb.append("`stopTime`=`stopTime`+"+stopTime);
					}
					sb.append(";");
					sb.append("\r\n");
				}else if(data.contains("appId")){//应用点击和下载次数
					String appId=bean.get("appId").toString();
					String channel=bean.get("channel").toString();
					String sys=bean.get("sys").toString();
					String appCode=bean.get("appCode").toString();
					int type=Integer.parseInt(bean.get("type").toString());
					String top=bean.get("top").toString();
					String appModel=bean.get("appModel").toString();
					
					sb.append("INSERT INTO `s_app_download` (`appId`,`channel`,`detailCount`,`downloadCount`,`time`,`sys`,`appCode`,`top`,`appModel`) VALUES(");
					sb.append(appId+",");
					sb.append(channel+",");
					if(type==1){//点击详情
						sb.append(1+",");
						sb.append(0+",");
					}else{//点击下载
						sb.append(0+",");
						sb.append(1+",");
					}
					sb.append("'"+createtime+"',");
					sb.append(sys+",");
					sb.append(appCode+",");
					sb.append(top+",");
					sb.append(appModel+") ");
					sb.append("ON DUPLICATE KEY UPDATE ");
					if(type==1){//点击详情
						sb.append("`detailCount`=`detailCount`+1");
					}else{//点击下载
						sb.append("`downloadCount`=`downloadCount`+1");
					}
					sb.append(";");
					sb.append("\r\n");
				}else if(data.contains("top") && data.contains("aboutId")){//应用商城广告栏的点击量
					String deviceModel=bean.get("deviceModel").toString();
					String sys=bean.get("sys").toString();
					int type=Integer.parseInt(bean.get("type").toString());
					String aboutId=bean.get("aboutId").toString();
					String top=bean.get("top").toString();
					String appCode=bean.get("appCode").toString();
					
					sb.append("INSERT INTO `s_app_banner` (`count`,`deviceModel`,`time`,`sys`,`type`,`aboutId`,`top`,`appCode`) VALUES(");
					sb.append(1+",");
					sb.append(getAppModel(deviceModel,sys)+",");
					sb.append("'"+createtime+"',");
					sb.append(sys+",");
					sb.append(type+",");
					sb.append(aboutId+",");
					sb.append(top+",");
					sb.append(appCode+") ");
					sb.append("ON DUPLICATE KEY UPDATE ");
					sb.append("`count`=`count`+1");
					sb.append(";");
					sb.append("\r\n");
				}else if(data.contains("key")){//应用商城关键字的点击量
					String key=bean.get("key").toString();
					String deviceModel=bean.get("deviceModel").toString();
					String sys=bean.get("sys").toString();
					String appCode=bean.get("appCode").toString();
					String result=bean.get("result").toString();
					String type=bean.get("type").toString();
					
					sb.append("INSERT INTO `s_app_search` (`key`,`deviceModel`,`sys`,`appCode`,`result`,`time`,`type`,`count`) VALUES(");
					sb.append("'"+key+"',");
					sb.append(getAppModel(deviceModel,sys)+",");
					sb.append(sys+",");
					sb.append(appCode+",");
					sb.append(result+",");
					sb.append("'"+createtime+"',");
					sb.append(type+",");
					sb.append(1+") ");
					sb.append("ON DUPLICATE KEY UPDATE ");
					sb.append("`count`=`count`+1");
					sb.append(";");
					sb.append("\r\n");
				}else if(data.contains("aboutId") && !data.contains("top")){//商城分类和专题点击统计
					String deviceModel=bean.get("deviceModel").toString();
					String sys=bean.get("sys").toString();
					String appCode=bean.get("appCode").toString();
					String type=bean.get("type").toString();
					String aboutId=bean.get("aboutId").toString();
					
					sb.append("INSERT INTO `s_app_speandcat` (`count`,`deviceModel`,`time`,`sys`,`appCode`,`type`,`aboutId`) VALUES(");
					sb.append(1+",");
					sb.append(getAppModel(deviceModel,sys)+",");
					sb.append("'"+createtime+"',");
					sb.append(sys+",");
					sb.append(appCode+",");
					sb.append(type+",");
					sb.append(aboutId+") ");
					sb.append("ON DUPLICATE KEY UPDATE ");
					sb.append("`count`=`count`+1");
					sb.append(";");
					sb.append("\r\n");
				}else if(data.contains("url")){//生活圈点击
					String deviceModel=bean.get("deviceModel").toString();
					String sys=bean.get("sys").toString();
					String url=bean.get("url").toString();
					String appCode=bean.get("appCode").toString();
					String appModel=bean.get("appModel").toString();
					
					sb.append("INSERT INTO `s_lifestyle` (`deviceModel`,`sys`,`url`,`count`,`appCode`,`appModel`,`time`) VALUES(");
					sb.append(getAppModel(deviceModel,sys)+",");
					sb.append(sys+",");
					sb.append("'"+url+"',");
					sb.append(1+",");
					sb.append(appCode+",");
					sb.append(appModel+",");
					sb.append("'"+createtime+"') ");
					sb.append("ON DUPLICATE KEY UPDATE ");
					sb.append("`count`=`count`+1");
					sb.append(";");
					sb.append("\r\n");
				}else if(data.contains("adId")){
					String appCode=bean.get("appCode").toString();
					String appModel=bean.get("appModel").toString();
					String sys=bean.get("sys").toString();
					String adId=bean.get("adId").toString();
					
					sb.append("INSERT INTO `s_ad` (`appCode`,`appModel`,`sys`,`time`,`count`,`adId`) VALUES(");
					sb.append(appCode+",");
					sb.append(appModel+",");
					sb.append(sys+",");
					sb.append("'"+createtime+"',");
					sb.append(1+",");
					sb.append(adId+") ");
					sb.append("ON DUPLICATE KEY UPDATE ");
					sb.append("`count`=`count`+1");
					sb.append(";");
					sb.append("\r\n");
				}
			} catch (Exception e) {
				return "";
			}
		}
		return sb.toString();
	}
	/**
	 * tengh 2015年9月9日 上午11:40:52
	 * @param app_model
	 * @param sys 
	 * @return 查询缓存是否有该型号 先从缓存中查找 查不到删除缓存重新查找数据库 再缓存
	 * TODO
	 */
	private int getAppModel(String device_model, String sys) {
		List<Map<String,Object>> device_models=(List<Map<String,Object>>)get("device_model_"+sys);
		if (device_models == null) {
			device_models = statsDao.getDeviceModelsBySys(sys);
			// 设置缓存
			set("device_model_"+sys, device_models, 0);
		}
		for (Map<String, Object> v : device_models) {
			if (((String) v.get("model")).equals(device_model)) {
				//从缓存中查到
				return (Integer) v.get("id");
			}
		}
		delete("device_model_"+sys);
		//插入数据库并返回id
		device_models = statsDao.getDeviceModelsBySys(sys);
		// 设置缓存
		set("device_model_"+sys, device_models, 0);
		for (Map<String, Object> v : device_models) {
			if (((String) v.get("model")).equals(device_model)) {
				//从缓存中查到
				return (Integer) v.get("id");
			}
		}
		return 0;
	}
	/**
	 * tengh 2015年9月9日 上午9:17:30
	 * @return 获得昨天时间
	 * TODO
	 */
	private static String getPostDay() {
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return format.format(calendar.getTime());
	}
	
	/**
	 * tengh 2015年12月30日 下午8:23:41
	 * @param uuid
	 * @param idfa
	 * TODO 插入
	 */
	@Override
	public void insertIdfa(IdfaVo insertIdfa) {
		statsDao.insertIdfa(insertIdfa.getUuid(),insertIdfa.getIdfa(),insertIdfa.getAppId(),insertIdfa.getRemoteIp());
	}
	
	/**
	 * tengh 2016年2月29日 上午10:48:03
	 * @param appid
	 * @param idfa
	 * @return
	 * TODO cpa回调
	 */
	@Override
	public int appActive(String appid, String idfa,String channel) {
		return this.statsDao.getAppActive(appid,idfa,channel);
	}
	
	@Override
	public Map<String, Object> queryChannel(int flag) {
		return this.statsDao.queryChannel(flag);
	}
	
	/**
	 * tengh 2016年3月4日 下午5:48:24
	 * @param appid
	 * @param idfa
	 * TODO 第三方回调
	 */
	@Override
	public void appFlagActive(String appid, String idfa) {
		this.statsDao.appFlagActive(appid,idfa);
	}
	
	@Override
	public String queryflagUrl(String appid) {
		String json=(String)get("cpa_appid_"+appid);
		Map<String, Object> info=new HashMap<>();
		if(StringUtils.isBlank(json)){
			info=logDao.getCpaAppId(appid); //标识
			if(info.get("flagUrl")==null){
				info.put("flagUrl","");
			}
			if(info!=null && info.size()>0){
				JSONObject jsonObject=JSONObject.fromObject(info);
				json=jsonObject.toString();
				set("cpa_appid_"+appid, json, 0);
			}
		}
		if(StringUtils.isBlank(json)){
			return null;
		}else{
			try {
				Map<String, Object> appInfo=(Map<String, Object>) JSONObject.fromObject(json);
				String flagUrl=(String)appInfo.get("flagUrl");
				if(StringUtils.isBlank(flagUrl)){
					return null;
				}else{
					return flagUrl;
				}
			} catch (Exception e) {
				return null;
			}
			
		}
	}
	
	@Override
	public String queryflagCallback(String appid, String idfa) {
		return this.statsDao.queryflagCallback(appid,idfa);
	}
	
	@Override
	public boolean checkIdfa(ActiveIdfa activeIdfa) {
		try {
			String idfa=activeIdfa.getIdfa();
			String appId=activeIdfa.getAppid();
			if(StringUtils.isNotBlank(idfa) && StringUtils.isNotBlank(appId)){
				return this.statsDao.checkIdfa(idfa,appId);
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	@Override
	public String queryIsbaby(String appid,String idfa) {
		try {
			String isBaby=(String)get("isbaby_"+appid);
			if(StringUtils.isBlank(isBaby)){
				isBaby=this.statsDao.queryIsbaby(appid);
				set("isbaby_"+appid, isBaby, 0);
			}
			String appleid=(String)get("appleid_appid_"+appid);
			if(StringUtils.isBlank(appleid)){
				appleid=this.statsDao.queryAppleid(appid);
				set("appleid_appid_"+appid, appleid, 0);
			}
			Thread.sleep(2000);
			if("1".equals(isBaby)){//需要处理   回调就给钱
				HttpUtils.get("http://api.baby.51app.cn/baby/u/directFinish.do?appleid="+appleid+"&idfa="+idfa, null, 5, "utf-8");
			}else if("2".equals(isBaby)){ //数量先减少
				HttpUtils.get("http://api.baby.51app.cn/baby/u/desHomeNum.do?appleid="+appleid+"&idfa="+idfa, null, 5, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public String queryIdfaInfo(String createDate) {
		List<Map<String, Object>> list=this.statsDao.queryIdfaInfo(createDate);
		int result=0;
		for (int i = 0; i < list.size(); i++) {
			if((int)(1+Math.random()*10)<=3){
					result++;
					Integer appid=(Integer)list.get(i).get("appid");
					String idfa=(String)list.get(i).get("idfa");
					Integer id=(Integer)list.get(i).get("id");
					String url="";
					if(appid==6){
						url="http://ios.api.i4.cn/appactivatecb.xhtml?aisicid=200176&aisi=257034&appid=1071503483&rt=1&idfa=";
					}else if(appid==5){
						url="http://ios.api.i4.cn/appactivatecb.xhtml?aisicid=200176&aisi=261078&appid=1068353024&rt=1&idfa=";
					}else if(appid==2){
						url="http://ios.api.i4.cn/appactivatecb.xhtml?aisicid=200176&aisi=259589&appid=1078206114&rt=1&idfa=";
					}
					try {
						HttpUtils.get(url+idfa, null, 5, "utf-8");
						this.statsDao.updateI4(id);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		return result+"";
	}
	
	@Override
	public int checkActiveIdfa(String idfa, String appid) {
		return this.statsDao.checkActiveIdfa(idfa, appid);
	}
}
