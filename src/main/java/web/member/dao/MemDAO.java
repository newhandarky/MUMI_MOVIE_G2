package web.member.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import web.member.entity.MemVO;


public class MemDAO implements MemDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO mumi_member (mem_account, mem_name, mem_phone, mem_password, mem_nickname, mem_register, mem_pic, mem_address, mem_state, mem_point) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, '請輸入地址',1, 0)";
	
	private static final String GET_ALL_STMT = 
			"SELECT mem_id, mem_account, mem_name, mem_phone, mem_birthday, mem_gender, mem_address, "
			+ "mem_password, mem_nickname, mem_pic, mem_register, mem_update, mem_point, mem_state FROM mumi_member order by mem_id";
	
	private static final String GET_ONE_STMT = 
			"SELECT mem_id, mem_account, mem_name, mem_phone, mem_birthday, mem_gender, mem_address, "
			+ "mem_password, mem_nickname, mem_pic, mem_register, mem_update, mem_point, mem_state FROM mumi_member where mem_id = ?";
	
	private static final String DELETE = 
			"DELETE FROM mumi_member where mem_id = ?";
	
	private static final String UPDATE = 
			"UPDATE mumi_member set mem_name=?, mem_phone=?, mem_address=?, mem_password=? ,mem_nickname=? ,"
			+ "mem_birthday=? , mem_pic =? , mem_gender=? , mem_update=NOW(), mem_state=1 where mem_id = ?";
	
	private static final String UPDATE_STATE_CLOSE = 
			"UPDATE mumi_member set mem_state=0 where mem_id = ?";
	
	private static final String UPDATE_STATE_OPEN = 
			"UPDATE mumi_member set mem_state=1 where mem_id = ?";
	
	private static final String GET_ONE_ACCOUNT = 
			"SELECT mem_id FROM mumi_member where mem_account = ?";
	
	private static final String LOGIN = 
			"select mem_id from mumi_member where mem_account = ? and mem_password = ?";
	
	private static final String CHANGE_PASSWORD = 
			"UPDATE mumi_member set mem_password = ? where mem_account = ?";
	
	private static final String GET_ONE_VO = 
			"SELECT mem_id, mem_account, mem_name, mem_phone, mem_birthday, mem_gender, mem_address, "
			+ "mem_password, mem_nickname, mem_pic, mem_register, mem_update, mem_point, mem_state FROM mumi_member where mem_account = ?";
	
	private static final String GET_SHEET = "SELECT ticket_orders_id, showing_date, showing, movie_ch, hall_name, select_seat_name, ticket_price, buyticket_date, h.hall_id "
			+ "FROM hall h "
			+ "JOIN ( "
			+ "	SELECT ticket_orders_id, mem_id, buyticket_date, select_seat_name, movie_time_id, ticket_price, hall_id, m.movie_id, showing, showing_date, movie_ch "
			+ "	FROM movie m "
			+ "	JOIN ( "
			+ "		SELECT ticket_orders_id, mem_id, buyticket_date, select_seat_name, mt.movie_time_id, ticket_price, hall_id, movie_id, showing, showing_date "
			+ "		FROM movie_time mt "
			+ "		JOIN ( "
			+ "			SELECT tl.ticket_orders_id, mem_id, buyticket_date, select_seat_name, movie_time_id, ticket_price "
			+ "			FROM ticket_list tl "
			+ "			JOIN ( "
			+ "				SELECT ticket_orders_id, mem_id, buyticket_date "
			+ "				FROM ticket_orders "
			+ "				WHERE mem_id = ? "
			+ "				) toi "
			+ "			ON tl.ticket_orders_id = toi.ticket_orders_id "
			+ "			) tl2 "
			+ "		ON mt.movie_time_id = tl2.movie_time_id "
			+ "		) mt2 "
			+ "	ON m.movie_id = mt2.movie_id "
			+ "    ) m2 "
			+ "ON h.hall_id = m2.hall_id "
			+ "ORDER BY buyticket_date "
			+ "DESC ;";
	
	@Override
	public void insert(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			checkAccount(memVO.getMem_account());
			
			pstmt.setString(1, memVO.getMem_account());
			pstmt.setString(2, memVO.getMem_name());
			pstmt.setString(3, memVO.getMem_phone());
			pstmt.setString(4, memVO.getMem_password());
			pstmt.setString(5, memVO.getMem_nickname());
			pstmt.setDate(6, memVO.getMem_register());
			pstmt.setBytes(7, memVO.getMem_pic());
			
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
	public void update(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, memVO.getMem_name());
			pstmt.setString(2, memVO.getMem_phone());
			pstmt.setString(3, memVO.getMem_address());
			pstmt.setString(4, memVO.getMem_password());
			pstmt.setString(5, memVO.getMem_nickname());
			pstmt.setDate(6, memVO.getMem_birthday());
			pstmt.setBytes(7, memVO.getMem_pic());
			pstmt.setInt(8, memVO.getMem_gender());			
			pstmt.setInt(9, memVO.getMem_id());

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
	
	
	public void updateClose(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATE_CLOSE);

			
			pstmt.setInt(1, memVO.getMem_id());

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
	
	public void updateOpen(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATE_OPEN);

			
			pstmt.setInt(1, memVO.getMem_id());

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
	public void updateState(Integer mem_id) {
		// TODO Auto-generated method stub
		
		
		if(findByPrimaryKey(mem_id).getMem_state() == 1) {
			
			updateClose(findByPrimaryKey(mem_id));
		}else if(findByPrimaryKey(mem_id).getMem_state() == 0) {
			
			updateOpen(findByPrimaryKey(mem_id));
			
		}
		
	}

	@Override
	public void delete(Integer mem_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mem_id);

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
	public MemVO findByPrimaryKey(Integer mem_id) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));;
				memVO.setMem_account(rs.getString("mem_account"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_birthday(rs.getDate("mem_birthday"));;
				memVO.setMem_gender(rs.getInt("mem_gender"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_nickname(rs.getString("mem_nickname"));
				memVO.setMem_pic(rs.getBytes("mem_pic"));
				memVO.setMem_register(rs.getDate("mem_register"));
				memVO.setMem_update(rs.getTimestamp("mem_update"));
				memVO.setMem_point(rs.getInt("mem_point"));
				memVO.setMem_state(rs.getInt("mem_state"));
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {


				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));;
				memVO.setMem_account(rs.getString("mem_account"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_birthday(rs.getDate("mem_birthday"));;
				memVO.setMem_gender(rs.getInt("mem_gender"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_nickname(rs.getString("mem_nickname"));
				memVO.setMem_pic(rs.getBytes("mem_pic"));
				memVO.setMem_update(rs.getTimestamp("mem_update"));
				memVO.setMem_point(rs.getInt("mem_point"));
				memVO.setMem_state(rs.getInt("mem_state"));
				list.add(memVO); // Store the row in the list
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
	public MemVO checkAccount(String mem_account) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACCOUNT);  // Anaturis@superrito.com
			pstmt.setString(1, mem_account);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {				
				if(rs.getRow() != 0) {
					throw new Exception();
				}
			}
			

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (Exception e) {
			throw new RuntimeException("此帳號已有人使用"
					+ e.getMessage());
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
		return memVO;
	}

	@Override
	public MemVO login(String mem_account, String mem_password) {
		MemVO memVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN); 
			pstmt.setString(1, mem_account);
			pstmt.setString(2, mem_password);
			rs = pstmt.executeQuery();
			
			while (!rs.next()) {				
					throw new Exception();
			}
			

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (Exception e) {
			throw new RuntimeException("帳號密碼不正確, 請重新輸入"
					+ e.getMessage());
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
		
		return memVO;
	}

	@Override
	public MemVO findByAccount(String mem_account) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_VO);

			pstmt.setString(1, mem_account);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));;
				memVO.setMem_account(rs.getString("mem_account"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_birthday(rs.getDate("mem_birthday"));;
				memVO.setMem_gender(rs.getInt("mem_gender"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_nickname(rs.getString("mem_nickname"));
				memVO.setMem_pic(rs.getBytes("mem_pic"));
				memVO.setMem_register(rs.getDate("mem_register"));
				memVO.setMem_update(rs.getTimestamp("mem_update"));
				memVO.setMem_point(rs.getInt("mem_point"));
				memVO.setMem_state(rs.getInt("mem_state"));
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
		return memVO;
	}

	@Override
	public void changePWD(String mem_account, String mem_password) {
		MemVO memVO = findByAccount(mem_account);
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGE_PASSWORD);
			
			pstmt.setString(1, mem_password);
			pstmt.setString(2, memVO.getMem_account());
			
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
	public List<MemVO> getSheet(Integer mem_id) {
		List<MemVO> listSheet = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SHEET);
			pstmt.setInt(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {


				memVO = new MemVO();
				memVO.setTicket_orders_id(rs.getInt("ticket_orders_id"));;
				memVO.setShowing_date(rs.getDate("showing_date"));
				memVO.setShowing(rs.getInt("showing"));
				memVO.setMovie_ch(rs.getString("movie_ch"));
				memVO.setHall_name(rs.getString("hall_name"));;
				memVO.setSelect_seat_name(rs.getString("select_seat_name"));
				memVO.setTicket_price(rs.getInt("ticket_price"));
				memVO.setBuyticket_date(rs.getTimestamp("buyticket_date"));
				listSheet.add(memVO); // Store the row in the list
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
		return listSheet;
	}
	
}
