package cn._51app.stats.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author nimo
 * @date 2016-8-14下午3:30:25
 * @version V1.0
 * @Description: 常用加解密工具
 */
public class EncryptionUtil {
	private final static String MD5="MD5";
	private final static String SHA="SHA";
	
	/**
	 * MD5加密
	 * @param source 待加密字符串
	 * @return
	 */
    public static String MD5(String source){
    	   StringBuilder sb = new StringBuilder();
    	    MessageDigest md5;
    	    try {
    	      md5 = MessageDigest.getInstance(MD5);
    	      md5.update(source.getBytes());
    	      for (byte b : md5.digest()) {
    	        sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
    	      }
    	      return sb.toString();
    	    } catch (NoSuchAlgorithmException e) {
    	      e.printStackTrace();
    	    }
    	 return null;
     }
    /**
     * SHA1加密
     * @param source 待加密字符串
     * @return
     */
    public static String SHA(String source){
 	   StringBuilder sb = new StringBuilder();
 	    MessageDigest md5;
 	    try {
 	      md5 = MessageDigest.getInstance(SHA);
 	      md5.update(source.getBytes());
 	      for (byte b : md5.digest()) {
 	        sb.append(String.format("%02X", b));
 	      }
 	      return sb.toString();
 	    } catch (NoSuchAlgorithmException e) {
 	      e.printStackTrace();
 	    }
 	 return null;
    }
 
    public static void main(String[] args) {
    	String source="1";
		System.out.println(MD5(source));
		System.out.println(SHA(source));
	}
}
