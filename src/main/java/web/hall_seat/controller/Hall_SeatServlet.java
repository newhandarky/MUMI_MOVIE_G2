package web.hall_seat.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import web.hall_seat.*;
import web.hall_seat.entity.Hall_SeatVO;
import web.hall_seat.service.Hall_SeatService;

@WebServlet(urlPatterns = {"/view/hall_seat/Hall_SeatServlet"})
public class Hall_SeatServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("hall_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入影廳編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer hall_id = null;
				try {
					hall_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("影廳編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Hall_SeatService hall_seatSvc = new Hall_SeatService();
				List<Hall_SeatVO> list = hall_seatSvc.getSeatInfo(hall_id);
//				System.out.println(hall_seatVO.get(1).getSeat_name());
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出的hall_seatVO物件,存入req
				String url = "/view/hall_seat/listOneHall_Seat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer hall_id = new Integer(req.getParameter("hall_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				Hall_SeatService hall_seatSvc = new Hall_SeatService();
				Hall_SeatVO hall_seatVO = hall_seatSvc.getOneHall_Seat(hall_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("hall_seatVO", hall_seatVO); // 資料庫取出的empVO物件,存入req
				String url = "/view/hall_seat/update_hallseat_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				
				String[] seat_id = req.getParameterValues("seat_id");
				String[] seat_state = req.getParameterValues("seat_state");
				for(int i = 0; i<seat_id.length; i++) {
				Hall_SeatVO hall_seatVO = new Hall_SeatVO();
				hall_seatVO.setSeat_id(seat_id[i]);
				hall_seatVO.setSeat_state(seat_state[i]);

				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("hall_seatVO", hall_seatVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/hall_seat/listOneHall_Seat.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}

				/*************************** 2.開始修改資料 *****************************************/
				Hall_SeatService hall_seatSvc = new Hall_SeatService();
				hall_seatVO = hall_seatSvc.updateSeat_State(seat_id[i], seat_state[i]);
				}

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("hall_seatVO", hall_seatVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/view/hall_seat/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/hall_seat/listOneHall.jsp");
//				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addHall_Seat.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String[] seat_name = req.getParameterValues("seat_name");
				String[] seat_state = req.getParameterValues("seat_state");
				String[] seat_no = req.getParameterValues("seat_no");
				for (int i = 0; i<seat_name.length; i++) {
					Integer hall_id = new Integer(req.getParameter("hall_id"));
					Integer seat_row = new Integer(req.getParameter("seat_row"));
					Integer seat_col = new Integer(req.getParameter("seat_col"));
					Integer seat_left = new Integer(req.getParameter("seat_left"));
					Integer seat_right = new Integer(req.getParameter("seat_right"));
					Integer seat_row_aisle1 = new Integer(req.getParameter("seat_row_aisle1"));
					Integer seat_row_aisle2 = new Integer(req.getParameter("seat_row_aisle2"));
					
					Hall_SeatVO hall_seatVO = new Hall_SeatVO();
					
					hall_seatVO.setSeat_name(seat_name[i]);
					hall_seatVO.setHall_id(hall_id);
					hall_seatVO.setSeat_state(seat_state[i]);
					hall_seatVO.setSeat_row(seat_row);
					hall_seatVO.setSeat_col(seat_col);
					hall_seatVO.setSeat_left(seat_left);
					hall_seatVO.setSeat_right(seat_right);
					hall_seatVO.setSeat_row_aisle1(seat_row_aisle1);
					hall_seatVO.setSeat_row_aisle2(seat_row_aisle2);
					hall_seatVO.setSeat_no(seat_no[i]);
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("hall_seatVO", hall_seatVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/addHall_Seat.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/*************************** 2.開始新增資料 ***************************************/
					Hall_SeatService hall_seatSvc = new Hall_SeatService();
					hall_seatVO = hall_seatSvc.addHall_Seat(hall_id, seat_state[i], seat_name[i], seat_row, seat_col, seat_left, seat_right, seat_row_aisle1, seat_row_aisle2, seat_no[i]);
				}

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/hall_seat/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
			}
		}
//
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ***************************************/
//				Integer emp_id = new Integer(req.getParameter("emp_id"));
//
//				/*************************** 2.開始刪除資料 ***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(emp_id);
//
//				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}
