package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.Product;

public class CartService {

    Map<Integer, List<Cart>> cart = new HashMap<>();
    ProductService serv = new ProductService();

    public void addProductToCart(int customerId, int productId) {
        Product product = serv.findProductById(productId);
        if (product == null) {
            return;
        }

        cart.computeIfAbsent(customerId, key -> new ArrayList<>())
            .add(new Cart(product.getProductId(), product.getProductName(), product.getPrice(), 1, product.getPrice()));
    }
}
