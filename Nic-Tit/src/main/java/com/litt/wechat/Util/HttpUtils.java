package com.litt.wechat.Util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
/**
 * Http 请求工具类
 * @author Lenovo
 *
 */
@SuppressWarnings("deprecation")
public class HttpUtils {
	
	/**
     * @Description: http post请求json数据
     * @param @param urls
     * @param @param params
     * @param @return
     * @param @throws ClientProtocolException
     * @param @throws IOException
     * @author dapengniao
     * @date 2016年3月10日 下午3:58:15
     */
    public static String sendPostBuffer(String urls, String params)
            throws ClientProtocolException, IOException {
        HttpPost request = new HttpPost(urls);
 
        StringEntity se = new StringEntity(params, HTTP.UTF_8);
        request.setEntity(se);
        // 发送请求
        @SuppressWarnings("resource")
        HttpResponse httpResponse = new DefaultHttpClient().execute(request);
        // 得到应答的字符串，这也是一个 JSON 格式保存的数据
        String retSrc = EntityUtils.toString(httpResponse.getEntity());
        request.releaseConnection();
        return retSrc;
 
    }
	
}
