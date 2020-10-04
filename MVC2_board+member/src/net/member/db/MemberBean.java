package net.member.db;

//DB에 값을 주고 받을 때 사용하는 빈즈 클래스
public class MemberBean {
	
	private int user_no = 0;
	private String idid = null;
	private String pw = null;
	private String mail = null;
	private String name1 = null;
	private String birthday = null;
	private String chk = null;
	private String address = null;
	
	
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getIdid() {
		return idid;
	}
	public void setIdid(String idid) {
		this.idid = idid;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
