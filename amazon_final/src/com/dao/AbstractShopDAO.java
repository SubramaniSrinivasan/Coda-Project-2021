package com.dao;

import java.util.List;

import com.model.Item;
import com.model.Shop;

public abstract class AbstractShopDAO {

	public abstract Shop getShopByShopId(String shopId);
	public abstract Item getItemByItemId(String itemId);
	public abstract List<Item> getAllItemOfShop(String shopId);
	
}
