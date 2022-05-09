package web.expect.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import model.hibernate.HibernateUtil;

public class ExpectDAO implements ExpectBean_interface {
public static void main(String[] args) {
	ExpectDAO dao = new ExpectDAO();
	dao.findByMovieID(); 
	
	
}
	@Override
	public void insert(ExpectBean expectBean) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		ExpectBean insert = new ExpectBean();
		insert.setExpect_id(6);
		insert.setMem_id(12345);
		insert.setMovie_id(2);
		insert.setMovie_expect(5);
		session.save(insert);

		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();
	}



	@Override
	public void findByMovieID() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		NativeQuery<Object[]> query2 = session.createNativeQuery("select * from expect");
		List<Object[]> list2 = query2.getResultList();
 		for (Object[] aArray : list2) {
 			for (Object aColumn : aArray) {
 				System.out.print(aColumn + " ");
 			}
 			System.out.println();
 		}
//		List<Object[]> list = session.createQuery(
//				"from ExpectBean as e, MovieBean as m where e.movie_id = 1 and m.movie_id = 1  ").list();
//		
//		for (Object[] objects : list) {
//			ExpectBean eb = (ExpectBean) objects[0];
//			MovieBean mb = (MovieBean) objects[1];
//			System.out.println(eb);
//		}
		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();
	}

	@Override
	public List<ExpectBean> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void findByMovieID(int movie_id) {
		// TODO Auto-generated method stub
		
	}

}
