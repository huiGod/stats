package cn._51app.stats.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**  
 * @author nimo
 * @date 2016-8-12 
 * @version V1.0  
 * @Description: 压寨签名工具类
 */
public class YzSignUtils {
	private static final String KEY="gEMxsEHKfEpi";
		
	/**
	 * 验证用户
	 *@param map
	 *@return
	 */
	public static boolean validationSign(Map<String,String> map){
		   if(map==null)return false;
	       String sign=(String)map.get("sign");
		   return sign.equals(getSign(map));	
	}
	
	
	
	public static String getSign(Map<String, String> map){
	       String content=getCont(map);
		   return EncryptionUtil.MD5(content);	
	}
	
	private static String getCont(Map<String,String> map){
		map.remove("sign");
		Collection<String> keyset= map.keySet();  
	    List<String> list = new ArrayList<String>(keyset); 
	    Collections.sort(list);  
	    StringBuffer content=new StringBuffer();
	     for (int i = 0; i < list.size(); i++) {  
	    	 content.append(list.get(i)+"="+map.get(list.get(i))+"&") ;
	     }  
		return content+KEY;
	}
	
	 public static void main(String[] args) {
		 Map<String,String> map =new HashMap<String, String>();
		 map.put("idfa", "3BA5ADEE-6467-4FB7-9424-6D873071A73D");
		 map.put("cid", "youqitech");
		 map.put("appid", "1035270114");
		 map.put("times", 1471230733000l+"");
		System.out.println(getSign(map));
	}
	
}
