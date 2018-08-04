package com.litt.micro.util.MD5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class MD5{

	/**
     * 
    * @Title: MD5 
    * @Description: 根据不同编码进行MD5转换
    * @param @param s
    * @param @param encodingType
    * @param @return
    * @return String
    * @author tanglei
    * @throws
     */
    public final static String MD5Password(String s,String encodingType) {
    	System.out.println("加密前的字符串:"+s);
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        try {
            // 按照相应编码格式获取byte[]
            byte[] btInput = s.getBytes(encodingType);
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式

            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            System.out.println("加密后的字符串:"+str);
            return new String(str);
        } catch (Exception e) {
            return "-1";
        }
    }

    
    
	/**
	 * 验证签名
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static String checkSignature(String sch_id, String stud_name, String stud_no) {
		String app_key = "tygyxysdhfj3343##$$";
		Map<String, String> map = new HashMap<String, String>();
		// String[] arr = new String[] {sch_id,stud_name,stud_no};
		map.put("sch_id", sch_id);
		map.put("stud_name", stud_name);
		map.put("stud_no", stud_no);
		Map<String, String> resultMap = sortMapByKey(map);
		// 将四个属性参数进行字典序排序
		// Arrays.sort(arr);

		String content = "";
		/*
		 * for (int i = 0; i < arr.length; i++) { if(i==arr.length-1){
		 * content=content+arr[i]; }else{ content=content+arr[i]+""; } }
		 */

		for (Map.Entry<String, String> entry : resultMap.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
			content = content + entry.getValue();
		}
		String tmpStr = "";
		System.out.println("加密前字符串:" + content);
		tmpStr = MD5Password(MD5Password(content,"GBK") + app_key,"GBK");
		return tmpStr;
	}


	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}
	
	

	

}





// 比较器类
class MapKeyComparator implements Comparator<String> {
	public int compare(String str1, String str2) {
		return str1.compareTo(str2);
	}
}


/*public class MD5 {
	
	
		*//**
		 * MD5加密
		 * @param signature
		 * @param timestamp
		 * @param nonce
		 * @return
		 *//*
		public static String checkSignature(String sch_id, String app_key,
				String stud_name,String stud_no) {
			String key = "C4960C80E48676D26FDB57AA684E9070";
			String[] arr = new String[] {  "sch_id="+sch_id,"app_key="+app_key,"stud_name="+stud_name, "stud_no="+stud_no };
			
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
			return tmpStr ;
		}

		*//**
		 * 将字节数组转换为十六进制字符串
		 * @param byteArray
		 * @return
		 *//*
		public static String byteToStr(byte[] byteArray) {
			String strDigest = "";
			for (int i = 0; i < byteArray.length; i++) {
				strDigest += byteToHexStr(byteArray[i]);
			}
			return strDigest;
		}

		*//**
		 * 将字节转换为十六进制字符串
		 * @param mByte
		 * @return
		 *//*
		private static String byteToHexStr(byte mByte) {
			char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
					'B', 'C', 'D', 'E', 'F' };
			char[] tempArr = new char[2];
			tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
			tempArr[1] = Digit[mByte & 0X0F];
			String s = new String(tempArr);
			return s;
		}
	}*/

