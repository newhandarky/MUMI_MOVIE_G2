package web.forum_article.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet(urlPatterns = { "/view/forum_article/ShowPicServlet" })
public class ShowPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String article_id = request.getParameter("article_id");
			ResultSet rs = stmt.executeQuery("select article_pic from forum_article where article_id=" + article_id);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("article_pic"));

				byte[] buf = new byte[4 * 1024]; // 4K buffer
				// 不可用availible()，因為是一段一段送，總大小不確定
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				// 用預設圖來取代Error404
				InputStream in = getServletContext().getResourceAsStream("/view/forum_article/NoData/none.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
//			System.out.println(e);
			// 用預設圖來取代Error500
			InputStream in = getServletContext().getResourceAsStream("/view/forum_article/NoData/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			// 連線池方式
			Context ctx = new InitialContext(); // import javax.naming.InitialContext
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mumiMovie");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
