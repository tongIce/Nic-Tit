package com.litt.micro.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 读取配置文件的工具类
 * 
 */
public class PropertiesReadUtils {
	/*
	 * 私有化构造方法
	 */
	private PropertiesReadUtils() {

	}

	// 私有化的内部类
	private static class SingletonInstance {
		static PropertiesReadUtils instance = new PropertiesReadUtils();
	}

	// 获取PropertiesReadUtils的单例对象
	public static PropertiesReadUtils getInstance() {
		return SingletonInstance.instance;
	}

	/**
	 * 获取微信相关参数
	 * 
	 * @param param 参数的key
	 * @return 参数的value
	 */
	public static String getWechatString(String param) {
		return getInstance().getPropertiesString("/wechat.properties", param);
	}

	
	/**
	 * 获取Peoperties文件的参数的值
	 * 
	 * @param path路径
	 * @param param参数的key
	 * @return 参数的value
	 */
	public String getPropertiesString(String path, String param) {// 应为private
		Properties prop = new Properties();
		InputStream in = this.getClass().getResourceAsStream(path);// path是录播设备的配置文件
		try {
			prop.load(in);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		return prop.getProperty(param);
	}
}
