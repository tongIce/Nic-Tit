package com.litt.wechat.Message.resp;

/**
 * 图文model
 * 
 * @author litt
 * @date 2013-05-19
 */
public class Article {
	// // 图文消息名称
	// private String Title;
	// // 图文消息描述
	// private String Description;
	// // 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	private String PicUrl;
	// // 点击图文消息跳转链接
	private String Url;

	private String Thumb_media_id;
	private String Author;
	private String Title;
	private String Content_source_url;
	private String Content;
	private String Digest;
	private int Show_cover_pic;

	public String getThumb_media_id() {
		return Thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		Thumb_media_id = thumb_media_id;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContent_source_url() {
		return Content_source_url;
	}

	public void setContent_source_url(String content_source_url) {
		Content_source_url = content_source_url;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getDigest() {
		return Digest;
	}

	public void setDigest(String digest) {
		Digest = digest;
	}

	public int getShow_cover_pic() {
		return Show_cover_pic;
	}

	public void setShow_cover_pic(int show_cover_pic) {
		Show_cover_pic = show_cover_pic;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}