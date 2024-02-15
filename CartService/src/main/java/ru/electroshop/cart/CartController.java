package ru.electroshop.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;
    private CartItemRepository cartItemRepository;

    @GetMapping("/{cartId}")
    public List<CartItem> getCartItems(@PathVariable Long cartId) {
        return cartService.findAllCartItemsByCartId(cartId);
    }

    @PostMapping("/{cartId}/products/{productId}")
    public CartItem addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) {
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId);
        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            return cartItemRepository.save(cartItem);
        }
        CartItem cartItem = new CartItem();
        cartItem.setId(cartId);
        cartItem.getProductId();
        cartItem.setQuantity(1);
        return cartItemRepository.save(cartItem);
    }

    @PutMapping("/{cartId}/products/{productId}")
    public CartItem updateProductQuantityInCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestBody Integer quantity) {
        CartItem cartItem = cartService.findCartItemById(productId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public void removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartItemRepository.deleteByCartIdAndProductId(cartId, productId);
    }

    @PostMapping("/{cartId}/orders")
    public void placeOrder(@PathVariable Long cartId) {
        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cartId);
    }
}
