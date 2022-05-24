package web.forum_article_report.entity;

import java.sql.Timestamp;

public class ReportArticle {

	private Integer report_article_id;

	private Integer mem_id;

	private Integer article_id;

	private Integer emp_id;

	private String report_article_reason;

	private Timestamp report_article_time;

	private String report_article_state;

	private Timestamp report_update_time;
	
	private String article_board;
	private String article_type;
	private String article_subject;
	private String article_contain;
	private String article_state;
	private Integer article_like_num;
	
	@Override
	public String toString() {
		return "ReportArticle [report_article_id=" + report_article_id + ", mem_id=" + mem_id + ", article_id="
				+ article_id + ", emp_id=" + emp_id + ", report_article_reason=" + report_article_reason
				+ ", report_article_time=" + report_article_time + ", report_article_state=" + report_article_state
				+ ", report_update_time=" + report_update_time + ", article_board=" + article_board + ", article_type="
				+ article_type + ", article_subject=" + article_subject + ", article_contain=" + article_contain
				+ ", article_state=" + article_state + ", article_like_num=" + article_like_num + "]";
	}
	
	public Integer getReport_article_id() {
		return report_article_id;
	}
	public void setReport_article_id(Integer report_article_id) {
		this.report_article_id = report_article_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public String getReport_article_reason() {
		return report_article_reason;
	}
	public void setReport_article_reason(String report_article_reason) {
		this.report_article_reason = report_article_reason;
	}
	public Timestamp getReport_article_time() {
		return report_article_time;
	}
	public void setReport_article_time(Timestamp report_article_time) {
		this.report_article_time = report_article_time;
	}
	public String getReport_article_state() {
		return report_article_state;
	}
	public void setReport_article_state(String report_article_state) {
		this.report_article_state = report_article_state;
	}
	public Timestamp getReport_update_time() {
		return report_update_time;
	}
	public void setReport_update_time(Timestamp report_update_time) {
		this.report_update_time = report_update_time;
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
	public String getArticle_state() {
		return article_state;
	}
	public void setArticle_state(String article_state) {
		this.article_state = article_state;
	}
	public Integer getArticle_like_num() {
		return article_like_num;
	}
	public void setArticle_like_num(Integer article_like_num) {
		this.article_like_num = article_like_num;
	}


}
