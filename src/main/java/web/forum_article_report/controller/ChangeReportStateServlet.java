package web.forum_article_report.controller;

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
import web.forum_article_report.entity.ReportArticle;
import web.forum_article_report.service.ReportArticleService;

@WebServlet("/view/forum_article/ChangeReportStateServlet")
public class ChangeReportStateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {

			// 接收資料
			String temp1 = request.getParameter("report_article_id");
			Integer temp2 = new Integer(request.getParameter("report_article_state"));
			Timestamp report_update_time = new Timestamp(System.currentTimeMillis());
			
			String temp3 = request.getParameter("article_id");
			Timestamp article_updated = new Timestamp(System.currentTimeMillis());
			

			// 驗證資料
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("errors", errors);

			// 轉換資料
			int report_article_id = 0;
			int article_id = 0;
			if (temp1 != null && temp1.length() != 0) {
				try {
					report_article_id = Integer.parseInt(temp1);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("report_article_id", "Article Id must be an integer");
				}
			}
			if (temp3 != null && temp3.length() != 0) {
				try {
					article_id = Integer.parseInt(temp3);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("article_id", "Article Id must be an integer");
				}
			}

			// 呼叫方法
			ReportArticleService reportArticleSvc = new ReportArticleService();
			ReportArticle reportArticle = reportArticleSvc.getOneReportArticle(report_article_id);
			
			String report_article_state = reportArticle.getReport_article_state();
			
			if (temp2 == 1) {
				report_article_state = "管理員已核准";
			}
			
			if (temp2 == 0) {
				report_article_state = "管理員已刪除";
				
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_id);
				
				//修改文章狀態
				String article_state = "已被管理員刪除";
				
				articleVO.setArticle_id(article_id);
				articleVO.setArticle_updated(article_updated);
				articleVO.setArticle_state(article_state);
				
				// 修改資料
				articleVO = articleSvc.changeArticleState(article_id, article_updated, article_state);
						
			}
			
			System.out.println("report_article_state=" + report_article_state);
			
			reportArticle.setReport_article_id(report_article_id);
			reportArticle.setReport_update_time(report_update_time);
			reportArticle.setReport_article_state(report_article_state);
			
			// 修改檢舉文章狀態
			reportArticle = reportArticleSvc.changeReportState(report_article_id, report_update_time, report_article_state);
			System.out.println("changeReportState()有執行到"); // 除錯			
			reportArticle = reportArticleSvc.getOneReportArticle(report_article_id);

			// 修改完成，準備轉交
			request.setAttribute("reportArticle", reportArticle); // 資料庫update成功後,正確的的empVO物件,存入req
			System.out.println("修改完成的reportArticle=" + reportArticle);
			
			String url = request.getContextPath() + "/view/forum_article/AdminForumIndex.html";
			
			response.sendRedirect(url);

		} catch (Exception e) {
			e.getMessage();
			String url = request.getContextPath() + "/view/forum_article/AdminForumIndex.html";
			response.sendRedirect(url);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
