package web.forum_article_report.dao;

import java.util.List;

import web.forum_article_report.entity.ReportArticle;

public interface ReportArticleDao {
	
	public void insert(ReportArticle reportArticle);

	public void update(ReportArticle reportArticle);

	public void delete(ReportArticle reportArticle);
	
	public List<ReportArticle> getAll();
	
	public List<ReportArticle> indexGetAll();
	
	public void changeReportState(ReportArticle reportArticle);
}
