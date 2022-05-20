package web.movie.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jboss.jandex.Main;

import web.hall_seat.dao.Hall_SeatDAO;
import web.movie.entity.MovieVO;

public class MovieDAO implements MovieDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO MOVIE (movie_state_id, movie_rating_id, movie_ch, movie_en, movie_runtime, release_date, movie_poster, movie_slide_poster, movie_intro, casts, director, trailer) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT movie_id, movie_state_id, movie_rating_id, emp_id, movie_updated_time, movie_ch, movie_en, movie_runtime, release_date, movie_poster, movie_slide_poster, movie_intro, "
			+ "casts, director, trailer, expect_num, sati_num, movie_likes, expect_total, sati_total FROM MOVIE order by movie_id";
	private static final String GET_ONE_STMT = "SELECT movie_id, movie_state_id, movie_rating_id, emp_id, movie_updated_time, movie_ch, movie_en, movie_runtime, release_date, movie_poster, movie_slide_poster, movie_intro, "
			+ "casts, director, trailer, expect_num, sati_num, movie_likes, expect_total, sati_total FROM MOVIE where movie_id = ?";
	private static final String DELETE = "DELETE FROM MOVIE where movie_id = ?";
	private static final String UPDATE = "UPDATE MOVIE set movie_state_id=?, movie_rating_id=?, movie_ch=?, movie_en=?, movie_runtime=?, release_date=?, movie_poster=?, movie_slide_poster=?, movie_intro=?, casts=?, director=?, trailer=?, movie_updated_time=?  where movie_id = ?";
	private static final String GET_BY_STATE_ID_STMT = "SELECT movie_id, movie_state_id, movie_rating_id, emp_id, movie_updated_time, movie_ch, movie_en, movie_runtime, release_date, movie_poster, movie_slide_poster, movie_intro, "
			+ "casts, director, trailer, expect_num, sati_num, movie_likes, expect_total, sati_total FROM MOVIE where movie_state_id=? order by release_date desc";

	@Override
	public void insert(MovieVO movieVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, movieVO.getMovie_state_id());
			pstmt.setInt(2, movieVO.getMovie_rating_id());
			pstmt.setString(3, movieVO.getMovie_ch());
			pstmt.setString(4, movieVO.getMovie_en());
			pstmt.setInt(5, movieVO.getMovie_runtime());
			pstmt.setDate(6, movieVO.getRelease_date());
			pstmt.setBytes(7, movieVO.getMovie_poster());
			pstmt.setBytes(8, movieVO.getMovie_slide_poster());
			pstmt.setString(9, movieVO.getMovie_intro());
			pstmt.setString(10, movieVO.getCasts());
			pstmt.setString(11, movieVO.getDirector());
			pstmt.setString(12, movieVO.getTrailer());
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
	public void update(MovieVO movieVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, movieVO.getMovie_state_id());
			pstmt.setInt(2, movieVO.getMovie_rating_id());
			pstmt.setString(3, movieVO.getMovie_ch());
			pstmt.setString(4, movieVO.getMovie_en());
			pstmt.setInt(5, movieVO.getMovie_runtime());
			pstmt.setDate(6, movieVO.getRelease_date());
			pstmt.setBytes(7, movieVO.getMovie_poster());
			pstmt.setBytes(8, movieVO.getMovie_slide_poster());
			pstmt.setString(9, movieVO.getMovie_intro());
			pstmt.setString(10, movieVO.getCasts());
			pstmt.setString(11, movieVO.getDirector());
			pstmt.setString(12, movieVO.getTrailer());
			pstmt.setTimestamp(13, movieVO.getMovie_updated_time());
			pstmt.setInt(14, movieVO.getMovie_id());

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
	public void delete(Integer movie_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, movie_id);

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
	public MovieVO findByPrimaryKey(Integer movie_id) {
		MovieVO movieVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, movie_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movieVO = new MovieVO();
				movieVO.setMovie_id(rs.getInt("movie_id"));
				movieVO.setMovie_state_id(rs.getInt("movie_state_id"));
				movieVO.setMovie_rating_id(rs.getInt("movie_rating_id"));
				movieVO.setEmp_id(rs.getInt("emp_id"));
				movieVO.setMovie_updated_time(rs.getTimestamp("movie_updated_time"));
				movieVO.setMovie_ch(rs.getString("movie_ch"));
				movieVO.setMovie_en(rs.getString("movie_en"));
				movieVO.setMovie_runtime(rs.getInt("movie_runtime"));
				movieVO.setRelease_date(rs.getDate("release_date"));
				movieVO.setMovie_poster(rs.getBytes("movie_poster"));
				movieVO.setMovie_slide_poster(rs.getBytes("movie_slide_poster"));
				movieVO.setMovie_intro(rs.getString("movie_intro"));
				movieVO.setCasts(rs.getString("casts"));
				movieVO.setDirector(rs.getString("director"));
				movieVO.setTrailer(rs.getString("trailer"));
				movieVO.setExpect_num(rs.getInt("expect_num"));
				movieVO.setSati_num(rs.getInt("sati_num"));
				movieVO.setMovie_likes(rs.getInt("movie_likes"));
				movieVO.setExpect_total(rs.getInt("expect_total"));
				movieVO.setSati_total(rs.getInt("sati_total"));
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

		return movieVO;
	}

	@Override
	public List<MovieVO> getAll() {

		List<MovieVO> list = new ArrayList<MovieVO>();
		MovieVO movieVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movieVO = new MovieVO();
				movieVO.setMovie_id(rs.getInt("movie_id"));
				movieVO.setMovie_state_id(rs.getInt("movie_state_id"));
				movieVO.setMovie_rating_id(rs.getInt("movie_rating_id"));
				movieVO.setEmp_id(rs.getInt("emp_id"));
				movieVO.setMovie_updated_time(rs.getTimestamp("movie_updated_time"));
				movieVO.setMovie_ch(rs.getString("movie_ch"));
				movieVO.setMovie_en(rs.getString("movie_en"));
				movieVO.setMovie_runtime(rs.getInt("movie_runtime"));
				movieVO.setRelease_date(rs.getDate("release_date"));
				movieVO.setMovie_poster(rs.getBytes("movie_poster"));
				movieVO.setMovie_slide_poster(rs.getBytes("movie_slide_poster"));
				movieVO.setMovie_intro(rs.getString("movie_intro"));
				movieVO.setCasts(rs.getString("casts"));
				movieVO.setDirector(rs.getString("director"));
				movieVO.setTrailer(rs.getString("trailer"));
				movieVO.setExpect_num(rs.getInt("expect_num"));
				movieVO.setSati_num(rs.getInt("sati_num"));
				movieVO.setMovie_likes(rs.getInt("movie_likes"));
				movieVO.setExpect_total(rs.getInt("expect_total"));
				movieVO.setSati_total(rs.getInt("sati_total"));
				list.add(movieVO);
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

	public static byte[] pic(String pic1) {

		File fi = new File(pic1);
		try {
			FileInputStream fis = new FileInputStream(fi);
			byte[] bt = fis.readAllBytes();
			return bt;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<MovieVO> getByState_id(Integer  movie_state_id) {
		List<MovieVO> list = new ArrayList<MovieVO>();
		MovieVO movieVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_STATE_ID_STMT);
			pstmt.setInt(1,  movie_state_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				movieVO = new MovieVO();
				movieVO.setMovie_id(rs.getInt("movie_id"));
				movieVO.setMovie_state_id(rs.getInt("movie_state_id"));
				movieVO.setMovie_rating_id(rs.getInt("movie_rating_id"));
				movieVO.setEmp_id(rs.getInt("emp_id"));
				movieVO.setMovie_updated_time(rs.getTimestamp("movie_updated_time"));
				movieVO.setMovie_ch(rs.getString("movie_ch"));
				movieVO.setMovie_en(rs.getString("movie_en"));
				movieVO.setMovie_runtime(rs.getInt("movie_runtime"));
				movieVO.setRelease_date(rs.getDate("release_date"));
				movieVO.setMovie_poster(rs.getBytes("movie_poster"));
				movieVO.setMovie_slide_poster(rs.getBytes("movie_slide_poster"));
				movieVO.setMovie_intro(rs.getString("movie_intro"));
				movieVO.setCasts(rs.getString("casts"));
				movieVO.setDirector(rs.getString("director"));
				movieVO.setTrailer(rs.getString("trailer"));
				movieVO.setExpect_num(rs.getInt("expect_num"));
				movieVO.setSati_num(rs.getInt("sati_num"));
				movieVO.setMovie_likes(rs.getInt("movie_likes"));
				movieVO.setExpect_total(rs.getInt("expect_total"));
				movieVO.setSati_total(rs.getInt("sati_total"));
				list.add(movieVO);
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
