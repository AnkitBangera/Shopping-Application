package com.Shopping.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Shopping.demo.Dao.Dao;
import com.Shopping.demo.Pojo.BaseResponse;
import com.Shopping.demo.Pojo.CartItemDetails;
import com.Shopping.demo.Pojo.DelivaryAddress;
import com.Shopping.demo.Pojo.Login;
import com.Shopping.demo.Pojo.ProductDetails;
import com.Shopping.demo.Pojo.ProductIdList;
import com.Shopping.demo.Pojo.UserDetails;
import com.Shopping.demo.Util.EmailUtil;

@org.springframework.stereotype.Service
public class Service {
	@Autowired
	Dao dao;
	@Autowired
	EmailUtil email;

	public BaseResponse addUser(UserDetails userDetails) {
		BaseResponse baseResponse = dao.employeeExists(userDetails);
		if (baseResponse.getStatusCode() == 200) {
			UserDetails details = dao.addUser(userDetails);
			if (details != null) {
				try {
					email.sendmail(userDetails.getEmailId().substring(0, 3) + 123, userDetails.getEmailId(),
							userDetails.getFirstName() + " " + userDetails.getLastName(), "");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return baseResponse;
			}
		}
		return baseResponse;
	}

	public List<ProductDetails> getProductDetailsDashBoard(Login login) {
		return dao.getProductDetails(login);
	}

	public BaseResponse addToCart(ProductIdList productIdList) {
		return dao.addToCast(productIdList);
	}

	public List<CartItemDetails> viewCart(long userId,String cart) {
		return dao.viewCart(userId,cart);
	}

	public BaseResponse paymentUpdation(ProductIdList productIdList) {
		return dao.upatePaymentStatus(productIdList);
	}
	
	public BaseResponse addReview(long productId,long userId, int rating) {
		return dao.addRating(productId, userId, rating);
	}
	public BaseResponse addDelivaryAddress(DelivaryAddress delivaryAddress) {
		return dao.addDelivaryAddress(delivaryAddress);
	}
}
