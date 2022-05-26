package web.satisfy.entity;

import java.util.List;


public interface SatisfyBean_interface {
    public void insert(int Mem_id, int Movie_id, int Movie_sati);
	public List<Object[]> findByMovieID(int movie_id);
	public  SatisfyBean findByID(int mem_id ,int movie_id);
	public String findSatifyAvg(int movie_id) ;
}
