package com.litt.micro.util;

import java.io.UnsupportedEncodingException;
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
		sign=sign.replace(" ","");
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
	 * 课表签名认证
	 * @param card_number
	 * @param app_key
	 * @param timestamp
	 * @param nonce_str
	 * @param sign
	 * @return
	 */
	public static boolean KBcheckSignature(String card_number, String app_key,
			String timestamp,String nonce_str,String sign){
		sign=sign.replace(" ","");
		String key = "C4960C80E48676D26FDB57AA684E9070";
		String[] arr = new String[] {  "card_number="+card_number,"app_key="+app_key,"timestamp="+timestamp, "nonce_str="+nonce_str };
		// 将四个属性参数进行字典序排序
		Arrays.sort(arr);
		String content = "";
		for (int i = 0; i < arr.length; i++) {
			if(i==arr.length-1){
				content=content+arr[i];
			}else{
				content=content+arr[i]+"&";
			}
		}
		content=content+"&key="+key;
		System.out.println(content);
		MessageDigest md = null;
		String tmpStr = new String();
		try {
			md = MessageDigest.getInstance("MD5");
			// 将5个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		content = "";
		//！--直接进行比较大的话sign长度是后面多了空字符，先去除在比较
		//sign = sign.substring(0,tmpStr.length());
		// 将MD5加密后的字符串可与sign对比，标识该请求来源于腾讯微校
		//System.out.println("tmpStr:"+tmpStr+"  length:"+tmpStr.length());
		//System.out.println("sign:"+sign+"  length:"+sign.length());
		//System.out.println(sign);
		//System.out.println(tmpStr.equals(sign));
		sign  = sign.trim();
		return tmpStr != null ? tmpStr.equals(sign.toUpperCase()) : false;
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
