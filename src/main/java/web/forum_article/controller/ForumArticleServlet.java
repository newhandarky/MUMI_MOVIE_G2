package web.forum_article.controller;

import java.io.IOException;
import java.io.InputStream;
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

import web.forum_article.entity.ArticleVO;
import web.forum_article.service.ArticleService;


@WebServlet(urlPatterns = { "/view/forum_article/forumArticle.do" })
@MultipartConfig
public class ForumArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action= " + action); // 測試

		// 新增文章
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer mem_id = new Integer(req.getParameter("mem_id"));
			String article_board = req.getParameter("article_board");
			String article_type = req.getParameter("article_type");
			String article_subject = req.getParameter("article_subject");
			String article_contain = req.getParameter("article_contain");
			InputStream defaultPic = getServletContext().getResourceAsStream("/NoData/none3.jpg");

			byte[] article_pic = ((req.getPart("article_pic")).getInputStream()).readAllBytes();
			if (article_pic.length == 0) {
				article_pic = defaultPic.readAllBytes();
			}
//				byte[] article_pic = new byte[((req.getPart("article_pic")).getInputStream()).available()];
			Timestamp article_publish = new Timestamp(System.currentTimeMillis());
			String article_state = req.getParameter("article_state");
			Integer re_article_id = 0;

//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if (!ename.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//				}
//
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}
//
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate = new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}
//
//				Integer deptno = new Integer(req.getParameter("deptno").trim());

			ArticleVO articleVO = new ArticleVO();

			articleVO.setMem_id(mem_id);
			articleVO.setArticle_board(article_board);
			articleVO.setArticle_type(article_type);
			articleVO.setArticle_subject(article_subject);
			articleVO.setArticle_contain(article_contain);
			articleVO.setArticle_pic(article_pic);
			articleVO.setArticle_publish(article_publish);
			articleVO.setArticle_state(article_state);
			articleVO.setRe_article_id(re_article_id);

			System.out.println("有執行到set!!"); // 除錯測試
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
//					failureView.forward(req, res);
//					return;
//				}

			/*************************** 2.開始新增資料 ***************************************/

			ArticleService articleSvc = new ArticleService();
			articleVO = articleSvc.addArticle(mem_id, article_board, article_type, article_subject, article_contain, article_pic, article_publish, article_state, re_article_id);
			System.out.println("addArticle()有執行到"); // 除錯
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/view/forum_article/listAllArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交(listAllArticle.jsp)
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/view/forum_article/listAllArticle.jsp"); // 把錯誤訊息丟到View，方便觀察除錯，但某些情況反而難找錯誤，再適時註解找
//				failureView.forward(req, res);
//			}
		}

		// 用文章板塊查詢
		if ("getBoard_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

//				if (str == null || (str.trim()).length() == 0) { // 檢查是否null，防呆用，避免粗心
//					errorMsgs.add("請輸入員工編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}

				String article_board = req.getParameter("article_board");

//				try {
//					article_board = new String(article_board);
//				} catch (Exception e) {
//					errorMsgs.add("格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}

				/*************************** 2.開始查詢資料 *****************************************/
				ArticleService articleSvc = new ArticleService();
				List<ArticleVO> list = articleSvc.getOneBoardArticle(article_board);

				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/view/forum_article/listOneArticle.jsp";
				System.out.println("有執行單一條件查詢");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/forum_article/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// 刪除文章
		if ("delete".equals(action)) { // 來自listAllArticle.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer article_id = new Integer(req.getParameter("article_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.deleteArticle(article_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/view/forum_article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/forum_article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}

		// 修改文章
		if ("getOne_For_Update".equals(action)) { // 來自listAllAricle.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer article_id = new Integer(req.getParameter("article_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
				System.out.println("查getOne_For_Update的VO:" + articleVO);
				String url = "/view/forum_article/updateArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateArticle.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/view/forum_article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer article_id = new Integer(req.getParameter("article_id").trim());

			Integer mem_id = new Integer(req.getParameter("mem_id"));
			String article_board = req.getParameter("article_board");
			String article_type = req.getParameter("article_type");
			String article_subject = req.getParameter("article_subject");
			String article_contain = req.getParameter("article_contain");
			byte[] article_pic = ((req.getPart("article_pic")).getInputStream()).readAllBytes();
			if (article_pic.length == 0) {
				ArticleService articleSvc = new ArticleService();
				byte[] defaultPic = articleSvc.getOneArticle(article_id).getArticle_pic();
				System.out.println("defaultPic=" + defaultPic);
				article_pic = defaultPic;
			}

			Timestamp article_updated = new Timestamp(System.currentTimeMillis());
			String article_state = req.getParameter("article_state");
			Integer re_article_id = article_id;

//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}	
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}

//				Integer deptno = new Integer(req.getParameter("deptno").trim());
			ArticleVO articleVO = new ArticleVO();
			System.out.println("查update的" + articleVO);
			articleVO.setArticle_id(article_id);
			articleVO.setMem_id(mem_id);
			articleVO.setArticle_board(article_board);
			articleVO.setArticle_type(article_type);
			articleVO.setArticle_subject(article_subject);
			articleVO.setArticle_contain(article_contain);
			articleVO.setArticle_pic(article_pic);
			articleVO.setArticle_updated(article_updated);
			articleVO.setArticle_state(article_state);
			articleVO.setRe_article_id(re_article_id);

//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}

			/*************************** 2.開始修改資料 *****************************************/
			ArticleService articleSvc = new ArticleService();
			articleVO = articleSvc.updateArticle(article_id, article_board, article_type, article_subject,
					article_contain, article_pic, article_updated, article_state);
			System.out.println("updateArticle()有執行到"); // 除錯
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("articleVO", articleVO); // 資料庫update成功後,正確的的empVO物件,存入req
			System.out.println("修改完成的VO=" + articleVO);
			String url = "/view/forum_article/listAllArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交xxx.jsp
			successView.forward(req, res);
			System.out.println("轉交成功!!");

			/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/view/forum_article/updateArticle.jsp");
//				failureView.forward(req, res);
//			}
		}

	}

}
