package web.movie_type.dao;

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

import web.movie_type.entity.Movie_typeVO;

public class Movie_typeDAO implements Movie_typeDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	
	
	private static final String INSERT_STMT = "INSERT INTO MOVIE_TYPE (movie_type_en, movie_type_ch) "
			+ "VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT movie_type_id, movie_type_en, movie_type_ch "
			+ "FROM MOVIE_TYPE order by movie_type_id";
	private static final String GET_ONE_STMT = "SELECT movie_type_id, movie_type_en, movie_type_ch "
			+ "FROM MOVIE_TYPE where movie_type_id = ?";
	private static final String UPDATE = "UPDATE MOVIE_TYPE set movie_type_en = ?, movie_type_ch = ? where movie_type_id = ?";
	private static final String DELETE = "DELETE FROM MOVIE_TYPE where movie_type_id = ?";
	
	
	@Override
	public void insert(Movie_typeVO movie_typeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, movie_typeVO.getMovie_type_en());
			pstmt.setString(2, movie_typeVO.getMovie_type_ch());
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
	public void update(Movie_typeVO movie_typeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			
			pstmt.setString(1, movie_typeVO.getMovie_type_en());
			pstmt.setString(2, movie_typeVO.getMovie_type_ch());
			pstmt.setInt(3, movie_typeVO.getMovie_type_id());

			
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
	public void delete(Integer movie_type_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, movie_type_id);

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
	public Movie_typeVO findByPrimaryKey(Integer movie_type_id) {
		Movie_typeVO movie_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, movie_type_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movie_typeVO = new Movie_typeVO();
				movie_typeVO.setMovie_type_id(rs.getInt("movie_type_id"));
				movie_typeVO.setMovie_type_en(rs.getString("movie_type_en"));
				movie_typeVO.setMovie_type_ch(rs.getString("movie_type_ch"));
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

		return movie_typeVO;
	}
	
	@Override
	public List<Movie_typeVO> getAll(){
		
		List<Movie_typeVO> list = new ArrayList<Movie_typeVO>();
		Movie_typeVO movie_typeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movie_typeVO = new Movie_typeVO();
				movie_typeVO.setMovie_type_id(rs.getInt("movie_type_id"));
				movie_typeVO.setMovie_type_en(rs.getString("movie_type_en"));
				movie_typeVO.setMovie_type_ch(rs.getString("movie_type_ch"));
				list.add(movie_typeVO);
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
