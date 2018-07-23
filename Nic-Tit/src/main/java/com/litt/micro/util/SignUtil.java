package com.litt.micro.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/**
 * ClassName: SignUtil
 * @Description: 请求校验工具类
 */
public class SignUtil {
	/**
	 * 验证签名
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String state, String app_key,
			String timestamp,String nonce_str,String sign) {
		String key = "C4960C80E48676D26FDB57AA684E9070";
		String[] arr = new String[] {  "state="+state,"app_key="+app_key,"timestamp="+timestamp, "nonce_str="+nonce_str };
		// 将四个属性参数进行字典序排序
		Arrays.sort(arr);
		//StringBuilder content = new StringBuilder();
		String content = "";
		for (int i = 0; i < arr.length; i++) {
			if(i==arr.length-1){
				content=content+arr[i];
			}else{
				content=content+arr[i]+"&";
			}
		}
		content=content+"&key="+key;
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("MD5");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		content = "";
		// 将MD5加密后的字符串可与sign对比，标识该请求来源于腾讯微校
		return tmpStr != null ? tmpStr.toUpperCase().equals(sign) : false;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * @param byteArray
	 * @return
	 */
	public static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
}
