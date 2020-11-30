package com.Shopping.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Shopping.demo.Pojo.BaseResponse;
import com.Shopping.demo.Pojo.CartItemDetails;
import com.Shopping.demo.Pojo.DelivaryAddress;
import com.Shopping.demo.Pojo.Login;
import com.Shopping.demo.Pojo.ProductDetails;
import com.Shopping.demo.Pojo.ProductIdList;
import com.Shopping.demo.Pojo.UserDetails;
import com.Shopping.demo.Service.Service;
import com.google.gson.Gson;

@RestController
@RequestMapping("account")
public class AccountController {
	@Autowired
	Service service;

	@PostMapping("registration")
	public BaseResponse getRespoString(@RequestBody UserDetails userDetails) {
		return service.addUser(userDetails);
	}

	@PostMapping("login")
	public List<ProductDetails> getProductListDashBoard(@RequestBody Login login) {
		return service.getProductDetailsDashBoard(login);
	}

	@PostMapping("addToCart")
	public BaseResponse addToCart(@RequestBody ProductIdList productIdList) {
		System.out.println(new Gson().toJson(productIdList));
		return service.addToCart(productIdList);

	}

	@GetMapping("viewCart")
	public List<CartItemDetails> viewCart(@RequestParam long userId) {
		String cart="viewCartItem";
		return service.viewCart(userId,cart);
	}
	
	@PostMapping("payment")
	public BaseResponse updatePayment(@RequestBody ProductIdList productIdList) {
		System.out.println(new Gson().toJson(productIdList));
		return service.paymentUpdation(productIdList);

	}
	@GetMapping("viewOrders")
	public List<CartItemDetails> viewOrders(@RequestParam long userId) {
		String cart="viewOrderedItem";
		return service.viewCart(userId,cart);
	}
	
	@GetMapping("addReview")
	public BaseResponse addReview(@RequestParam long userId,@RequestParam long productId,@RequestParam int rating) {
		return service.addReview(productId, userId, rating);
	}
	
	@PostMapping("addDelivaryAddress")
	public BaseResponse addDelivaryAddress(@RequestBody DelivaryAddress delivaryAddress) {
		return service.addDelivaryAddress(delivaryAddress);

	}
	
}
