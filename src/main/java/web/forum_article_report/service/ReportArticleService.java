package web.forum_article_report.service;

import java.sql.Timestamp;
import java.util.List;

import web.forum_article_report.dao.ReportArticleDao;
import web.forum_article_report.dao.ReportArticleDaoImpl;
import web.forum_article_report.entity.ReportArticle;


public class ReportArticleService {

	private ReportArticleDao reportArticleDao;

	public ReportArticleService() {
		reportArticleDao = new ReportArticleDaoImpl();
	}

	public ReportArticle addReportArticle(Integer mem_id, Integer article_id, String report_article_reason,
			String report_article_state) {

		ReportArticle reportArticle = new ReportArticle();
		
		reportArticle.setMem_id(mem_id);
		reportArticle.setArticle_id(article_id);
		reportArticle.setReport_article_reason(report_article_reason);
		reportArticle.setReport_article_state(report_article_state);
		reportArticleDao.insert(reportArticle);
		
		return reportArticle;
	}

	public List<ReportArticle> getAll() {
		return reportArticleDao.getAll();
	}
	
	public List<ReportArticle> indexGetAll() {
		return reportArticleDao.indexGetAll();
	}
	
	public ReportArticle getOneReportArticle(Integer report_article_id) {
		return reportArticleDao.findByPrimaryKey(report_article_id);
	}
	
	public ReportArticle changeReportState(Integer re_article_id, Timestamp report_update_time, String report_article_state) {

		ReportArticle reportArticle = new ReportArticle();
		reportArticle.setReport_article_id(re_article_id);
		reportArticle.setReport_update_time(report_update_time);
		reportArticle.setReport_article_state(report_article_state);
		
		reportArticleDao.changeReportState(reportArticle);

		return reportArticle;
	}

}
