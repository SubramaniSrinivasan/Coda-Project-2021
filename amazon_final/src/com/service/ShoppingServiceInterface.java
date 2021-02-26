package com.service;

import java.util.List;

import com.model.Item;
import com.model.Shop;

public interface ShoppingServiceInterface {

	public List<Item> getAllItemOfShop(String shopId);
	public Shop getShopByShopId(String shopId);
	public Item getItemByItemId(String itemId);
	
}
