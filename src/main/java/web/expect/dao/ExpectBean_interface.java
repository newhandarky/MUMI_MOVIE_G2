package web.expect.dao;

import java.util.List;

public interface ExpectBean_interface {
    public void insert(ExpectBean expectBean);
    public void findByMovieID(int movie_id);
    public List<ExpectBean> getAll();
	void findByMovieID();
}
