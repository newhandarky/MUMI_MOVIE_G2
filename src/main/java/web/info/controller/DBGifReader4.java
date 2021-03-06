package web.info.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet(urlPatterns = {"/view/info/DBGifReader4"})
public class DBGifReader4 extends HttpServlet {

//	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
			Connection con = ds.getConnection();
			
			Statement stmt = con.createStatement();
			String info_id = req.getParameter("info_id").trim();
			ResultSet rs = stmt.executeQuery(
					"select info_pic from MUMI_MOVIE.information where info_id =" + info_id);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("info_pic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			InputStream in = getServletContext().getResourceAsStream("/image/none2.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
//			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/image/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

//	public void init() throws ServletException {
//	      try {
//			Context ctx = new javax.naming.InitialContext();
//			  DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
//			  con = ds.getConnection();
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void destroy() {
//		try {
//			if (con != null) con.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}

}