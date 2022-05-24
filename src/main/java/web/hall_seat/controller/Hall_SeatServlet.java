package web.hall_seat.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 **********************/
				String str = req.getParameter("hall_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入影廳編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/system_hall_seat.jsp");
					failureView.forward(req, res);
					return;
				}

				Integer hall_id = null;
				try {
					hall_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("影廳編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/system_hall_seat.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Hall_SeatService hall_seatSvc = new Hall_SeatService();
				List<Hall_SeatVO> list = hall_seatSvc.getSeatInfo(hall_id);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/system_hall_seat.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list);
				String url = "/view/hall_seat/system_show_one_hall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/system_hall_seat.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 **********************/
				
				String[] seat_id = req.getParameterValues("seat_id");
				String[] seat_state = req.getParameterValues("seat_state");
				for(int i = 0; i<seat_id.length; i++) {
				Hall_SeatVO hall_seatVO = new Hall_SeatVO();
				hall_seatVO.setSeat_id(Integer.parseInt(seat_id[i]));
				hall_seatVO.setSeat_state(Integer.parseInt(seat_state[i]));

				/*************************** 2.開始修改資料 *****************************************/
				Hall_SeatService hall_seatSvc = new Hall_SeatService();
				hall_seatVO = hall_seatSvc.updateSeat_State(Integer.parseInt(seat_id[i]), Integer.parseInt(seat_state[i]));
				}

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = "/view/hall_seat/system_hall_seat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/system_hall_seat.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
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
					hall_seatVO.setSeat_state(Integer.parseInt(seat_state[i]));
					hall_seatVO.setSeat_row(seat_row);
					hall_seatVO.setSeat_col(seat_col);
					hall_seatVO.setSeat_left(seat_left);
					hall_seatVO.setSeat_right(seat_right);
					hall_seatVO.setSeat_row_aisle1(seat_row_aisle1);
					hall_seatVO.setSeat_row_aisle2(seat_row_aisle2);
					hall_seatVO.setSeat_no(Integer.parseInt(seat_no[i]));

					if (!errorMsgs.isEmpty()) {
						req.setAttribute("hall_seatVO", hall_seatVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/system_add_seat.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/*************************** 2.開始新增資料 ***************************************/
					Hall_SeatService hall_seatSvc = new Hall_SeatService();
					hall_seatVO = hall_seatSvc.addHall_Seat(hall_id, Integer.parseInt(seat_state[i]), seat_name[i], seat_row, seat_col, seat_left, seat_right, seat_row_aisle1, seat_row_aisle2, Integer.parseInt(seat_no[i]));
				}
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/hall_seat/system_hall_seat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/system_hall_seat.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer hall_id = new Integer(req.getParameter("hall_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Hall_SeatService hall_seatSvc = new Hall_SeatService();
				hall_seatSvc.deleteHall(hall_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/view/hall_seat/system_hall_seat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/system_hall_seat.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert_hall".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 *************************/
				
					String hall_name = new String(req.getParameter("hall_name"));
					Hall_SeatVO hall_seatVO = new Hall_SeatVO();
					hall_seatVO.setHall_name(hall_name);
					
					/*************************** 2.開始新增資料 ***************************************/
					Hall_SeatService hall_seatSvc = new Hall_SeatService();
					hall_seatVO = hall_seatSvc.addHall(hall_name);
				

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/hall_seat/system_add_seat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/hall_seat/system_hall_seat.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
