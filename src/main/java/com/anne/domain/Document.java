package com.anne.domain;

import java.util.Date;

public class Document {
	private String[] authors;
	private String barcode;
	private String contents;
	private Date datetime;
	private String ebook_barcode;
	private String isbn;
	private int price;
	private String publisher;
	private int sale_price;
	private String sale_yn;
	private String status;
	private String thumbnail;
	private String title;
	private String[] translators;
	
	public String[] getAuthors() {
		return authors;
	}
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getEbook_barcode() {
		return ebook_barcode;
	}
	public void setEbook_barcode(String ebook_barcode) {
		this.ebook_barcode = ebook_barcode;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getSale_price() {
		return sale_price;
	}
	public void setSale_price(int sale_price) {
		this.sale_price = sale_price;
	}
	public String getSale_yn() {
		return sale_yn;
	}
	public void setSale_yn(String sale_yn) {
		this.sale_yn = sale_yn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getTranslators() {
		return translators;
	}
	public void setTranslators(String[] translators) {
		this.translators = translators;
	}
}