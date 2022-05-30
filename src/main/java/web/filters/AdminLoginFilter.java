package web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.employee.entity.*;

@WebFilter(
		urlPatterns = {"/view/index/admin_index.jsp", "/view/employee/system_employee_list.jsp", "/view/employee/system_employee_add.jsp",
				"/view/employee/system_employee_revise.jsp", "/view/info/system_addinfo.jsp", "/view/info/system_editinfo.jsp", "/view/info/system_info_list.jsp"
				,"/view/employee/system_mem_list.jsp"}
)
public class AdminLoginFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object account = session.getAttribute("account");
		EmployeeVO empVO = (EmployeeVO)session.getAttribute("empVO");
		Integer emp_id = (Integer)session.getAttribute("emp_id");
		
		if (account == null) {
			
			System.out.println("狀態取得失敗?");
			
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/view/index/admin_login.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

}
