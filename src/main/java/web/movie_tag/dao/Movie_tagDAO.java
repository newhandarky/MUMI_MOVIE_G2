package web.movie_tag.dao;

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

import web.movie_tag.entity.Movie_tagVO;


public class Movie_tagDAO implements Movie_tagDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	
	private static final String INSERT_STMT = "INSERT INTO MOVIE_TAG (movie_id, movie_type_id) "
			+ "VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT movie_tag_id, movie_id, movie_type_id "
			+ "FROM MOVIE_TAG order by movie_tag_id";
	private static final String GET_ONE_STMT = "SELECT movie_tag_id, movie_id, movie_type_id "
			+ "FROM MOVIE_TAG where movie_tag_id = ?";
	private static final String UPDATE = "UPDATE MOVIE_TAG set movie_id = ?, movie_type_id = ? where movie_tag_id = ?";
	private static final String DELETE = "DELETE FROM MOVIE_TAG where movie_tag_id = ?";
	
	@Override
	public void insert(Movie_tagVO movie_tagVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, movie_tagVO.getMovie_id());
			pstmt.setInt(2, movie_tagVO.getMovie_type_id());
			pstmt.executeUpdate();

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
	public void update(Movie_tagVO movie_tagVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			
			pstmt.setInt(1, movie_tagVO.getMovie_id());
			pstmt.setInt(2, movie_tagVO.getMovie_type_id());
			pstmt.setInt(3, movie_tagVO.getMovie_tag_id());

			
			pstmt.executeUpdate();
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
	public void delete(Integer movie_tag_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, movie_tag_id);

			pstmt.executeUpdate();

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
	public Movie_tagVO findByPrimaryKey(Integer movie_tag_id) {
		Movie_tagVO movie_tagVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, movie_tag_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movie_tagVO = new Movie_tagVO();
				movie_tagVO.setMovie_tag_id(rs.getInt("movie_tag_id"));
				movie_tagVO.setMovie_id(rs.getInt("movie_id"));
				movie_tagVO.setMovie_type_id(rs.getInt("movie_type_id"));

			}

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

		return movie_tagVO;
	}
	
	@Override
	public List<Movie_tagVO> getAll(){
		
		List<Movie_tagVO> list = new ArrayList<Movie_tagVO>();
		Movie_tagVO movie_tagVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movie_tagVO = new Movie_tagVO();
				movie_tagVO.setMovie_tag_id(rs.getInt("movie_tag_id"));
				movie_tagVO.setMovie_id(rs.getInt("movie_id"));
				movie_tagVO.setMovie_type_id(rs.getInt("movie_type_id"));	
				list.add(movie_tagVO);
			}

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
	
	
	
	
	
	
	
	
	
	
	
}