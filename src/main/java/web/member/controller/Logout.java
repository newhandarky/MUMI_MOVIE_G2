package web.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.member.entity.MemVO;

@WebServlet(urlPatterns = {"/member/Logout"})
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  public void doPost(HttpServletRequest req, HttpServletResponse res)
	                                throws ServletException, IOException {
		  
		  req.setCharacterEncoding("UTF-8");
		  res.setContentType("text/html; charset=UTF-8");
		  
		  try {
	    		HttpSession session = req.getSession();
	    		MemVO memVO = (MemVO)session.getAttribute("memVO");
	    		
	    		if (memVO==null) {
	    			
	    			res.sendRedirect(req.getContextPath()+"/view/index/login.jsp");
				}else {
					res.sendRedirect(req.getContextPath()+"/view/index/logout.jsp");
				}
	    		
			} catch (Exception e) {
				e.printStackTrace();
			}
	  }
	
}
