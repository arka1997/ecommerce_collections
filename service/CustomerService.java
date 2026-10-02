package service;

import java.util.*;
import model.Customer;
import model.Product;

public class CustomerService {
    List<Customer> cust = new ArrayList<>();
    ProductService serv = new ProductService();
    public void register(int customerId, String name, String email,
                    String mobile, String address, String password){
        cust.add(new Customer(customerId, name, email, mobile, address, password));
    }
    public boolean login(String password){
        // Check if password matched, then set isLogeedIn to true. by default we keep it as false during new customer registration
        return true;
    }
    // sHOW PROducts to customers
    public List<Product> viewAllProducts(){
        return serv.viewAllProducts();
    }
    public List<Customer> viewCustomerDetails(int id){
        return null;
    }
    public Customer findCustomerById(int id){
        for(Customer cos : cust){
            if(cos.getCustomerId() == id) {
                return cos;
            }
        }
        return null;
    }
    public int findCustomerIndexById(int id){
        for(int i = 0; i < cust.size(); i++){
            Customer cos = cust.get(i);
            if(cos.getCustomerId() == id) {
                return i;
            }
        }
        return 0;
    }
    public void updateAddress(int index, String address){
        Customer cos = cust.get(index);
        cos.setCustomerId(index); // we use public setter to chnage or modify the restriceted private variable address of Customer Object
    }
    public List<Customer> viewAllCustomers(){
        return cust;
    }
}
