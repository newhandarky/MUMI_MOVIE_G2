package web.employee.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import web.employee.entity.*;
import web.employee.service.*;

@WebServlet(urlPatterns = {"/view/employee/LoginHandler"})
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    res.setContentType("text/html; charset=UTF-8");
    
    String action = req.getParameter("action");
    String account = req.getParameter("emp_account");
    String password = req.getParameter("emp_password");

    // 【取得使用者 帳號(account) 密碼(password)】
    
    System.out.println("有進入到控制器");
    
    if("logout".equals(action)) {
    	
    	
    	try {
    		HttpSession session = req.getSession();
    		
    		session.invalidate();
			
    		System.out.println("登出成功");
    		
    		res.sendRedirect(req.getContextPath()+"/view/index/admin_index.jsp");
		} catch (Exception e) {

		}
    }
    
    if ("login".equals(action)) { // 來自login.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			if (account == null || (account.trim()).length() == 0) {
				
				System.out.println("取得帳號失敗");
				
				errorMsgs.add("請輸入員工帳號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/view/index/admin_login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			if (password == null || (password.trim()).length() == 0) {
				
				System.out.println("取得密碼失敗");
				
				errorMsgs.add("請輸入員工密碼");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/view/index/admin_login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			EmployeeService empSvc = new EmployeeService();
			EmployeeVO employeeVO = empSvc.getOneEmpByAccount(account);
			if (employeeVO == null) {
				
				System.out.println("進入查帳號");
				
				errorMsgs.add("很抱歉查無此帳號");
				
			}else if(!password.equals(employeeVO.getEmp_password())) {
				
				System.out.println("進入查密碼");
				
				errorMsgs.add("密碼錯誤");
			}else if(employeeVO.getEmp_state() == 0) {
				
				System.out.println("很抱歉您已非本公司員工!!");
				
				errorMsgs.add("離職員工無法繼續使用此系統功能");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/view/index/admin_login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			
			System.out.println("帳密驗證成功");			
			System.out.println(employeeVO.getEmp_account());
			
			HttpSession session = req.getSession();
		      session.setAttribute("account", account);   //*工作1: 才在session內做已經登入過的標識
		      session.setAttribute("empVO", employeeVO);
		      session.setAttribute("emp_id", employeeVO.getEmp_id());
		      
		       try {                                                        
		         String location = (String) session.getAttribute("location");
		         
		         System.out.println(session);
		         System.out.println(location);
		         
		         if (location != null) {
		           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
		           res.sendRedirect(location);            
		           return;
		         }
		       }catch (Exception ignored) { }

		      res.sendRedirect(req.getContextPath()+"/view/index/admin_index.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
		      System.out.println("登入成功導轉到其他頁面");

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/view/index/admin_login.jsp");
			failureView.forward(req, res);
			}
		}
    }

}
