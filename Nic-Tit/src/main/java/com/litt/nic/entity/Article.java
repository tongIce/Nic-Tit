package com.litt.nic.entity;

public class Article {
	private Integer id;

	private String thumbMediaId;

	private String author;

	private String title;

	private String contentSourceUrl;

	private String content;

	private String digest;

	private String picurl;// 新增的图片存放的地址

	private Integer showCoverPic;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId == null ? null : thumbMediaId.trim();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getContentSourceUrl() {
		return contentSourceUrl;
	}

	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl == null ? null
				: contentSourceUrl.trim();
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest == null ? null : digest.trim();
	}

	public Integer getShowCoverPic() {
		return showCoverPic;
	}

	public void setShowCoverPic(Integer showCoverPic) {
		this.showCoverPic = showCoverPic;
	}

	public Article(Integer id, String thumbMediaId, String author,
			String title, String contentSourceUrl, String content,
			String digest, Integer showCoverPic) {
		super();
		this.id = id;
		this.thumbMediaId = thumbMediaId;
		this.author = author;
		this.title = title;
		this.contentSourceUrl = contentSourceUrl;
		this.content = content;
		this.digest = digest;
		this.showCoverPic = showCoverPic;
	}

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

}