package web.movie.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet(urlPatterns = "/view/movie/DBGifReader2")

public class DBGifReader2 extends HttpServlet {

//	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
			Connection con = ds.getConnection();
			Statement stmt = con.createStatement();
			String movie_id = req.getParameter("movie_id").trim();

			ResultSet rs = stmt.executeQuery("select movie_slide_poster from movie where movie_id =" + movie_id);

			if (rs.next()) {
//				InputStream in = rs.getBinaryStream("image");
//				InputStream in = new BufferedInputStream(rs.getBinaryStream("image"));
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("movie_slide_poster"));
				if (in.available() == -1) {
					InputStream on = getServletContext().getResourceAsStream("/NoData/none2.jpg");
					byte[] b = new byte[in.available()];
					on.read(b);
					out.write(b);
					on.close();
				}
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
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
			InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

//	public void init() throws ServletException {
//		try {
//			Context ctx = new javax.naming.InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
//			con = ds.getConnection();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

//	public void destroy() {
//		try {
//			if (con != null)
//				con.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}

}