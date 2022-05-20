package web.ticket_orders.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.ticket_orders.entity.Ticket_OrdersVO;
import web.ticket_orders.service.Ticket_OrdersService;

@WebServlet(urlPatterns = {"/view/ticket_orders/Ticket_OrdersServlet"})

public class Ticket_OrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
		if ("addOrders".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
					Integer mem_id = new Integer(req.getParameter("mem_id"));
					
					Ticket_OrdersVO ticket_ordersVO = new Ticket_OrdersVO();
					ticket_ordersVO.setMem_id(mem_id);
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("ticket_ordersVO", ticket_ordersVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/order_beware.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/*************************** 2.開始新增資料 ***************************************/
					Ticket_OrdersService ticket_ordersSvc = new Ticket_OrdersService();
					ticket_ordersVO = ticket_ordersSvc.addOrders(mem_id);			
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/ticket_orders/choose_movie.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/order_beware.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Choose".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("movie_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/choose_movie.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer movie_id = null;
				try {
					movie_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("電影編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/choose_movie.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				Ticket_OrdersService ticket_ordersSvc = new Ticket_OrdersService();
				List<Ticket_OrdersVO> list = ticket_ordersSvc.getMoive_Id(movie_id);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/choose_movie.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出的hall_seatVO物件,存入req
				String url = "/view/ticket_orders/choose_time.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/choose_movie.jsp");
				failureView.forward(req, res);
			}
		}
//
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ****************************************/
//				Integer hall_id = new Integer(req.getParameter("hall_id"));
//
//				/*************************** 2.開始查詢資料 ****************************************/
//				Hall_SeatService hall_seatSvc = new Hall_SeatService();
//				Hall_SeatVO hall_seatVO = hall_seatSvc.getOneHall_Seat(hall_id);
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//				req.setAttribute("hall_seatVO", hall_seatVO); // 資料庫取出的empVO物件,存入req
//				String url = "/view/hall_seat/update_hallseat_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				e.printStackTrace();
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				
//				String[] seat_id = req.getParameterValues("seat_id");
//				String[] seat_state = req.getParameterValues("seat_state");
//				for(int i = 0; i<seat_id.length; i++) {
//				Hall_SeatVO hall_seatVO = new Hall_SeatVO();
//				hall_seatVO.setSeat_id(Integer.parseInt(seat_id[i]));
//				hall_seatVO.setSeat_state(Integer.parseInt(seat_state[i]));
//
//				/*************************** 2.開始修改資料 *****************************************/
//				Hall_SeatService hall_seatSvc = new Hall_SeatService();
//				hall_seatVO = hall_seatSvc.updateSeat_State(Integer.parseInt(seat_id[i]), Integer.parseInt(seat_state[i]));
//				}
//
//				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
////				req.setAttribute("hall_seatVO", hall_seatVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/view/hall_seat/select_page.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				e.printStackTrace();
//				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
		if ("confirm_time_number".equals(action)) { // 來自addHall_Seat.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
					Integer mem_id = new Integer(req.getParameter("mem_id"));
					Integer movie_time_id = new Integer(req.getParameter("movie_time_id"));
					Integer ticket_number = new Integer(req.getParameter("ticket_number"));
					
					Ticket_OrdersVO ticket_ordersVO = new Ticket_OrdersVO();
					
					ticket_ordersVO.setMem_id(mem_id);
					ticket_ordersVO.setMovie_time_id(movie_time_id);
					ticket_ordersVO.setTicket_number(ticket_number);
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("ticket_ordersVO", ticket_ordersVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/choose_movie.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/*************************** 2.開始新增資料 ***************************************/
					Ticket_OrdersService ticket_ordersSvc = new Ticket_OrdersService();
					ticket_ordersVO = ticket_ordersSvc.addTime_Number(mem_id, movie_time_id, ticket_number);
					List<Ticket_OrdersVO> list = ticket_ordersSvc.getSeat(movie_time_id);
					if (list == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/choose_movie.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
					req.setAttribute("list", list); // 資料庫取出的hall_seatVO物件,存入req					
					List<Ticket_OrdersVO> list_ticket = ticket_ordersSvc.getTicket_List_Id_Number(mem_id);
					if (list_ticket == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/choose_movie.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
					req.setAttribute("list_ticket", list_ticket); // 資料庫取出的hall_seatVO物件,存入req
					String url = "/view/ticket_orders/choose_seat.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/choose_movie.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("choose_seat".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer mem_id = new Integer(req.getParameter("mem_id"));
				Integer movie_time_id = new Integer(req.getParameter("movie_time_id"));
				Integer ticket_list_id = new Integer(req.getParameter("ticket_list_id"));
				String[] seat_select_state = req.getParameterValues("seat_select_state");
				String[] select_seat_name = req.getParameterValues("seat_name");
				String seat_select_state_all = new String();
				String select_seat_name_all = new String();
				Ticket_OrdersVO ticket_ordersVO = new Ticket_OrdersVO();
				
				for(int i = 0; i<seat_select_state.length; i++) {
					String str = seat_select_state[i];
					if(str.equals("1")) {
						select_seat_name_all += (select_seat_name[i] + ",");
						seat_select_state_all += "2";
					} else if (str.equals("0")){
						seat_select_state_all += "0";
					} else if (str.equals("2")){
						seat_select_state_all += "2";
					} else {
						seat_select_state_all += "3";
					}
					System.out.println(seat_select_state_all);
				}
				select_seat_name_all = select_seat_name_all.substring(0, select_seat_name_all.length()-1);
				ticket_ordersVO.setMem_id(mem_id);
				ticket_ordersVO.setMovie_time_id(movie_time_id);
				ticket_ordersVO.setSeat_select_state(seat_select_state_all);
				ticket_ordersVO.setTicket_list_id(ticket_list_id);
				ticket_ordersVO.setSelect_seat_name(select_seat_name_all);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ticket_ordersVO", ticket_ordersVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/choose_seat.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				Ticket_OrdersService ticket_ordersSvc = new Ticket_OrdersService();
				ticket_ordersVO = ticket_ordersSvc.choose_Seat(movie_time_id, seat_select_state_all, select_seat_name_all, ticket_list_id);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("ticket_ordersVO", ticket_ordersVO); // 資料庫update成功後,正確的的empVO物件,存入req
				List<Ticket_OrdersVO> list_ticket_price = ticket_ordersSvc.getList_Ticket_Price(mem_id);
				if (list_ticket_price == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/choose_seat.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list_ticket_price", list_ticket_price); // 資料庫取出的hall_seatVO物件,存入req
				String url = "/view/select_seat/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/view/ticket_orders/choose_seat.jsp");
				failureView.forward(req, res);
			}
		}
//
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ***************************************/
//				Integer hall_id = new Integer(req.getParameter("hall_id"));
//
//				/*************************** 2.開始刪除資料 ***************************************/
//				Hall_SeatService hall_seatSvc = new Hall_SeatService();
//				hall_seatSvc.deleteHall(hall_id);
//
//				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "/view/hall_seat/select_page.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				e.printStackTrace();
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		if ("insert_hall".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//				
//					String hall_name = new String(req.getParameter("hall_name"));
//					Hall_SeatVO hall_seatVO = new Hall_SeatVO();
//					hall_seatVO.setHall_name(hall_name);
//					
//					
//					// Send the use back to the form, if there were errors
////					if (!errorMsgs.isEmpty()) {
////						req.setAttribute("hallVO", hallVO); // 含有輸入格式錯誤的empVO物件,也存入req
////						RequestDispatcher failureView = req.getRequestDispatcher("/view/hall/addHall.jsp");
////						failureView.forward(req, res);
////						return;
////					}
//					
//					/*************************** 2.開始新增資料 ***************************************/
//					Hall_SeatService hall_seatSvc = new Hall_SeatService();
//					hall_seatVO = hall_seatSvc.addHall(hall_name);
//				
//
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/view/hall_seat/addHall_Seat.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				e.printStackTrace();
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}
