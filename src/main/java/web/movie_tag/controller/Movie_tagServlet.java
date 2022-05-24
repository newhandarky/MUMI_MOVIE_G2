package web.movie_tag.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.movie_tag.entity.Movie_tagVO;
import web.movie_tag.service.Movie_tagService;

@WebServlet("/view/movie_tag/Movie_tagServlet")
public class Movie_tagServlet extends HttpServlet {
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
				String str = req.getParameter("movie_tag_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影標籤編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_tag/system_movie_tag_add.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer movie_tag_id = null;
				try {
					movie_tag_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("電影標籤編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_tag/system_movie_tag_add.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Movie_tagService movie_tagSvc = new Movie_tagService();
				Movie_tagVO movie_tagVO = movie_tagSvc.getOneMovie_tag(movie_tag_id);
				if (movie_tagVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_tag/system_movie_tag_add.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movie_tagVO", movie_tagVO); // 資料庫取出的movie_tagVO物件,存入req
				String url = "/view/movie_tag/system_movie_tag_listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 system_movie_tag_listOne.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie_type/system_movie_tag_add.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getType_By_Movie".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("movie_id");
				System.out.println("111");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入電影標籤編號");
				}
				System.out.println("222");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_tag/system_movie_tag_listAll.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				System.out.println("333");
				Integer movie_id = null;
				try {
					movie_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("電影標籤編號格式不正確");
				}
				System.out.println("444");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("555");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_tag/system_movie_tag_listAll.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/*************************** 2.開始查詢資料 *****************************************/
				System.out.println("666");
				Movie_tagService movie_tagSvc = new Movie_tagService();
				List<Movie_tagVO> list = movie_tagSvc.getTagByMovie(movie_id);
//				if (movie_tagVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					System.out.println("777");
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/view/movie_tag/system_movie_tag_listAll.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
				System.out.println(list);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出的movie_tagVO物件,存入req
				String url = "/view/movie_tag/system_movie_tag_searchByMovie.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 system_movie_tag_listOne.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie_tag/system_movie_tag_listAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自system_movie_tag_listAll.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer movie_tag_id = new Integer(req.getParameter("movie_tag_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				Movie_tagService movie_tagSvc = new Movie_tagService();
				Movie_tagVO movie_tagVO = movie_tagSvc.getOneMovie_tag(movie_tag_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("movie_tagVO", movie_tagVO); // 資料庫取出的movie_tagVO物件,存入req
				String url = "/view/movie_tag/system_movie_tag_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 system_movie_tag_update.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/movie_tag/system_movie_tag_listAll.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自system_movie_tag_update.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Movie_tagVO movie_tagVO = new Movie_tagVO();
				Integer movie_tag_id = new Integer(req.getParameter("movie_tag_id").trim());

				Integer movie_id = new Integer(req.getParameter("movie_id").trim());
				String movie_idReg = "^[0-9]+$";
				if (movie_id == null) {
					errorMsgs.add("電影編號: 請勿空白");
				} else if (movie_id <= 0) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影編號: 只能是數字");
				}
				Integer movie_type_id = new Integer(req.getParameter("movie_type_id").trim());
				String movie_type_idReg = "^[0-9]+$";
				if (movie_type_id == null) {
					errorMsgs.add("電影分類編號: 請勿空白");
				} else if (movie_type_id <= 0) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影分類編號: 只能是數字");
				}

				movie_tagVO.setMovie_tag_id(movie_tag_id);
				movie_tagVO.setMovie_id(movie_id);
				movie_tagVO.setMovie_type_id(movie_type_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movie_tagVO", movie_tagVO); // 含有輸入格式錯誤的movie_tagVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_tag/system_movie_tag_update.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Movie_tagService movie_tagSvc = new Movie_tagService();
				movie_tagVO = movie_tagSvc.updateMovie_tag(movie_tag_id, movie_id, movie_type_id);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movie_tagVO", movie_tagVO); // 資料庫update成功後,正確的的movie_tagVO物件,存入req
				String url = "/view/movie_tag/system_movie_tag_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交system_movie_tag_listAll.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie_tag/system_movie_tag_update.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自system_movie_tag_add.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				Integer movie_id = new Integer(req.getParameter("movie_id").trim());
				String movie_idReg = "^[0-9]+$";
				if (movie_id == null) {
					errorMsgs.add("電影編號: 請勿空白");
				} else if (movie_id <= 0) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影編號: 只能是數字");
				}
				Integer movie_type_id = new Integer(req.getParameter("movie_type_id").trim());
				String movie_type_idReg = "^[0-9]+$";
				if (movie_type_id == null) {
					errorMsgs.add("電影分類編號: 請勿空白");
				} else if (movie_type_id <= 0) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影分類編號: 只能是數字");
				}
				
				Movie_tagVO movie_tagVO = new Movie_tagVO();
				movie_tagVO.setMovie_id(movie_id);
				movie_tagVO.setMovie_type_id(movie_type_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movie_tagVO", movie_tagVO); // 含有輸入格式錯誤的movie_tagVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/movie_tag/system_movie_tag_add.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Movie_tagService movie_tagSvc = new Movie_tagService();
				movie_tagVO = movie_tagSvc.addMovie_tag(movie_id, movie_type_id);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/movie_tag/system_movie_tag_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交system_movie_tag_listAll.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie_tag/system_movie_tag_add.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自system_movie_tag_listAll.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer movie_tag_id = new Integer(req.getParameter("movie_tag_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Movie_tagService movie_tagSvc = new Movie_tagService();
				movie_tagSvc.deleteMovie_tag(movie_tag_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/view/movie_tag/system_movie_tag_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/view/movie_tag/system_movie_tag_listAll.jsp");
				failureView.forward(req, res);
			}
		}
				
	}

}
