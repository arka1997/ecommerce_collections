package service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import model.*;

public class OrderService {
     ProductService servProduct;
     CartService cart;
    public OrderService( ProductService serv, CartService cart){
        this.servProduct = serv;
        this.cart = cart;
    }
    // Can we Use Map<CustomerId, List<Order>> for customer to viewOrders and other lookups in O(1)
    private final List<Order> order = new ArrayList<>();

    // 1. Optimize lookup by orderId to O(1)
    private final Map<UUID, Order> order2 = new HashMap<>();
    
    // 2. Optimize lookup by customerId to O(1)
    private final Map<Integer, List<Order>> customerOrdersMap = new HashMap<>();

    public void placeOrder(int customerId){
        /**
        1) Check for each cart Items, if quantity > orderItems count before placing Order
        2) Decrease the quantity from Product Service
        3) Clearing the cart, and customer Id, from Map
        // T.C: O(n) Due to calculation of totalCartValue, S.C: O(n)
        new ArrayList<>(viewCart) because, it should be a new snapshot of the cart items. Not the old stored cart reference
        */
        List<Cart> viewCart = cart.viewCart(customerId);
        if(viewCart == null){
            System.out.println("Add items to Cart first");
        } else {
            UUID uniqueOrderId = UUID.randomUUID();
            boolean isStockAvailable = cart.reduceStockBeforeOrder(customerId);
            if(isStockAvailable){
                System.out.println("Order Placed Successfully.......");
                System.out.println("Clearing Cart iTEMS.......");
                double totalAmount = cart.totalCartValue(customerId);
                Order newOrder = new Order(uniqueOrderId, customerId, new ArrayList<>(viewCart), totalAmount, OrderStatusEnum.PLACED, LocalDateTime.now(ZoneId.systemDefault()));
                order.add(newOrder);
                /** Clearing cart */
                cart.clearCart(customerId);
                /** Optimization with HashMap */
                order2.put(uniqueOrderId, newOrder);
                customerOrdersMap.computeIfAbsent(customerId, key -> new ArrayList<>()).add(newOrder);
            }else{
                System.out.println("Order could not be Placed, Sorry ");
            }
    }
    }
    public List<Order> viewMyOrders(int customerId){
        // T.C: O(n), S.C: O(n), with HashMap its 1 line
        List<Order> list = new ArrayList<>();
        for(Order ord : order){
            if(ord.getCustomerId() == customerId){
                list.add(ord);
            }
        }
        return list;
    }

    /**
     * Immediately change customerId -> orderId
     * @param customerId
     */
    public void cancelOrder(UUID orderId){
        System.out.println(orderId);
        // Making Order Status null
        // Again increasing the inventory stock counts of the productItems against the Order
        // T.C: O(n*n*n), S.C: O(n) ?? ALERT reduce it with HashMap to search Order List in O(1)
        for(Order ord : order){
            if(ord.getOrderId().equals(orderId)){
                List<Cart> list = ord.getItems();
                servProduct.increaseQuantityinInventoryAfterCancelOrder(list);
                ord.setOrderStatus(OrderStatusEnum.CANCELLED);
            }
        }
    }
}
