package web.movie.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.movie.entity.MovieVO;
import web.movie.service.MovieService;

@WebServlet(urlPatterns = { "/view/movie/MovieServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class MovieServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String deType = req.getParameter("delete_type");
		

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

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
					RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MovieService movieSvc = new MovieService();
				MovieVO movieVO = movieSvc.getOneMovie(movie_id);
				if (movieVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movieVO", movieVO); // 資料庫取出的movieVO物件,存入req
				String url = "/view/movie/listOneMovie.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMovie.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("type_Update".equals(action)) { // 來自system_movie_update.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String[] movie_type_id = req.getParameterValues("movie_type_id");
				Integer movie_tag_id = new Integer(req.getParameter("movie_tag_id"));
//				for (int i = 0; i < movie_tag_id.length; i++) {
//					Integer movie_id = new Integer(req.getParameter("movie_id"));
//					MovieVO movieVO = new MovieVO();
//					movieVO.setMovie_id(movie_id);
//					movieVO.setMovie_tag_id(movie_tag_id);
//					movieVO.setMovie_type_id(Integer.parseInt(movie_type_id[i]));
//
//				/*************************** 2.開始查詢資料 ****************************************/
//					MovieService movieSvc = new MovieService();
//					movieVO = movieSvc.updateType(movie_id, Integer.parseInt(movie_type_id[i]));
//					req.setAttribute("movieVO", movieVO); // 資料庫取出的movieVO物件,存入req
//				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/view/movie/system_movie_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 system_movie_update.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/system_movie_listAll.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("getOne_For_Update".equals(action)) { // 來自listAllMovie.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer movie_id = new Integer(req.getParameter("movie_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				MovieService movieSvc = new MovieService();
				MovieVO movieVO = movieSvc.getOneMovie(movie_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("movieVO", movieVO); // 資料庫取出的movieVO物件,存入req
				String url = "/view/movie/system_movie_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 system_movie_update.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/system_movie_listAll.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自system_movie_update.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				MovieVO movieVO = new MovieVO();
				Integer movie_id = new Integer(req.getParameter("movie_id").trim());

				String movie_ch = req.getParameter("movie_ch");
				String movie_chReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]+$";
				if (movie_ch == null || movie_ch.trim().length() == 0) {
					errorMsgs.add("電影中文名稱: 請勿空白");
				} else if (!movie_ch.trim().matches(movie_chReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影中文名稱: 只能是中、英文字母、數字和_");
				}

				String movie_en = req.getParameter("movie_en");
				String movie_enReg = "^[a-zA-Z0-9_]$";
				if (movie_ch == null || movie_ch.trim().length() == 0) {
					errorMsgs.add("電影英文名稱: 請勿空白");
				} else if (!movie_ch.trim().matches(movie_chReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影英文名稱: 只能是英文字母、數字和_");
				}

				Integer movie_state_id = new Integer(req.getParameter("movie_state_id").trim());
//				if (movie_state_id != 1 && movie_state_id != 2 && movie_state_id != 3) {
//					errorMsgs.add("電影狀態只能填入123");
//				}

				Integer movie_rating_id = new Integer(req.getParameter("movie_rating_id").trim());
//				if (movie_rating_id != 1 && movie_rating_id != 2 && movie_rating_id != 3 && movie_rating_id != 4
//						&& movie_rating_id != 5) {
//					errorMsgs.add("電影分級只能填入12345");
//				}

				Integer movie_runtime = null;
				try {
					movie_runtime = new Integer(req.getParameter("movie_runtime").trim());
				} catch (NumberFormatException e) {
					movie_runtime = 0;
					errorMsgs.add("片長請填數字(分鐘)");
				}

				java.sql.Date release_date = null;
				try {
					release_date = java.sql.Date.valueOf(req.getParameter("release_date").trim());
				} catch (IllegalArgumentException e) {
					release_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				byte[] movie_poster = ((req.getPart("movie_poster")).getInputStream()).readAllBytes();
				if (movie_poster.length == 0) {
					MovieService movieSvc = new MovieService();
					movie_poster = movieSvc.getOneMovie(movie_id).getMovie_poster();
				} else {
					movieVO.setMovie_poster(movie_poster);
				}

				byte[] movie_slide_poster = ((req.getPart("movie_slide_poster")).getInputStream()).readAllBytes();
				if (movie_slide_poster.length == 0) {
					MovieService movieSvc = new MovieService();
					movie_slide_poster = movieSvc.getOneMovie(movie_id).getMovie_slide_poster();
				} else {
					movieVO.setMovie_slide_poster(movie_slide_poster);
				}

				String movie_intro = req.getParameter("movie_intro");
//				String movie_introReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(/.:,?!！\\=，。)]{1,}$";
				if (movie_intro == null || movie_intro.trim().length() == 0) {
					errorMsgs.add("電影簡介: 請勿空白");
				} // else if (!movie_intro.trim().matches(movie_introReg)) { //
					// 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("電影簡介: 只能是中、英文字母、數字和_");
//				}

				String casts = req.getParameter("casts");
//				String castsReg = "^[(\u4e00-\u9fa5)(、)]+$";
				if (casts == null || casts.trim().length() == 0) {
					errorMsgs.add("演員名稱: 請勿空白");
				} // else if (!casts.trim().matches(castsReg)) { //
					// 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("演員名稱: 只能是中文");
//				}

				String director = req.getParameter("director");
//				String directorReg = "^[(\u4e00-\u9fa5)(、)]+$";
				if (director == null || director.trim().length() == 0) {
					errorMsgs.add("導演名稱: 請勿空白");
				} // else if (!casts.trim().matches(directorReg)) { //
					// 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("導演名稱: 只能是中文");
//				}

				String trailer = req.getParameter("trailer");
//				String trailerReg = "^[(a-zA-Z0-9_)(/.:,()?!\\=。、，)]{1,}$";
				if (trailer == null || trailer.trim().length() == 0) {
					errorMsgs.add("預告: 請勿空白");
				} // else if (!trailer.trim().matches(trailerReg)) { //
					// 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("預告: 只能是英文、數字");
//				}

				Timestamp movie_updated_time;
				try {
					movie_updated_time = new Timestamp(System.currentTimeMillis());
				} catch (Exception e) {
					movie_updated_time = new java.sql.Timestamp(System.currentTimeMillis());
				}

				movieVO.setMovie_id(movie_id);
				movieVO.setMovie_state_id(movie_state_id);
				movieVO.setMovie_rating_id(movie_rating_id);
				movieVO.setMovie_ch(movie_ch);
				movieVO.setMovie_en(movie_en);
				movieVO.setMovie_runtime(movie_runtime);
				movieVO.setRelease_date(release_date);
				movieVO.setMovie_poster(movie_poster);
				movieVO.setMovie_slide_poster(movie_slide_poster);
				movieVO.setMovie_intro(movie_intro);
				movieVO.setCasts(casts);
				movieVO.setDirector(director);
				movieVO.setTrailer(trailer);
				movieVO.setMovie_updated_time(movie_updated_time);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movieVO", movieVO); // 含有輸入格式錯誤的movieVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/system_movie_update.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				MovieService movieSvc = new MovieService();
				movieVO = movieSvc.updateMovie(movie_id, movie_state_id, movie_rating_id, movie_ch, movie_en,
						movie_runtime, release_date, movie_poster, movie_slide_poster, movie_intro, casts, director,
						trailer, movie_updated_time);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("movieVO", movieVO); // 資料庫update成功後,正確的的movieVO物件,存入req
				
				List<MovieVO> list = movieSvc.getType_New(movie_id);
				
				req.setAttribute("list", list);

				String url = "/view/movie/system_movie_update_success.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交system_movie_update2.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/system_movie_update.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自system_movie_add.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer movie_state_id = new Integer(req.getParameter("movie_state_id").trim());
//				if (movie_state_id != 1 && movie_state_id != 2 && movie_state_id != 3) {
//					errorMsgs.add("電影狀態只能填入123");
//				}

				Integer movie_rating_id = new Integer(req.getParameter("movie_rating_id").trim());
//				if (movie_rating_id != 1 && movie_rating_id != 2 && movie_rating_id != 3 && movie_rating_id != 4
//						&& movie_rating_id != 5) {
//					errorMsgs.add("電影分級只能填入12345");
//				}

				String movie_ch = req.getParameter("movie_ch");
				String movie_chReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]+$";
				if (movie_ch == null || movie_ch.trim().length() == 0) {
					errorMsgs.add("電影中文名稱: 請勿空白");
				} else if (!movie_ch.trim().matches(movie_chReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影中文名稱: 只能是中、英文字母、數字和_");
				}

				String movie_en = req.getParameter("movie_en");
				String movie_enReg = "^[a-zA-Z0-9_]+$";
				if (movie_ch == null || movie_ch.trim().length() == 0) {
					errorMsgs.add("電影英文名稱: 請勿空白");
				} else if (!movie_ch.trim().matches(movie_chReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影英文名稱: 只能是英文字母、數字和_");
				}

				Integer movie_runtime = null;
				try {
					movie_runtime = new Integer(req.getParameter("movie_runtime").trim());
				} catch (NumberFormatException e) {
					movie_runtime = 0;
					errorMsgs.add("片長請填數字(分鐘)");
				}

				java.sql.Date release_date = null;
				try {
					release_date = java.sql.Date.valueOf(req.getParameter("release_date").trim());
				} catch (IllegalArgumentException e) {
					release_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				byte[] movie_poster = ((req.getPart("movie_poster")).getInputStream()).readAllBytes();
				if (movie_poster.length == 0) {
					movie_poster = null;
					errorMsgs.add("請新增海報");
				}

				byte[] movie_slide_poster = ((req.getPart("movie_slide_poster")).getInputStream()).readAllBytes();
				if (movie_slide_poster.length == 0) {
					movie_slide_poster = null;
					errorMsgs.add("請新增輪播海報");
				}

				String movie_intro = req.getParameter("movie_intro");
				// String movie_introReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)(/.:,?!！\\=，。)]{1,}$";
				if (movie_intro == null || movie_intro.trim().length() == 0) {
					errorMsgs.add("電影簡介: 請勿空白");
				}
//				else if (!movie_intro.trim().matches(movie_introReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("電影簡介: 只能是中、英文字母、數字和標點符號");
//				}

				String casts = req.getParameter("casts");
				String castsReg = "^[(\u4e00-\u9fa5)(、)]+$";
				if (casts == null || casts.trim().length() == 0) {
					errorMsgs.add("演員名稱: 請勿空白");
				} else if (!casts.trim().matches(castsReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("演員名稱: 只能是中文");
				}

				String director = req.getParameter("director");
				String directorReg = "^[(\u4e00-\u9fa5)(、)]+$";
				if (director == null || director.trim().length() == 0) {
					errorMsgs.add("導演名稱: 請勿空白");
				} else if (!casts.trim().matches(directorReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("導演名稱: 只能是中文");
				}

				String trailer = req.getParameter("trailer");
				String trailerReg = "^[(a-zA-Z0-9_)(/.:,?!\\=)]{1,}$";
				if (trailer == null || trailer.trim().length() == 0) {
					errorMsgs.add("預告: 請勿空白");
				} else if (!trailer.trim().matches(trailerReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("預告: 只能是英文、數字");
				}
		
				MovieVO movieVO = new MovieVO();
				movieVO.setMovie_state_id(movie_state_id);
				movieVO.setMovie_rating_id(movie_rating_id);
				movieVO.setMovie_ch(movie_ch);
				movieVO.setMovie_en(movie_en);
				movieVO.setMovie_runtime(movie_runtime);
				movieVO.setRelease_date(release_date);
				movieVO.setMovie_poster(movie_poster);
				movieVO.setMovie_slide_poster(movie_slide_poster);
				movieVO.setMovie_intro(movie_intro);
				movieVO.setCasts(casts);
				movieVO.setDirector(director);
				movieVO.setTrailer(trailer);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("movieVO", movieVO); // 含有輸入格式錯誤的movieVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/system_movie_add.jsp");
					failureView.forward(req, res);
					return;
				}
		
				/*************************** 2.開始新增資料 ***************************************/
				MovieService movieSvc = new MovieService();
				movieVO = movieSvc.addMovie(movie_state_id, movie_rating_id, movie_ch, movie_en, movie_runtime,
						release_date, movie_poster, movie_slide_poster, movie_intro, casts, director, trailer);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/movie/system_movie_add2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交system_movie_listAll.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/system_movie_add.jsp");
				failureView.forward(req, res);
			}
		}

		if ("type_insert".equals(action)) {
	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String[] movie_type_id = req.getParameterValues("movie_type_id");
	
				for (int i = 0; i < movie_type_id.length; i++) {
					Integer movie_id = new Integer(req.getParameter("movie_id"));
					MovieVO movieVO = new MovieVO();
					movieVO.setMovie_id(movie_id);
					movieVO.setMovie_type_id(Integer.parseInt(movie_type_id[i]));

					/*************************** 2.開始新增資料 *****************************************/
					MovieService movieSvc = new MovieService();
					movieVO = movieSvc.insertType(movie_id, Integer.parseInt(movie_type_id[i]));
				}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/movie/system_movie_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交system_movie_tag_listAll.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("新增資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/system_movie_listAll.jsp");
				failureView.forward(req, res);
			}

		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer movie_id = new Integer(req.getParameter("movie_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				MovieService movieSvc = new MovieService();
				movieSvc.deleteMovie(movie_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/view/movie/system_movie_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/system_movie_add2.jsp");
				failureView.forward(req, res);
			}
		}

		if("delete_type".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer movie_id = new Integer(req.getParameter("movie_id"));
				System.out.println("111");
				/*************************** 2.開始刪除資料 ***************************************/
				MovieService movieSvc = new MovieService();
				movieSvc.deleteType(movie_id);

				System.out.println("222");
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/view/movie/system_movie_add2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				System.out.println("333");
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/movie/system_movie_update_success.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
	}

}
