package cn._51app.stats.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * http请求
 * @author Zhu Jian
 *
 */
public class HttpUtils {
    /**
     * post请求 ，超时默认10秒, 默认utf-8
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public String post(String url, Map<String, String> params) throws Exception {
        return this.post(url, params, 10, HTTP.UTF_8);
    }
    /**
     * post请求, 超时默认10秒
     * @param url
     * @param params
     * @param charset 编码方式
     * @return
     * @throws Exception
     */
    public String post(String url, Map<String, String> params, String charset) throws Exception {
        return this.post(url, params, 10, charset);
    }
    /**
     * post请求, 默认utf-8
     * @param url
     * @param params
     * @param timeout 超时时间，秒
     * @return
     * @throws Exception
     */
    public String post(String url, Map<String, String> params, int timeout) throws Exception {
        return this.post(url, params, timeout, HTTP.UTF_8);
    }
    /**
     * post请求
     * @param url
     * @param params
     * @param timeout 超时时间，秒
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params, int timeout, String charset) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        String retVal = "";
        try {
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    formparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, charset);
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(entity);
            HttpResponse resp = httpclient.execute(httppost);
            retVal = EntityUtils.toString(resp.getEntity(), charset);
        } catch (IOException e) {
            throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    
    public static String post(String url, int timeout, String str) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
        String retVal = "";
        try {
            HttpPost httppost = new HttpPost(url);
            StringEntity reqEntity = new StringEntity(str, "UTF-8");
            httppost.setEntity(reqEntity);
            httppost.setHeader("Content-type", "text/xml; charset=\"utf-8\"");
            httppost.setHeader("User-Agent", "InetURL/1.0");
            HttpResponse resp = httpclient.execute(httppost);
            retVal = EntityUtils.toString(resp.getEntity(), "GB2312");
        } catch (IOException e) {
            throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    
    public static String postOut(String url, int timeout, String str) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
        String retVal = "";
        try {
            HttpPost httppost = new HttpPost(url);
            StringEntity reqEntity = new StringEntity(str, "UTF-8");
            httppost.setEntity(reqEntity);
            httppost.setHeader("Content-type", "text/xml; charset=\"UTF-8\"");
            httppost.setHeader("User-Agent", "InetURL/1.0");
            HttpResponse resp = httpclient.execute(httppost);
            retVal = EntityUtils.toString(resp.getEntity(), "UTF-8");
        } catch (IOException e) {
            throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    
    public static String postOut(String url, int timeout, String str, String charset) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
        String retVal = "";
        try {
            HttpPost httppost = new HttpPost(url);
            StringEntity reqEntity = new StringEntity(str, charset);
            httppost.setEntity(reqEntity);
            httppost.setHeader("Content-type", "text/xml; charset=\"UTF-8\"");
            httppost.setHeader("User-Agent", "InetURL/1.0");
            HttpResponse resp = httpclient.execute(httppost);
            retVal = EntityUtils.toString(resp.getEntity(), "UTF-8");
        } catch (IOException e) {
            throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    
    public static String postByLock(String url, int timeout, String str) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
        String retVal = "";
        try {
            HttpPost httppost = new HttpPost(url);
            StringEntity reqEntity = new StringEntity(str);
            httppost.setEntity(reqEntity);

            httppost.setHeader("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, " +
            		"application/x-shockwave-flash, application/vnd.ms-excel, " +
            		"application/vnd.ms-powerpoint, application/msword, application/xaml+xml, " +
            		"application/x-ms-xbap, application/x-ms-application, */*");
            httppost.setHeader("Accept-Language", "zh-cn");
            httppost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; " +
            		"Trident/4.0; .NET CLR 2.0.50727; Zune 4.7; .NET4.0C; .NET4.0E)");
            httppost.setHeader("Content-type", "application/x-www-form-urlencoded");
//            httppost.setHeader("Accept-Encoding", "gzip, deflate");
            httppost.setHeader("Host", "iunlocker.net");
            httppost.setHeader("Connection", "Keep-Alive");
            httppost.setHeader("Cache-Control", "no-cache");
            httppost.setHeader("Referer", "http://iunlocker.net/check_imei_bulk.php");
            httppost.setHeader("Content-Encoding", "gzip");
            HttpResponse resp = httpclient.execute(httppost);
            retVal = EntityUtils.toString(resp.getEntity(), "GB2312");
        } catch (IOException e) {
            throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    
    /**
     * get请求
     * @param url
     * @param params
     * @param timeout 超时时间，秒
     * @param charset 编码方式
     * @return
     * @throws Exception
     */
    public static String get(String url, Map<String, String> params, int timeout, String charset) throws Exception {
        HttpClient httpclient = null;
        if(url.startsWith("https")){
        	httpclient = createSSLClientDefault();
        }else{
        	httpclient=new DefaultHttpClient();
        }
        String retVal = "";
        try {
            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    qparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
            }
            String paramstr = URLEncodedUtils.format(qparams, charset);
            if (StringUtils.isNotEmpty(paramstr)) {
                url = url + "?" + paramstr;
            }
            HttpGet httpget = new HttpGet(url);
            HttpResponse resp = httpclient.execute(httpget);
            retVal = EntityUtils.toString(resp.getEntity(), charset);
        } catch (IOException e) {
            throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    
    
    /**
     * get请求
     * @param url
     * @param params
     * @param timeout 超时时间，秒
     * @param charset 编码方式
     * @param fNameEndChar 方法名后结束字符 默认“?”
     * @return
     * @throws Exception
     */
    public static String get(String url, Map<String, String> params, int timeout, String charset,String fNameEndChar) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
//        org.apache.http.impl.client.ProxyClient proc = new org.apache.http.impl.client.ProxyClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        //请求超时 
        //CoreConnectionPNames.CONNECTION_TIMEOUT  == "http.connection.timeout"
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);  
//        //读取超时
//        //CoreConnectionPNames.SO_TIMEOUT  == "http.socket.timeout"
//        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout * 1000);
//        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        String retVal = "";
        fNameEndChar=(fNameEndChar==null || "".endsWith(fNameEndChar))? "?":fNameEndChar;
        try {
            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    qparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
            }
            String paramstr = URLEncodedUtils.format(qparams, charset);
            if (StringUtils.isNotEmpty(paramstr)) {
                url = url + fNameEndChar + paramstr;
            }
            HttpGet httpget = new HttpGet(url);
            
            HttpResponse resp = httpclient.execute(httpget);
//            System.out.println(httpclient.getParams().getParameter("port"));
            retVal = EntityUtils.toString(resp.getEntity(), charset);
        } catch (IOException e) {
            throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
            httpclient = null;
        }
        return retVal;
    }
    
    /**
     * get请求
     * @param url
     * @param params
     * @param timeout 超时时间，秒
     * @param charset 编码方式
     * @param fNameEndChar 方法名后结束字符 默认“?”
     * @return
     * @throws Exception
     */
    public static String getByIacqua(String url, Map<String, String> params, int timeout, String charset,String fNameEndChar) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        //请求超时 
        //CoreConnectionPNames.CONNECTION_TIMEOUT  == "http.connection.timeout"
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);  
//        //读取超时
//        //CoreConnectionPNames.SO_TIMEOUT  == "http.socket.timeout"
//        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout * 1000);
//        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        String retVal = "";
        fNameEndChar=(fNameEndChar==null || "".endsWith(fNameEndChar))? "?":fNameEndChar;
        try {
            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    qparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
            }
            String paramstr = URLEncodedUtils.format(qparams, charset);
            if (StringUtils.isNotEmpty(paramstr)) {
                url = url + fNameEndChar + paramstr;
            }
            HttpGet httpget = new HttpGet(url);
            httpget.setHeader("User-Agent", "iacqua/1.1-405");
            
            HttpResponse resp = httpclient.execute(httpget);
            retVal = EntityUtils.toString(resp.getEntity(), charset);
        } catch (IOException e) {
            throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
            httpclient = null;
        }
        return retVal;
    }
    
    /**
     * get请求,超时默认10秒
     * @param url
     * @param params
     * @param charset 编码方式
     * @return
     * @throws IOException
     */
    public String get(String url, Map<String, String> params, String charset) throws Exception {
        return this.get(url, params, 10, charset);
    }
    /**
     * get请求,超时默认10秒, 默认utf-8
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public String get(String url, Map<String, String> params) throws Exception {
        return this.get(url, params, 10, HTTP.UTF_8);
    }
    /**
     * get请求, 默认utf-8
     * @param url
     * @param params
     * @param timeout 超时时间，秒
     * @return
     * @throws Exception
     */
    public String get(String url, Map<String, String> params, int timeout) throws Exception {
        return this.get(url, params, timeout, HTTP.UTF_8);
    }
    
    public static Map<String, String> getHeaderFromUrl(String url){
    	Map<String, String> map = new HashMap<String, String>();
    	HttpGet httpget = new HttpGet(url);
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setIntParameter("http.socket.timeout", 5 * 1000);
		httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
		try {
			HttpResponse resp = httpclient.execute(httpget);
			Header[] h = resp.getAllHeaders();
			map.put("HttpStatus", resp.getStatusLine().toString().split(" ")[1]);
			map.put("reqUrl", url);
			String[] ar;
			for(int i=0; i<h.length; i++){
				ar = h[i].toString().split(":");
				map.put(ar[0], h[i].toString().replaceFirst(ar[0], "").trim().replaceFirst(":", "").trim());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			map = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			map = null;
		}
		return map;
    } 
    
    
    public static CloseableHttpClient createSSLClientDefault(){
		try {
			SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
		             SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
		                 //信任所有
		                 public boolean isTrusted(X509Certificate[] chain,
		                                 String authType) throws CertificateException {
		                     return true;
		                 }
		             }).build();
		             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		             return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		         } catch (KeyManagementException e) {
		             e.printStackTrace();
		         } catch (NoSuchAlgorithmException e) {
		             e.printStackTrace();
		         } catch (KeyStoreException e) {
		             e.printStackTrace();
		         }
		         return  HttpClients.createDefault();
		}
    
    public static void main(String[] args){
    	try {
			System.err.println(HttpUtils.get("https://cpa.yyjinbao.com/deliver/51app?ref=51app&idfa=D9393194-CD8A-4A10-93E1-A630700F4FED&ip=183.14.132.202&callback=http%3A%2F%2Fios.api.51app.cn%2Fios_appActive.action%3Fappid%3D1186700086%26idfa%3DD9393194-CD8A-4A10-93E1-A630700F4FED%26rt%3D4", null,5,HTTP.UTF_8));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}