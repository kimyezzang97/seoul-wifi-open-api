package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 초기 설정 DB 정보 수정 필요
	public static String JDBC = "jdbc:mariadb://";
	public static String IP = ""; // 자신의 DB ip 및 port 입력 ex) 127.0.0.1:3306 
	public static String SCHEMA = "/"; // 스키마명 까지
	public static String DB_ID = ""; // DB 계정
	public static String DB_PASSWORD = ""; // DB 비밀번호

	// OPEN API 인증키
	public static String API_KEY =""; // API KEY 입력 필요
	
	public UserDAO() {
		try {
			String dbURL = JDBC + IP + SCHEMA; 
			Class.forName("org.mariadb.jdbc.Driver"); // 드라이버 로드
			conn = DriverManager.getConnection(dbURL, DB_ID, DB_PASSWORD);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT member_password FROM member WHERE member_id = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword))
					return 1; // 로그인 성공
				else
					return 0; // 비밀번호 불일치
			}
			return -1; // 아이디가 없음
		} catch (Exception e){
			e.printStackTrace();
		}
		return -2; // 데이터베이스 오류
	}
	
	public int join(User user) {
		String SQL = "INSERT INTO member(member_id, member_password) VALUES(?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getMember_id());
			pstmt.setString(2, user.getMember_password());
			return pstmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return -1; // 데이터베이스 오류
	}
}
