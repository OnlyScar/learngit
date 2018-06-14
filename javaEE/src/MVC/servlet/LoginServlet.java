package MVC.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import MVC.bean.User;
import MVC.dao.UserDao;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LoginServlet.class);
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String pwd = req.getParameter("password");
		
		User user = new UserDao().getUser(name,pwd);
		if (user != null) {
			req.getSession().setAttribute("user", user);
	        resp.sendRedirect("/javaEE/listProduct");
		}else{
			resp.sendRedirect("/login.jsp");	
		}	
		
		/*if ("admin".equals(name)&&"123".equals(pwd)) {
			req.getSession().setAttribute("userName", name);
			resp.sendRedirect("/helloworld/listHero");
		}else{
			resp.sendRedirect("login.jsp");
		}*/
		
	}
  

}
