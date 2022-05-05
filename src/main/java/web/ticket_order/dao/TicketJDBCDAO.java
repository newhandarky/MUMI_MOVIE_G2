package web.ticket_order.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.ticket_order.entity.TicketVO;


public class TicketJDBCDAO implements TicketDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/MUMI_MOVIE";
	String userid = "root";
	String passwd = "password";
	
	
		private static final String INSERT_STMT =
				"INSERT INTO ticket_orders (mem_id, buyticket_date, ticket_qrcode, total_price) VALUES (?, ?, ?, ?)";	
		private static final String GET_ALL_STMT = 	
				"SELECT ticket_orders_id, mem_id, buyticket_date, ticket_qrcode, total_price FROM ticket_orders order by ticket_orders_id";
		private static final String GET_ONE_STMT = 
				"SELECT ticket_orders_id, mem_id, buyticket_date, ticket_qrcode, total_price FROM ticket_orders where ticket_orders_id = ?";
		private static final String DELETE = 
				"DELETE FROM ticket_orders where ticket_orders_id = ?";
		private static final String UPDATE = 
				"UPDATE ticket_orders set mem_id=?,buyticket_date=?,ticket_qrcode=?,total_price=? where ticket_orders_id=?";
		
		
		@Override
		public void insert(TicketVO ticketVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, ticketVO.getMem_id());
				pstmt.setDate(2, ticketVO.getBuyticket_date());
				pstmt.setBytes(3, ticketVO.getTicket_qrcode());
				pstmt.setInt(4, ticketVO.getTotal_price());
				
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public void update(TicketVO ticketVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, ticketVO.getMem_id());
				pstmt.setDate(2, ticketVO.getBuyticket_date());
				pstmt.setBytes(3, ticketVO.getTicket_qrcode());
				pstmt.setInt(4, ticketVO.getTotal_price());
				pstmt.setInt(5, ticketVO.getTicket_orders_id());

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public void delete(Integer ticket_orders_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, ticket_orders_id);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public TicketVO findByPrimaryKey(Integer ticket_orders_id) {

			TicketVO ticketVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, ticket_orders_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					ticketVO = new TicketVO();
					ticketVO.setTicket_orders_id(rs.getInt("ticket_orders_id"));
					ticketVO.setMem_id(rs.getInt("mem_id"));
					ticketVO.setBuyticket_date(rs.getDate("buyticket_date"));
					ticketVO.setTicket_qrcode(rs.getBytes("ticket_qrcode"));
					ticketVO.setTotal_price(rs.getInt("total_price"));
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
			return ticketVO;
		}
		
		@Override
		public List<TicketVO> getAll() {
			List<TicketVO> list = new ArrayList<TicketVO>();
			TicketVO ticketVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					ticketVO = new TicketVO();
					ticketVO.setTicket_orders_id(rs.getInt("ticket_orders_id"));
					ticketVO.setMem_id(rs.getInt("mem_id"));
					ticketVO.setBuyticket_date(rs.getDate("buyticket_date"));
					ticketVO.setTicket_qrcode(rs.getBytes("ticket_qrcode"));
					ticketVO.setTotal_price(rs.getInt("total_price"));
					list.add(ticketVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

		public static void main(String[] args) throws IOException {

			File fi = new File("C:\\Users\\Tibame_T14\\Downloads\\圖片\\14.jpg");
			FileInputStream fis = new FileInputStream(fi);
			byte[] bt = fis.readAllBytes();
			
			TicketJDBCDAO dao = new TicketJDBCDAO();

			// 新增
			TicketVO ticketVO1 = new TicketVO();
			ticketVO1.setMem_id(1);
			ticketVO1.setBuyticket_date(java.sql.Date.valueOf("2005-01-02"));
			ticketVO1.setTicket_qrcode(bt);
			ticketVO1.setTotal_price(1000);
			dao.insert(ticketVO1);

			// 修改
			TicketVO ticketVO2 = new TicketVO();
			ticketVO2.setMem_id(2);
			ticketVO2.setBuyticket_date(java.sql.Date.valueOf("2005-01-02"));
			ticketVO2.setTicket_qrcode(bt);
			ticketVO2.setTotal_price(1000);
			ticketVO2.setTicket_orders_id(1);
			dao.update(ticketVO2);

			// 刪除
			dao.delete(1);

			// 查詢
			TicketVO ticketVO3 = dao.findByPrimaryKey(1);
			System.out.print(ticketVO3.getTicket_orders_id() + ",");
			System.out.print(ticketVO3.getMem_id() + ",");
			System.out.print(ticketVO3.getBuyticket_date() + ",");
			System.out.print(ticketVO3.getTicket_qrcode() + ",");
			System.out.print(ticketVO3.getTotal_price());
			System.out.println("---------------------");

			// 查詢
			List<TicketVO> list = dao.getAll();
			for (TicketVO aTicket : list) {
				System.out.print(aTicket.getTicket_orders_id() + ",");
				System.out.print(aTicket.getMem_id() + ",");
				System.out.print(aTicket.getBuyticket_date() + ",");
				System.out.print(aTicket.getTicket_qrcode() + ",");
				System.out.print(aTicket.getTotal_price());
				System.out.println();
			}
		}
}
