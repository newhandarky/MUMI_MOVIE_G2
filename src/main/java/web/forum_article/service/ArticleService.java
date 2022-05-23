package web.forum_article.service;

import java.sql.Timestamp;
import java.util.List;

import web.forum_article.dao.ArticleDAO;
import web.forum_article.dao.ArticleDAO_interface;
import web.forum_article.entity.ArticleVO;

public class ArticleService {

	private ArticleDAO_interface articleDAO;

	public ArticleService() {
		articleDAO = new ArticleDAO();
	}

	public ArticleService(ArticleDAO_interface articleDAO) {
		this.articleDAO = articleDAO;
	}

	public ArticleVO addArticle(Integer mem_id, String article_board, String article_type, String article_subject,
			String article_contain, byte[] article_pic, Timestamp article_publish, String article_state, Integer re_article_id) {

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
		articleDAO.insert(articleVO);

		return articleVO;
	}

	public ArticleVO addReArticleId(Integer article_id, Integer re_article_id) {
		ArticleVO articleVO = new ArticleVO();

		articleVO.setArticle_id(article_id);
		articleVO.setRe_article_id(re_article_id);

		articleDAO.inserReArticleId(articleVO);
		return articleVO;
	}
	
	public ArticleVO addAricleVisitCount(Integer article_id, Integer article_like_num) {
		ArticleVO articleVO = new ArticleVO();
		
		articleVO.setArticle_id(article_id);
		articleVO.setArticle_like_num(article_like_num);

		articleDAO.addAricleVisitCount(articleVO);
		return articleVO;
	}

	public ArticleVO updateArticle(Integer article_id, String article_board, String article_type,
			String article_subject, String article_contain, byte[] article_pic, Timestamp article_updated,
			String article_state) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArticle_id(article_id);
		articleVO.setArticle_board(article_board);
		articleVO.setArticle_type(article_type);
		articleVO.setArticle_subject(article_subject);
		articleVO.setArticle_contain(article_contain);
		articleVO.setArticle_pic(article_pic);
		articleVO.setArticle_updated(article_updated);
		articleVO.setArticle_state(article_state);
		articleDAO.update(articleVO);

		return articleVO;

	}

	public ArticleVO changeArticleState(Integer article_id, Timestamp article_updated, String article_state) {

		ArticleVO articleVO = new ArticleVO();
		articleVO.setArticle_id(article_id);
		articleVO.setArticle_updated(article_updated);
		articleVO.setArticle_state(article_state);
		
		articleDAO.changeArticleState(articleVO);

		return articleVO;
	}

	public void deleteArticle(Integer article_id) {
		articleDAO.delete(article_id);
	}

	public List<ArticleVO> getAll() {
		return articleDAO.getAll();
	}
	
	public List<ArticleVO> indexGetAll() {
		return articleDAO.indexGetAll();
	}
	
	public List<ArticleVO> getHotAll() {
		return articleDAO.getHotAll();
	}
	
	

	public List<ArticleVO> getOneBoardArticle(String article_board) {
		return articleDAO.findByBoard(article_board);
	}

	public ArticleVO getOneArticle(Integer article_id) {
		return articleDAO.findByPrimaryKey(article_id);
	}

}
