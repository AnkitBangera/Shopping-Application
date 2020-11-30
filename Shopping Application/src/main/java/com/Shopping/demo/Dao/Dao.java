package com.Shopping.demo.Dao;
import java.util.List;

import com.Shopping.demo.Pojo.BaseResponse;
import com.Shopping.demo.Pojo.CartItemDetails;
import com.Shopping.demo.Pojo.DelivaryAddress;
import com.Shopping.demo.Pojo.Login;
import com.Shopping.demo.Pojo.ProductDetails;
import com.Shopping.demo.Pojo.ProductIdList;
import com.Shopping.demo.Pojo.UserDetails;


public interface Dao {
	public BaseResponse employeeExists(UserDetails userDetails);
	
	public UserDetails addUser(UserDetails userDetails);
	
	public List<ProductDetails> getProductDetails(Login login);
	
	public BaseResponse addToCast(ProductIdList productIdList);
	
	public List<CartItemDetails> viewCart(long userId,String cart);
	
	public BaseResponse upatePaymentStatus(ProductIdList productIdList);
	
	public BaseResponse addRating(long productId,long userId,int rating);
	
	public BaseResponse addDelivaryAddress(DelivaryAddress delivaryAddress);
}
