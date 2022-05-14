package web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/member/Logout"})
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  public void doPost(HttpServletRequest req, HttpServletResponse res)
	                                throws ServletException, IOException {
		  
		  req.setCharacterEncoding("UTF-8");
		  res.setContentType("text/html; charset=UTF-8");
		  
		  
		  System.out.println("有進來登出");
//			
//			req.setCharacterEncoding("UTF-8");
//			res.setContentType("text/html; charset=UTF-8");
//			
//			HttpSession session = req.getSession();
//			System.out.println("登出成功");
//			
//	        // 清除資料
//	        session.invalidate();
//	        RequestDispatcher failureView = req.getRequestDispatcher("/view/index/index.jsp");
//	        failureView.forward(req, res);
		  
		  String action = req.getParameter("action");
		    
		  if("logout".equals(action)) {
		    	
		    	try {
		    		HttpSession session = req.getSession();
		    		
		    		session.invalidate();
					
		    		System.out.println("登出成功");
		    		
		    		res.sendRedirect(req.getContextPath()+"/view/index/index.jsp");
				} catch (Exception e) {
					// TODO: handle exception
					
				}
		    }
	  }
	
}
