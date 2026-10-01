import java.util.Scanner;

import model.ProductCategory;
import service.ProductService;

public class EcommerceApplication{
    static int option = 0;
    static ProductService serv = new ProductService();
    public static void main(String[] args) {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        Scanner sc = new Scanner(System.in);
        option = sc.nextInt();
        switch(option){
            case 1:
                System.out.println("\n===== ADMIN MENU =====");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Search Product");
                System.out.println("4. Update Product");
                System.out.println("5. Delete Product");
                System.out.println("6. View Customers");
                System.out.println("7. View All Orders");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                int opt = sc.nextInt();
                switch(opt){
                    case 1:
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
                    default:
                        break;
                }
            case 2:
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
                option = sc.nextInt();
                break;
            default:
                System.out.println("Exitting Successfully");

        }
    }
}