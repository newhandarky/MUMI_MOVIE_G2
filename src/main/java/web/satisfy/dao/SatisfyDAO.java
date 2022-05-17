package web.satisfy.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import core.util.HibernateUtil;
import web.expect.entity.ExpectBean;
import web.satisfy.entity.SatisfyBean_interface;

public class SatisfyDAO implements SatisfyBean_interface{

	@Override
	public void insert(int Satisfy_id, int Mem_id, int Movie_id, int Movie_sati) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		ExpectBean insert = new ExpectBean();
		insert.setExpect_id(Satisfy_id);
		insert.setMem_id(Mem_id);
		insert.setMovie_id(Movie_id);
		insert.setMovie_expect(Movie_sati);
		session.save(insert);

		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();
		
	}

	@Override
	public List<Object[]> findByMovieID(int movie_id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createQuery("from MovieBean m,SatisfyBean s where m.movie_id = 1 and s.movie_id = 1 ").list();
		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();
		return list;
	}

}
