package com.easydevs.purchase.service;

import com.easydevs.purchase.model.Cart;

/**
 * Created by arekotto on 18/01/2017.
 */
public interface CartService {

    Cart createNewCart(long userId, boolean isTemp);

    Cart getCartForUser(long userId, boolean isTemp);

    void updateCartForUser(long userId, Cart cart, boolean isTemp);
}
