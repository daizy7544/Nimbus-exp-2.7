import java.sql.*;
import java.util.*;

public class ProductDAO {
    private Connection con;

    public ProductDAO() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "yourpassword");
        con.setAutoCommit(false);
    }

    public void addProduct(Product p) throws Exception {
        String q = "INSERT INTO Product(ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, p.getProductId());
        ps.setString(2, p.getProductName());
        ps.setDouble(3, p.getPrice());
        ps.setInt(4, p.getQuantity());
        ps.executeUpdate();
        con.commit();
        ps.close();
        System.out.println("Product added successfully!");
    }

    public void viewProducts() throws Exception {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Product");
        System.out.println("ID\tName\tPrice\tQty");
        System.out.println("-------------------------------");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getDouble(3) + "\t" + rs.getInt(4));
        }
        rs.close();
        st.close();
    }

    public void updateProduct(int id, double price, int qty) throws Exception {
        String q = "UPDATE Product SET Price=?, Quantity=? WHERE ProductID=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setDouble(1, price);
        ps.setInt(2, qty);
        ps.setInt(3, id);
        int count = ps.executeUpdate();
        if (count > 0) {
            con.commit();
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found!");
        }
        ps.close();
    }

    public void deleteProduct(int id) throws Exception {
        String q = "DELETE FROM Product WHERE ProductID=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1, id);
        int count = ps.executeUpdate();
        if (count > 0) {
            con.commit();
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found!");
        }
        ps.close();
    }

    public void close() throws Exception {
        con.close();
    }
}
