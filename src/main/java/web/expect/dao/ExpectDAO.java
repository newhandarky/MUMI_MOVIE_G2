package web.expect.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jboss.jandex.Main;

import core.util.HibernateUtil;
import web.expect.entity.ExpectBean;
import web.expect.entity.ExpectBean_interface;


public class ExpectDAO implements ExpectBean_interface {
	private SessionFactory sessionFactory;
	public ExpectDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	

	
	public int findExpectTotal(int movie_id) {
		sessionFactory.getCurrentSession().beginTransaction();
		Query query = this.getSession().createQuery("select count(e) from ExpectBean e where e.movie_id = :movie_id");
		query.setParameter("movie_id", movie_id);
		List list  = query.list();
		long OB = (Long)list.get(0);
		int total = (int)OB;
		sessionFactory.getCurrentSession().getTransaction().commit();
		return total;
	} 
	public int findLikeTotal(int movie_id) {
		sessionFactory.getCurrentSession().beginTransaction();
		Query query = this.getSession().createQuery("select count(e) from ExpectBean e where e.movie_id = :movie_id and e.movie_expect = 1");
		query.setParameter("movie_id", movie_id);
		List list  = query.list();
		long OB = (Long)list.get(0);
		int total = (int)OB;
		sessionFactory.getCurrentSession().getTransaction().commit();
		return total;
	} 
	
	
	

	@Override
	public void insert(int Mem_id ,int Movie_id ,int Movie_expect) {

		sessionFactory.getCurrentSession().beginTransaction();
		ExpectBean insert = new ExpectBean();
		insert.setMem_id(Mem_id);
		insert.setMovie_id(Movie_id);
		insert.setMovie_expect(Movie_expect);
		this.getSession().save(insert);
		sessionFactory.getCurrentSession().getTransaction().commit();
	}



	@Override
	public  ExpectBean findByID(int mem_id ,int movie_id) {

//		@SuppressWarnings("unchecked")
//		NativeQuery<Object[]> query2 = session.createNativeQuery("select * from expect,movie where expect.movie_id = 1");
//		List<Object[]> list2 = query2.getResultList();
// 		for (Object[] aArray : list2) {
// 			for (Object aColumn : aArray) {
// 				System.out.print(aColumn + " ");
// 			}
// 			System.out.println();
// 		}
		try{sessionFactory.getCurrentSession().beginTransaction();
			@SuppressWarnings("unchecked")
			Query query = this.getSession().createQuery("from ExpectBean e where e.movie_id = :movie_id and e.mem_id = :mem_id ");
			query.setParameter("movie_id", movie_id);
			query.setParameter("mem_id", mem_id);
			List<ExpectBean> list = query.list();
			ExpectBean eb = list.get(0);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return eb;
		}catch(Exception e) {
		sessionFactory.getCurrentSession().getTransaction().rollback();
		return null;
		}
	}
}
