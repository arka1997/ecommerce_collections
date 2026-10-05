package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID orderId;
    private int customerId;
    private List<Cart> items;
    private double totalAmount;
    private OrderStatusEnum orderStatus;
    private LocalDateTime orderDate;

    public Order(UUID orderId, int customerId, List<Cart> items, double totalAmount, OrderStatusEnum orderStatus, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Cart> getItems() {
        return items;
    }

    public void setItems(List<Cart> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", customerId=").append(customerId);
        sb.append(", items=").append(items);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", orderDate=").append(orderDate);
        sb.append('}');
        return sb.toString();
    }


}
