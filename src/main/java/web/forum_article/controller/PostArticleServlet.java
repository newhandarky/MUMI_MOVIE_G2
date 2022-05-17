package web.forum_article.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.forum_article.entity.ArticleVO;
import web.forum_article.service.ArticleService;


@WebServlet("/PostArticleServlet")
@MultipartConfig
public class PostArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//接收資料
		Integer mem_id = new Integer(request.getParameter("mem_id"));
		String article_board = request.getParameter("article_board");
		String article_type = request.getParameter("article_type");
		String article_subject = request.getParameter("article_subject");
		String article_contain = request.getParameter("article_contain");
		InputStream defaultPic = getServletContext().getResourceAsStream("/view/forum_article/NoData/none3.jpg");
		
		byte[] article_pic = ((request.getPart("article_pic")).getInputStream()).readAllBytes();
		if (article_pic.length == 0) {
			article_pic = defaultPic.readAllBytes();
		}
		Timestamp article_publish = new Timestamp(System.currentTimeMillis());
		String article_state = request.getParameter("article_state");
	
		
		//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		//轉換資料
		
		//呼叫Model		
		ArticleVO articleVO = new ArticleVO();
		articleVO.setMem_id(mem_id);
		articleVO.setArticle_board(article_board);
		articleVO.setArticle_type(article_type);
		articleVO.setArticle_subject(article_subject);
		articleVO.setArticle_contain(article_contain);
		articleVO.setArticle_pic(article_pic);
		articleVO.setArticle_publish(article_publish);
		articleVO.setArticle_state(article_state);
		
		System.out.println("articleVO=" + articleVO);
		
		ArticleService articleSvc = new ArticleService();
		articleVO = articleSvc.addArticle(mem_id, article_board, article_type, article_subject, article_contain,
				article_pic, article_publish, article_state);
		
		String url = request.getContextPath() + "/view/forum_article/ForumIndex.html";
//		RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交
//		successView.forward(request, response);
		response.sendRedirect(url);
		
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

}
