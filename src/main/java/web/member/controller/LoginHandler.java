package web.member.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import web.member.dao.MemDAO_interface;
import web.member.entity.MemVO;
import web.member.service.MemService;

@WebServlet(urlPatterns = {"/view/member/LoginHandler"})
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    res.setContentType("text/html; charset=UTF-8");
    
    String action = req.getParameter("action");
    String account = req.getParameter("mem_account");
    String password = req.getParameter("mem_password");
    

    // 【取得使用者 帳號(account) 密碼(password)】
    
    if("logout".equals(action)) {
    	
    	try {
    		HttpSession session = req.getSession();
    		
    		session.invalidate();
			
    		
    		res.sendRedirect(req.getContextPath()+"/view/index/index.jsp");
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    if ("login".equals(action)) { // 來自login.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		
		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			if (account == null || (account.trim()).length() == 0) {
				
				errorMsgs.add("請輸入會員帳號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/view/index/login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			if (password == null || (password.trim()).length() == 0) {
				
				errorMsgs.add("請輸入會員密碼");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/view/index/login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneMemByAccount(account);
			
			
			if (memVO == null) {
				
				errorMsgs.add("很抱歉查無此帳號");
			}else if(memVO.getMem_state() == 0) {
				
				errorMsgs.add("很抱歉您的帳號已暫時停止使用, 請聯繫影城工作人員");
			}else if(!password.equals(memVO.getMem_password())) {
				
				// 登入錯誤次數累計
				memSvc.memLoginCount(account);
				
				// 確認登入錯誤次數
				if(memVO.getLogin_count() == 3) {
					
					// 超過三次鎖帳號
					memSvc.updateState(memVO.getMem_id());
	
					RequestDispatcher failureView = req.getRequestDispatcher("/view/index/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
					
					// 錯誤達三次跳提示
				}else if(memVO.getLogin_count() == 2) {
					
					errorMsgs.add("輸入密碼錯誤已達三次");
					errorMsgs.add("再輸入錯誤將會導致帳號暫時無法使用");
					errorMsgs.add("建議您使用忘記密碼功能取得新密碼");
					RequestDispatcher failureView = req.getRequestDispatcher("/view/index/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				errorMsgs.add("密碼錯誤");
				
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/view/index/login.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			
			
			// 登入成功, 登入錯誤次數歸零
			memSvc.memCleanCount(memVO.getMem_account());
			
			HttpSession session = req.getSession();
		      session.setAttribute("account", account);   //*工作1: 才在session內做已經登入過的標識
		      session.setAttribute("memVO", memVO);
		      session.setAttribute("mem_id", memVO.getMem_id());
		      
		       try {                                                        
		         String location = (String) session.getAttribute("location");
		         
		         
		         if (location != null) {
		           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
		           res.sendRedirect(location);            
		           return;
		         }
		       }catch (Exception ignored) { }

		      res.sendRedirect(req.getContextPath()+"/view/index/index.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/view/index/login.jsp");
			failureView.forward(req, res);
			}
		}
    }
  
}