package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ShopDAO;
import com.model.Item;
import com.model.Shop;

@Service("shoppingService")
@Transactional
public class ShoppingService implements ShoppingServiceInterface {
	
	@Autowired
	private ShopDAO shopDAO;

	public ShopDAO getShopDAO() {
		return shopDAO;
	}

	public void setShopDAO(ShopDAO shopDAO) {
		this.shopDAO = shopDAO;
	}

	@Override
	public List<Item> getAllItemOfShop(String shopId) {
		
		return this.shopDAO.getAllItemOfShop(shopId);
		
	}
	
	@Override
	public Shop getShopByShopId(String shopId) {
		
		return this.shopDAO.getShopByShopId(shopId);
		
	}
	
	@Override
	public Item getItemByItemId(String itemId) {
		
		return this.shopDAO.getItemByItemId(itemId);
		
	}
	
}
