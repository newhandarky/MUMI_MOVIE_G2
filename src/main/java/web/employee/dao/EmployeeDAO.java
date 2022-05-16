package web.employee.dao;

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

import web.employee.entity.EmployeeVO;


public class EmployeeDAO implements EmployeeDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO employee (emp_account,emp_name,emp_password,emp_nickname,emp_phone,emp_hiredate) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL = "SELECT emp_id,emp_account,emp_name,emp_password,emp_nickname,emp_phone,emp_hiredate FROM employee order by emp_id";
	private static final String GET_ONE = "SELECT emp_id,emp_account,emp_name,emp_password,emp_nickname,emp_phone,emp_hiredate FROM employee where emp_id = ?";
//	private static final String DELETE = "DELETE FROM emp_list where emp_id = ?";
	private static final String UPDATE = "UPDATE employee set emp_account=?, emp_name=?, emp_password=?, emp_nickname=?, emp_phone=?, emp_hiredate=? where emp_id = ?";
	
	@Override
	public void insert(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, employeeVO.getEmp_account());
			pstmt.setString(2, employeeVO.getEmp_name());
			pstmt.setString(3, employeeVO.getEmp_password());
			pstmt.setString(4, employeeVO.getEmp_nickname());
			pstmt.setString(5, employeeVO.getEmp_phone());
			pstmt.setDate(6, employeeVO.getEmp_hiredate());

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
	public void update(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, employeeVO.getEmp_account());
			pstmt.setString(2, employeeVO.getEmp_name());
			pstmt.setString(3, employeeVO.getEmp_password());
			pstmt.setString(4, employeeVO.getEmp_nickname());
			pstmt.setString(5, employeeVO.getEmp_phone());
			pstmt.setDate(6, employeeVO.getEmp_hiredate());
			pstmt.setInt(7, employeeVO.getEmp_id());

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

	@Override
	public EmployeeVO findByPrimaryKey(Integer emp_id) {

		EmployeeVO emplyeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setInt(1, emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				emplyeeVO = new EmployeeVO();
				emplyeeVO.setEmp_id(rs.getInt("emp_id"));
				emplyeeVO.setEmp_account(rs.getString("emp_account"));
				emplyeeVO.setEmp_name(rs.getString("emp_name"));
				emplyeeVO.setEmp_password(rs.getString("emp_password"));
				emplyeeVO.setEmp_nickname(rs.getString("emp_nickname"));
				emplyeeVO.setEmp_phone(rs.getString("emp_phone"));
				emplyeeVO.setEmp_hiredate(rs.getDate("emp_hiredate"));
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
		return emplyeeVO;
	}
	
	
	@Override
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				employeeVO = new EmployeeVO();
				employeeVO.setEmp_id(rs.getInt("emp_id"));
				employeeVO.setEmp_account(rs.getString("emp_account"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_password(rs.getString("emp_password"));
				employeeVO.setEmp_nickname(rs.getString("emp_nickname"));
				employeeVO.setEmp_phone(rs.getString("emp_phone"));
				employeeVO.setEmp_hiredate(rs.getDate("emp_hiredate"));
				list.add(employeeVO); // Store the row in the list
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
//	public List<EmpVO> getAll() {
//		System.out.println("BBB");
//		List<EmpVO> list = new ArrayList<EmpVO>();
//		EmpVO empVO = null;
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
//				empVO = new EmpVO();
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

	
	
	
//	private static final String INSERT_STMT = 
//			"INSERT INTO mumi_member (mem_account, mem_name, mem_phone, mem_password, mem_nickname, mem_register, mem_pic, mem_state, mem_point) "
//			+ "VALUES (?, ?, ?, ?, ?, ?, ?, 1, 0)";
//	
//	private static final String GET_ALL_STMT = 
//			"SELECT mem_id, mem_account, mem_name, mem_phone, mem_birthday, mem_gender, mem_address, "
//			+ "mem_password, mem_nickname, mem_pic, mem_register, mem_update, mem_point, mem_state FROM mumi_member order by mem_id";
//	
//	private static final String GET_ONE_STMT = 
//			"SELECT mem_id, mem_account, mem_name, mem_phone, mem_birthday, mem_gender, mem_address, "
//			+ "mem_password, mem_nickname, mem_pic, mem_register, mem_update, mem_point, mem_state FROM mumi_member where mem_id = ?";
//	
//	private static final String GET_ONE_ACCOUNT = 
//			"SELECT mem_id FROM mumi_member where mem_account = ?";
//	
//	private static final String DELETE = 
//			"DELETE FROM mumi_member where mem_id = ?";
//	
//	private static final String UPDATE = 
//			"UPDATE mumi_member set mem_name=?, mem_phone=?, mem_address=?, mem_password=? ,mem_nickname=? ,"
//			+ "mem_birthday=? , mem_pic =? , mem_gender=? , mem_update=NOW(), mem_state=1 where mem_id = ?";
//	
//	private static final String UPDATE_STATE_CLOSE = 
//			"UPDATE mumi_member set mem_state=0 where mem_id = ?";
//	
//	private static final String UPDATE_STATE_OPEN = 
//			"UPDATE mumi_member set mem_state=1 where mem_id = ?";
//	
//	private static final String pic = "C:\\TGA101_WebApp\\eclipse_WTP_workspace1\\MUMI_MOVIE\\src\\main\\webapp\\mem\\image\\icons\\user.png"; 
//
//	
//	@Override
//	public void insert(MemVO memVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		
//
//		try {
//			
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//			
//			checkAccount(memVO.getMem_account());
//			
//			pstmt.setString(1, memVO.getMem_account());
//			pstmt.setString(2, memVO.getMem_name());
//			pstmt.setString(3, memVO.getMem_phone());
//			pstmt.setString(4, memVO.getMem_password());
//			pstmt.setString(5, memVO.getMem_nickname());
//			pstmt.setDate(6, memVO.getMem_register());
//			
//			try {
//				FileInputStream in = new FileInputStream(pic);
//				byte[] bt = in.readAllBytes();
//				pstmt.setBytes(7, bt);
//				in.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
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
//
//	}
//
//	@Override
//	public void update(MemVO memVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//
//			pstmt.setString(1, memVO.getMem_name());
//			pstmt.setString(2, memVO.getMem_phone());
//			pstmt.setString(3, memVO.getMem_address());
//			pstmt.setString(4, memVO.getMem_password());
//			pstmt.setString(5, memVO.getMem_nickname());
//			pstmt.setDate(6, memVO.getMem_birthday());
//			pstmt.setBytes(7, memVO.getMem_pic());
//			pstmt.setInt(8, memVO.getMem_gender());			
//			pstmt.setInt(9, memVO.getMem_id());
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
//	
//	public void updateClose(MemVO memVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE_STATE_CLOSE);
//
//			
//			pstmt.setInt(1, memVO.getMem_id());
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
//	public void updateOpen(MemVO memVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE_STATE_OPEN);
//
//			
//			pstmt.setInt(1, memVO.getMem_id());
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
//	public void updateState(Integer mem_id) {
//		// TODO Auto-generated method stub
//		
//		
//		if(findByPrimaryKey(mem_id).getMem_state() == 1) {
//			
//			updateClose(findByPrimaryKey(mem_id));
//		}else if(findByPrimaryKey(mem_id).getMem_state() == 0) {
//			
//			updateOpen(findByPrimaryKey(mem_id));
//			
//		}
//		
//	}
//
//	@Override
//	public void delete(Integer mem_id) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, mem_id);
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
////	@Override
//	public MemVO findByPrimaryKey(Integer mem_id) {
//
//		MemVO memVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, mem_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				memVO = new MemVO();
//				memVO.setMem_id(rs.getInt("mem_id"));;
//				memVO.setMem_account(rs.getString("mem_account"));
//				memVO.setMem_name(rs.getString("mem_name"));
//				memVO.setMem_phone(rs.getString("mem_phone"));
//				memVO.setMem_birthday(rs.getDate("mem_birthday"));;
//				memVO.setMem_gender(rs.getInt("mem_gender"));
//				memVO.setMem_address(rs.getString("mem_address"));
//				memVO.setMem_password(rs.getString("mem_password"));
//				memVO.setMem_nickname(rs.getString("mem_nickname"));
//				memVO.setMem_pic(rs.getBytes("mem_pic"));
//				memVO.setMem_register(rs.getDate("mem_register"));
//				memVO.setMem_update(rs.getTimestamp("mem_update"));
//				memVO.setMem_point(rs.getInt("mem_point"));
//				memVO.setMem_state(rs.getInt("mem_state"));
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
//		return memVO;
//	}
//
//	@Override
//	public List<MemVO> getAll() {
//		List<MemVO> list = new ArrayList<MemVO>();
//		MemVO memVO = null;
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
//
//
//				memVO = new MemVO();
//				memVO.setMem_id(rs.getInt("mem_id"));;
//				memVO.setMem_account(rs.getString("mem_account"));
//				memVO.setMem_name(rs.getString("mem_name"));
//				memVO.setMem_phone(rs.getString("mem_phone"));
//				memVO.setMem_birthday(rs.getDate("mem_birthday"));;
//				memVO.setMem_gender(rs.getInt("mem_gender"));
//				memVO.setMem_address(rs.getString("mem_address"));
//				memVO.setMem_password(rs.getString("mem_password"));
//				memVO.setMem_nickname(rs.getString("mem_nickname"));
//				memVO.setMem_pic(rs.getBytes("mem_pic"));
//				memVO.setMem_update(rs.getTimestamp("mem_update"));
//				memVO.setMem_point(rs.getInt("mem_point"));
//				memVO.setMem_state(rs.getInt("mem_state"));
//				list.add(memVO); // Store the row in the list
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
//
//	@Override
//	public MemVO checkAccount(String mem_account) {
//		MemVO memVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_ACCOUNT);  // Anaturis@superrito.com
//			pstmt.setString(1, mem_account);
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {				
//				if(rs.getRow() != 0) {
//					throw new Exception();
//				}
//			}
//			
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} catch (Exception e) {
//			throw new RuntimeException("此帳號已有人使用"
//					+ e.getMessage());
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
//		return memVO;
//	}

	
	
}
