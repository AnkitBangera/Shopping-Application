package com.Shopping.demo.Dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jongo.Jongo;
import org.springframework.stereotype.Repository;

import com.Shopping.demo.Constants.MongoConstants;
import com.Shopping.demo.Pojo.CartItemDetails;
import com.Shopping.demo.Pojo.Login;
import com.Shopping.demo.Pojo.ProductDetails;
import com.Shopping.demo.Util.CommonUtil;
import com.Shopping.demo.Util.EncryptDecrypt;
import com.Shopping.demo.Util.MongoConnectionUtil;

@Repository
public class DaoImpl implements Dao {

	@Override
	public List<ProductDetails> getProductDetails(Login login) {
		Iterator<ProductDetails> proIterator = null;
		List<ProductDetails> productDetails = null;
		try {
			EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
			String password=encryptDecrypt.encrypt(login.getPassword());
			Login loginDetail = new Jongo(MongoConnectionUtil.getDB()).getCollection(MongoConstants.CN_LOGINCREDENTIAL)
					.findOne("{'emailId':#,'password':#}", login.getEmailId(), password).as(Login.class);
			if (loginDetail != null) {
				proIterator = new Jongo(MongoConnectionUtil.getDB()).getCollection(MongoConstants.CN_PRODUCTDETAILS)
						.find().as(ProductDetails.class);
				if (proIterator != null)
					productDetails = CommonUtil.convertFromIteratorToList(proIterator);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productDetails;
	}


	@Override
	public List<CartItemDetails> viewCart(long userId,String cart) {
		Iterator<CartItemDetails> cartItemDetails = null;
		List<CartItemDetails> cartItemDetailsList=new ArrayList<CartItemDetails>();
		boolean flag=false;
		if(cart.equalsIgnoreCase("viewCartItem"))
		{
			flag=false;
		}
		else if(cart.equalsIgnoreCase("viewOrderedItem")){
			flag=true;
		}
		cartItemDetails=new Jongo(MongoConnectionUtil.getDB()).getCollection(MongoConstants.CN_CARTIREM).find("{paymentStatus:#,userId:#}",flag,userId).as(CartItemDetails.class);
		if(cartItemDetails!=null) {
			cartItemDetailsList=CommonUtil.convertFromIteratorToList(cartItemDetails);
		}
		return cartItemDetailsList;
	}
}
