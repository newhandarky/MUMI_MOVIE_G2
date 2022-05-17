package web.expect.entity;

import java.util.List;

public interface ExpectBean_interface {
    public void insert(int Expect_id, int Mem_id ,int Movie_id ,int Movie_expect);
	public List<Object[]> findByMovieID(int movie_id);
}
