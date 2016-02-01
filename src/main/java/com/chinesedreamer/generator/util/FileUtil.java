package com.chinesedreamer.generator.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	/**
	 * 写入文件
	 * @param content
	 * @param path
	 * @param encoding
	 */
	public static void write2File(String content, String path, String encoding){
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter writer = null;
		try {
			fos = new FileOutputStream(path);
			osw = new OutputStreamWriter(fos, encoding);
			writer = new BufferedWriter(osw);
			writer.write(content);
		} catch (Exception e) {
			logger.error("{}",e);
		}finally{
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException e) {
					logger.error("{}",e);
				}
			}
			
			if (null != osw) {
				try {
					osw.close();
				} catch (IOException e) {
					logger.error("{}",e);
				}
			}
			
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
