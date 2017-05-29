package com.easydevs.purchase.service;

import com.easydevs.purchase.model.Cart;

/**
 * Created by arekotto on 18/01/2017.
 */
public interface CartService {

    /**
     * Create new cart cart.
     *
     * @param userId the user id
     * @param isTemp the is temp
     * @return the cart
     */
    Cart createNewCart(long userId, boolean isTemp);

    /**
     * Gets cart for user.
     *
     * @param userId the user id
     * @param isTemp the is temp
     * @return the cart for user
     */
    Cart getCartForUser(long userId, boolean isTemp);

    /**
     * Update cart for user.
     *
     * @param userId the user id
     * @param cart   the cart
     */
    void updateCartForUser(long userId, Cart cart);
}
