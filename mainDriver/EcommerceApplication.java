package mainDriver;

import java.util.List;
import java.util.Scanner;

import model.Product;
import model.ProductCategory;
import service.CustomerService;
import service.ProductService;

public class EcommerceApplication {

    public static void main(String[] args) {
        ProductService serv = new ProductService();
        CustomerService servCustomer = new CustomerService();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int option = sc.nextInt();
            
            switch (option) {
                case 1:
                    handleAdminMenu(sc, serv, servCustomer);
                    break;
                case 2:
                    handleCustomerMenu(sc);
                    break;
                case 3:
                default:
                    System.out.println("Exitting Successfully");
                    running = false;
                    break;
            }
        }
        sc.close();
    }

    private static void handleAdminMenu(Scanner sc, ProductService serv, CustomerService servCustomer) {
        System.out.println("\n===== ADMIN MENU =====");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Search Product By ID");
        System.out.println("4. Search Product By Name");
        System.out.println("5. Search Product By Category");
        System.out.println("6. Update Product By Price");
        System.out.println("7. Update Product By Quantity");
        System.out.println("8. Delete Product");
        System.out.println("9. View Customers");
        System.out.println("10. View All Orders");
        System.out.println("11. Exit");
        System.out.print("Enter your choice: ");
        
        int opt = sc.nextInt();
        
        switch (opt) {
            case 1:
                if(serv.viewAllProducts() == null) {
                    System.out.println(" No Products Found");
                } else {
                    serv.viewAllProducts();
                }
                System.out.print("Enter Product ID: ");
                int productId = sc.nextInt();
                sc.nextLine(); // Consume newline

                System.out.print("Enter Product Name: ");
                String productName = sc.nextLine();

                System.out.print("Enter Product Brand: ");
                String brand = sc.nextLine();

                System.out.print("Enter Product Price: ");
                double price = sc.nextDouble();

                System.out.print("Enter Available Quantity: ");
                int quantity = sc.nextInt();

                System.out.println("\n===== Product Category MENU =====");
                System.out.println("1. INDOOR");
                System.out.println("2. OUTDOOR");
                System.out.println("3. SUCCULENTS");
                ProductCategory category = null;
                int opt2 = sc.nextInt();
                switch(opt2){
                    case 1:
                        category = ProductCategory.INDOOR;
                        break;
                    case 2:
                        category = ProductCategory.OUTDOOR;
                        break;
                    case 3:
                        category = ProductCategory.SUCCULENTS;
                        break;
                    default:
                        break;
                }
                
                serv.addNewProduct(productId, productName, category, brand, price, quantity);
                break;
                
            case 2:
                List<Product> list = serv.viewAllProducts();
                if(list == null) {
                    System.out.println("No Products added yet");
                } else {
                    System.out.println(list);
                }
                break;
                
            case 3:
                System.out.println(serv.viewAllProducts());
                System.out.println("Enter the Product ID from View List of Products");
                int opt3 = sc.nextInt();
                System.out.println(serv.findProductById(opt3));
                break;
                
            case 4:
                System.out.println(serv.viewAllProducts());
                System.out.println("Enter the Product Name from View List of Products");
                String opt4 = sc.next();
                System.out.println(serv.findProductByName(opt4));
                break;
                
            case 5:
                System.out.println("View All Products " + serv.viewAllProducts());
                System.out.println("Enter the Product Category from View List of Products");
                String opt5 = sc.next();
                ProductCategory selectedCategory = ProductCategory.valueOf(opt5.trim().toUpperCase());
                System.out.println(serv.findProductByCategory(selectedCategory));
                break;
                
            case 6:
                System.out.println("View All Products " + serv.viewAllProducts());
                System.out.println("Tell item ID, for which you wanna Edit the Product Price");
                int opt6 = sc.nextInt();
                System.out.println("Tell the new price");
                int opt7 = sc.nextInt();
                int id = serv.findProductIndexById(opt6);
                serv.updatePrice(id, opt7);
                System.out.println("View All Products after Modification " + serv.viewAllProducts());
                break;
                
            case 7:
                System.out.println("View All Products " + serv.viewAllProducts());
                System.out.println("Tell item ID, for which you wanna Edit the Product Quantity");
                int opt8 = sc.nextInt();
                System.out.println("Tell the new Quantity");
                int opt9 = sc.nextInt();
                int id2 = serv.findProductIndexById(opt8);
                serv.updateQuantity(id2, opt9);
                System.out.println("View All Products after Modification " + serv.viewAllProducts());
                break;
                
            case 8:
                System.out.println("Before Deletion " + serv.viewAllProducts());
                System.out.println("Tell item ID, for Deletion");
                int opt10 = sc.nextInt();
                int id3 = serv.findProductIndexById(opt10);
                serv.deleteById(id3);
                System.out.println("After Deletion " + serv.viewAllProducts());
                break;
                
            case 9:
                System.out.println("View All Customers " + servCustomer.viewAllCustomers());
                break;
        }
    }

    private static void handleCustomerMenu(Scanner sc) {
        System.out.println("\n===== CUSTOMER MENU =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. View Products");
        System.out.println("4. Search Product");
        System.out.println("5. Add Product to Cart");
        System.out.println("6. View Cart");
        System.out.println("7. Remove Product from Cart");
        System.out.println("8. Place Order");
        System.out.println("9. View My Orders");
        System.out.println("10. Cancel Order");
        System.out.println("11. Logout");
        System.out.print("Enter your choice: ");
        
        int option = sc.nextInt();
        // You can build out the switch for the customer logic here
    }
}