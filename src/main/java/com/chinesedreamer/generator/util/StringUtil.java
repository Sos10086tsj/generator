package com.chinesedreamer.generator.util;

import org.springframework.util.StringUtils;

public class StringUtil {
	/**
	 * 删除末尾的字符
	 * @param str
	 * @return
	 */
	public static String removeLastCharacter(String str,String ch) {
		if (!StringUtils.isEmpty(str) && str.endsWith(ch)) {
			return str.substring(0, str.length() - ch.length());
		}
		return str;
	}
}
