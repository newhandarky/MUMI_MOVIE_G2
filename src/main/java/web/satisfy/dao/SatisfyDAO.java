package web.satisfy.dao;

import java.text.NumberFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.expect.entity.ExpectBean;
import web.satisfy.entity.SatisfyBean;
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
	public void insert( int Mem_id, int Movie_id, int Movie_sati) {
		sessionFactory.getCurrentSession().beginTransaction();
		SatisfyBean insert = new SatisfyBean();
		insert.setMem_id(Mem_id);
		insert.setMovie_id(Movie_id);
		insert.setMovie_sati(Movie_sati);
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
	
	
	public  SatisfyBean findByID(int mem_id ,int movie_id) {

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
			Query query = this.getSession().createQuery("from SatisfyBean e where e.movie_id = :movie_id and e.mem_id = :mem_id ");
			query.setParameter("movie_id", movie_id);
			query.setParameter("mem_id", mem_id);
			List<SatisfyBean> list = query.list();
			SatisfyBean sb = list.get(0);
			sessionFactory.getCurrentSession().getTransaction().commit();
			return sb;
			}catch(Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return null;
			}
	}
	
	
	public String findSatifyAvg(int movie_id) {
			sessionFactory.getCurrentSession().beginTransaction();
		try{Query query = this.getSession().createQuery("SELECT avg(s.movie_sati) FROM SatisfyBean s where s.movie_id = :movie_id");
			query.setParameter("movie_id", movie_id);
			List list  = query.list();
			double OB = (Double)list.get(0);

			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(1); 

			sessionFactory.getCurrentSession().getTransaction().commit();
			return nf.format(OB);
		}catch(Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
			return "";
		}
	} 

}
