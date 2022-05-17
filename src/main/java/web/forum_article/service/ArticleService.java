package web.forum_article.service;

import java.sql.Timestamp;
import java.util.List;

import web.forum_article.dao.ArticleDAO;
import web.forum_article.dao.ArticleDAO_interface;
import web.forum_article.entity.ArticleVO; 

public class ArticleService {

	private ArticleDAO_interface dao;

	public ArticleService() {
		dao = new ArticleDAO();
	}

	public ArticleVO addArticle(Integer mem_id, String article_board, String article_type, String article_subject,
			String article_contain, byte[] article_pic, Timestamp article_publish, String article_state) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setMem_id(mem_id);
		articleVO.setArticle_board(article_board);
		articleVO.setArticle_type(article_type);
		articleVO.setArticle_subject(article_subject);
		articleVO.setArticle_contain(article_contain);
		articleVO.setArticle_pic(article_pic);
		articleVO.setArticle_publish(article_publish);
		articleVO.setArticle_state(article_state);
		dao.insert(articleVO);

		return articleVO;
	}
	
	public ArticleVO updateArticle(Integer article_id, String article_board, String article_type, String article_subject, String article_contain,
			byte[] article_pic, Timestamp article_updated, String article_state) {

		ArticleVO articleVO = new ArticleVO();
		
		articleVO.setArticle_id(article_id);
		articleVO.setArticle_board(article_board);
		articleVO.setArticle_type(article_type);
		articleVO.setArticle_subject(article_subject);
		articleVO.setArticle_contain(article_contain);
		articleVO.setArticle_pic(article_pic);
		articleVO.setArticle_updated(article_updated);
		articleVO.setArticle_state(article_state);
		dao.update(articleVO);

		return articleVO;
		
	}

	public void deleteArticle(Integer article_id) {
		dao.delete(article_id);
	}
	
	public List<ArticleVO> getAll() {
		return dao.getAll();
	}
	
	public List<ArticleVO> getOneBoardArticle(String article_board) {
		return dao.findByBoard(article_board);
	}
	
	public ArticleVO getOneArticle(Integer article_id) {
		return dao.findByPrimaryKey(article_id);
	}
	
}
