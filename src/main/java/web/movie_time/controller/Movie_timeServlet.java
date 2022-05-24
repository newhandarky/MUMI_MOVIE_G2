package web.movie_time.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.movie_time.entity.Movie_timeVO;
import web.movie_time.service.Movie_timeService;

@WebServlet("/view/movie_time/Movie_timeServlet")

public class Movie_timeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String action2 = req.getParameter("action2");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("movie_time_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影時刻編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_time/system_movie_time_add.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer movie_time_id = null;
				try {
					movie_time_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("電影時刻編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/system_movie_time_add.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Movie_timeService movie_timeSvc = new Movie_timeService();
				Movie_timeVO movie_timeVO = movie_timeSvc.getOneMovie_time(movie_time_id);
				if (movie_timeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/system_movie_time_add.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movie_timeVO", movie_timeVO); // 資料庫取出的movie_timeVO物件,存入req
				String url = "/view/movie_time/system_movie_time_listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 system_movie_time_listOne.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie_time/system_movie_time_add.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自system_movie_time_add.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer movie_time_id = new Integer(req.getParameter("movie_time_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				Movie_timeService movie_timeSvc = new Movie_timeService();
				Movie_timeVO movie_timeVO = movie_timeSvc.getOneMovie_time(movie_time_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				System.out.println(movie_timeVO);
				req.setAttribute("movie_timeVO", movie_timeVO); // 資料庫取出的movie_timeVO物件,存入req
				
				String url = "/view/movie_time/system_movie_time_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 system_movie_update.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/movie_time/system_movie_time_listAll.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自system_movie_time_update.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Movie_timeVO movie_timeVO = new Movie_timeVO();

				Integer hall_id = new Integer(req.getParameter("hall_id"));
				Integer movie_id = new Integer(req.getParameter("movie_id"));
				Integer showing = new Integer(req.getParameter("showing"));
				java.sql.Date showing_date = java.sql.Date.valueOf(req.getParameter("showing_date").trim());
				Integer movie_time_id = new Integer(req.getParameter("movie_time_id"));
				movie_timeVO.setMovie_time_id(movie_time_id);
				movie_timeVO.setHall_id(hall_id);
				movie_timeVO.setMovie_id(movie_id);
				movie_timeVO.setShowing(showing);
				movie_timeVO.setShowing_date(showing_date);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movie_timeVO", movie_timeVO); // 含有輸入格式錯誤的movie_timeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_time/system_movie_time_update.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Movie_timeService movie_timeSvc = new Movie_timeService();
				movie_timeVO = movie_timeSvc.updateMovie_time(movie_time_id, hall_id, movie_id, showing, showing_date);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movie_timeVO", movie_timeVO); // 資料庫update成功後,正確的的movie_timeVO物件,存入req
				String url = "/view/movie_time/system_movie_time_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交system_movie_time_listAll.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/movie_time/system_movie_time_update.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自system_movie_add.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			Movie_timeVO movie_timeVO = null;
			Movie_timeVO movie_timeVO2 = null;

			try {

				String[] movie_id = req.getParameterValues("movie_id");
				String[] showing = req.getParameterValues("showing");

				for (int i = 0; i < showing.length; i++) {
					java.sql.Date showing_date = java.sql.Date.valueOf(req.getParameter("showing_date").trim());
					System.out.println(showing_date);
					Integer hall_id = new Integer(req.getParameter("hall_id"));

					movie_timeVO = new Movie_timeVO();
					movie_timeVO.setHall_id(hall_id);
					movie_timeVO.setShowing_date(showing_date);

					if (Integer.parseInt(movie_id[i]) != 0) {
						
						movie_timeVO.setMovie_id(Integer.parseInt(movie_id[i]));
						movie_timeVO.setShowing(Integer.parseInt(showing[i]));
						if ("ifHad".equals(action2)) {
							List<String> errorMsgs2 = new LinkedList<String>();
							// Store this set in the request scope, in case we need to
							// send the ErrorPage view.
							req.setAttribute("errorMsgs", errorMsgs);

							movie_timeVO2 = new Movie_timeVO();
							movie_timeVO2.setHall_id(hall_id);
							movie_timeVO2.setShowing(Integer.parseInt(showing[i]));
							movie_timeVO2.setShowing_date(showing_date);
							Movie_timeService movie_timeSvc2 = new Movie_timeService();
							movie_timeVO2 = movie_timeSvc2.getAlreadyHad(hall_id, Integer.parseInt(showing[i]),
									showing_date);

							if (movie_timeVO2 != null) {
								
								errorMsgs.add("場次重複");
								RequestDispatcher failureView = req
										.getRequestDispatcher("/view/movie_time/system_movie_time_add.jsp");
								failureView.forward(req, res);
								
							}else {

								/*************************** 2.開始新增資料 *****************************************/
								Movie_timeService movie_timeSvc = new Movie_timeService();
								movie_timeVO = movie_timeSvc.addMovie_time(hall_id, Integer.parseInt(movie_id[i]),
										Integer.parseInt(showing[i]), showing_date);
							}

						}

					}

				}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/movie_time/system_movie_time_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交system_movie_time_listAll.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie_time/system_movie_time_add.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自system_movie_time_listAll.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer movie_time_id = new Integer(req.getParameter("movie_time_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Movie_timeService movie_timeSvc = new Movie_timeService();
				movie_timeSvc.deleteMovie_time(movie_time_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/view/movie_time/system_movie_time_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/movie_time/system_movie_time_listAll.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
