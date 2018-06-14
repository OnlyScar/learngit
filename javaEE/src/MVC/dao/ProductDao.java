package MVC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import MVC.bean.Product;

public class ProductDao {
	public static void main(String[] args) {
		System.out.println(new ProductDao().ListProduct().size());
		System.out.println(new ProductDao().getProduct(1).getName());
		
	}

	public Product getProduct(int id) {
		Product result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/cart";
			String user = "root";
			String pwd = "1234";
			Connection con = DriverManager.getConnection(url,user,pwd);
			String sql = "select * from product where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = new Product();
				result.setId(id);
				result.setName(rs.getString("name"));
				result.setPrice(rs.getFloat("price"));
			}
			ps.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Product> ListProduct() {
		List<Product> products = new ArrayList<Product>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/cart";
			String user = "root";
			String pwd = "1234";
			Connection con = DriverManager.getConnection(url,user,pwd);
			String sql = "select * from product order by id desc";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Product product = new Product();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);				
				product.setId(id);
				product.setName(name);
				product.setPrice(price);
				products.add(product);
				
			}
			ps.close();
			con.close();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return products;
	}
	

}
