package web.releasing.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.movie.entity.MovieVO;
import web.movie.service.MovieService;
import web.releasing.entity.ReleasingVO;
import web.releasing.service.ReleasingService;

@WebServlet(urlPatterns = { "/view/releasing/ReleasingServlet" })
public class ReleasingServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");

		if ("update".equals(action)) { // 來自update_movie_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				ReleasingVO releasingVO = new ReleasingVO();
				Integer movie_state_id = new Integer(req.getParameter("movie_state_id").trim());

				String movie_state = req.getParameter("movie_state");
				String movie_stateReg = "^[(\u4e00-\u9fa5)]+$";
				if (movie_state == null || movie_state.trim().length() == 0) {
					errorMsgs.add("電影狀態: 請勿空白");
				} else if (!movie_state.trim().matches(movie_stateReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影狀態: 只能是中文");
				}

				releasingVO.setMovie_state_id(movie_state_id);
				releasingVO.setMovie_state(movie_state);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("releasingVO", releasingVO); // 含有輸入格式錯誤的releasingVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/view/releasing/update_releasing_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ReleasingService releasingSvc = new ReleasingService();
				releasingVO = releasingSvc.updateReleasing(movie_state_id, movie_state);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("releasingVO", releasingVO); // 資料庫update成功後,正確的的releasingVO物件,存入req
				String url = "/view/releasing/listAllReleasing.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllreleasing.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/releasing/update_releasing_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addMovie.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String movie_state = req.getParameter("movie_state");
				String movie_stateReg = "^[(\u4e00-\u9fa5)]+$";
				if (movie_state == null || movie_state.trim().length() == 0) {
					errorMsgs.add("電影狀態: 請勿空白");
				} else if (!movie_state.trim().matches(movie_stateReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電影狀態: 只能是中文");
				}

				ReleasingVO releasingVO = new ReleasingVO();
				releasingVO.setMovie_state(movie_state);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("releasingVO", releasingVO); // 含有輸入格式錯誤的releasingVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/view/releasing/addReleasing.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ReleasingService releasingSvc = new ReleasingService();
				releasingVO = releasingSvc.addReleasing(movie_state);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/view/releasing/listAllReleasing.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMovie.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/releasing/addReleasing.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
