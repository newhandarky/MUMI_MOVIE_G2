package web.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.member.entity.MemVO;
import web.member.service.MemService;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
		urlPatterns = {"/view/mem/mem_revise.jsp", "/view/ticket_orders/order_beware.jsp" ,"/view/forum_article/ForumIndex.html", "/view/mem/mem_profiles.jsp"}
)
public class LoginFilter implements Filter {

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
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		Integer mem_id = (Integer)session.getAttribute("mem_id");
		String mem_nickname = (String)session.getAttribute("mem_nickname");
		
		if (account == null) {
			
			System.out.println("狀態取得失敗?");
			
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/view/index/login.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

}