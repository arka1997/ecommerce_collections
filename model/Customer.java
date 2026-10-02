package model;

public class Customer {

    private int customerId;
    private String name;
    private String email;
    private String mobile;
    private String address;
    private String password;
    private boolean isLoggedIn;

    // Constructor
    public Customer(int customerId, String name, String email,
                    String mobile, String address, String password) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.password = password;
        this.isLoggedIn = false; // Default state upon creation
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}