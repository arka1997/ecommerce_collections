package mainDriver;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import model.Cart;
import model.Customer;
import model.Product;
import model.ProductCategory;
import service.CartService;
import service.CustomerService;
import service.OrderService;
import service.ProductService;
public class EcommerceApplication {
static Customer login = null;
    public static void main(String[] args) {
        ProductService serv = new ProductService();
        CustomerService servCustomer = new CustomerService();
        // Done with constructor injection. iNSTEAD OF PSSING THERE OBJECT THROUGH METHODS, WE INJECTED IT THROUGH CONSTRUCTOR, AND MADE THESE Objects avaiable for Order Service, for accessing its arraylIST contents
        CartService cart = new CartService(serv);
        OrderService serveOrder = new OrderService(serv, cart);
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n╔════════════════════════════════╗");
        System.out.println("🌿 ===== MAIN MENU ===== 🌿");
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
                    handleCustomerMenu(sc, serv, servCustomer, cart, serveOrder);
                    break;
                case 3:
                    System.out.println("Exitting Successfully");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        sc.close();
    }
    private static void handleAdminMenu(Scanner sc, ProductService serv, CustomerService servCustomer) {
        boolean adminRunning = true;
        while(adminRunning) {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("🛠️ ===== ADMIN MENU ===== 🛠️");
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
                    System.out.println(serv.viewAllProducts());
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
                System.out.println("\n────────────────────────────────");
               System.out.println("🌱 ===== Product Category MENU ===== 🌱");
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
                String opt4 = sc.nextLine();
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
            case 11:
                adminRunning = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        }
    }
    private static void handleCustomerMenu(Scanner sc, ProductService serv, CustomerService servCustomer, CartService cart, OrderService serveOrder) {
         boolean customerRunning = true;
         while(customerRunning){
        // Keeping it here, instead at top, to save memory. Admin doesnot need cart service, Customer needs.
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("🛒 ===== CUSTOMER MENU ===== 🛒");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. View Products");
        System.out.println("4. Search Product");
        System.out.println("5. Add Product to Cart");
        System.out.println("6. View Cart");
        System.out.println("7. Remove Product from Cart");
        System.out.println("8. Increase Cart Quantity");
        System.out.println("9. Decrease Cart Quantity");
        System.out.println("10. View Total Cart Value");
        System.out.println("11. Place Order");
        System.out.println("12. View My Orders");
        System.out.println("13. Cancel Order");
        System.out.println("14. Logout");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();
        int productId = 0;
        switch(option){
            case 1:
                System.out.println("\n────────────────────────────────");
               System.out.println("👤 --- Customer Registration --- 👤");
                System.out.println("View Existing customers" + servCustomer.viewAllCustomers());
                System.out.print("Enter ID: ");
                int id = sc.nextInt();// Reads only number, not enter. So when I press Enter after giving input, it skips the next fullName, thinking i have given it, and asks for email
                sc.nextLine();
                System.out.print("Enter Full Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Email ID: ");
                String email = sc.nextLine();
                System.out.print("Enter Complete Address: ");
                String address = sc.nextLine();
                System.out.print("Enter Mobile Number: ");
                String mobile = sc.nextLine(); // Using String for mobile prevents dropping leading zeros
                System.out.print("Enter Password: ");
                String password = sc.nextLine();
                // Call your service method with the captured inputs
                servCustomer.register(id, name, email, mobile, address, password);
                System.out.println("Registration successful! You can now log in.");
                break;
            case 2:
                System.out.println("Enter lOGIN pASSWORD");
                String pass = sc.next();
                login = servCustomer.login(pass);
                if(login == null){
                    System.out.println("Account doesnot exist, Please register");
                }else {
                    System.out.println("Successfully Logged In" + login);
                }
                break;
            case 3: 
                System.out.println(serv.viewAllProducts());
                break;
            case 4:
                System.out.println(serv.viewAllProducts());
                System.out.println("Enter the Product Name from View List of Products");
                String searchProductName = sc.nextLine();
                System.out.println(serv.findProductByName(searchProductName));
            break;
            case 5: 
                /** 
                 *  I realised, instead of using ArrayList, I will use HashMap for storing Customer -> cartItems for current logged iN cUSTOMER. 
                    During login, i WILL FETCH that customer details, Id. And then lookup of viewing,adding, removing cart items against that customerID, will take O(1) TC
                    Too see Product iTEMS, first select option 3, then you can add items to Cart
                */
                System.out.println("Viewing All Products" + serv.viewAllProducts());
                if(serv.viewAllProducts().isEmpty()){
                    System.out.println(productId + "products found");
                    break;
                }
                System.out.println("Select the ProductId");
                productId = sc.nextInt();
                System.out.println("How many Quantity you want to add to Cart");
                int cartQuantity = sc.nextInt();
                Product prd = serv.findProductById(productId);
                String message = null;
                if (login == null) {
                    message = cart.addProductToGuestCart(productId, cartQuantity);
                } else {
                    message = cart.addProductToCart(login.getCustomerId(), prd.getProductId(), cartQuantity);
                }
                System.out.println(message);
            break;
            case 6:
                System.out.println("Item in your Cart");
                if(login == null){
                    System.out.println("Viewing Guest Cart " + cart.viewCart(-1));
                }else{
                    System.out.println("Viewing Cart" + cart.viewCart(login.getCustomerId()));
                }
            break;
            case 7:
                System.out.println("Select Product Item you want to remove from your cart");
                productId = sc.nextInt();
                List<Cart> listCart = null;
                if (login == null) {
                    listCart= cart.removeCart(-1, productId);
                } else {
                    listCart= cart.removeCart(login.getCustomerId(), productId);
                }
                System.out.println("Items after rEMOVAL" + listCart);
            break;
            case 8:
                System.out.println("Select Product Item you want to increase the Items quantity");
                productId = sc.nextInt();
                List<Cart> listCartIncreaseItem = null;
                if (login == null) {
                    listCartIncreaseItem = cart.increaseCartQuantity(-1, productId);
                } else {
                    listCartIncreaseItem = cart.increaseCartQuantity(login.getCustomerId(), productId);
                }
                System.out.println("Your Cart Items: " + listCartIncreaseItem);
            break;
            case 9:
                System.out.println("Select Product Item you want to decrease the Items quantity");
                productId = sc.nextInt();
                List<Cart> listCartDecreaseItem = null;
                if (login == null) {
                    listCartDecreaseItem = cart.decreaseCartQuantity(-1, productId);
                } else {
                    listCartDecreaseItem = cart.decreaseCartQuantity(login.getCustomerId(), productId);
                }
                System.out.println("Your Cart Items: " + listCartDecreaseItem);
            break;
            case 10:
                System.out.println("Yours total Cart Value");
                if (login == null) {
                    System.out.println(cart.totalCartValue(-1));
                } else {
                    System.out.println(cart.totalCartValue(login.getCustomerId()));
                }
            break;
            case 11:
                if(login == null){
                    System.out.println("Enter lOGIN pASSWORD");
                    String loginPassword = sc.next();
                    login = servCustomer.login(loginPassword);
                    if(login == null) {
                        System.out.println("Invalid Password, or account doesnot exist");
                    }
                    int customerId = login.getCustomerId();
                    cart.moveGuestCartToCart(customerId);
                }
                if(login != null){
                    serveOrder.placeOrder(login.getCustomerId());
                }
            break;
            case 12:
                System.out.println(serveOrder.viewMyOrders(login.getCustomerId()));
            break;
            case 13:
                System.out.println("Input the Orderid for cancellation");
                UUID orderId = UUID.fromString(sc.next());
                serveOrder.cancelOrder(orderId);
            break;
            case 14:
                servCustomer.logout(login.getCustomerId());
                login = null;
                customerRunning = false;
            break;
            default:
                System.out.println("Invalid choice. Please try again.");
            break;
        }
        }
        // For Customer logimn, from Point 1. to Point 7, we dont need customers to be loggedIn.
        // But from Point 8, we have to fetch value of isLoggedIn, and keep a check if the customer is LoggedIn or not.
    }
}