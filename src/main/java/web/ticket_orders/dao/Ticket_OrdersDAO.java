package web.ticket_orders.dao;

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

import web.select_seat.entity.Select_SeatVO;
import web.ticket_orders.entity.Ticket_OrdersVO;

public class Ticket_OrdersDAO implements Ticket_OrdersDAO_interface {

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
	
	private static final String INSERT_ORDERS = "INSERT INTO ticket_orders (mem_id, buyticket_date) VALUE (?, NOW())";
	private static final String INSERT_TIME_NUMBER = "INSERT INTO ticket_list(ticket_orders_id,movie_time_id,ticket_number,ticket_price)"
			+ 										 "VALUES ((SELECT ticket_orders_id FROM ticket_orders WHERE mem_id = ? ORDER BY buyticket_date DESC LIMIT 1), ?, ?, ticket_number*220)";
	private static final String GET_SEAT = "SELECT movie_time_id, mt.hall_id, movie_id, seat_select_state, showing, showing_date, seat_id, seat_state, seat_name, seat_row, seat_col, seat_left, seat_right, seat_row_aisle1, seat_row_aisle2, seat_no "
			+ "FROM movie_time mt "
			+ "JOIN ( "
			+ "	SELECT hall_id, seat_id, seat_state, seat_name, seat_row, seat_col, seat_left, seat_right, seat_row_aisle1, seat_row_aisle2, seat_no "
			+ "	FROM hall_seat "
			+ "	WHERE hall_id = ( "
			+ "		SELECT hall_id "
			+ "		FROM movie_time "
			+ "		WHERE movie_time_id = ? "
			+ "        ) "
			+ ") hs "
			+ "ON mt.hall_id = hs.hall_id "
			+ "WHERE movie_time_id = ?;";
	private static final String CHOOSE_SEAT = "UPDATE movie_time set seat_select_state = ? where movie_time_id = ?";
    private static final String GET_TICKET_LIST_ID_NUMBER = "SELECT ticket_list_id, ticket_number FROM ticket_list WHERE ticket_orders_id = (SELECT ticket_orders_id FROM ticket_orders WHERE mem_id = ? ORDER BY buyticket_date DESC LIMIT 1)";
//	private static final String GET_ONE_STMT = "SELECT seat_id,seat_state,seat_name FROM hall_seat where hall_id = ?";
//	private static final String GET_HALL_NAME = "SELECT hall_id, hall_name FROM hall order by hall_id";
	private static final String GET_MOVIE_TIME = "SELECT movie_time_id, showing, showing_date FROM movie_time WHERE movie_id = ?";
//	private static final String INSERT_HALL = "INSERT INTO hall (hall_name, hall_update) VALUES (?, now())";
//	private static final String GET_HALL_NEW = "SELECT hall_id, hall_name FROM hall ORDER BY hall_update DESC LIMIT 1;";
	
	@Override
	public void addOrders(Ticket_OrdersVO ticket_ordersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_ORDERS);

			pstmt.setInt(1, ticket_ordersVO.getMem_id());
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
	public void addTime_Number(Ticket_OrdersVO ticket_ordersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_TIME_NUMBER);

			pstmt.setInt(1, ticket_ordersVO.getMem_id());
			pstmt.setInt(2, ticket_ordersVO.getMovie_time_id());
			pstmt.setInt(3, ticket_ordersVO.getTicket_number());		

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
	public List<Ticket_OrdersVO> getSeat(Integer movie_time_id) {
		List<Ticket_OrdersVO> list = new ArrayList<Ticket_OrdersVO>();
		Ticket_OrdersVO ticket_ordersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SEAT);
			pstmt.setInt(1, movie_time_id);
			pstmt.setInt(2, movie_time_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ticket_ordersVO = new Ticket_OrdersVO();
				ticket_ordersVO.setMovie_time_id(rs.getInt("movie_time_id"));
				ticket_ordersVO.setHall_id(rs.getInt("hall_id"));
				ticket_ordersVO.setMovie_id(rs.getInt("movie_id"));
				ticket_ordersVO.setSeat_select_state(rs.getString("seat_select_state"));
				ticket_ordersVO.setShowing(rs.getInt("showing"));
				ticket_ordersVO.setShowing_date(rs.getDate("showing_date"));
				ticket_ordersVO.setSeat_id(rs.getInt("seat_id"));
				ticket_ordersVO.setSeat_state(rs.getInt("seat_state"));
				ticket_ordersVO.setSeat_name(rs.getString("seat_name"));
				ticket_ordersVO.setSeat_row(rs.getInt("seat_row"));
				ticket_ordersVO.setSeat_col(rs.getInt("seat_col"));
				ticket_ordersVO.setSeat_left(rs.getInt("seat_left"));
				ticket_ordersVO.setSeat_right(rs.getInt("seat_right"));
				ticket_ordersVO.setSeat_row_aisle1(rs.getInt("seat_row_aisle1"));
				ticket_ordersVO.setSeat_row_aisle2(rs.getInt("seat_row_aisle2"));
				ticket_ordersVO.setSeat_no(rs.getInt("seat_no"));
				list.add(ticket_ordersVO);
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
	
	@Override
	public List<Ticket_OrdersVO> getTicket_List_Id_Number(Integer mem_id) {
		List<Ticket_OrdersVO> list_ticket = new ArrayList<Ticket_OrdersVO>();
		Ticket_OrdersVO ticket_ordersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TICKET_LIST_ID_NUMBER);
			pstmt.setInt(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ticket_ordersVO = new Ticket_OrdersVO();
				ticket_ordersVO.setTicket_list_id(rs.getInt("ticket_list_id"));
				ticket_ordersVO.setTicket_number(rs.getInt("ticket_number"));
				list_ticket.add(ticket_ordersVO);
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
		return list_ticket;
	}
	
	@Override
	public void choose_Seat(Ticket_OrdersVO ticket_ordersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHOOSE_SEAT);
			pstmt.setString(1, ticket_ordersVO.getSeat_select_state());
			pstmt.setInt(2, ticket_ordersVO.getMovie_time_id());

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

//	@Override
//	public void update(Hall_SeatVO hall_seatVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, hall_seatVO.getSeat_state());
//			pstmt.setInt(2, hall_seatVO.getSeat_id());
//			
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
//	@Override
//	public void delete(Integer hall_id) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, hall_id);
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
//	@Override
//	public Hall_SeatVO findByPrimaryKey(Integer hall_id) {
//
//		Hall_SeatVO hall_seatVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, hall_id);
//
//			rs = pstmt.executeQuery();
//
//			hall_seatVO = new Hall_SeatVO();
//			while (rs.next()) {
//				// empVo 也稱為 Domain objects
//				hall_seatVO.setSeat_id(rs.getInt("seat_id"));
//				hall_seatVO.setSeat_state(rs.getInt("seat_state"));
//				hall_seatVO.setSeat_name(rs.getString("seat_name"));
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			se.printStackTrace(System.err);
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
//		return hall_seatVO;
//	}
//
//	@Override
//	public List<Hall_SeatVO> getHall_Name() {
//		List<Hall_SeatVO> list = new ArrayList<Hall_SeatVO>();
//		Hall_SeatVO hall_SeatVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_HALL_NAME);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				hall_SeatVO = new Hall_SeatVO();
//				hall_SeatVO.setHall_id(rs.getInt("hall_id"));
//				hall_SeatVO.setHall_name(rs.getString("hall_name"));
//
//				list.add(hall_SeatVO); // Store the row in the list
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
	public List<Ticket_OrdersVO> getMoive_Id(Integer movie_id) {
		List<Ticket_OrdersVO> list = new ArrayList<Ticket_OrdersVO>();
		Ticket_OrdersVO ticket_ordersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MOVIE_TIME);
			pstmt.setInt(1, movie_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ticket_ordersVO = new Ticket_OrdersVO();
				ticket_ordersVO.setMovie_time_id(rs.getInt("movie_time_id"));
				ticket_ordersVO.setShowing(rs.getInt("showing"));
				ticket_ordersVO.setShowing_date(rs.getDate("showing_date"));
				list.add(ticket_ordersVO);
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
	
//	@Override
//	public void insert_hall(Hall_SeatVO hall_seatVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_HALL);
//
//			pstmt.setString(1, hall_seatVO.getHall_name());			
//
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
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
//	}
//	
//	@Override
//	public List<Hall_SeatVO> getHall_New() {
//		List<Hall_SeatVO> list = new ArrayList<Hall_SeatVO>();
//		Hall_SeatVO hall_SeatVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_HALL_NEW);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				hall_SeatVO = new Hall_SeatVO();
//				hall_SeatVO.setHall_id(rs.getInt("hall_id"));
//				hall_SeatVO.setHall_name(rs.getString("hall_name"));
//
//				list.add(hall_SeatVO); // Store the row in the list
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
}