package web.expect.entity;

import java.util.List;

public interface ExpectBean_interface {
    public void insert(int Expect_id, int Mem_id ,int Movie_id ,int Movie_expect);
	public ExpectBean findByID(int mem_id, int movie_id);
	public int findExpectTotal(int movie_id);
}
