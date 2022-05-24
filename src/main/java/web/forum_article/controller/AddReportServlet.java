package web.forum_article.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.forum_article_report.entity.ReportArticle;
import web.forum_article_report.service.ReportArticleService;

@WebServlet(urlPatterns = {"/view/forum_article/AddReportServlet"})
public class AddReportServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			//接收資料
			Integer mem_id = Integer.valueOf(request.getParameter("mem_id"));
			Integer article_id = Integer.valueOf(request.getParameter("article_id"));
			String report_article_reason = request.getParameter("report_article_reason");
			String report_article_state = request.getParameter("report_article_state");
			
			
			//驗證資料
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("errors", errors);
			
			//轉換資料
			
			//呼叫Model
			ReportArticle reportArticle = new ReportArticle();
			reportArticle.setMem_id(mem_id);
			reportArticle.setArticle_id(article_id);
			reportArticle.setReport_article_reason(report_article_reason);
			reportArticle.setReport_article_state(report_article_state);
			
			System.out.println("reportArticle=" + reportArticle);
			
			ReportArticleService reportArticleSvc = new ReportArticleService();
			
			reportArticle = reportArticleSvc.addReportArticle(mem_id, article_id, report_article_reason, report_article_state);
			
			System.out.println("PO文的reportArticle=" + reportArticle);
			
			
			String url = request.getContextPath() + "/view/forum_article/ForumIndex.html";
			response.sendRedirect(url);
			
		} catch (Exception e) {
			e.getMessage();
			String url = request.getContextPath() + "/view/forum_article/ForumIndex.html";
			response.sendRedirect(url);
		}
		
		
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}
	

}
