//package core.util;
//import java.io.IOException;
//
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//
//
//import org.hibernate.SessionFactory;
//
//
//@WebFilter("/*")
//public class OpenSessionInViewFilter implements Filter {
//
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
//		try {
//			sessionFactory.getCurrentSession().beginTransaction();
//			chain.doFilter(request, response);
//			sessionFactory.getCurrentSession().getTransaction().commit();
//		}catch(Exception e) {
//			sessionFactory.getCurrentSession().getTransaction().rollback();
//			e.printStackTrace();
//			chain.doFilter(request, response);
//		}
//	}
//
//
//	
//	
//
//}
