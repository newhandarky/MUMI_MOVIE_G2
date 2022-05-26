package web.satisfy.entity;

public class SatisfyVO {
	private int mem_id;
	private int movie_id;
	private int satisfy;
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
	public int getSatisfy() {
		return satisfy;
	}
	public void setSatisfy(int satisfy) {
		this.satisfy = satisfy;
	}
	@Override
	public String toString() {
		return "SatisfyVO [mem_id=" + mem_id + ", movie_id=" + movie_id + ", satisfy=" + satisfy + "]";
	}
	
}
