package cn._51app.stats.base;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author jonny
 */
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = -8461921835590045388L;
	
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected ServletContext getConext() {
		return ServletActionContext.getServletContext();
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpSession getHttpSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 设置响应头信息编码
	 * 
	 * @param encoding
	 *            编码名称
	 */
	protected void setResponseEncoding(String encoding) {
		this.getResponse().setContentType("text/html;charset=" + encoding);
	}

	/**
	 * 使用response输出响应文本
	 * 
	 * @param data
	 *            文本信息
	 */
	protected void responseWrite(String data) {
		try {
			setResponseEncoding("UTF-8");
			this.getResponse().getWriter().write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-real-ip");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
