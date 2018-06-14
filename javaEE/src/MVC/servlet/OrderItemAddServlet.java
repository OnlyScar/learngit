package MVC.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import MVC.bean.OrderItem;
import MVC.bean.Product;
import MVC.dao.ProductDao;


@WebServlet("/addOrderItem")
public class OrderItemAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(OrderItemAddServlet.class);
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	
    	log.info("---OrderItemAddServlet---start: ");
    	int num = Integer.parseInt(req.getParameter("num"));
    	int pid = Integer.parseInt(req.getParameter("pid"));
    	Product p = new ProductDao().getProduct(pid);
    	OrderItem oi = new OrderItem();
    	oi.setNum(num);
    	oi.setProduct(p);
    	List<OrderItem> ois = (List<OrderItem>)req.getSession().getAttribute("ois");
    	if (ois == null) {
			ois = new ArrayList<OrderItem>();
			req.getSession().setAttribute("ois", ois);
		}
    	
    	boolean found = false;
    	for (OrderItem orderItem : ois) {
			if (orderItem.getProduct().getId()==oi.getProduct().getId()) {
				orderItem.setNum(orderItem.getNum()+oi.getNum());
				found = true;
				break;
			}
		}
    	if (!found)
			ois.add(oi);
    	log.info("---OrderItemListServlet---ois: "+ois.toString());
    	resp.sendRedirect("/javaEE/listOrderItem");
    	
    }   
   

}
