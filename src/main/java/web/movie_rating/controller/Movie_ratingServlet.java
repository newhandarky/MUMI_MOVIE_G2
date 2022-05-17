package web.movie_rating.controller;

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

import web.movie_rating.entity.Movie_ratingVO;
import web.movie_rating.service.Movie_ratingService;

@WebServlet("/view/movie_rating/Movie_ratingServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Movie_ratingServlet extends HttpServlet {
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
				String str = req.getParameter("movie_rating_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影分級編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_rating/system_movie_rating_add.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer movie_rating_id = null;
				try {
					movie_rating_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("電影分級編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_rating/system_movie_rating_add.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Movie_ratingService movie_ratingSvc = new Movie_ratingService();
				Movie_ratingVO movie_ratingVO = movie_ratingSvc.getOneMovie_rating(movie_rating_id);
				if (movie_ratingVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_rating/system_movie_rating_add.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movie_ratingVO", movie_ratingVO); // 資料庫取出的movie_ratingVO物件,存入req
				String url = "/view/movie_rating/system_movie_rating_listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 system_movie_rating_listOne.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/movie_rating/system_movie_rating_listAll.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自system_movie_rating_listAll.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer movie_rating_id = new Integer(req.getParameter("movie_rating_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				Movie_ratingService movie_ratingSvc = new Movie_ratingService();
				Movie_ratingVO movie_ratingVO = movie_ratingSvc.getOneMovie_rating(movie_rating_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("movie_ratingVO", movie_ratingVO); // 資料庫取出的movie_ratingVO物件,存入req
				String url = "/view/movie_rating/system_movie_rating_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 system_movie_rating_update.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/movie_rating/system_movie_rating_update.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自system_movie_rating_update.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Movie_ratingVO movie_ratingVO = new Movie_ratingVO();
				Integer movie_rating_id = new Integer(req.getParameter("movie_rating_id").trim());

				String movie_rating_ch = req.getParameter("movie_rating_ch");
				String movie_rating_chReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]+$";
				if (movie_rating_ch == null || movie_rating_ch.trim().length() == 0) {
					errorMsgs.add("電影中文分級: 請勿空白");
				} else if (!movie_rating_ch.trim().matches(movie_rating_chReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影中文分級: 只能是中、英文字母、數字和_");
				}

				String movie_rating_en = req.getParameter("movie_rating_en");
				String movie_rating_enReg = "^[a-zA-Z0-9_]+$";
				if (movie_rating_en == null || movie_rating_en.trim().length() == 0) {
					errorMsgs.add("電影英文分級: 請勿空白");
				} else if (!movie_rating_en.trim().matches(movie_rating_enReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影英文分級: 只能是英文字母、數字和_");
				}

				byte[] movie_rating_pic = ((req.getPart("movie_rating_pic")).getInputStream()).readAllBytes();
				if (movie_rating_pic.length == 0) {
					Movie_ratingService movie_ratingSvc = new Movie_ratingService();
					movie_rating_pic = movie_ratingSvc.getOneMovie_rating(movie_rating_id).getMovie_rating_pic();
				} else {
					movie_ratingVO.setMovie_rating_pic(movie_rating_pic);
				}

				movie_ratingVO.setMovie_rating_id(movie_rating_id);
				movie_ratingVO.setMovie_rating_ch(movie_rating_ch);
				movie_ratingVO.setMovie_rating_en(movie_rating_en);
				movie_ratingVO.setMovie_rating_pic(movie_rating_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movie_ratingVO", movie_ratingVO); // 含有輸入格式錯誤的movie_ratingVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_rating/system_movie_rating_update.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Movie_ratingService movie_ratingSvc = new Movie_ratingService();
				movie_ratingVO = movie_ratingSvc.updateMovie_rating(movie_rating_id, movie_rating_ch, movie_rating_en,
						movie_rating_pic);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movie_ratingVO", movie_ratingVO); // 資料庫update成功後,正確的的movie_ratingVO物件,存入req
				String url = "/view/movie_rating/system_movie_rating_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交system_movie_rating_listAll.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/movie_rating/system_movie_rating_update.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自system_movie_r_add.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String movie_rating_ch = req.getParameter("movie_rating_ch");
				String movie_rating_chReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]+$";
				if (movie_rating_ch == null || movie_rating_ch.trim().length() == 0) {
					errorMsgs.add("電影中文分級: 請勿空白");
				} else if (!movie_rating_ch.trim().matches(movie_rating_chReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影中文分級: 只能是中、英文字母、數字和_");
				}

				String movie_rating_en = req.getParameter("movie_rating_en");
				String movie_rating_enReg = "^[a-zA-Z0-9_]+$";
				if (movie_rating_en == null || movie_rating_en.trim().length() == 0) {
					errorMsgs.add("電影英文分級: 請勿空白");
				} else if (!movie_rating_en.trim().matches(movie_rating_enReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影英文分級: 只能是英文字母、數字和_");
				}

				byte[] movie_rating_pic = ((req.getPart("movie_rating_pic")).getInputStream()).readAllBytes();
				if (movie_rating_pic.length == 0) {
					movie_rating_pic = null;
					errorMsgs.add("請新增分級圖");
				}

				Movie_ratingVO movie_ratingVO = new Movie_ratingVO();
				movie_ratingVO.setMovie_rating_ch(movie_rating_ch);
				movie_ratingVO.setMovie_rating_en(movie_rating_en);
				movie_ratingVO.setMovie_rating_pic(movie_rating_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movie_ratingVO", movie_ratingVO); // 含有輸入格式錯誤的movie_ratingVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_rating/system_movie_rating_add.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Movie_ratingService movie_ratingSvc = new Movie_ratingService();
				movie_ratingVO = movie_ratingSvc.addMovie_rating(movie_rating_ch, movie_rating_en, movie_rating_pic);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/movie_rating/system_movie_rating_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交system_movie_rating_listAll.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/movie_rating/system_movie_rating_add.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自system_movie_rating_listAll.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer movie_rating_id = new Integer(req.getParameter("movie_rating_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Movie_ratingService movie_ratingSvc = new Movie_ratingService();
				movie_ratingSvc.deleteMovie_rating(movie_rating_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/view/movie_rating/system_movie_rating_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/movie_tag/system_movie_rating_listAll.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
