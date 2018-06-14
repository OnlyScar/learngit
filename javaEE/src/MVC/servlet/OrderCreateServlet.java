package MVC.servlet;
 
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MVC.bean.Order;
import MVC.bean.OrderItem;
import MVC.bean.User;
import MVC.dao.OrderDao;
import MVC.dao.OrderItemDao;
 
@WebServlet("/createOrder")
public class OrderCreateServlet extends HttpServlet {
 
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        User u = (User) request.getSession().getAttribute("user");
        if(null==u){
            response.sendRedirect("/javaEE/login.jsp");
            return;
        }
              
        Order o = new Order();
        o.setUser(u);
 
        new OrderDao().insert(o);
 
        List<OrderItem> ois = (List<OrderItem>) request.getSession().getAttribute("ois");
        for (OrderItem oi : ois) {
            oi.setOrder(o);
            new OrderItemDao().insert(oi);
        }
         
        ois.clear();
         
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("订单创建成功");
 
    }
}