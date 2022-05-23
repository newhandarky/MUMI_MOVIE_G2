package web.forum_article.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.forum_article.entity.ArticleVO;
import web.forum_article.service.ArticleService;

@WebServlet("/view/forum_article/ChangeArticleStateServlet")
public class ChangeArticleStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {

			// 接收資料
			String temp1 = request.getParameter("article_id");
			Integer temp2 = new Integer(request.getParameter("state"));
			Timestamp article_updated = new Timestamp(System.currentTimeMillis());

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
			
			String article_state = articleVO.getArticle_state();
			if (temp2 == 0) {
				article_state = "會員已刪除";
			}
			System.out.println(article_state);
			
			articleVO.setArticle_id(article_id);
			articleVO.setArticle_updated(article_updated);
			articleVO.setArticle_state(article_state);
			// 修改資料

			articleVO = articleSvc.changeArticleState(article_id, article_updated, article_state);
			System.out.println("updateArticle()有執行到"); // 除錯
			
			articleVO = articleSvc.getOneArticle(article_id);

			// 修改完成，準備轉交
			request.setAttribute("articleVO", articleVO); // 資料庫update成功後,正確的的empVO物件,存入req
			System.out.println("修改完成的VO=" + articleVO);

			String url = request.getContextPath() + "/view/forum_article/ForumIndex.html";
//				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交
//				successView.forward(request, response);
			response.sendRedirect(url);

		} catch (Exception e) {
			e.getMessage();
			String url = request.getContextPath() + "/view/forum_article/ForumIndex.html";
			response.sendRedirect(url);
//			RequestDispatcher failureView = request.getRequestDispatcher("/view/forum_article/ForumIndex.html");
//			failureView.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
