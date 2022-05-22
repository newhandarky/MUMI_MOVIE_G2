package web.satisfy.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.expect.entity.ExpectBean;
import web.satisfy.entity.SatisfyBean_interface;

public class SatisfyDAO implements SatisfyBean_interface{
	private SessionFactory sessionFactory;
	public SatisfyDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	

	@Override
	public void insert(int Satisfy_id, int Mem_id, int Movie_id, int Movie_sati) {
		sessionFactory.getCurrentSession().beginTransaction();
		ExpectBean insert = new ExpectBean();
		insert.setExpect_id(Satisfy_id);
		insert.setMem_id(Mem_id);
		insert.setMovie_id(Movie_id);
		insert.setMovie_expect(Movie_sati);
		sessionFactory.getCurrentSession().save(insert);
		sessionFactory.getCurrentSession().getTransaction().commit();
		
	}

	@Override
	public List<Object[]> findByMovieID(int movie_id) {
		sessionFactory.getCurrentSession().beginTransaction();
		@SuppressWarnings("unchecked")
		Query query = this.getSession().createQuery("from MovieBean m,SatisfyBean s where m.movie_id = :movie_id and s.movie_id = :movie_id ");
		query.setParameter("movie_id", movie_id);
		List<Object[]> list = query.list(); 
		sessionFactory.getCurrentSession().getTransaction().commit();
		return list;
	}

}
