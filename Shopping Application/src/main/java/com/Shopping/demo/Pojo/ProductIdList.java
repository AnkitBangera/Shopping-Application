package com.Shopping.demo.Pojo;

import java.util.List;

public class ProductIdList {
	private long userId;
	private List<Long> productIdList;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<Long> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<Long> productIdList) {
		this.productIdList = productIdList;
	}
}
