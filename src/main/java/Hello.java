import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = null;
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/mumiMovie");
		} catch (NamingException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		try(
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from mumi_member ");
				ResultSet rs = ps.executeQuery();
				PrintWriter pw = response.getWriter();
			) {
			while(rs.next()) {
				final Integer mem_id = rs.getInt("mem_id");
				final String mem_nickname = rs.getString("mem_nickname");
				pw.println(mem_id);
				pw.println(mem_nickname);
			}
			pw.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
