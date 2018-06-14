package MVC.servlet;

import java.io.IOException;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MVC.bean.Hero;
import MVC.dao.HeroDao;

@WebServlet("/listHero")
public class HeroListServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		String userName = (String) req.getSession().getAttribute("userName");
		if (userName == null) {
			resp.sendRedirect("login.jsp");
			return;
		}
		
		int start = 0;
		int count = 5;
		try {
			start = Integer.parseInt(req.getParameter("start"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		int next = start + count;
		int pre = start - count;
		int total = new HeroDao().getTotal();
		int last;
		if (total%count == 0) {
			last = total - count;
		}else{
			last = total - total%count;
		}
		
		//±ﬂΩÁ¥¶¿Ì
		pre = pre<0?0:pre;
		next = next>last?last:next;
		
		List<Hero> heros = new HeroDao().list(start,count);
		
		req.setAttribute("next", next);	
		req.setAttribute("pre", pre);
		req.setAttribute("last", last);
		req.setAttribute("heros", heros);
		
		req.getRequestDispatcher("listHero.jsp").forward(req, resp);
		
	}
}
