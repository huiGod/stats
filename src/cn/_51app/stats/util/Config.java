package cn._51app.stats.util;

import java.io.InputStream;
import java.util.PropertyResourceBundle;

public class Config {
	public static String WHITE_IP;
	
	private static final String KEY_WHITE_IP = "white.ip";
	private static final String CONF_FILE_NAME = "config.properties";
	
	static {
        try {
			InputStream fis = Config.class.getClassLoader().getResourceAsStream(CONF_FILE_NAME);
			PropertyResourceBundle props = new PropertyResourceBundle(fis);
			WHITE_IP = props.getString(KEY_WHITE_IP);
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
