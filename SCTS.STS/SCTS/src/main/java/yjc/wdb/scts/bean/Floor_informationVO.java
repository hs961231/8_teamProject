package yjc.wdb.scts.bean;

import java.sql.Date;

public class Floor_informationVO {
	private int		bhf_code;
	private int		drw_code;
	private String	floorinfo_floor;
	private Date	floorinfo_rgsde;
	
	public int getBhf_code() {
		return bhf_code;
	}
	public void setBhf_code(int bhf_code) {
		this.bhf_code = bhf_code;
	}
	public int getDrw_code() {
		return drw_code;
	}
	public void setDrw_code(int drw_code) {
		this.drw_code = drw_code;
	}
	public String getFloorinfo_floor() {
		return floorinfo_floor;
	}
	public void setFloorinfo_floor(String floorinfo_floor) {
		this.floorinfo_floor = floorinfo_floor;
	}
	public Date getFloorinfo_rgsde() {
		return floorinfo_rgsde;
	}
	public void setFloorinfo_rgsde(Date floorinfo_rgsde) {
		this.floorinfo_rgsde = floorinfo_rgsde;
	}
	
}
