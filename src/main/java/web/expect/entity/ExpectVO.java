package web.expect.entity;

public class ExpectVO {
	private int mem_id;
	private int movie_id;
	private int expect;
	@Override
	public String toString() {
		return "ExpectVO [mem_id=" + mem_id + ", movie_id=" + movie_id + ", expect=" + expect + "]";
	}
	public int getMem_id() {
		return mem_id;
	}
	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public int getExpect() {
		return expect;
	}
	public void setExpect(int expect) {
		this.expect = expect;
	}
	
}
