package web.expect.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import core.util.HibernateUtil;
import web.expect.entity.ExpectBean;
import web.expect.entity.ExpectBean_interface;

public class ExpectDAO implements ExpectBean_interface {

	@Override
	public void insert(int Expect_id, int Mem_id ,int Movie_id ,int Movie_expect) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		ExpectBean insert = new ExpectBean();
		insert.setExpect_id(Expect_id);
		insert.setMem_id(Mem_id);
		insert.setMovie_id(Movie_id);
		insert.setMovie_expect(Movie_expect);
		session.save(insert);

		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();
	}



	@Override
	public List<Object[]> findByMovieID(int movie_id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
//		@SuppressWarnings("unchecked")
//		NativeQuery<Object[]> query2 = session.createNativeQuery("select * from expect,movie where expect.movie_id = 1");
//		List<Object[]> list2 = query2.getResultList();
// 		for (Object[] aArray : list2) {
// 			for (Object aColumn : aArray) {
// 				System.out.print(aColumn + " ");
// 			}
// 			System.out.println();
// 		}
		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createQuery("from MovieBean m,ExpectBean e where m.movie_id = 1 and e.movie_id = 1 ").list();
		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();
		return list;
	}
}
