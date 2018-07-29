package com.litt.micro.util.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5 {

	/**
     * 生成32位md5码
     * @param password
     * @return
     */
	
    /*public static String md5Password(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }*/
	
	
	
	
	/**
	 * 验证签名
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static String checkSignature(String sch_id, String stud_name,String stud_no) {
		String key = "tygyxysdhfj3343##$$";
		String[] arr = new String[] {"sch_id="+sch_id,"stud_name="+stud_name,"stud_no="+stud_no};
		// 将四个属性参数进行字典序排序
		Arrays.sort(arr);
		//StringBuilder content = new StringBuilder();
		String content ="";
		for (int i = 0; i < arr.length; i++) {
			if(i==arr.length-1){
				content=content+arr[i];
			}else{
				content=content+arr[i]+"";
			}
		}
		content=content+key;
		MessageDigest md = null;
		String tmpStr = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
			System.out.println("第一次加密:"+tmpStr);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		content ="";
		content=content+tmpStr;
		
		try {
			md = MessageDigest.getInstance("MD5");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(tmpStr.toString().getBytes());
			tmpStr = byteToStr(digest);
			System.out.println("第二次加密:"+tmpStr);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将MD5加密后的字符串可与sign对比，标识该请求来源于腾讯微校
		
		return tmpStr;
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
