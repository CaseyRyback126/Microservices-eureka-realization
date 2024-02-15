package ru.electroshop.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public Optional<CartItem> findCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    public List<CartItem> findAllCartItemsByCartId(Long cartId) {
        return cartItemRepository.findAllByCartId(cartId);
    }

    public Optional<CartItem> findCartItemByCartIdAndProductId(Long cartId, Long productId) {
        return cartItemRepository.findByCartIdAndProductId(cartId, productId);
    }

    public void addProductToCart(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public void updateCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public void removeProductFromCart(Long cartId, Long productId) {
        cartItemRepository.deleteByCartIdAndProductId(cartId, productId);
    }

    public void clearCart(Long cartId) {
        List<CartItem> cartItems = findAllCartItemsByCartId(cartId);
        cartItemRepository.deleteAll(cartItems);
    }
}
