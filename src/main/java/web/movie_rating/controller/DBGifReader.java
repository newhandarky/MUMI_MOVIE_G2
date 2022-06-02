package web.movie_rating.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/view/movie_rating/DBGifReader")
public class DBGifReader extends HttpServlet {
	
//	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
			Connection con = ds.getConnection();
			
			
			Statement stmt = con.createStatement();
			String movie_rating_id = req.getParameter("movie_rating_id").trim();
			ResultSet rs = stmt.executeQuery("select movie_rating_pic from movie_rating where movie_rating_id =" + movie_rating_id);

			if (rs.next()) {
//				InputStream in = rs.getBinaryStream("image");
//				InputStream in = new BufferedInputStream(rs.getBinaryStream("image"));
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("movie_rating_pic"));
				if (in.available() == -1) {
					InputStream on = getServletContext().getResourceAsStream("/view/movie/images/none3.jpg");
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
				InputStream in = getServletContext().getResourceAsStream("/view/movie/images/none3.jpg");
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
			InputStream in = getServletContext().getResourceAsStream("/view/movie/images/none3.jpg");
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
//
//	public void destroy() {
//		try {
//			if (con != null)
//				con.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}

}