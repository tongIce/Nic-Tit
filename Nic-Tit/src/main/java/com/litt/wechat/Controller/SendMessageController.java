package com.litt.wechat.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.wechat.Util.HttpUtils;
import com.litt.wechat.Util.Token.WeixinUtil;

import net.sf.json.JSONObject;

import com.litt.nic.entity.Article;
import com.litt.nic.service.IArticleService;

@Controller
@RequestMapping(value = "/send")
public class SendMessageController {
	
	@Autowired
	private IArticleService articleService;
	/**
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public String upload_persistent_news() throws ParseException, IOException{  
		
		int maxID=articleService.findMaxId();
		Article articlePojo=articleService.findById(maxID);
		String pictureNmae= articlePojo.getThumbMediaId();
		String path = System.getProperty("catalina.home") + "/webapps" + "/download/"+"downloadFromWeixin/";
		File file=new File(path+pictureNmae);
		
		
		String media_id=getArticleId(file,articlePojo );
		return media_id;
/*		//获取微信服务器图片id
		String id= WeixinUtil.upload(file);
		List<Article> articles = new ArrayList<Article>(); 
		Article articleMess = new Article();  
		articleMess.setTitle(articlePojo.getTitle());  
		articleMess.setThumb_media_id(id);  
		articleMess.setShow_cover_pic(1);  
		articleMess.setDigest(articlePojo.getDigest());  
		articleMess.setContent_source_url(articlePojo.getContentSourceUrl());  
		articleMess.setContent(articlePojo.getContent());  
		articleMess.setAuthor(articlePojo.getAuthor());  
        articles.add(articleMess);  
        List<Article> list = new ArrayList<Article>();  
        list.add(articleMess);*/
		
		
		//这里用的临时的，需要从数据库中获取
/*		String pictureNmae="150210970713612799761-1350-47fc-9988-b6d7e1ba9968.jpg";
		String path = System.getProperty("catalina.home") + "/webapps" + "/download/";
		File file=new File(path+pictureNmae);
		//获取微信服务器图片id
		String id= WeixinUtil.upload(file);
		
		List<Article> articles = new ArrayList<Article>();  
        
        Article article = new Article();  
        article.setTitle("测试上传");  
        article.setThumb_media_id(id);  
        article.setShow_cover_pic(1);  
        article.setDigest("摘要");  
        article.setContent_source_url("https://www.baidu.com");  
        article.setContent("我看到一个东西哈哈！");  
        article.setAuthor("zhangxf");  
        articles.add(article);  
        List<Article> list = new ArrayList<Article>();  
        list.add(article);  */
        
        
        
      /*  String accessToken = WeixinUtil.getAccessToken().getAccessToken();  
        String url_upload = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", accessToken);  
        Map<String, Object> params = new HashMap<String, Object>();  
        params.put("articles", list);  
          
        String responsetext = null;  
        try {  
        	
        	responsetext = HttpUtils.sendPostBuffer(url_upload, JSONObject.fromObject(params).toString());
            System.out.println(responsetext);
            JSONObject jsonObject=JSONObject.fromObject(responsetext);
            String media_id=jsonObject.getString("media_id");
            System.out.println("media_id="+media_id);
            return media_id;
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;*/
    }  
	/**
	 * 群发图文消息
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/message")
	public  String sendNewsToAll(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException{  
		//图文消息id
		String media_id= upload_persistent_news();
		
		String jsonStr="{\"filter\":{\"is_to_all\":"+true+",\"tag_id\":"+0+"},\"mpnews\":{\"media_id\":\""+media_id+"\"},\"msgtype\":\""+"mpnews"+"\",\"send_ignore_reprint\":"+0+"}"; 
 		System.out.println(jsonStr);
		String accessToken = WeixinUtil.getAccessToken().getAccessToken();
		String url="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", accessToken);  
		
		String jsontext = HttpUtils.sendPostBuffer(url, jsonStr);  
		
		JSONObject jsonObject=JSONObject.fromObject(jsontext);
		if (jsonObject.containsKey("errcode")) { 
			System.out.println("json"+jsonObject.toString());
			return jsonObject.toString();  
		}  
		System.out.println("分组群发消息失败 errcode:{" + jsonObject.getInt("errcode")+"} errmsg:{"+jsonObject.getString("errmsg")+"} ");  
        return null;
    }
	/**
	 * 获取图文消息id
	 * @param file 图片路径
	 * @param articlePojo 图文类
	 */
	public static String getArticleId(File file,Article articlePojo) throws ParseException, IOException{
		//获取微信服务器图片id
		String id= WeixinUtil.upload(file);
		List<Article> articles = new ArrayList<Article>(); 
		Article articleMess = new Article();  
		articleMess.setTitle(articlePojo.getTitle());  
		articleMess.setThumbMediaId(id);  
		articleMess.setShowCoverPic(1);  
		articleMess.setDigest(articlePojo.getDigest());  
		articleMess.setContentSourceUrl(articlePojo.getContentSourceUrl());  
		articleMess.setContent(articlePojo.getContent());  
		articleMess.setAuthor(articlePojo.getAuthor());  
        articles.add(articleMess);  
        List<Article> list = new ArrayList<Article>();  
        list.add(articleMess);
        
        String accessToken = WeixinUtil.getAccessToken().getAccessToken();  
        String url_upload = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", accessToken);  
        Map<String, Object> params = new HashMap<String, Object>();  
        params.put("articles", list);  
          
        String responsetext = null;  
        try {  
        	
        	responsetext = HttpUtils.sendPostBuffer(url_upload, JSONObject.fromObject(params).toString());
            System.out.println(responsetext);
            JSONObject jsonObject=JSONObject.fromObject(responsetext);
            String media_id=jsonObject.getString("media_id");
            System.out.println("media_id="+media_id);
            return media_id;
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;
        
	}
}
