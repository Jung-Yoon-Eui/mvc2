package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	// 메소드 				자바 파일 명 			> 연결할 메소드 이름
	// 로그인			MemberLoginAction.java  	> memberLogin
	// 회원 가입		MemberJoinAction.java		> memberJoin
	// 회원 목록 보기	MemberListAction.java		> getList
	// 회원 상세 정보	MemberIntoAction.java		> getInto
	// 회원 삭제		MemberDeleteAction.java	> setDelete
	
	// 커넥션 풀 (Connection Pool) : 생성자에 넣어서 매번 초기화.
	public MemberDAO() {
		try{
			Context init = new InitialContext();
	  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	  		conn = ds.getConnection();
		}catch(Exception e){
			System.out.println("DB 연결 실패 : " + e);
			return;
		}
	}
	
	// 로그인 memberLogin
	public boolean memberLogin(String idid, String pw) {
		
		String sql = "SELECT pw"
						+" FROM MEMBER0921"
						+" WHERE idid=?";
		try{
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, idid);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				if(pw.equals(rs.getString("pw"))){
					return true;
				}
			}
			
		}catch(SQLException e){
			System.out.println("memberLogin 에러 : "+e);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt !=null)try{pstmt.close();}catch(SQLException e){}
		}
		return false;
	}
	
	// 회원 가입 memberJoin
	public boolean memberJoin(MemberBean member) {
		
		int result = 0;
		String sql = "INSERT INTO MEMBER0921 (USER_NO, idid, pw, mail, name1, birthday, chk, address) VALUES"
				+ " (MEM_NUM.NEXTVAL,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1,member.getIdid());
			pstmt.setString(2,member.getPw());
			pstmt.setString(3,member.getMail());
			pstmt.setString(4,member.getName1());
			pstmt.setString(5,member.getBirthday());
			pstmt.setString(6,member.getChk());
			pstmt.setString(7,member.getAddress());
			
			result = pstmt.executeUpdate();
			
			if(result==0) {
				return false;
			}
			return true;
			
		}catch(Exception e){
			System.out.println("memberJoin 에러 : "+e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return false;
	}
	
	// 회원 목록 보기 getList
	public List getList() {
		
		String sql = "SELECT user_no, idid, name1 FROM MEMBER0921 order by user_no";
	
		List list = new ArrayList();
		
		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MemberBean member = new MemberBean();

				member.setUser_no(rs.getInt("user_no"));
				member.setIdid(rs.getString("idid"));
				member.setName1(rs.getString("name1"));
				list.add(member);
			}

			return list;
		}catch(Exception e){
			System.out.println("getList 에러 : " + e);
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}
	
	
	// 회원 상세 정보 getInto
	public MemberBean getInto (int num) {
		
		MemberBean member = new MemberBean();
		
		try{
			pstmt = conn.prepareStatement("select * from MEMBER0921 where user_no = ?");
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				member.setUser_no(rs.getInt("user_no"));
				member.setIdid(rs.getString("idid"));
				member.setPw(rs.getString("pw"));
				member.setMail(rs.getString("mail"));
				member.setName1(rs.getString("name1"));
				member.setBirthday(rs.getString("birthday"));
				member.setChk(rs.getString("chk"));
				member.setAddress(rs.getString("address"));
			}
			return member;
			
		}catch(Exception e){
			System.out.println("getInto 에러 : " + e);
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt !=null)try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}
	
	
	// 회원 삭제 setDelete
	public boolean setDelete(int num) {
		
		String sql = "delete from MEMBER0921 where user_no= ? ";
		
		int result = 0;
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
			if(result==0) {
				return false;
			}
			return true;
			
		}catch(Exception e){
			System.out.println("setDelete 에러 : "+e);
		}finally{
			try{
				if(pstmt!=null) {
					pstmt.close();
				}
			}catch(Exception e) {}
		}
		return false;
	}
}