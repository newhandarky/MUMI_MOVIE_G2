package web.movie_time.dao;

import java.sql.Connection;
import java.sql.Date;
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
import web.movie_time.entity.Movie_timeVO;

public class Movie_timeDAO implements Movie_timeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into movie_time(hall_id, movie_id, showing, showing_date) "
			+ "values(?, ?, ?, ?)";
	private static final String UPDATE = "update movie_time set hall_id=?, movie_id=?, showing=?, showing_date=? "
			+ "where movie_time_id=?";
	private static final String DELETE = "delete from movie_time where movie_time_id=?";
	private static final String GET_ALL_STMT = "select movie_time_id, hall_id, movie_id, showing, showing_date "
			+ "from movie_time order by movie_time_id";
	private static final String GET_ONE_STMT = "select a.movie_time_id, a.hall_id, b.hall_name, a.movie_id, a.showing, a.showing_date "
			+ "from movie_time as a join hall as b "
			+ "where a.hall_id = b.hall_id and movie_time_id = ?";
	private static final String GET_REPEAT_STMT = "SELECT hall_id, showing, showing_date from movie_time "
			+ "where hall_id = ? and showing = ? and showing_date = ?";
	private static final String GET_RELEASING_NOW_STMT = "select movie_id, movie_ch from movie "
			+ "where movie_state_id = 21 ";
	private static final String GET_MOVIE_CH_STMT = "select c.movie_time_id, c.hall_name, d.movie_ch, c.showing, c.showing_date "
			+ "from (select a.movie_time_id, b.hall_name, a.showing, a.showing_date "
			+ "from movie_time as a join hall as b " + "where a.hall_id = b.hall_id) as c " + "join "
			+ "(select a.movie_time_id, b.movie_ch, a.showing, a.showing_date "
			+ "from movie_time as a join movie as b " + "where a.movie_id = b.movie_id) as d "
			+ "where c.movie_time_id = d.movie_time_id";
	private static final String GET_HALL_NAME = "SELECT hall_id, hall_name FROM hall order by hall_id";

	@Override
	public void insert(Movie_timeVO movie_timeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, movie_timeVO.getHall_id());
			pstmt.setInt(2, movie_timeVO.getMovie_id());
			pstmt.setInt(3, movie_timeVO.getShowing());
			pstmt.setDate(4, movie_timeVO.getShowing_date());
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

//	"update movie_time set hall_id=?, movie_id=?, showing=?, showing_date=? "
//	+ "where movie_time_id=?"

	@Override
	public void update(Movie_timeVO movie_timeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, movie_timeVO.getHall_id());
			pstmt.setInt(2, movie_timeVO.getMovie_id());
			pstmt.setInt(3, movie_timeVO.getShowing());
			pstmt.setDate(4, movie_timeVO.getShowing_date());
			pstmt.setInt(5, movie_timeVO.getMovie_time_id());
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
	public void delete(Integer movie_time_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, movie_time_id);

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
	public Movie_timeVO findByPrimaryKey(Integer movie_time_id) {

		Movie_timeVO movie_timeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, movie_time_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				movie_timeVO = new Movie_timeVO();
				movie_timeVO.setMovie_time_id(rs.getInt("movie_time_id"));
				movie_timeVO.setHall_id(rs.getInt("hall_id"));
				movie_timeVO.setHall_name(rs.getString("hall_name"));
				movie_timeVO.setMovie_id(rs.getInt("movie_id"));
				movie_timeVO.setShowing(rs.getInt("showing"));
				movie_timeVO.setShowing_date(rs.getDate("showing_date"));
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
		return movie_timeVO;
	}

	@Override
	public List<Movie_timeVO> getAll() {

		List<Movie_timeVO> list = new ArrayList<Movie_timeVO>();
		Movie_timeVO movie_timeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				movie_timeVO = new Movie_timeVO();
				movie_timeVO.setMovie_time_id(rs.getInt("movie_time_id"));
				movie_timeVO.setHall_id(rs.getInt("hall_id"));
				movie_timeVO.setMovie_id(rs.getInt("movie_id"));
				movie_timeVO.setShowing(rs.getInt("showing"));
				movie_timeVO.setShowing_date(rs.getDate("showing_date"));

				list.add(movie_timeVO);
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

	@Override
	public Movie_timeVO findIfAlreadyHad(Integer hall_id, Integer showing, java.sql.Date showing_date) {

		Movie_timeVO movie_timeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_REPEAT_STMT);

			pstmt.setInt(1, hall_id);
			pstmt.setInt(2, showing);
			pstmt.setDate(3, showing_date);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				movie_timeVO = new Movie_timeVO();
				movie_timeVO.setHall_id(rs.getInt("hall_id"));
				movie_timeVO.setShowing(rs.getInt("showing"));
				movie_timeVO.setShowing_date(rs.getDate("showing_date"));
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

		return movie_timeVO;
	}

	@Override
	public List<Movie_timeVO> getNowCh() {

		List<Movie_timeVO> list = new ArrayList<Movie_timeVO>();
		Movie_timeVO movie_timeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RELEASING_NOW_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				movie_timeVO = new Movie_timeVO();
				movie_timeVO.setMovie_id(rs.getInt("movie_id"));
				movie_timeVO.setMovie_ch(rs.getString("movie_ch"));
				list.add(movie_timeVO);
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

	@Override
	public List<Movie_timeVO> getAllCh() {

		List<Movie_timeVO> list = new ArrayList<Movie_timeVO>();
		Movie_timeVO movie_timeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MOVIE_CH_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

//	c.movie_time_id, c.hall_name, d.movie_ch, c.showing, c.showing_date
				movie_timeVO = new Movie_timeVO();
				movie_timeVO.setMovie_time_id(rs.getInt("movie_time_id"));
				movie_timeVO.setHall_name(rs.getString("hall_name"));
				movie_timeVO.setMovie_ch(rs.getString("movie_ch"));
				movie_timeVO.setShowing(rs.getInt("showing"));
				movie_timeVO.setShowing_date(rs.getDate("showing_date"));
				list.add(movie_timeVO);
			}

		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
			se.printStackTrace();
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
	public List<Movie_timeVO> getHall_Name() {
		List<Movie_timeVO> list = new ArrayList<Movie_timeVO>();
		Movie_timeVO movie_timeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_HALL_NAME);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movie_timeVO = new Movie_timeVO();
				movie_timeVO.setHall_id(rs.getInt("hall_id"));
				movie_timeVO.setHall_name(rs.getString("hall_name"));

				list.add(movie_timeVO); // Store the row in the list
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

}
