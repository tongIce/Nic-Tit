package com.litt.wechat.Message.req;

/** 
 * 图片消息 
 *  
 * @author litt 
 * @date 2013-05-19 
 */  
public class ImageMessage extends BaseMessage {  
    // 图片链接  
    private String PicUrl;  
  
    public String getPicUrl() {  
        return PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    }  
}  
