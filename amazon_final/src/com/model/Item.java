package com.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "item")
@Table(name = "item")
public class Item {
	
	@Id()
	private String itemID;
	private String shopID;
	private String itemDescription;
	private String unit;
	private int price;
	private String imageURL;
	
	public String getItemID() {
		return itemID;
	}
	
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
	public String getShopID() {
		return shopID;
	}
	
	public void setShopID(String shopID) {
		this.shopID = shopID;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getImageURL() {
		return imageURL;
	}
	
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	@Override
	public String toString() {
		return "ItemDTO [itemID = " + this.itemID + ", shopID = " + this.shopID + ", itemDescription = " + this.itemDescription + ", unit = " + this.unit + ", price = " + this.price + "]";
	}
	
}
