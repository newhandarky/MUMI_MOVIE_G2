package web.ticket_order.dao;

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

import web.ticket_order.entity.TicketVO;

public class TicketDAO implements TicketDAO_interface{
	
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
			"INSERT INTO ticket_orders (mem_id,buyticket_date,ticket_qrcode,total_price) VALUES (?, ?, ?, ?)";	
	private static final String GET_ALL_STMT = 	
			"SELECT ticket_orders_id,mem_id,buyticket_date,ticket_qrcode,total_price FROM ticket_orders order by ticket_orders_id";
	private static final String GET_ONE_STMT = 
			"SELECT ticket_orders_id,mem_id,buyticket_date,ticket_qrcode,total_price FROM ticket_orders where ticket_orders_id = ?";
	private static final String DELETE = 
			"DELETE FROM ticket_orders where ticket_orders_id = ?";
	private static final String UPDATE = 
			"UPDATE ticket_orders set mem_id=?,buyticket_date=?,ticket_qrcode=?,total_price=? where ticket_orders_id=?";
		
	@Override
	public void insert(TicketVO ticketVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ticketVO.getMem_id());
			pstmt.setDate(2, ticketVO.getBuyticket_date());
			pstmt.setBytes(3, ticketVO.getTicket_qrcode());
			pstmt.setInt(4, ticketVO.getTotal_price());

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
	public void update(TicketVO ticketVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ticketVo.getMem_id());
			pstmt.setDate(2, ticketVo.getBuyticket_date());
			pstmt.setBytes(3, ticketVo.getTicket_qrcode());
			pstmt.setInt(4, ticketVo.getTotal_price());
			pstmt.setInt(5, ticketVo.getTicket_orders_id());

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
	public void delete(Integer ticket_orders_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ticket_orders_id);

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
	public TicketVO findByPrimaryKey(Integer ticket_orders_id) {

		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ticketVO 也稱為 Domain objects
				ticketVO = new TicketVO();
				ticketVO.setTicket_orders_id(rs.getInt("ticket_orders_id"));
				ticketVO.setMem_id(rs.getInt("mem_id"));
				ticketVO.setBuyticket_date(rs.getDate("buyticket_date"));
				ticketVO.setTicket_qrcode(rs.getBytes("ticket_qrcode"));
				ticketVO.setTotal_price(rs.getInt("total_price"));
				list.add(ticketVO); // Store the row in the list
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
