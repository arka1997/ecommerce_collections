package service;

import java.util.*;
import java.util.stream.Collectors;
import model.Cart;
import model.Product;

public class CartService {

    ProductService servProduct;
    public CartService( ProductService serv){
        this.servProduct = serv;
    }

    //  * S.C -> O(K + N), where k <= N, k is the count of customer which always less N(no. of cart Items)
    // Used HashMap, for optimization and normalization, so that we dont have same redundant customerIds repeated, and customerId based lookups for different customer apis will take O(1)
    Map<Integer, List<Cart>> cart = new HashMap<>();
    List<Cart> guestCart = new ArrayList<>();

    public String addProductToGuestCart(int productId, int cartQuantity){
        Product prd = servProduct.findProductById(productId);
        if(verifyProductQuantity(cartQuantity, prd.getQuantity())){
            double totalPrice = cartQuantity * prd.getPrice();
            guestCart.add(new Cart(prd.getProductId(), prd.getProductName(), prd.getPrice(), cartQuantity, totalPrice));
            return "Cart Item Added Successfully";
        }
        return "Cart Items is more then current Stocks";
    }
    public boolean verifyProductQuantity(int cartQuantity, int availableProductStocks){
        return (cartQuantity <= availableProductStocks);
    }
    public void moveGuestCartToCart(int customerId){
        // Might happen when i deleted cart items, and then trying to place Order
        if(guestCart.isEmpty()){
            System.out.println("Please add Items to Cart");
            return;
        }
        List<Cart> movingCart = cart.computeIfAbsent(customerId, key -> new ArrayList<>());
        movingCart.addAll(guestCart);
        guestCart.clear();
    }
    public List<Cart> viewGuestCart(){
        return guestCart;
    }
    // this will be called, when login is done, and all the items of guest cart will be added to the special map's cart with customerId
    public String addProductToCart(int customerId, int productId, int cartQuantity) {

        Product prd = servProduct.findProductById(productId);
        if(verifyProductQuantity(cartQuantity, prd.getQuantity())){
        // Here we are storing customer Id and There carts. iF customer Id is same, then the cart Item will be mapped to same customer
        /**
         * computeIfAbsent(customerId, key -> new ArrayList<>()) does two things:
         * If customerId doesn't exist yet → creates a new ArrayList and stores it under that key.
         * If customerId already exists → returns the existing list (no new key, no overwrite).
         * Then .add(...) appends to that list.
         */
        List<Cart> compute = cart.computeIfAbsent(customerId, key -> new ArrayList<>());
        double totalPrice = cartQuantity * prd.getPrice();
        compute.add(new Cart(prd.getProductId(), prd.getProductName(), prd.getPrice(), cartQuantity, totalPrice));
        return "Cart Item Added Successfully";
        }
        return "Cart Items is more then current Stocks";
    }
    public List<Cart> viewCart(int customerId){
        if (customerId == -1){
            return guestCart;
        } else {
            return cart.get(customerId);
        }
    }
    public List<Cart> removeCart(int customerId, int productId){
        // T.C: O(n), S.C: O(n)
        List<Cart> cartList;
        if (customerId == -1){
            cartList = guestCart;
        } else {
            cartList = cart.get(customerId);
        }
        // Used iterator for removal during iteration, as normal loops with give concurrentModificationException, 
        Iterator<Cart> itr = cartList.iterator();
        while(itr.hasNext()){
            if(itr.next().getProductId() == productId){
                itr.remove();
            }
        }
        return cartList;
    }
    public List<Cart> increaseCartQuantity(int customerId, int productId){
        // After updating, should increase the totalPrice
        // T.C: O(n), S.C: O(n)
        List<Cart> cartList;
        if (customerId == -1){
            cartList = guestCart;
        } else {
            cartList = cart.get(customerId);
        }
        for(Cart c : cartList){
            if(c.getProductId() == productId) {
                c.setQuantity(c.getQuantity() + 1);// On increasing cart item, its always increased by + 1
                c.setTotalPrice(c.getQuantity() * c.getPrice()); // New TotalPrice for the new Quantity
            }
        }
        return cartList;
    }
    public List<Cart> decreaseCartQuantity(int customerId, int productId){
        // After updating, should decrease the totalPrice
        // T.C: O(n), S.C: O(n)
        List<Cart> cartList;
        if (customerId == -1){
            cartList = guestCart;
        } else {
            cartList = cart.get(customerId);
        }
        for(Cart c : cartList){
            if(c.getProductId() == productId) {
                if(c.getQuantity() > 1){
                    c.setQuantity(c.getQuantity() - 1);// On increasing cart item, its always increased by + 1
                    c.setTotalPrice(c.getQuantity() * c.getPrice()); // New TotalPrice for the new Quantity
                }else{
                    System.out.println("[ALERT:]: Only 1 quantity is remaining for your Item IN cART, can't decrease. Directly DELETE Item from cart");
                }
            }
        }
        return cartList;
    }

    public double totalCartValue(int customerId){
        // T.C: O(n), S.C: O(n)
        List<Cart> cartList;
        if (customerId == -1){
            cartList = guestCart;
        } else {
            cartList = cart.get(customerId);
        }
        Optional<Double> opt =  cartList.stream().map(e -> e.getTotalPrice()).reduce((a,b) -> a + b);
        if(opt.isPresent()){
            return opt.get();
        }
        System.out.println("No Items Present for showing cart value");
        return 0.0;
    }
    public boolean reduceStockBeforeOrder(int customerId){
        // T.C: O(n), S.C: O(n) for Map
        // First i stored the respective productIds, the item count in cart for a specific Customer
        Map<Integer, Integer> orderedProductIds = cart.get(customerId).stream()
                                                                      .collect(Collectors.toMap(
                                                                                                Cart::getProductId,
                                                                                                Cart::getQuantity,
                                                                                                Integer::sum
                                                                                            ));
        return servProduct.reduceQuantityinInventoryAfterOrder(orderedProductIds);
    }
    public void clearCart(int customerId){
        cart.remove(customerId);
    }
}