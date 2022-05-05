package web.member.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class MemVO implements java.io.Serializable{
	private Integer mem_id;
	private String mem_account;
	private String mem_name;
	private String mem_phone;
	private Date mem_birthday;
	private Integer mem_gender;
	private String mem_address;
	private String mem_password;
	private String mem_nickname;
	private byte[] mem_pic;
	private Date mem_register;
	private Timestamp mem_update;
	private Integer mem_point;
	private Integer mem_state;
	
	
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_account() {
		return mem_account;
	}
	public void setMem_account(String mem_account) {
		this.mem_account = mem_account;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public Date getMem_birthday() {
		return mem_birthday;
	}
	public void setMem_birthday(Date mem_birthday) {
		this.mem_birthday = mem_birthday;
	}
	public Integer getMem_gender() {
		return mem_gender;
	}
	public void setMem_gender(Integer mem_gender) {
		this.mem_gender = mem_gender;
	}
	public String getMem_address() {
		return mem_address;
	}
	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	}
	public String getMem_password() {
		return mem_password;
	}
	public void setMem_password(String mem_password) {
		this.mem_password = mem_password;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
	public byte[] getMem_pic() {
		return mem_pic;
	}
	public void setMem_pic(byte[] mem_pic) {
		this.mem_pic = mem_pic;
	}
	public Date getMem_register() {
		return mem_register;
	}
	public void setMem_register(Date mem_register) {
		this.mem_register = mem_register;
	}
	public Timestamp getMem_update() {
		return mem_update;
	}
	public void setMem_update(Timestamp mem_update) {
		this.mem_update = mem_update;
	}
	public Integer getMem_point() {
		return mem_point;
	}
	public void setMem_point(Integer mem_point) {
		this.mem_point = mem_point;
	}
	public Integer getMem_state() {
		return mem_state;
	}
	public void setMem_state(Integer mem_state) {
		this.mem_state = mem_state;
	}
	

}
