package MVC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import MVC.bean.Order;

public class OrderDao {

	
	public void insert(Order o) {
		 try {
	            Class.forName("com.mysql.jdbc.Driver");
	 
	            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8",
	                    "root", "1234");
	 
	            String sql = "insert into order_user values(null,?)";
	            
	            //貌似从5.1.7版本之后的mysql-connector增加了返回GeneratedKeys的条件，如果需要返回GeneratedKeys，则PreparedStatement需要显示添加一个参数Statement.RETURN_GENERATED_KEYS。
	            PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	 
	            ps.setInt(1, o.getUser().getId());
	 
	            ps.execute();
	            //获取自动生成的主键
	            ResultSet rs = ps.getGeneratedKeys();
	            if (rs.next()) {
	                int id = rs.getInt(1);
	                o.setId(id);
	                System.out.println(id);
	            }
	 
	            ps.close();
	 
	            c.close();
	 
	        } catch (ClassNotFoundException e) {
	 
	            e.printStackTrace();
	        } catch (SQLException e) {
	    
	            e.printStackTrace();
	        }
	 
	}

}
