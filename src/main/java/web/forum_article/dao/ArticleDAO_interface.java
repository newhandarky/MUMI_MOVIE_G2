package web.forum_article.dao;

import java.util.List;

import web.forum_article.entity.ArticleVO;


public interface ArticleDAO_interface {
	public void insert(ArticleVO articleVO);

	public void update(ArticleVO articleVO);

	public void delete(Integer article_id);
	
	public ArticleVO findByPrimaryKey(Integer article_id);

	public List<ArticleVO> findByBoard(String article_board);
	
	public List<ArticleVO> findByType(String article_type);
	
	public List<ArticleVO> findByState(String article_state);

//	public Optional<ArticleVO> getAll();
	public List<ArticleVO> getAll();
	
	public List<ArticleVO> indexGetAll();
	
	public void inserReArticleId(ArticleVO articleVO);
	
	public void changeArticleState(ArticleVO articleVO);
	
	public void addAricleVisitCount(ArticleVO articleVO);
	
	public List<ArticleVO> getHotAll();

	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}
