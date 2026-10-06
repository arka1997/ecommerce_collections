package service;

import exception.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.Product;
import model.ProductCategory;

public class ProductService {
    // Our Global DB
    // Use HashMap, here, then most of the find lookups will reduce from O(n) -> O(1)
    private final List<Product> prd = new ArrayList<>();

    public void addNewProduct(int productId, String productName, ProductCategory category,
                                String brand, double price, int quantity){
        prd.add(new Product(productId, productName, category, brand, price, quantity));
    }
    public List<Product> viewAllProducts(){
        // T.C: O(1), S.C: O(1)
        return prd;
    }
    public Product findProductById(int id){
        // T.C: O(n), S.C: O(1)
        for(Product item : prd){
            if(item.getProductId() == id){
                return item;
            }
        }
        throw new ProductNotFoundException("Product with " + id + " Not Found");
    }
    public List<Product> findProductByName(String name){
        List<Product> searchResult = new ArrayList<>();
        // T.C: O(n), S.C: O(n)
        for(Product item : prd){
            if(item.getProductName().toLowerCase().contains(name.toLowerCase())){
                searchResult.add(item);
            }
        }
        return searchResult; // Handle NullPointer with Optional classes, if product name is not found
    }
    public List<Product> findProductByCategory(ProductCategory category){
        List<Product> searchResult = new ArrayList<>();
        // T.C: O(n), S.C: O(1)
        for(Product item : prd){
            if(item.getCategory().equals(category)){
                searchResult.add(item);
            }
        }
        throw new ProductNotFoundException("Product with " + category + " Not Found");
    }

    //If we use HashMap, this line will not be needed,a s with productId, we can directly fetch the product object values
    public int findProductIndexById(int id){
        // T.C: O(n), S.C: O(1)
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
    public void reduceQuantityAfterOrderCompletion(int productId, int orderItems){
        // And order can be multiple, now traversing product array, and decreasing each of the items
        // T.C: O(n), S.C: O(1)
        for(Product item: prd){
            if(item.getProductId() == productId){
                item.setQuantity(item.getQuantity() - orderItems);
            }
        }
    }
    public void deleteById(int id){
        // T.C: O(1), S.C: O(1)
        prd.remove(id);
    }

    /**
     * Time Complexity is O(n*n) -> Must Optimize this
     * S.C.: O(n)
     */
    public boolean reduceQuantityinInventoryAfterOrder(Map<Integer, Integer> productCartItemIds){
        // Here we have to fetch each productIds in a loop, and try to check in ProductArraylist DB, if matched, update the quantity
        // T.C: O(n*n) -> REDUCE it
        // S.C: O(n)
        for(Map.Entry<Integer, Integer> product : productCartItemIds.entrySet()){
            Product foundProduct = null;
            for(Product p : prd){
                if(product.getKey() == p.getProductId()){
                    foundProduct = p;
                    break;
                }
                if(foundProduct == null || p.getQuantity() < product.getValue()){
                    return false;
                }
            }
        }
        for (Map.Entry<Integer, Integer> product : productCartItemIds.entrySet()) {
            for (Product p : prd) {
                if (p.getProductId() == product.getKey()) {
                    p.setQuantity(
                            p.getQuantity() - product.getValue()
                    );
                    break;
                }
            }
        }
        return true;
    }
    public void increaseQuantityinInventoryAfterCancelOrder(List<Cart> productCartItemIds){
        // For updating the inventory with stocks, that were cancelled from the Order
        //T.C: O(n*n) -> REDUCE it,  S.C.: O(n)
        for(Cart product : productCartItemIds){
            for(Product p : prd){
                if(product.getProductId() == p.getProductId()){
                    p.setQuantity(p.getQuantity() + product.getQuantity());
                    System.out.println("Items added back to stock");
                    break;
                }
            }
        }
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