package web.forum_article.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.forum_article.entity.ArticleVO;
import web.forum_article.service.ArticleService;

@WebServlet(urlPatterns = {"/view/forum_article/UpdateArticleServlet"})
@MultipartConfig
public class UpdateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println("action= " + action); // 測試

		if("getOne_For_Update".equals(action)) {
			
			try {
				
				// 接收資料
				String temp1 = request.getParameter("article_id");
				// 驗證資料
				Map<String, String> errors = new HashMap<String, String>();
				request.setAttribute("errors", errors);
				
				// 轉換資料
				int article_id = 0;
				if (temp1 != null && temp1.length() != 0) {
					try {
						article_id = Integer.parseInt(temp1);
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errors.put("article_id", "Article Id must be an integer");
					}
				}
				
				// 呼叫方法
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_id);
				
				// 查詢完成,準備轉交
				request.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
				System.out.println("查getOne_For_Update的VO:" + articleVO);
						
				String url = "/view/forum_article/updateArticle.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 updateArticle.jsp
				successView.forward(request, response);

							
			} catch (Exception e) {
				e.getMessage();
				RequestDispatcher failureView = request.getRequestDispatcher("/view/forum_article/ForumIndex.html");
				failureView.forward(request, response);
			}
			
			
		}
		

		if ("update".equals(action)) {
			
			//驗證資料
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				//接收資料
				Integer article_id = new Integer(request.getParameter("article_id").trim());
				Integer mem_id = new Integer(request.getParameter("mem_id"));
				String article_board = request.getParameter("article_board");
				String article_type = request.getParameter("article_type");
				String article_subject = request.getParameter("article_subject");
				String article_contain = request.getParameter("article_contain");
				byte[] article_pic = ((request.getPart("article_pic")).getInputStream()).readAllBytes();
				if (article_pic.length == 0) {
					ArticleService articleSvc = new ArticleService();
					byte[] defaultPic = articleSvc.getOneArticle(article_id).getArticle_pic();
					System.out.println("defaultPic=" + defaultPic);
					article_pic = defaultPic;
				}

				Timestamp article_updated = new Timestamp(System.currentTimeMillis());
				String article_state = request.getParameter("article_state");
						

				
				//呼叫model
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

//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
				
				//修改資料
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.updateArticle(article_id, article_board, article_type, article_subject, article_contain,
						article_pic, article_updated, article_state);
				System.out.println("updateArticle()有執行到"); // 除錯
				
				//修改完成，準備轉交
				request.setAttribute("articleVO", articleVO); // 資料庫update成功後,正確的的empVO物件,存入req
				System.out.println("修改完成的VO=" + articleVO);
				
				String url = request.getContextPath() + "/view/forum_article/ForumIndex.html";
//				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交
//				successView.forward(request, response);
				response.sendRedirect(url);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/view/forum_article/updateArticle.jsp");
				failureView.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
