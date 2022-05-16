package web.member.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
			"INSERT INTO mumi_member (mem_account, mem_name, mem_phone, mem_password, mem_nickname, mem_register, mem_pic, mem_state, mem_point) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, 1, 0)";
	
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
	
	private static final String pic = "C:\\TGA101_WebApp\\eclipse_WTP_workspace1\\MUMI_MOVIE\\src\\main\\webapp\\mem\\image\\icons\\user.png"; 
	
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
			
			try {
				FileInputStream in = new FileInputStream(pic);
				byte[] bt = in.readAllBytes();
				pstmt.setBytes(7, bt);
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
	
}
