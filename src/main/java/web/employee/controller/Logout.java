package web.employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.employee.entity.EmployeeVO;

@WebServlet(urlPatterns = {"/view/employee/Logout"})
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  public void doPost(HttpServletRequest req, HttpServletResponse res)
	                                throws ServletException, IOException {
		  
		  req.setCharacterEncoding("UTF-8");
		  res.setContentType("text/html; charset=UTF-8");
		  
		  try {
	    		HttpSession session = req.getSession();
	    		EmployeeVO employeeVO = (EmployeeVO)session.getAttribute("employeeVO");
	    		
	    		if (employeeVO==null) {
	    			
	    			res.sendRedirect(req.getContextPath()+"/view/index/admin_login.jsp");
				}else {
					res.sendRedirect(req.getContextPath()+"/view/index/admin_logout.jsp");
				}
	    		
			} catch (Exception e) {
				e.printStackTrace();
			}
	  }
	
}
