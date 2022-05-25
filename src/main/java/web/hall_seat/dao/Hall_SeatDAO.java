package web.hall_seat.dao;

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

import web.hall_seat.entity.Hall_SeatVO;

public class Hall_SeatDAO implements Hall_SeatDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO hall_seat (hall_id,seat_state,seat_name,seat_row,seat_col,seat_left,seat_right,seat_row_aisle1,seat_row_aisle2,seat_no) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE hall_seat set seat_state = ? where seat_id = ?";
	private static final String DELETE = "DELETE FROM hall where hall_id = ?";
	private static final String GET_ONE_STMT = "SELECT seat_id,seat_state,seat_name FROM hall_seat where hall_id = ?";
	private static final String GET_HALL_NAME = "SELECT hall_id, hall_name FROM hall order by hall_id";
	private static final String GET_SEAT_INFO = "SELECT hall_id,seat_id,seat_state,seat_name,seat_row,seat_col,seat_left,seat_right,seat_row_aisle1,seat_row_aisle2,seat_no FROM hall_seat where hall_id = ? ORDER BY seat_id;";
	private static final String INSERT_HALL = "INSERT INTO hall (hall_name, hall_update) VALUES (?, now())";
	private static final String GET_HALL_NEW = "SELECT hall_id, hall_name FROM hall ORDER BY hall_update DESC LIMIT 1;";
	
	@Override
	public void insert(Hall_SeatVO hall_seatVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, hall_seatVO.getHall_id());
			pstmt.setInt(2, hall_seatVO.getSeat_state());
			pstmt.setString(3, hall_seatVO.getSeat_name());
			pstmt.setInt(4, hall_seatVO.getSeat_row());
			pstmt.setInt(5, hall_seatVO.getSeat_col());
			pstmt.setInt(6, hall_seatVO.getSeat_left());
			pstmt.setInt(7, hall_seatVO.getSeat_right());
			pstmt.setInt(8, hall_seatVO.getSeat_row_aisle1());
			pstmt.setInt(9, hall_seatVO.getSeat_row_aisle2());
			pstmt.setInt(10, hall_seatVO.getSeat_no());
			

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(Hall_SeatVO hall_seatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, hall_seatVO.getSeat_state());
			pstmt.setInt(2, hall_seatVO.getSeat_id());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(Integer hall_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, hall_id);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Hall_SeatVO findByPrimaryKey(Integer hall_id) {

		Hall_SeatVO hall_seatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, hall_id);

			rs = pstmt.executeQuery();

			hall_seatVO = new Hall_SeatVO();
			while (rs.next()) {
				hall_seatVO.setSeat_id(rs.getInt("seat_id"));
				hall_seatVO.setSeat_state(rs.getInt("seat_state"));
				hall_seatVO.setSeat_name(rs.getString("seat_name"));
			}

		} catch (SQLException se) {
			se.printStackTrace(System.err);
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return hall_seatVO;
	}

	@Override
	public List<Hall_SeatVO> getHall_Name() {
		List<Hall_SeatVO> list = new ArrayList<Hall_SeatVO>();
		Hall_SeatVO hall_SeatVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_HALL_NAME);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				hall_SeatVO = new Hall_SeatVO();
				hall_SeatVO.setHall_id(rs.getInt("hall_id"));
				hall_SeatVO.setHall_name(rs.getString("hall_name"));

				list.add(hall_SeatVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<Hall_SeatVO> getSeatInfo(Integer hall_id) {
		List<Hall_SeatVO> list = new ArrayList<Hall_SeatVO>();
		Hall_SeatVO hall_seatVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SEAT_INFO);
			pstmt.setInt(1, hall_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				hall_seatVO = new Hall_SeatVO();
				hall_seatVO.setHall_id(rs.getInt("hall_id"));
				hall_seatVO.setSeat_id(rs.getInt("seat_id"));
				hall_seatVO.setSeat_state(rs.getInt("seat_state"));
				hall_seatVO.setSeat_name(rs.getString("seat_name"));
				hall_seatVO.setSeat_row(rs.getInt("seat_row"));
				hall_seatVO.setSeat_col(rs.getInt("seat_col"));
				hall_seatVO.setSeat_left(rs.getInt("seat_left"));
				hall_seatVO.setSeat_right(rs.getInt("seat_right"));
				hall_seatVO.setSeat_row_aisle1(rs.getInt("seat_row_aisle1"));
				hall_seatVO.setSeat_row_aisle2(rs.getInt("seat_row_aisle2"));
				hall_seatVO.setSeat_no(rs.getInt("seat_no"));
				list.add(hall_seatVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void insert_hall(Hall_SeatVO hall_seatVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_HALL);

			pstmt.setString(1, hall_seatVO.getHall_name());			

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<Hall_SeatVO> getHall_New() {
		List<Hall_SeatVO> list = new ArrayList<Hall_SeatVO>();
		Hall_SeatVO hall_SeatVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_HALL_NEW);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				hall_SeatVO = new Hall_SeatVO();
				hall_SeatVO.setHall_id(rs.getInt("hall_id"));
				hall_SeatVO.setHall_name(rs.getString("hall_name"));

				list.add(hall_SeatVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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