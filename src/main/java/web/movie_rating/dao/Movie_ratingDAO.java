package web.movie_rating.dao;

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

import web.movie_rating.entity.Movie_ratingVO;

public class Movie_ratingDAO implements Movie_ratingDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	
	private static final String INSERT_STMT = "INSERT INTO MOVIE_RATING (movie_rating_ch, movie_rating_en, movie_rating_pic) "
			+ "VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT movie_rating_id, movie_rating_ch, movie_rating_en, movie_rating_pic "
			+ "FROM MOVIE_RATING order by movie_rating_id";
	private static final String GET_ONE_STMT = "SELECT movie_rating_id, movie_rating_ch, movie_rating_en, movie_rating_pic "
			+ "FROM MOVIE_RATING where movie_rating_id = ?";
	private static final String UPDATE = "UPDATE MOVIE_RATING set movie_rating_ch = ?, movie_rating_en = ?, movie_rating_pic = ? where movie_rating_id = ?";
	private static final String DELETE = "DELETE FROM MOVIE_RATING where movie_rating_id = ?";
	
	@Override
	public void insert(Movie_ratingVO movie_ratingVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, movie_ratingVO.getMovie_rating_ch());
			pstmt.setString(2, movie_ratingVO.getMovie_rating_en());
			pstmt.setBytes(3, movie_ratingVO.getMovie_rating_pic());
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
	public void update(Movie_ratingVO movie_ratingVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			
			pstmt.setString(1, movie_ratingVO.getMovie_rating_ch());
			pstmt.setString(2, movie_ratingVO.getMovie_rating_en());
			pstmt.setBytes(3, movie_ratingVO.getMovie_rating_pic());
			pstmt.setInt(4, movie_ratingVO.getMovie_rating_id());

			
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
	public void delete(Integer movie_rating_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, movie_rating_id);

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
	public Movie_ratingVO findByPrimaryKey(Integer movie_rating_id) {
		Movie_ratingVO movie_ratingVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, movie_rating_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movie_ratingVO = new Movie_ratingVO();
				movie_ratingVO.setMovie_rating_id(rs.getInt("movie_rating_id"));
				movie_ratingVO.setMovie_rating_ch(rs.getString("movie_rating_ch"));
				movie_ratingVO.setMovie_rating_en(rs.getString("movie_rating_en"));
				movie_ratingVO.setMovie_rating_pic(rs.getBytes("movie_rating_pic"));


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

		return movie_ratingVO;
	}
	
	@Override
	public List<Movie_ratingVO> getAll(){
		
		List<Movie_ratingVO> list = new ArrayList<Movie_ratingVO>();
		Movie_ratingVO movie_ratingVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movie_ratingVO = new Movie_ratingVO();
				movie_ratingVO.setMovie_rating_id(rs.getInt("movie_rating_id"));
				movie_ratingVO.setMovie_rating_ch(rs.getString("movie_rating_ch"));
				movie_ratingVO.setMovie_rating_en(rs.getString("movie_rating_en"));
				movie_ratingVO.setMovie_rating_pic(rs.getBytes("movie_rating_pic"));
				list.add(movie_ratingVO);
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
