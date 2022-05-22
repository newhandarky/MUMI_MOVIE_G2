package web.expect.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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


	@Override
	public void insert(int Expect_id, int Mem_id ,int Movie_id ,int Movie_expect) {


		ExpectBean insert = new ExpectBean();
		insert.setExpect_id(Expect_id);
		insert.setMem_id(Mem_id);
		insert.setMovie_id(Movie_id);
		insert.setMovie_expect(Movie_expect);
		this.getSession().save(insert);

	}



	@Override
	public List<Object[]> findByMovieID(int movie_id) {

//		@SuppressWarnings("unchecked")
//		NativeQuery<Object[]> query2 = session.createNativeQuery("select * from expect,movie where expect.movie_id = 1");
//		List<Object[]> list2 = query2.getResultList();
// 		for (Object[] aArray : list2) {
// 			for (Object aColumn : aArray) {
// 				System.out.print(aColumn + " ");
// 			}
// 			System.out.println();
// 		}
		sessionFactory.getCurrentSession().beginTransaction();
		@SuppressWarnings("unchecked")
		Query query = this.getSession().createQuery("from MovieBean m,ExpectBean e where m.movie_id = :movie_id and e.movie_id = :movie_id ");
		query.setParameter("movie_id", movie_id);
		List<Object[]> list = query.list(); 
		sessionFactory.getCurrentSession().getTransaction().commit();
		return list;
	}
}
