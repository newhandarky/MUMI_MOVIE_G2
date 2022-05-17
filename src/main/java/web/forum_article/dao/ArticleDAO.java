package web.forum_article.dao;

import java.io.FileInputStream;
import java.io.IOException;
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

import web.forum_article.entity.ArticleVO;

   

public class ArticleDAO implements ArticleDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO forum_article(mem_id, article_board, article_type, article_subject, "
			+ "article_contain, article_pic, article_publish, article_state) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String DELETE = "DELETE FROM forum_article where article_id = ?";

	private static final String UPDATE = "UPDATE forum_article set article_board = ?, article_type = ?, article_subject = ?, article_contain = ?, "
			+ "article_pic = ?, article_updated = ?,article_state = ?  where article_id = ?";

	private static final String GET_ALL_STMT = "SELECT article_id, mem_id, emp_id, article_board, article_type, article_subject, article_contain, article_pic, article_publish, "
			+ "article_updated, article_like_num, article_dislike_num, article_state, re_article_id FROM forum_article order by article_id desc";
	
	private static final String GET_ONE_STMT = "SELECT article_id, mem_id, emp_id, article_board, article_type, article_subject, article_contain, article_pic, article_publish, "
			+ "article_updated, article_like_num, article_dislike_num, article_state, re_article_id FROM forum_article where article_id = ?";

	private static final String GET_BOARD_STMT = "SELECT article_id, mem_id, emp_id, article_board, article_type, article_subject, article_contain, article_pic, article_publish, "
			+ "article_updated, article_like_num, article_dislike_num, article_state, re_article_id FROM forum_article where article_board = ? order by article_id desc";

	private static final String GET_TYPE_STMT = "SELECT article_id, mem_id, emp_id, article_board, article_type, article_subject, article_contain, article_pic, article_publish, "
			+ "article_updated, article_like_num, article_dislike_num, article_state, re_article_id FROM forum_article where article_type = ? order by article_id";

	private static final String GET_STATE_STMT = "SELECT article_id, mem_id, emp_id, article_board, article_type, article_subject, article_contain, article_pic, article_publish, "
			+ "article_updated, article_like_num, article_dislike_num, article_state, re_article_id FROM forum_article where article_state = ? order by article_id";

	@Override
	public void insert(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		FileInputStream fis = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleVO.getMem_id());
			pstmt.setString(2, articleVO.getArticle_board());
			pstmt.setString(3, articleVO.getArticle_type());
			pstmt.setString(4, articleVO.getArticle_subject());
			pstmt.setString(5, articleVO.getArticle_contain());
			pstmt.setBytes(6, articleVO.getArticle_pic());
			pstmt.setTimestamp(7, articleVO.getArticle_publish());
			pstmt.setString(8, articleVO.getArticle_state());
			
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
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		FileInputStream fis = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, articleVO.getArticle_board());
			pstmt.setString(2, articleVO.getArticle_type());
			pstmt.setString(3, articleVO.getArticle_subject());
			pstmt.setString(4, articleVO.getArticle_contain());
			pstmt.setBytes(5, articleVO.getArticle_pic());
			pstmt.setTimestamp(6, articleVO.getArticle_updated());
			pstmt.setString(7, articleVO.getArticle_state());
			pstmt.setInt(8, articleVO.getArticle_id());
			
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
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer article_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, article_id);

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
	public List<ArticleVO> getAll() {
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				articleVO = new ArticleVO();				
				articleVO.setArticle_id(rs.getInt("article_id"));
				articleVO.setMem_id(rs.getInt("mem_id"));
				articleVO.setEmp_id(rs.getInt("emp_id"));
				articleVO.setArticle_board(rs.getString("article_board"));
				articleVO.setArticle_type(rs.getString("article_type"));
				articleVO.setArticle_subject(rs.getString("article_subject"));
				articleVO.setArticle_contain(rs.getString("article_contain"));			
				articleVO.setArticle_pic(rs.getBytes("article_pic"));
				articleVO.setArticle_publish(rs.getTimestamp("article_publish"));
				articleVO.setArticle_updated(rs.getTimestamp("article_updated"));
				articleVO.setArticle_like_num(rs.getInt("article_like_num"));
				articleVO.setArticle_dislike_num(rs.getInt("article_dislike_num"));
				articleVO.setArticle_state(rs.getString("article_state"));
				articleVO.setRe_article_id(rs.getInt("re_article_id"));

				list.add(articleVO);// Store the row in the list
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
	public List<ArticleVO> findByBoard(String article_board) {

		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BOARD_STMT);

			pstmt.setString(1, article_board);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticle_id(rs.getInt("article_id"));
				articleVO.setMem_id(rs.getInt("mem_id"));
				articleVO.setEmp_id(rs.getInt("emp_id"));
				articleVO.setArticle_board(rs.getString("article_board"));
				articleVO.setArticle_type(rs.getString("article_type"));
				articleVO.setArticle_subject(rs.getString("article_subject"));
				articleVO.setArticle_contain(rs.getString("article_contain"));
				articleVO.setArticle_pic(rs.getBytes("article_pic"));
				articleVO.setArticle_publish(rs.getTimestamp("article_publish"));
				articleVO.setArticle_updated(rs.getTimestamp("article_updated"));
				articleVO.setArticle_like_num(rs.getInt("article_like_num"));
				articleVO.setArticle_dislike_num(rs.getInt("article_dislike_num"));
				articleVO.setArticle_state(rs.getString("article_state"));
				articleVO.setRe_article_id(rs.getInt("re_article_id"));
				
				list.add(articleVO);
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
	public List<ArticleVO> findByType(String article_type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVO> findByState(String article_state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticleVO findByPrimaryKey(Integer article_id) {
		
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, article_id);
	
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticle_id(rs.getInt("article_id"));
				articleVO.setMem_id(rs.getInt("mem_id"));
				articleVO.setEmp_id(rs.getInt("emp_id"));
				articleVO.setArticle_board(rs.getString("article_board"));
				articleVO.setArticle_type(rs.getString("article_type"));
				articleVO.setArticle_subject(rs.getString("article_subject"));
				articleVO.setArticle_contain(rs.getString("article_contain"));
				articleVO.setArticle_pic(rs.getBytes("article_pic"));
				articleVO.setArticle_publish(rs.getTimestamp("article_publish"));
				articleVO.setArticle_updated(rs.getTimestamp("article_updated"));
				articleVO.setArticle_like_num(rs.getInt("article_like_num"));
				articleVO.setArticle_dislike_num(rs.getInt("article_dislike_num"));
				articleVO.setArticle_state(rs.getString("article_state"));
				articleVO.setRe_article_id(rs.getInt("re_article_id"));	
				
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
		return articleVO;
	}
	
	

}
