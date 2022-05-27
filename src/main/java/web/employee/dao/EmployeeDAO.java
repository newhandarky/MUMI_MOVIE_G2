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
import web.member.entity.MemVO;


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
	
	private static final String INSERT = "INSERT INTO employeetest (emp_account,emp_name,emp_password,emp_nickname,emp_phone,emp_hiredate,emp_state) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL = "SELECT emp_id,emp_account,emp_name,emp_password,emp_nickname,emp_phone,emp_hiredate,emp_state FROM employeetest order by emp_id";
	private static final String GET_ONE = "SELECT emp_id,emp_account,emp_name,emp_password,emp_nickname,emp_phone,emp_hiredate,emp_state FROM employeetest where emp_id = ?";
//	private static final String DELETE = "DELETE FROM emp_list where emp_id = ?";
	private static final String UPDATE = "UPDATE employeetest set emp_account=?, emp_name=?, emp_password=?, emp_nickname=?, emp_phone=?, emp_hiredate=?,emp_state=? where emp_id = ?";
	
	private static final String GET_ONE_VO = "SELECT emp_id, emp_account, emp_name, emp_password, emp_nickname, "
	           + "emp_phone, emp_hiredate, emp_state FROM employeetest where emp_account = ?";
	
	private static final String LOGIN = 
			"select emp_id from employeetest where emp_account = ? and emp_password = ?";
	
	
	
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
			pstmt.setInt(7, employeeVO.getEmp_state());
			
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
			pstmt.setInt(7, employeeVO.getEmp_state());
			pstmt.setInt(8, employeeVO.getEmp_id());
			
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
	public EmployeeVO findByPrimaryKey(Integer emp_id) {

		EmployeeVO employeeVO = null;
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
				employeeVO = new EmployeeVO();
				employeeVO.setEmp_id(rs.getInt("emp_id"));
				employeeVO.setEmp_account(rs.getString("emp_account"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_password(rs.getString("emp_password"));
				employeeVO.setEmp_nickname(rs.getString("emp_nickname"));
				employeeVO.setEmp_phone(rs.getString("emp_phone"));
				employeeVO.setEmp_hiredate(rs.getDate("emp_hiredate"));
				employeeVO.setEmp_state(rs.getInt("emp_state"));
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
		return employeeVO;
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
				employeeVO.setEmp_state(rs.getInt("emp_state"));
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

	
	@Override
	public EmployeeVO findByAccount(String emp_account) {
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_VO);

			pstmt.setString(1, emp_account);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				employeeVO = new EmployeeVO();
				employeeVO.setEmp_id(rs.getInt("emp_id"));
				employeeVO.setEmp_account(rs.getString("emp_account"));
				employeeVO.setEmp_name(rs.getString("emp_name"));
				employeeVO.setEmp_password(rs.getString("emp_password"));
				employeeVO.setEmp_nickname(rs.getString("emp_nickname"));
				employeeVO.setEmp_phone(rs.getString("emp_phone"));
				employeeVO.setEmp_hiredate(rs.getDate("emp_hiredate"));
				employeeVO.setEmp_state(rs.getInt("emp_state"));
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
		return employeeVO;
	}

	
	@Override
	public EmployeeVO login(String emp_account, String emp_password) {
		EmployeeVO empVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN); 
			pstmt.setString(1, emp_account);
			pstmt.setString(2, emp_password);
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
			return empVO;
	}	
}
