import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ProductDAO dao = new ProductDAO();
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n=== PRODUCT MENU ===");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Choose option: ");
                int ch = sc.nextInt();

                switch (ch) {
                    case 1:
                        System.out.print("Enter ProductID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        dao.addProduct(new Product(id, name, price, qty));
                        break;

                    case 2:
                        dao.viewProducts();
                        break;

                    case 3:
                        System.out.print("Enter ProductID to update: ");
                        int uid = sc.nextInt();
                        System.out.print("Enter new Price: ");
                        double newPrice = sc.nextDouble();
                        System.out.print("Enter new Quantity: ");
                        int newQty = sc.nextInt();
                        dao.updateProduct(uid, newPrice, newQty);
                        break;

                    case 4:
                        System.out.print("Enter ProductID to delete: ");
                        int did = sc.nextInt();
                        dao.deleteProduct(did);
                        break;

                    case 5:
                        dao.close();
                        System.out.println("Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
