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

import web.ticket_orders.entity.Ticket_OrdersVO;

public class Ticket_OrdersDAO implements Ticket_OrdersDAO_interface {

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
	private static final String INSERT_TIME_NUMBER = "INSERT INTO ticket_list(ticket_orders_id,movie_time_id,ticket_number,ticket_price) "
			+ "VALUES ((SELECT ticket_orders_id FROM ticket_orders WHERE mem_id = ? ORDER BY buyticket_date DESC LIMIT 1), ?, ?, ticket_number*250);";
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
	private static final String CHOOSE_SEAT = "UPDATE movie_time set seat_select_state = ? where movie_time_id = ?; ";
	private static final String TRANSACTION = "start transaction; ";
	private static final String SAVEPOINT = "savepoint sp1;";
	private static final String ROOLBACK = "rollback to sp1;";
	private static final String COMMIT = "commit;";
	private static final String SAVE_TO_LIST = "UPDATE ticket_list set select_seat_name = ? WHERE ticket_list_id = ?;"; 
	private static final String GET_TICKET_LIST_ID_NUMBER = "SELECT ticket_list_id, ticket_number FROM ticket_list WHERE ticket_orders_id = (SELECT ticket_orders_id FROM ticket_orders WHERE mem_id = ? ORDER BY buyticket_date DESC LIMIT 1)";
	private static final String GET_LIST_TICKET_PRICE = "SELECT ticket_price FROM ticket_list WHERE ticket_orders_id = (SELECT ticket_orders_id FROM ticket_orders WHERE mem_id = ? ORDER BY buyticket_date DESC LIMIT 1);";
	private static final String FINISH_ORDERS = "SELECT mem_id, ticket_orders_id, movie_ch, hall_name, showing_date, showing, ticket_number, select_seat_name, ticket_price, h.hall_id "
			+ "FROM hall h "
			+ "JOIN ( "
			+ "	SELECT ticket_orders_id, mem_id, select_seat_name, ticket_price, ticket_number, movie_time_id, hall_id, m.movie_id, showing, showing_date, movie_ch "
			+ "	FROM movie m "
			+ "	JOIN ( "
			+ "		SELECT ticket_orders_id, mem_id, select_seat_name, ticket_price, ticket_number, mt.movie_time_id, hall_id, movie_id, showing, showing_date "
			+ "		FROM movie_time mt "
			+ "		JOIN ( "
			+ "			SELECT tko.ticket_orders_id, mem_id, select_seat_name, ticket_price, ticket_number, movie_time_id "
			+ "			FROM ticket_orders tko "
			+ "			JOIN( "
			+ "				SELECT ticket_list_id, ticket_orders_id, select_seat_name, movie_time_id, ticket_price, ticket_number "
			+ "				FROM ticket_list "
			+ "				WHERE ticket_orders_id = ( "
			+ "					SELECT ticket_orders_id "
			+ "					FROM ticket_orders "
			+ "					WHERE mem_id = ? "
			+ "					ORDER BY buyticket_date "
			+ "					DESC LIMIT 1 "
			+ "					) "
			+ "				) tl "
			+ "			ON tko.ticket_orders_id = tl.ticket_orders_id "
			+ "			WHERE mem_id = ? "
			+ "			) t2 "
			+ "		ON mt.movie_time_id = t2.movie_time_id "
			+ "		) t3 "
			+ "	ON m.movie_id = t3.movie_id "
			+ "	) t4 "
			+ "ON h.hall_id = t4.hall_id;";
	private static final String GET_ONLINE_MOVIE = "SELECT m.movie_id, movie_rating_id, movie_ch, movie_en, movie_poster "
			+ "FROM movie m "
			+ "JOIN ( "
			+ "	SELECT DISTINCT movie_id "
			+ "	FROM movie_time "
			+ "	WHERE date_add(curdate(),INTERVAL 6 DAY) >= date(showing_date) AND date(showing_date) >= curdate() "
			+ "	ORDER BY movie_id "
			+ "    )mt "
			+ "ON m.movie_id = mt.movie_id;";
	private static final String GET_MOVIE_TIME = "SELECT m.movie_id, movie_ch, movie_poster, movie_time_id, showing, showing_date "
			+ "FROM movie m "
			+ "JOIN ( "
			+ "	SELECT movie_id, movie_time_id, showing, showing_date "
			+ "	FROM movie_time "
			+ "	WHERE movie_id = ? AND date_add(curdate(),INTERVAL 6 DAY) >= date(showing_date) AND date(showing_date) >= curdate() "
			+ "    )mt "
			+ "ON m.movie_id = mt.movie_id "
			+ "ORDER BY showing_date, showing;";
	private static final String DELETE_ORDERS = "DELETE FROM ticket_orders "
			+ "WHERE ticket_orders_id =  "
			+ "	(SELECT * FROM( "
			+ "		SELECT ticket_orders_id "
			+ "		FROM ticket_orders "
			+ "		WHERE mem_id = ? "
			+ "		ORDER BY buyticket_date "
			+ "		DESC LIMIT 1 "
			+ "		) tko "
			+ "	);";
	
	@Override
	public void addOrders(Ticket_OrdersVO ticket_ordersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_ORDERS);

			pstmt.setInt(1, ticket_ordersVO.getMem_id());
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
		return list_ticket;
	}
	
	@Override
	public List<Ticket_OrdersVO> getList_Ticket_Price(Integer mem_id) {
		List<Ticket_OrdersVO> list_ticket_price = new ArrayList<Ticket_OrdersVO>();
		Ticket_OrdersVO ticket_ordersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIST_TICKET_PRICE);
			pstmt.setInt(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ticket_ordersVO = new Ticket_OrdersVO();
				ticket_ordersVO.setTicket_price(rs.getInt("ticket_price"));
				list_ticket_price.add(ticket_ordersVO);
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
		return list_ticket_price;
	}
	
	@Override
	public void choose_Seat(Ticket_OrdersVO ticket_ordersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(TRANSACTION);
			pstmt.execute();
			
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
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SAVEPOINT);
			pstmt.execute();
			
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

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHOOSE_SEAT);
			pstmt.setString(1, ticket_ordersVO.getSeat_select_state());
			pstmt.setInt(2, ticket_ordersVO.getMovie_time_id());
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
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SAVE_TO_LIST);
			pstmt.setString(1, ticket_ordersVO.getSelect_seat_name());
			pstmt.setInt(2, ticket_ordersVO.getTicket_list_id());
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
	public void deleteOrders(Integer mem_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_ORDERS);
			pstmt.setInt(1, mem_id);
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
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(COMMIT);
			pstmt.execute();
			
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
	public void deleteOrdersSeat(Integer mem_id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(ROOLBACK);
			pstmt.execute();
			
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
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(TRANSACTION);
			pstmt.execute();
			
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
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(COMMIT);
			pstmt.execute();
			
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
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_ORDERS);
			pstmt.setInt(1, mem_id);
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
	public List<Ticket_OrdersVO> getOnline_Moive() {
		List<Ticket_OrdersVO> list_online_movie = new ArrayList<Ticket_OrdersVO>();
		Ticket_OrdersVO ticket_ordersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONLINE_MOVIE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ticket_ordersVO = new Ticket_OrdersVO();
				ticket_ordersVO.setMovie_id(rs.getInt("movie_id"));
				ticket_ordersVO.setMovie_rating_id(rs.getInt("movie_rating_id"));
				ticket_ordersVO.setMovie_ch(rs.getString("movie_ch"));
				ticket_ordersVO.setMovie_en(rs.getString("movie_en"));
				ticket_ordersVO.setMovie_poster(rs.getBytes("movie_poster"));
				list_online_movie.add(ticket_ordersVO);
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
		return list_online_movie;
	}
	
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
				ticket_ordersVO.setMovie_id(rs.getInt("movie_id"));
				ticket_ordersVO.setMovie_ch(rs.getString("movie_ch"));
				ticket_ordersVO.setMovie_poster(rs.getBytes("movie_poster"));
				ticket_ordersVO.setMovie_time_id(rs.getInt("movie_time_id"));
				ticket_ordersVO.setShowing(rs.getInt("showing"));
				ticket_ordersVO.setShowing_date(rs.getDate("showing_date"));
				list.add(ticket_ordersVO);
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
	public List<Ticket_OrdersVO> getMem_Order(Integer mem_id) {
		List<Ticket_OrdersVO> list_mem_order = new ArrayList<Ticket_OrdersVO>();
		Ticket_OrdersVO ticket_ordersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FINISH_ORDERS);
			pstmt.setInt(1, mem_id);
			pstmt.setInt(2, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ticket_ordersVO = new Ticket_OrdersVO();
				ticket_ordersVO.setMem_id(rs.getInt("mem_id"));
				ticket_ordersVO.setTicket_orders_id(rs.getInt("ticket_orders_id"));
				ticket_ordersVO.setMovie_ch(rs.getString("movie_ch"));
				ticket_ordersVO.setHall_name(rs.getString("hall_name"));
				ticket_ordersVO.setShowing_date(rs.getDate("showing_date"));
				ticket_ordersVO.setShowing(rs.getInt("showing"));
				ticket_ordersVO.setTicket_number(rs.getInt("ticket_number"));
				ticket_ordersVO.setSelect_seat_name(rs.getString("select_seat_name"));
				ticket_ordersVO.setTicket_price(rs.getInt("ticket_price"));
				ticket_ordersVO.setHall_id(rs.getInt("hall_id"));
				list_mem_order.add(ticket_ordersVO);
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
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(COMMIT);
			pstmt.execute();
			

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
		
		return list_mem_order;
	}
}