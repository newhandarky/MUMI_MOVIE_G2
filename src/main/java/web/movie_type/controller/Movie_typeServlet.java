package web.movie_type.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.movie_type.entity.Movie_typeVO;
import web.movie_type.service.Movie_typeService;

@WebServlet("/view/movie_type/Movie_typeServlet")
public class Movie_typeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("movie_type_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影分類編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_type/system_movie_type_add.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer movie_type_id = null;
				try {
					movie_type_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("電影分類編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_type/system_movie_type_add.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Movie_typeService movie_typeSvc = new Movie_typeService();
				Movie_typeVO movie_typeVO = movie_typeSvc.getOneMovie_type(movie_type_id);
				if (movie_typeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_type/system_movie_type_add.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movie_typeVO", movie_typeVO); // 資料庫取出的movie_typeVO物件,存入req
				String url = "/view/movie_type/system_movie_type_listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 system_movie_type_listOne.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie_type/system_movie_type_add.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自system_movie_type_listAll.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer movie_type_id = new Integer(req.getParameter("movie_type_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				Movie_typeService movie_typeSvc = new Movie_typeService();
				Movie_typeVO movie_typeVO = movie_typeSvc.getOneMovie_type(movie_type_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("movie_typeVO", movie_typeVO); // 資料庫取出的movie_typeVO物件,存入req
				String url = "/view/movie_type/system_movie_type_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 system_movie_type_update.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/movie_type/system_movie_type_listAll.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_movie_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Movie_typeVO movie_typeVO = new Movie_typeVO();
				Integer movie_type_id = new Integer(req.getParameter("movie_type_id").trim());

				String movie_type_en = req.getParameter("movie_type_en");
				String movie_type_enReg = "^[a-zA-Z0-9_]+$";
				if (movie_type_en == null || movie_type_en.trim().length() == 0) {
					errorMsgs.add("英文分類: 請勿空白");
				} else if (!movie_type_en.trim().matches(movie_type_enReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("英文分類: 只能是英文");
				}
				String movie_type_ch = req.getParameter("movie_type_ch");
				String movie_type_chReg = "^[(\u4e00-\u9fa5)]+$";
				if (movie_type_ch == null || movie_type_ch.trim().length() == 0) {
					errorMsgs.add("中文分類: 請勿空白");
				} else if (!movie_type_ch.trim().matches(movie_type_chReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("中文分類: 只能是中文");
				}

				movie_typeVO.setMovie_type_id(movie_type_id);
				movie_typeVO.setMovie_type_en(movie_type_en);
				movie_typeVO.setMovie_type_ch(movie_type_ch);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movie_typeVO", movie_typeVO); // 含有輸入格式錯誤的movie_typeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_type/system_movie_type_update.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Movie_typeService movie_typeSvc = new Movie_typeService();
				movie_typeVO = movie_typeSvc.updateMovie_type(movie_type_id, movie_type_en, movie_type_ch);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movie_typeVO", movie_typeVO); // 資料庫update成功後,正確的的movie_typeVO物件,存入req
				String url = "/view/movie_type/system_movie_type_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交system_movie_type_listAll.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie_type/system_movie_type_update.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自system_movie_type_add.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String movie_type_en = req.getParameter("movie_type_en");
				String movie_type_enReg = "^[a-zA-Z0-9_]+$";
				if (movie_type_en == null || movie_type_en.trim().length() == 0) {
					errorMsgs.add("英文分類: 請勿空白");
				} else if (!movie_type_en.trim().matches(movie_type_enReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("英文分類: 只能是英文");
				}
				String movie_type_ch = req.getParameter("movie_type_ch");
				String movie_type_chReg = "^[(\u4e00-\u9fa5)]+$";
				if (movie_type_ch == null || movie_type_ch.trim().length() == 0) {
					errorMsgs.add("中文分類: 請勿空白");
				} else if (!movie_type_ch.trim().matches(movie_type_chReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("中文分類: 只能是中文");
				}

				Movie_typeVO movie_typeVO = new Movie_typeVO();
				movie_typeVO.setMovie_type_en(movie_type_en);
				movie_typeVO.setMovie_type_ch(movie_type_ch);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movie_typeVO", movie_typeVO); // 含有輸入格式錯誤的movie_typeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_type/system_movie_type_add.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Movie_typeService movie_typeSvc = new Movie_typeService();
				movie_typeVO = movie_typeSvc.addMovie_type(movie_type_en, movie_type_ch);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/movie_type/system_movie_type_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交system_movie_type_listAll.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie_type/system_movie_type_add.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自system_movie_type_listAll.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer movie_type_id = new Integer(req.getParameter("movie_type_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Movie_typeService movie_typeSvc = new Movie_typeService();
				movie_typeSvc.deleteMovie_type(movie_type_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/view/movie_type/system_movie_type_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/movie_type/system_movie_type_listAll.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
