package com.Shopping.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Shopping.demo.Dao.Dao;
import com.Shopping.demo.Pojo.CartItemDetails;
import com.Shopping.demo.Pojo.Login;
import com.Shopping.demo.Pojo.ProductDetails;

@org.springframework.stereotype.Service
public class Service {
	@Autowired
	Dao dao;
	public List<ProductDetails> getProductDetailsDashBoard(Login login) {
		return dao.getProductDetails(login);
	}

	public List<CartItemDetails> viewCart(long userId,String cart) {
		return dao.viewCart(userId,cart);
	}

}
