package web.expect.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EXPECT")
public class ExpectBean {
	@Id
	@Column(name="expect_id")
	private int expect_id;
	@Column(name="mem_id")
	private int mem_id;
	@Column(name="movie_id")
	private int movie_id;
	@Column(name="movie_expect")
	private int movie_expect;
	public int getExpect_id() {
		return expect_id;
	}
	public void setExpect_id(int expect_id) {
		this.expect_id = expect_id;
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
	public int getMovie_expect() {
		return movie_expect;
	}
	public void setMovie_expect(int movie_expect) {
		this.movie_expect = movie_expect;
	}
	@Override
	public String toString() {
		return "ExpectBean [expect_id=" + expect_id + ", mem_id=" + mem_id + ", movie_id=" + movie_id
				+ ", movie_expect=" + movie_expect + "]";
	}

}
