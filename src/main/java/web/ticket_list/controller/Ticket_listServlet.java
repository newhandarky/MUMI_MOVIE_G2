package web.ticket_list.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.ticket_list.entity.Ticket_listVO;
import web.ticket_list.service.Ticket_listService;


@MultipartConfig
@WebServlet(urlPatterns = {"/view/ticket_list/Ticket_listServlet"})

public class Ticket_listServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ticket_list_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入票券明細編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/ticket_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer ticket_list_id = null;
				try {
					ticket_list_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("票券明細編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/ticket_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Ticket_listService ticket_listSvc = new Ticket_listService();
				Ticket_listVO ticket_listVO = ticket_listSvc.getOneTicket(ticket_list_id);
				if (ticket_listVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/ticket_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ticket_listVO", ticket_listVO); // 資料庫取出的empVO物件,存入req
				String url = "/view/ticket_list/listOneTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/ticket_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer ticket_list_id = new Integer(req.getParameter("ticket_list_id"));
				
				/***************************2.開始查詢資料****************************************/
				Ticket_listService ticket_listSvc = new Ticket_listService();
				Ticket_listVO ticket_listVO = ticket_listSvc.getOneTicket(ticket_list_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("ticket_listVO", ticket_listVO);         // 資料庫取出的empVO物件,存入req
				String url = "/view/ticket_list/update_ticket_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/ticket_list/listAllTicket.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

				Integer ticket_list_id = new Integer(req.getParameter("ticket_list_id").trim());				
				Integer ticket_orders_id = new Integer(req.getParameter("ticket_orders_id").trim());				
				Integer seat_id = new Integer(req.getParameter("seat_id").trim());
				Integer movie_time_id = new Integer(req.getParameter("movie_time_id").trim());				
				Integer ticket_price = null;
				try {
					ticket_price = new Integer(req.getParameter("ticket_price").trim());
				} catch (NumberFormatException e) {
					ticket_price = 0;
					errorMsgs.add("票價:");
				}
				

				Ticket_listVO ticket_listVO = new Ticket_listVO();
				ticket_listVO.setTicket_list_id(ticket_list_id);
				ticket_listVO.setTicket_orders_id(ticket_orders_id);
				ticket_listVO.setSeat_id(seat_id);
				ticket_listVO.setMovie_time_id(movie_time_id);
				ticket_listVO.setTicket_price(ticket_price);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ticket_listVO", ticket_listVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
					.getRequestDispatcher("/view/ticket_list/update_ticket_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
									}
				
				/***************************2.開始修改資料*****************************************/
				Ticket_listService ticket_listSvc = new Ticket_listService();
				ticket_listVO = ticket_listSvc.updateTicket(ticket_list_id, ticket_orders_id, seat_id, movie_time_id,ticket_price);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ticket_listVO", ticket_listVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/view/ticket_list/listOneTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ticket_list/update_ticket_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				Integer ticket_orders_id = null;
				try {
					ticket_orders_id = new Integer(req.getParameter("ticket_orders_id").trim());
				} catch (NumberFormatException e) {
					ticket_orders_id = 0;
					errorMsgs.add("請輸入票券訂單編號 : ");
				}
				
				Integer seat_id = null;
				try {
					seat_id = new Integer(req.getParameter("seat_id").trim());
				} catch (NumberFormatException e) {
					seat_id = 0;
					errorMsgs.add("請輸入座位編號 : ");
				}
				
				Integer movie_time_id = null;
				try {
					movie_time_id = new Integer(req.getParameter("movie_time_id").trim());
				} catch (NumberFormatException e) {
					movie_time_id = 0;
					errorMsgs.add("請輸入電影時刻編號 : ");
				}
							
				Integer ticket_price = null;
				try {
					ticket_price = new Integer(req.getParameter("ticket_price").trim());
				} catch (NumberFormatException e) {
					ticket_price = 0;
					errorMsgs.add("請填票券價格 : ");
				}
				
				Ticket_listVO ticket_listVO = new Ticket_listVO();
				ticket_listVO.setTicket_orders_id(ticket_orders_id);
				ticket_listVO.setSeat_id(seat_id);
				ticket_listVO.setMovie_time_id(movie_time_id);
				ticket_listVO.setTicket_price(ticket_price);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ticket_listVO", ticket_listVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
					.getRequestDispatcher("/view/ticket_list/addTicket.jsp");
					failureView.forward(req, res);
							return;
									}
				
				/***************************2.開始新增資料***************************************/
				Ticket_listService ticket_listSvc = new Ticket_listService();
				ticket_listVO = ticket_listSvc.addTicket(ticket_orders_id, seat_id, movie_time_id, ticket_price);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/view/ticket_list/listAllTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/ticket_list/addTicket.jsp");
				failureView.forward(req, res);
			}
		}
			
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer ticket_list_id = new Integer(req.getParameter("ticket_list_id"));
				
				/***************************2.開始刪除資料***************************************/

				Ticket_listService ticket_listSvc = new Ticket_listService();
				ticket_listSvc.deleteTicket(ticket_list_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/view/ticket_list/listAllTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/ticket_list/listAllTicket.jsp");
				failureView.forward(req, res);
			}
		}
	}
}


