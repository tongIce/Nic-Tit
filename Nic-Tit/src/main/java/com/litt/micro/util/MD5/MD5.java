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
import org.jsoup.helper.StringUtil;

import com.alibaba.fastjson.JSONObject;
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
	public static String checkSignature(String sch_id, String stud_no, String stud_name,String app_key) {

		Map<String, String> map = new HashMap<String, String>();
		// String[] arr = new String[] {sch_id,stud_name,stud_no};
		map.put("sch_id", sch_id);
		map.put("stud_name", stud_name);
		map.put("stud_no", stud_no);
		Map<String, String> resultMap = sortMapByKey(map);
		// 将四个属性参数进行字典序排序
		// Arrays.sort(arr);

		String content = "";
		
		// for (int i = 0; i < arr.length; i++) { if(i==arr.length-1){
		 //content=content+arr[i]; }else{ content=content+arr[i]+""; } }
		 
		int i=0;
		for (Map.Entry<String, String> entry : resultMap.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
			content = content + entry.getValue();
			if(i<(resultMap.size()-1))
				content = content +"";
			i++;
		}
		String tmpStr = "";
		//tmpStr = MD5Password("我要加密","UTF-8");
		tmpStr = MD5Password(MD5Password(content,"UTF-8").toLowerCase()+""+ app_key ,"UTF-8").toLowerCase();
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


