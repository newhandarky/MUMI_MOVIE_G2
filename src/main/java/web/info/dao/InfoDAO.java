package web.info.dao;

import java.io.FileInputStream;
import java.io.IOException;
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

import web.member.entity.*;

import web.info.entity.InfoVO;


public class InfoDAO implements InfoDAO_interface{
	
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
			"INSERT INTO information (emp_id, info_title, info_pic, info_des, info_date, info_state) VALUES (2, ?, ?, ?, NOW(), 0)";
		private static final String GET_ALL_STMT = 
			"SELECT info_id, emp_id, info_title, info_pic, info_des, info_date, info_state FROM information order by info_id";
		private static final String GET_ONE_STMT = 
			"SELECT info_id, emp_id, info_title, info_pic, info_des, info_date, info_state FROM information where info_id = ?";
		private static final String DELETE = 
			"DELETE FROM information where info_id = ?";
		private static final String UPDATE = 
			"UPDATE information set info_title=?, info_pic=?, info_des=?, info_date=NOW(), info_state=? where info_id = ?";

	@Override
	public void insert(InfoVO infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, infoVO.getEmp_id());
			pstmt.setString(1, infoVO.getInfo_title());
			pstmt.setBytes(2, infoVO.getInfo_pic());
			pstmt.setString(3, infoVO.getInfo_des());
//			pstmt.setInt(5, infoVO.getInfo_state());
			

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
	public void update(InfoVO infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, infoVO.getInfo_title());
			pstmt.setBytes(2, infoVO.getInfo_pic());
			pstmt.setString(3, infoVO.getInfo_des());
			pstmt.setInt(4, infoVO.getInfo_state());
			pstmt.setInt(5, infoVO.getInfo_id());
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
	public void delete(Integer info_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, info_id);

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
	public InfoVO findByPrimaryKey(Integer info_id) {
		InfoVO infoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, info_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				infoVO = new InfoVO();
				infoVO.setInfo_id(rs.getInt("info_id"));;
				infoVO.setEmp_id(rs.getInt("emp_id"));
				infoVO.setInfo_title(rs.getString("info_title"));
				infoVO.setInfo_pic(rs.getBytes("info_pic"));
				infoVO.setInfo_des(rs.getString("info_des"));
				infoVO.setInfo_date(rs.getTimestamp("info_date"));
				infoVO.setInfo_state(rs.getInt("info_state"));
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
		return infoVO;
	}

	@Override
	public List<InfoVO> getAll() {
		List<InfoVO> list = new ArrayList<InfoVO>();
		InfoVO infoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				
				infoVO = new InfoVO();
				infoVO.setInfo_id(rs.getInt("info_id"));;
				infoVO.setEmp_id(rs.getInt("emp_id"));
				infoVO.setInfo_title(rs.getString("info_title"));
				infoVO.setInfo_pic(rs.getBytes("info_pic"));
				infoVO.setInfo_des(rs.getString("info_des"));
				infoVO.setInfo_date(rs.getTimestamp("info_date"));
				infoVO.setInfo_state(rs.getInt("info_state"));
				list.add(infoVO); // Store the row in the list
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
