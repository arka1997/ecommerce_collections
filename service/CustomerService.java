package service;

import java.util.*;
import model.Customer;

public class CustomerService {
    // Use List to understand why sir told to use Map<Integer, Customer>. The time complexitites are O(n). So to optimize it to O(1), we can use HashMap
    List<Customer> cust = new ArrayList<>();
    ProductService serv = new ProductService();
    public void register(int customerId, String name, String email,
                    String mobile, String address, String password){
        cust.add(new Customer(customerId, name, email, mobile, address, password));
    }
    
    public Customer login(String password){
        // Check if password matched, then set isLogeedIn to true. by default we keep it as false during new customer registration
        // T.C: O(n), S.C: O(1)
        for(Customer cos : cust){
            if(cos.getPassword().equals(password)){
                cos.setLoggedIn(true);
                return cos;
            }
        }
        return null;
    }
    public void logout(int customerId){
        // T.C: O(n), S.C: O(1)
        for(Customer cos : cust){
            if(cos.getCustomerId() == customerId){
                cos.setLoggedIn(false);
            }
        }
    }
    public List<Customer> viewCustomerDetails(int customerId){
        return null;
    }
    public Customer findCustomerById(int customerId){
        // T.C: O(n), S.C: O(1)
        for(Customer cos : cust){
            if(cos.getCustomerId() == customerId) {
                return cos;
            }
        }
        return null;
    }
    public int findCustomerIndexById(int customerId){
        // T.C: O(n), S.C: O(1)
        for(int i = 0; i < cust.size(); i++){
            Customer cos = cust.get(i);
            if(cos.getCustomerId() == customerId) {
                return i;
            }
        }
        return 0;
    }
    public void updateAddress(int index, String address){
        // T.C: O(1), S.C: O(1)
        Customer cos = cust.get(index);
        cos.setCustomerId(index); // we use public setter to chnage or modify the restriceted private variable address of Customer Object
    }
    public List<Customer> viewAllCustomers(){
        return cust;
    }
}
