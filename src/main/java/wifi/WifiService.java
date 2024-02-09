package wifi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import user.UserDAO;


public class WifiService {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public static int UPDATE_COUNT = 1; //
	private static String URL = "http://openapi.seoul.go.kr:8088/" + UserDAO.API_KEY + "/json/TbPublicWifiInfo/";
	
	public int getWifiCnt(String str) {
		HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + str)) // 요청할 URL을 지정합니다. 1/1
                .header("Content-Type", "application/json") // 요청의 Content-Type을 JSON으로 지정합니다.
                .GET() // GET 메서드를 사용합니다.
                .build();
        
        try {
        	HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            // 응답 코드 확인
            int statusCode = response.statusCode();
            System.out.println("Response Code: " + statusCode);

            // 응답 본문을 JSON 형식으로 출력
            String responseBody = response.body();
            System.out.println("Response Body: " + responseBody);
            
            JSONObject jsonObject = new JSONObject(responseBody);
            //String map = jsonObject.getString("TbPublicWifiInfo");
            
            JSONObject jsonObject2 = jsonObject.getJSONObject("TbPublicWifiInfo");
            
            int cnt = jsonObject2.getInt("list_total_count");
            return cnt;
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return 0;
	}
	
	public String getWifiData(String str) {
		HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + str)) // 요청할 URL을 지정합니다. 1/1
                .header("Content-Type", "application/json") // 요청의 Content-Type을 JSON으로 지정합니다.
                .GET() // GET 메서드를 사용합니다.
                .build();
        
        try {
        	HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            
            // 응답 코드 확인
            int statusCode = response.statusCode();
            System.out.println("Response Code: " + statusCode);

            // 응답 본문을 JSON 형식으로 출력
            String responseBody = response.body();
            //System.out.println("Response Body: " + responseBody);
            
            return responseBody;
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return "";
	}
	
	public ArrayList<Wifi> getWifiInfos(int cnt) {
		int quotient = cnt / 1000;
		ArrayList<Wifi> list = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
        String str = "";
		for(int i=0; i<quotient; i++) {
			str = (1+1000*i) + "/" + (1000+(i*1000));
			HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(URL + str)) // 요청할 URL을 지정합니다. 1/1
	                .header("Content-Type", "application/json") // 요청의 Content-Type을 JSON으로 지정합니다.
	                .GET() // GET 메서드를 사용합니다.
	                .build();
			
			try {
	        	HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
	            
	            // 응답 코드 확인
	            int statusCode = response.statusCode();
	            System.out.println("Response Code: " + statusCode);

	            // 응답 본문을 JSON 형식으로 출력
	            String responseBody = response.body();
	            //System.out.println("Response Body: " + responseBody);
	            
	            JSONObject jsonObject = new JSONObject(responseBody);
	            JSONObject jsonObject2 = jsonObject.getJSONObject("TbPublicWifiInfo");
	            JSONArray jArray = jsonObject2.getJSONArray("row");
	            
	            for(int k=0; k<jArray.length(); k++) {
	            	JSONObject obj = jArray.getJSONObject(k);
	            	String wifi_no = obj.getString("X_SWIFI_MGR_NO");
	            	String wifi_area = obj.getString("X_SWIFI_WRDOFC");;
	            	String wifi_main_nm = obj.getString("X_SWIFI_MAIN_NM");;
	            	String wifi_address1 = obj.getString("X_SWIFI_ADRES1");;
	            	String wifi_address2 = obj.getString("X_SWIFI_ADRES2");;
	            	String wifi_lat = obj.getString("LAT");;
	            	String wifi_lnt = obj.getString("LNT");;
	            	Wifi wifiw = new Wifi(0, wifi_no, wifi_area, wifi_main_nm, wifi_address1, wifi_address2, wifi_lat, wifi_lnt);
	            	list.add(wifiw);
	            }
	            
	        } catch(Exception e) {
	        	e.printStackTrace();
	        }
		}
		
		return list;
	}
	
	
	public int updateDB(ArrayList<Wifi> wifi) {
		try {
			String dbURL = UserDAO.JDBC + UserDAO.IP + UserDAO.SCHEMA;
			Class.forName("org.mariadb.jdbc.Driver"); // 드라이버 로드
			conn = DriverManager.getConnection(dbURL, UserDAO.DB_ID, UserDAO.DB_PASSWORD);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		for(int i=0; i<wifi.size(); i++) {
			Wifi wifit = wifi.get(i);
			String SQL = "insert into wifi(wifi_no, wifi_area, wifi_main_nm, wifi_address1, wifi_address2, wifi_lat, wifi_lnt) "
					+ "values (?, ?, ?, ?, ?, ?, ?);";
			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, wifit.getWifi_no());
				pstmt.setString(2, wifit.getWifi_area());
				pstmt.setString(3, wifit.getWifi_main_nm());
				pstmt.setString(4, wifit.getWifi_address());
				pstmt.setString(5, wifit.getWifi_address2());
				pstmt.setString(6, wifit.getWifi_lat());
				pstmt.setString(7, wifit.getWifi_lnt());
                
				pstmt.executeUpdate();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return 1;
	}
	
	public int updateWifi() {
		if(UPDATE_COUNT == 1) {
			return 0;
			
		} else {
			int wifiCnt = getWifiCnt("1/1");
			ArrayList<Wifi> wifiList = getWifiInfos(wifiCnt);
			int result = updateDB(wifiList);
			
			return result;
		}
	}
	
	public ArrayList<Wifi> getNearWifiList(String lat, String lnt){
		ArrayList<Wifi> list = new ArrayList<>();
		
		try {
			String dbURL = UserDAO.JDBC + UserDAO.IP + UserDAO.SCHEMA;
			Class.forName("org.mariadb.jdbc.Driver"); // 드라이버 로드
			conn = DriverManager.getConnection(dbURL, UserDAO.DB_ID, UserDAO.DB_PASSWORD);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
            //위도 경도 구하는 식
            String sql = " SELECT *, " +
                    " round(6371*acos(cos(radians(?))*cos(radians(wifi_lat))*cos(radians(wifi_lnt) " +
                    " -radians(?))+sin(radians(?))*sin(radians(wifi_lat))), 4) " +
                    " AS distance " +
                    " FROM wifi " +
                    " ORDER BY distance " +
                    " LIMIT 20;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, Double.parseDouble(lat));
            pstmt.setDouble(2, Double.parseDouble(lnt));
            pstmt.setDouble(3, Double.parseDouble(lat));

            rs = pstmt.executeQuery();

            while (rs.next()) {
            	Wifi wifiDTO = new Wifi(1, rs.getString("wifi_no"), rs.getString("wifi_area"), rs.getString("wifi_main_nm"),
            			rs.getString("wifi_address1"), rs.getString("wifi_address2"), rs.getString("wifi_lat"), rs.getString("wifi_lnt"));
                        
                list.add(wifiDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		
		return list;
	}
	
	public void bookMarkAdd(String id, String wifi_no){
		try {
			String dbURL = UserDAO.JDBC + UserDAO.IP + UserDAO.SCHEMA;
			Class.forName("org.mariadb.jdbc.Driver"); // 드라이버 로드
			conn = DriverManager.getConnection(dbURL, UserDAO.DB_ID, UserDAO.DB_PASSWORD);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
            String sql = " insert into bookmark(member_id, wifi_no) values(?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, wifi_no);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				conn.close();
				pstmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
			
	}
	
	public ArrayList<Wifi> getBookMarkList(String id){
		ArrayList<Wifi> list = new ArrayList<>();
		
		try {
			String dbURL = UserDAO.JDBC + UserDAO.IP + UserDAO.SCHEMA;
			Class.forName("org.mariadb.jdbc.Driver"); // 드라이버 로드
			conn = DriverManager.getConnection(dbURL, UserDAO.DB_ID, UserDAO.DB_PASSWORD);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			String sql = " select * from wifi w "
                    + "join bookmark b "
                    + "on w.wifi_no  = b.wifi_no "
                    + "where b.member_id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
       
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	Wifi wifiDTO = new Wifi(1, rs.getString("wifi_no"), rs.getString("wifi_area"), rs.getString("wifi_main_nm"),
            			rs.getString("wifi_address1"), rs.getString("wifi_address2"), rs.getString("wifi_lat"), rs.getString("wifi_lnt"));
                        
                list.add(wifiDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				conn.close();
				pstmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		
		return list;
	}
	
	public void bookMarkDelete(String id, String wifi_no){
		try {
			String dbURL = UserDAO.JDBC + UserDAO.IP + UserDAO.SCHEMA;
			Class.forName("org.mariadb.jdbc.Driver"); // 드라이버 로드
			conn = DriverManager.getConnection(dbURL, UserDAO.DB_ID, UserDAO.DB_PASSWORD);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
            String sql = " delete from bookmark where member_id = ? "
            		+ " and wifi_no =  ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, wifi_no);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }		
	}
	
}
