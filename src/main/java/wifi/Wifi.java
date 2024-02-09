package wifi;

public class Wifi {
	int wifi_id;
	String wifi_no;
	String wifi_area;
	String wifi_main_nm;
	String wifi_address;
	String wifi_address2;
	String wifi_lat;
	String wifi_lnt;
	
	public int getWifi_id() {
		return wifi_id;
	}
	public void setWifi_id(int wifi_id) {
		this.wifi_id = wifi_id;
	}
	public String getWifi_no() {
		return wifi_no;
	}
	public void setWifi_no(String wifi_no) {
		this.wifi_no = wifi_no;
	}
	public String getWifi_area() {
		return wifi_area;
	}
	public void setWifi_area(String wifi_area) {
		this.wifi_area = wifi_area;
	}
	public String getWifi_main_nm() {
		return wifi_main_nm;
	}
	public void setWifi_main_nm(String wifi_main_nm) {
		this.wifi_main_nm = wifi_main_nm;
	}
	public String getWifi_address() {
		return wifi_address;
	}
	public void setWifi_address(String wifi_address) {
		this.wifi_address = wifi_address;
	}
	public String getWifi_address2() {
		return wifi_address2;
	}
	public void setWifi_address2(String wifi_address2) {
		this.wifi_address2 = wifi_address2;
	}
	public String getWifi_lat() {
		return wifi_lat;
	}
	public void setWifi_lat(String wifi_lat) {
		this.wifi_lat = wifi_lat;
	}
	public String getWifi_lnt() {
		return wifi_lnt;
	}
	public void setWifi_lnt(String wifi_lnt) {
		this.wifi_lnt = wifi_lnt;
	}
	public Wifi(int wifi_id, String wifi_no, String wifi_area, String wifi_main_nm, String wifi_address,
			String wifi_address2, String wifi_lat, String wifi_lnt) {
		super();
		this.wifi_id = wifi_id;
		this.wifi_no = wifi_no;
		this.wifi_area = wifi_area;
		this.wifi_main_nm = wifi_main_nm;
		this.wifi_address = wifi_address;
		this.wifi_address2 = wifi_address2;
		this.wifi_lat = wifi_lat;
		this.wifi_lnt = wifi_lnt;
	}
	
}
