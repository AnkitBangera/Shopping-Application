package com.Shopping.demo.Dao;
import java.util.List;

import com.Shopping.demo.Pojo.CartItemDetails;
import com.Shopping.demo.Pojo.Login;
import com.Shopping.demo.Pojo.ProductDetails;


public interface Dao {
	
	public List<ProductDetails> getProductDetails(Login login);
	
	public List<CartItemDetails> viewCart(long userId,String cart);
	
}
