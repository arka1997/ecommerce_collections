package service;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.ProductCategory;

public class ProductService {
    Product p;
    // Our Global DB
    static List<Product> prd = new ArrayList<>(); 

    public void addNewProduct(int productId, String productName, ProductCategory category,
                                String brand, double price, int quantity){
        prd.add(new Product(productId, productName, category, brand, price, quantity));
    }
    public List<Product> viewAllProducts(){
        return prd;
    }
    public Product findProductById(int id){
        for(Product item : prd){
            if(item.getProductId() == id){
                return item;
            }
        }
        return null;
    }
    public Product findProductByName(String name){
        for(Product item : prd){
            if(item.getProductName().equals(name)){
                return item;
            }
        }
        return null; // Handle NullPointer with Optional classes, if product name is not found
    }
    public Product findProductByCategory(ProductCategory category){
        for(Product item : prd){
            if(item.getCategory().equals(category)){
                return item;
            }
        }
        return null;//Handle NullPointer with Optional classes, if product name is not found
    }

    public int findProductIndexById(int id){
        for(int i = 0 ;i < prd.size(); i++){
            Product item = prd.get(i);
            if(item.getProductId() == id){
                return i;
            }
        }
        return -1;
    }
    public void updatePrice(int index, double price){
        Product item = prd.get(index);
        item.setPrice(price);
    }

    public void updateQuantity(int index, int quantity){
        Product item = prd.get(index);
        item.setQuantity(quantity);
    }
    public void deleteById(int id){
        prd.remove(id);
    }
}



    /** 
     * One enhancement:
     * After fetching from Arraylist DB, we can create 3 hashMaps,
     *  1) with key as Id, and then Product oBJECT
        2) with key as name, and then Product oBJECT
        3) with key as Category, and then Product oBJECT
        Then searching/lookup from order service, or products ervice, etc. will take of O(1) T.C.
    */