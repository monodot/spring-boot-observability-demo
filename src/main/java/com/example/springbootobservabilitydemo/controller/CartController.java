package com.example.springbootobservabilitydemo.controller;

import com.example.springbootobservabilitydemo.dto.AddToCartResult;
import com.example.springbootobservabilitydemo.model.Cart;
import com.example.springbootobservabilitydemo.model.CartItem;
import com.example.springbootobservabilitydemo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @GetMapping(path = "/api/carts/{id}", produces = "application/json")
    public ResponseEntity<Cart> getCart(@PathVariable("id") Long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        System.out.println();

        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Expects application/x-www-form-urlencoded
    @PostMapping(path = "/api/carts/{id}/add", consumes = "application/x-www-form-urlencoded",
            produces = "application/json",
            params = {"sku", "quantity"})
    public ResponseEntity<AddToCartResult> addItemToCart(@PathVariable("id") Long id,
                                                         @RequestParam("sku") String sku,
                                                         @RequestParam("quantity") int quantity) {
        System.out.println("Adding item to cart " + id + " with sku " + sku + " and quantity " + quantity);

        Cart cart = cartRepository.findById(id).orElse(null);

        if (cart != null) {
            CartItem item = new CartItem();
            item.setSku(sku);
            item.setQuantity(quantity);

            cart.getCartItems().add(item);
            cartRepository.save(cart);

            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
