package web.ticket_order.controller;

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

import web.ticket_order.entity.TicketVO;
import web.ticket_order.service.TicketService;


@MultipartConfig
@WebServlet(urlPatterns = {"/view/ticket/TicketServlet"})

public class TicketServlet extends HttpServlet {

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
				String str = req.getParameter("ticket_orders_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入票券編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/ticket/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer ticket_orders_id = null;
				try {
					ticket_orders_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("票券編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/ticket/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				TicketService ticketSvc = new TicketService();
				TicketVO ticketVO = ticketSvc.getOneTicket(ticket_orders_id);
				if (ticketVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/ticket/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ticketVO", ticketVO); // 資料庫取出的empVO物件,存入req
				String url = "/view/ticket/listOneTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/ticket/select_page.jsp");
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
				Integer ticket_orders_id = new Integer(req.getParameter("ticket_orders_id"));
				
				/***************************2.開始查詢資料****************************************/
				TicketService ticketSvc = new TicketService();
				TicketVO ticketVO = ticketSvc.getOneTicket(ticket_orders_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("ticketVO", ticketVO);         // 資料庫取出的empVO物件,存入req
				String url = "/view/ticket/update_ticket_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/ticket/listAllTicket.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/

				Integer ticket_orders_id = new Integer(req.getParameter("ticket_orders_id").trim());
				
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				
				java.sql.Date buyticket_date = null;
				try {
					buyticket_date = java.sql.Date.valueOf(req.getParameter("buyticket_date").trim());
				} catch (IllegalArgumentException e) {
					TicketService ticketSvc = new TicketService();
					buyticket_date =ticketSvc.getOneTicket(ticket_orders_id).getBuyticket_date();	
				}

				byte[] ticket_qrcode = ((req.getPart("ticket_qrcode")).getInputStream()).readAllBytes();
				if (ticket_qrcode.length == 0) {
					TicketService ticketSvc = new TicketService();
					ticket_qrcode =ticketSvc.getOneTicket(ticket_orders_id).getTicket_qrcode();	
				}	
				//上面兩行先設定Service 用Service空間來存取抓到的舊資料 可以讓我們在不選圖的時候去抓到舊的資料(預設圖也是同樣方法)
				
				Integer total_price = null;
				try {
					total_price = new Integer(req.getParameter("total_price").trim());
				} catch (NumberFormatException e) {
					total_price = 0;
					errorMsgs.add("票價請填數字.");
				}
				

				TicketVO ticketVO = new TicketVO();
				ticketVO.setTicket_orders_id(ticket_orders_id);
				ticketVO.setMem_id(mem_id);
				ticketVO.setBuyticket_date(buyticket_date);
				ticketVO.setTicket_qrcode(ticket_qrcode);
				ticketVO.setTotal_price(total_price);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ticketVO", ticketVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
					.getRequestDispatcher("/view/ticket/update_ticket_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
									}
				
				/***************************2.開始修改資料*****************************************/
				TicketService ticketSvc = new TicketService();
				ticketVO = ticketSvc.updateTicket(ticket_orders_id, mem_id, buyticket_date, ticket_qrcode, total_price);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ticketVO", ticketVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/view/ticket/listOneTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/ticket/update_ticket_input.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				Integer mem_id = null;
				try {
					mem_id = new Integer(req.getParameter("mem_id").trim());
				} catch (NumberFormatException e) {
					mem_id = 0;
					errorMsgs.add("請填會員編號 : ");
				}
				
				java.sql.Date buyticket_date = null;
				try {
					buyticket_date = java.sql.Date.valueOf(req.getParameter("buyticket_date").trim());
				} catch (IllegalArgumentException e) {
					buyticket_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入票券購買日期!");
				}
				
				byte[] ticket_qrcode = ((req.getPart("ticket_qrcode")).getInputStream()).readAllBytes();
				if (ticket_qrcode == null || ticket_qrcode.length == 0) {
					errorMsgs.add("QRcode請勿空白");
				}	
				
				
				Integer total_price = null;
				try {
					total_price = new Integer(req.getParameter("total_price").trim());
				} catch (NumberFormatException e) {
					total_price = 0;
					errorMsgs.add("價格請填數字.");
				}
				
				
				TicketVO ticketVO = new TicketVO();
				ticketVO.setMem_id(mem_id);
				ticketVO.setBuyticket_date(buyticket_date);
				ticketVO.setTicket_qrcode(ticket_qrcode);
				ticketVO.setTotal_price(total_price);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ticketVO", ticketVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
					.getRequestDispatcher("/view/ticket/addTicket.jsp");
					failureView.forward(req, res);
							return;
									}
				
				/***************************2.開始新增資料***************************************/
				TicketService ticketSvc = new TicketService();
				ticketVO = ticketSvc.addTicket(mem_id, buyticket_date, ticket_qrcode, total_price);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/view/ticket/listAllTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/ticket/addTicket.jsp");
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
				Integer ticket_orders_id = new Integer(req.getParameter("ticket_orders_id"));
				
				/***************************2.開始刪除資料***************************************/
				TicketService ticketSvc = new TicketService();
				ticketSvc.deleteTicket(ticket_orders_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/view/ticket/listAllTicket.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/ticket/listAllTicket.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
