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
	            
	            //ò�ƴ�5.1.7�汾֮���mysql-connector�����˷���GeneratedKeys�������������Ҫ����GeneratedKeys����PreparedStatement��Ҫ��ʾ���һ������Statement.RETURN_GENERATED_KEYS��
	            PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	 
	            ps.setInt(1, o.getUser().getId());
	 
	            ps.execute();
	            //��ȡ�Զ����ɵ�����
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
