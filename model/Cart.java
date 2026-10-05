package model;

import java.util.Objects;

public class Cart {

    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private double totalPrice;

    public Cart(int productId, 
                String productName, 
                double price, 
                int quantity, 
                double totalPrice) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Cart [productId=" + productId + ", productName=" + productName + ", price=" + price + ", quantity="
                + quantity + ", totalPrice=" + totalPrice + "]";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.productId;
        hash = 53 * hash + Objects.hashCode(this.productName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cart other = (Cart) obj;
        if (this.productId != other.productId) {
            return false;
        }
        return Objects.equals(this.productName, other.productName);
    }

    
}
