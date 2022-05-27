package web.forum_article_report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.forum_article_report.entity.ReportArticle;

public class ReportArticleDaoImpl implements ReportArticleDao {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_REPORT_STMT = "INSERT INTO forum_article_report(mem_id, article_id, report_article_reason, report_article_state) VALUES (?, ?, ?, ?)";
	
	private static final String GET_ALL_REPORT_STMT = "SELECT FAR.report_article_id, FAR.mem_id, FAR.article_id, FAR.report_article_reason, FAR.report_article_state, FAR.emp_id, FAR.report_article_time, FAR.report_update_time, "
			+ "FA.article_subject, FA.article_board, FA.article_type, FA.article_contain, FA.article_state, FA.article_visit_count "
			+ "FROM forum_article_report FAR JOIN forum_article FA "
			+ "on FAR.article_id = FA.article_id "
			+ "ORDER BY FAR.report_update_time desc";
	
	private static final String GET_ALL_REPORT_DEAL_STMT = "SELECT FAR.report_article_id, FAR.mem_id, FAR.article_id, FAR.report_article_reason, FAR.report_article_state, FAR.emp_id, FAR.report_article_time, FAR.report_update_time, "
			+ "FA.article_subject, FA.article_board, FA.article_type, FA.article_contain, FA.article_state, FA.article_visit_count "
			+ "FROM forum_article_report FAR JOIN forum_article FA "
			+ "on FAR.article_id = FA.article_id "
			+ "ORDER BY FAR.report_update_time desc "
			+ "WHERE article_state NOT LIKE '%刪除%'";
	
	
	private static final String GET_ONE_REPORT_STMT = "SELECT report_article_id, mem_id, article_id, report_article_reason, report_article_state, emp_id, report_article_time, report_update_time FROM forum_article_report  where report_article_id = ?";

	private static final String CHANGE_REPORT_STATE = "UPDATE forum_article_report set report_article_state = ?, report_update_time = ? where report_article_id = ?";
	
	@Override
	public void insert(ReportArticle reportArticle) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_REPORT_STMT);

			pstmt.setInt(1, reportArticle.getMem_id());
			pstmt.setInt(2, reportArticle.getArticle_id());
			pstmt.setString(3, reportArticle.getReport_article_reason());
			pstmt.setString(4, reportArticle.getReport_article_state());

		
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ReportArticle reportArticle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ReportArticle reportArticle) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ReportArticle> getAll() {
		List<ReportArticle> list = new ArrayList<ReportArticle>();
		ReportArticle reportArticle = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_REPORT_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				reportArticle = new ReportArticle();				
				reportArticle.setReport_article_id(rs.getInt("report_article_id"));
				reportArticle.setMem_id(rs.getInt("mem_id"));
				reportArticle.setArticle_id(rs.getInt("article_id"));
				reportArticle.setEmp_id(rs.getInt("emp_id"));
				reportArticle.setReport_article_reason(rs.getString("report_article_reason"));
				reportArticle.setReport_article_state(rs.getString("report_article_state"));
				reportArticle.setArticle_board(rs.getString("article_board"));			
				reportArticle.setArticle_type(rs.getString("article_type"));
				reportArticle.setArticle_subject(rs.getString("article_subject"));
				reportArticle.setArticle_visit_count(rs.getInt("article_visit_count"));
				reportArticle.setArticle_contain(rs.getString("article_contain"));
				reportArticle.setArticle_state(rs.getString("article_state"));
				reportArticle.setReport_article_time(rs.getTimestamp("report_article_time"));
				reportArticle.setReport_update_time(rs.getTimestamp("report_update_time"));
;

				list.add(reportArticle);// Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}		
		}
		return list;
	}
	
	@Override
	public List<ReportArticle> indexGetAll() {
		List<ReportArticle> list = new ArrayList<ReportArticle>();
		ReportArticle reportArticle = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_REPORT_DEAL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				reportArticle = new ReportArticle();				
				reportArticle.setReport_article_id(rs.getInt("report_article_id"));
				reportArticle.setMem_id(rs.getInt("mem_id"));
				reportArticle.setArticle_id(rs.getInt("article_id"));
				reportArticle.setEmp_id(rs.getInt("emp_id"));
				reportArticle.setReport_article_reason(rs.getString("report_article_reason"));
				reportArticle.setReport_article_state(rs.getString("report_article_state"));
				reportArticle.setArticle_board(rs.getString("article_board"));			
				reportArticle.setArticle_type(rs.getString("article_type"));
				reportArticle.setArticle_subject(rs.getString("article_subject"));
				reportArticle.setArticle_visit_count(rs.getInt("article_visit_count"));
				reportArticle.setArticle_contain(rs.getString("article_contain"));
				reportArticle.setArticle_state(rs.getString("article_state"));
				reportArticle.setReport_article_time(rs.getTimestamp("report_article_time"));
				reportArticle.setReport_update_time(rs.getTimestamp("report_update_time"));
;

				list.add(reportArticle);// Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}		
		}
		return list;
	}
	
	@Override
	public ReportArticle findByPrimaryKey(Integer report_article_id) {
		
		ReportArticle reportArticle = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_REPORT_STMT);
			
			pstmt.setInt(1, report_article_id);
	
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				reportArticle = new ReportArticle();
				reportArticle.setReport_article_id(rs.getInt("report_article_id"));
				reportArticle.setMem_id(rs.getInt("mem_id"));
				reportArticle.setArticle_id(rs.getInt("article_id"));
				reportArticle.setEmp_id(rs.getInt("emp_id"));
				reportArticle.setReport_article_reason(rs.getString("report_article_reason"));
				reportArticle.setReport_article_state(rs.getString("report_article_state"));
				reportArticle.setReport_article_time(rs.getTimestamp("report_article_time"));
				reportArticle.setReport_update_time(rs.getTimestamp("report_update_time"));				
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return reportArticle;
	}

	@Override
	public void changeReportState(ReportArticle reportArticle) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGE_REPORT_STATE);

			pstmt.setString(1, reportArticle.getReport_article_state());
			pstmt.setTimestamp(2, reportArticle.getReport_update_time());
			pstmt.setInt(3, reportArticle.getReport_article_id());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		

	}

}
