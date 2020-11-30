package com.Shopping.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Shopping.demo.Pojo.BaseResponse;
import com.Shopping.demo.Pojo.CartItemDetails;
import com.Shopping.demo.Pojo.DelivaryAddress;
import com.Shopping.demo.Pojo.Login;
import com.Shopping.demo.Pojo.ProductDetails;
import com.Shopping.demo.Pojo.ProductIdList;
import com.Shopping.demo.Pojo.UserDetails;
import com.Shopping.demo.Service.Service;

@RestController
@RequestMapping("account")
public class AccountController {
	@Autowired
	Service service;
	@Autowired 
	private RestTemplate restTemplate;


	@PostMapping("registration")
	public BaseResponse getRespoString(@RequestBody UserDetails userDetails) {
		HttpEntity<UserDetails> request = new HttpEntity<>(userDetails);
		BaseResponse baseResponse =restTemplate.postForObject("http://shopping-base-responses/account/registration", request, BaseResponse.class);
		return baseResponse;
	}

	@PostMapping("login")
	public List<ProductDetails> getProductListDashBoard(@RequestBody Login login) {
		return service.getProductDetailsDashBoard(login);
	}

	@PostMapping("addToCart")
	public BaseResponse addToCart(@RequestBody ProductIdList productIdList) {
		HttpEntity<ProductIdList> request = new HttpEntity<>(productIdList);
		BaseResponse baseResponse =restTemplate.postForObject("http://shopping-base-responses/account/addToCart", request, BaseResponse.class);
		return baseResponse;

	}

	@GetMapping("viewCart")
	public List<CartItemDetails> viewCart(@RequestParam long userId) {
		String cart="viewCartItem";
		return service.viewCart(userId,cart);
	}
	
	@PostMapping("payment")
	public BaseResponse updatePayment(@RequestBody ProductIdList productIdList) {
		HttpEntity<ProductIdList> request = new HttpEntity<>(productIdList);
		BaseResponse baseResponse =restTemplate.postForObject("http://shopping-base-responses/account/payment", request, BaseResponse.class);
		return baseResponse;

	}
	@GetMapping("viewOrders")
	public List<CartItemDetails> viewOrders(@RequestParam long userId) {
		String cart="viewOrderedItem";
		return service.viewCart(userId,cart);
	}
	
	@GetMapping("addReview")
	public BaseResponse addReview(@RequestParam long userId,@RequestParam long productId,@RequestParam int rating) {
		BaseResponse baseResponse =restTemplate.getForObject("http://shopping-base-responses/account/addReview?userId="+userId+"&productId="+productId+"&rating="+rating, BaseResponse.class);
		return baseResponse;
	}
	
	@PostMapping("addDelivaryAddress")
	public BaseResponse addDelivaryAddress(@RequestBody DelivaryAddress delivaryAddress) {
		HttpEntity<DelivaryAddress> request = new HttpEntity<>(delivaryAddress);
		BaseResponse baseResponse =restTemplate.postForObject("http://shopping-base-responses/account/addDelivaryAddress", request, BaseResponse.class);
		return baseResponse;

	}
	
}
