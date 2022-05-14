package web.ticket_list.dao;

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

import web.ticket_list.entity.Ticket_listVO;

	
	public class Ticket_listDAO implements Ticket_listDAO_interface{
		
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");		
			}catch (NamingException e) {
				e.printStackTrace();
			}
		}
		private static final String INSERT_STMT =
				"INSERT INTO ticket_list (ticket_orders_id,seat_id,movie_time_id,ticket_price) VALUES (?, ?, ?, ?)";	
		private static final String GET_ALL_STMT = 	
				"SELECT ticket_list_id,ticket_orders_id,seat_id,movie_time_id,ticket_price FROM ticket_list order by ticket_list_id";
		private static final String GET_ONE_STMT = 
				"SELECT ticket_list_id,ticket_orders_id,seat_id,movie_time_id,ticket_price FROM ticket_list where ticket_list_id = ?";
		private static final String DELETE = 
				"DELETE FROM ticket_list where ticket_list_id = ?";
		private static final String UPDATE = 
				"UPDATE ticket_list set ticket_orders_id=?,seat_id=?,movie_time_id,ticket_price=? where ticket_list_id=?";
			
		@Override
		public void insert(Ticket_listVO ticket_listVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, ticket_listVO.getTicket_orders_id());
				pstmt.setInt(2, ticket_listVO.getSeat_id());
				pstmt.setInt(3, ticket_listVO.getMovie_time_id());
				pstmt.setInt(4, ticket_listVO.getTicket_price());

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
		public void update(Ticket_listVO ticket_listVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, ticket_listVO.getTicket_orders_id());
				pstmt.setInt(2, ticket_listVO.getSeat_id());
				pstmt.setInt(3, ticket_listVO.getMovie_time_id());
				pstmt.setInt(4, ticket_listVO.getTicket_price());
				pstmt.setInt(5, ticket_listVO.getTicket_list_id());

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
		
		@Override
		public void delete(Integer ticket_list_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, ticket_list_id);

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
		
		@Override
		public Ticket_listVO findByPrimaryKey(Integer ticket_list_id) {

			Ticket_listVO ticket_listVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, ticket_list_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					ticket_listVO = new Ticket_listVO();
					ticket_listVO.setTicket_list_id(rs.getInt("ticket_list_id"));
					ticket_listVO.setTicket_orders_id(rs.getInt("ticket_orders_id"));
					ticket_listVO.setSeat_id(rs.getInt("seat_id"));
					ticket_listVO.setMovie_time_id(rs.getInt("movie_time_id"));
					ticket_listVO.setTicket_price(rs.getInt("ticket_price"));
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
			return ticket_listVO;
		}
		
		@Override
		public List<Ticket_listVO> getAll() {
			List<Ticket_listVO> list = new ArrayList<Ticket_listVO>();
			Ticket_listVO ticket_listVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// ticketVO 也稱為 Domain objects
					ticket_listVO = new Ticket_listVO();
					ticket_listVO.setTicket_list_id(rs.getInt("ticket_list_id"));
					ticket_listVO.setTicket_orders_id(rs.getInt("ticket_orders_id"));
					ticket_listVO.setSeat_id(rs.getInt("seat_id"));
					ticket_listVO.setMovie_time_id(rs.getInt("movie_time_id"));
					ticket_listVO.setTicket_price(rs.getInt("ticket_price"));
					list.add(ticket_listVO); // Store the row in the list
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
