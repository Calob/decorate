package com.dec.common.config;

import java.util.HashMap;
import java.util.Map;

import com.dec.common.utils.PropertiesLoader;

public class Global {
	/**
	 * ����ȫ������ֵ
	 */
	private static Map<String, String> map = new HashMap<String, String>();

	/**
	 * �����ļ����ض���
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader(
			"application.properties");

	/**
	 * ��ȡ����
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = propertiesLoader.getProperty(key);
			map.put(key, value);
		}
		return value;
	}
}
