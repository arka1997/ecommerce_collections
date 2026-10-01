package service;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.ProductCategory;

public class ProductService {

    // Our Global DB
    static List<Product> prd = new ArrayList<>(); 
    //  
    /** 
     * One enhancement:
     * After fetching from Arraylist DB, we can create 3 hashMaps,
     *  1) with key as Id, and then Product oBJECT
        2) with key as name, and then Product oBJECT
        3) with key as Category, and then Product oBJECT
        Then searching/lookup from order service, or products ervice, etc. will take of O(1) T.C.
    */

    // if we create and store product details
    public void addNewProduct(int productId, String productName, ProductCategory category,
                                String brand, double price, int quantity){
        prd.add(new Product(productId, productName, category, brand, price, quantity));
        System.out.println("Products" + prd);
    }
    public List<Product> viewAllProducts(){
        System.out.println(prd);
        return prd;
    }
    public Product findProductById(int id){

        return prd.get(id);
    }
    public Product findProductByName(int id, String name){
        for(int i = 0; i < prd.size(); i++){
            Product traverseList = prd.get(i);
            if(traverseList.getProductName().equals(name)){
                return prd.get(i);
            }
        }
        return null; //Handle NullPointer with Optional classes, if product name is not found
    }
    public Product findProductByCategory(int id, ProductCategory category){
        for(int i = 0; i < prd.size(); i++){
            Product traverseList = prd.get(i);
            if(traverseList.getCategory().equals(category)){
                return prd.get(i);
            }
        }
        return null;//Handle NullPointer with Optional classes, if product name is not found
    }
    public void updatePrice(int id, double price){
        for(int i = 0; i < prd.size(); i++){
            Product traverseList = prd.get(i);
            if(traverseList.getPrice() == price){
                prd.set(i, traverseList);
                // prd.set(i,traverseList.setPrice(price));
            }
        }
    }

    public void updateQuantity(int id, int quantity){

    }
}
