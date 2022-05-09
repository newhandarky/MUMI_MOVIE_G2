package web.hall_seat.dao;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.hall_seat.entity.Hall_SeatVO;

public class Hall_SeatDAO implements Hall_SeatDAO_interface {

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
	
	private static final String INSERT_STMT = "INSERT INTO hall_seat (hall_id,seat_state,seat_name,seat_row,seat_col,seat_left,seat_right,seat_row_aisle1,seat_row_aisle2,seat_no) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = "SELECT emp_id,emp_account,emp_name,emp_password,emp_nickname,emp_phone,emp_hiredate,emp_birth FROM emp_list order by emp_id";
	private static final String GET_ONE_STMT = "SELECT seat_id,seat_state,seat_name FROM hall_seat where hall_id = ?";
//	private static final String DELETE = "DELETE FROM emp_list where emp_id = ?";
	private static final String UPDATE = "UPDATE hall_seat set seat_state = ? where seat_id = ?";
	private static final String GET_SEAT_INFO = "SELECT seat_id,seat_state,seat_name,seat_row,seat_col,seat_left,seat_right,seat_row_aisle1,seat_row_aisle2,seat_no FROM hall_seat where hall_id = ? ORDER BY seat_id;";
	
	@Override
	public void insert(Hall_SeatVO hall_seatVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, hall_seatVO.getHall_id());
			pstmt.setString(2, hall_seatVO.getSeat_state());
			pstmt.setString(3, hall_seatVO.getSeat_name());
			pstmt.setInt(4, hall_seatVO.getSeat_row());
			pstmt.setInt(5, hall_seatVO.getSeat_col());
			pstmt.setInt(6, hall_seatVO.getSeat_left());
			pstmt.setInt(7, hall_seatVO.getSeat_right());
			pstmt.setInt(8, hall_seatVO.getSeat_row_aisle1());
			pstmt.setInt(9, hall_seatVO.getSeat_row_aisle2());
			pstmt.setString(10, hall_seatVO.getSeat_no());
			

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(Hall_SeatVO hall_seatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, hall_seatVO.getSeat_state());
			pstmt.setString(2, hall_seatVO.getSeat_id());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
//
//	@Override
//	public void delete(Integer emp_id) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, emp_id);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
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
				// empVo 也稱為 Domain objects
				hall_seatVO.setSeat_id(rs.getString("seat_id"));
				hall_seatVO.setSeat_state(rs.getString("seat_state"));
				hall_seatVO.setSeat_name(rs.getString("seat_name"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace(System.err);
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
		return hall_seatVO;
	}

//	@Override
//	public List<Hall_SeatVO> getAll() {
//		List<Hall_SeatVO> list = new ArrayList<Hall_SeatVO>();
//		Hall_SeatVO empVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVO 也稱為 Domain objects
//				empVO = new Hall_SeatVO();
//				empVO.setEmp_id(rs.getInt("emp_id"));
//				empVO.setEmp_account(rs.getString("emp_account"));
//				empVO.setEmp_name(rs.getString("emp_name"));
//				empVO.setEmp_password(rs.getString("emp_password"));
//				empVO.setEmp_nickname(rs.getString("emp_nickname"));
//				empVO.setEmp_phone(rs.getString("emp_phone"));
//				empVO.setEmp_hiredate(rs.getDate("emp_hiredate"));
//				empVO.setEmp_birth(rs.getDate("emp_birth"));
//				list.add(empVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
	
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
				hall_seatVO.setSeat_id(rs.getString("seat_id"));
				hall_seatVO.setSeat_state(rs.getString("seat_state"));
				hall_seatVO.setSeat_name(rs.getString("seat_name"));
				hall_seatVO.setSeat_row(rs.getInt("seat_row"));
				hall_seatVO.setSeat_col(rs.getInt("seat_col"));
				hall_seatVO.setSeat_left(rs.getInt("seat_left"));
				hall_seatVO.setSeat_right(rs.getInt("seat_right"));
				hall_seatVO.setSeat_row_aisle1(rs.getInt("seat_row_aisle1"));
				hall_seatVO.setSeat_row_aisle2(rs.getInt("seat_row_aisle2"));
				hall_seatVO.setSeat_no(rs.getString("seat_no"));
				list.add(hall_seatVO);
			}
			// Handle any driver errors
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
		return list;
	}
}