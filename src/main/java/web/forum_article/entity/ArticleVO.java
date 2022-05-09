package web.forum_article.entity;

import java.sql.Timestamp;
import java.util.Arrays;

public class ArticleVO implements java.io.Serializable {
	private Integer article_id;
	private Integer mem_id;
	private Integer emp_id;
	private String article_board;
	private String article_type;
	private String article_subject;
	private String article_contain;
	private byte[] article_pic;
	private Timestamp article_publish;
	private Timestamp article_updated;
	private Integer article_like_num;
	private Integer article_dislike_num;
	private String article_state;
	private Integer re_article_id;
	
	@Override
	public String toString() {
		return "ArticleVO [article_id=" + article_id + ", mem_id=" + mem_id + ", emp_id=" + emp_id + ", article_board="
				+ article_board + ", article_type=" + article_type + ", article_subject=" + article_subject
				+ ", article_contain=" + article_contain + ", article_pic=" + Arrays.toString(article_pic)
				+ ", article_publish=" + article_publish + ", article_updated=" + article_updated
				+ ", article_like_num=" + article_like_num + ", article_dislike_num=" + article_dislike_num
				+ ", article_state=" + article_state + ", re_article_id=" + re_article_id + "]";
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public String getArticle_board() {
		return article_board;
	}
	public void setArticle_board(String article_board) {
		this.article_board = article_board;
	}
	public String getArticle_type() {
		return article_type;
	}
	public void setArticle_type(String article_type) {
		this.article_type = article_type;
	}
	public String getArticle_subject() {
		return article_subject;
	}
	public void setArticle_subject(String article_subject) {
		this.article_subject = article_subject;
	}
	public String getArticle_contain() {
		return article_contain;
	}
	public void setArticle_contain(String article_contain) {
		this.article_contain = article_contain;
	}
	public byte[] getArticle_pic() {
		return article_pic;
	}
	public void setArticle_pic(byte[] article_pic) {
		this.article_pic = article_pic;
	}
	public Timestamp getArticle_publish() {
		return article_publish;
	}
	public void setArticle_publish(Timestamp article_publish) {
		this.article_publish = article_publish;
	}
	public Timestamp getArticle_updated() {
		return article_updated;
	}
	public void setArticle_updated(Timestamp article_updated) {
		this.article_updated = article_updated;
	}
	public Integer getArticle_like_num() {
		return article_like_num;
	}
	public void setArticle_like_num(Integer article_like_num) {
		this.article_like_num = article_like_num;
	}
	public Integer getArticle_dislike_num() {
		return article_dislike_num;
	}
	public void setArticle_dislike_num(Integer article_dislike_num) {
		this.article_dislike_num = article_dislike_num;
	}
	public String getArticle_state() {
		return article_state;
	}
	public void setArticle_state(String article_state) {
		this.article_state = article_state;
	}
	public Integer getRe_article_id() {
		return re_article_id;
	}
	public void setRe_article_id(Integer re_article_id) {
		this.re_article_id = re_article_id;
	}
	
}
