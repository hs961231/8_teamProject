package yjc.wdb.scts.bean;

public class Coupon_holdVO {
	private String	user_id;
	private int		coupon_code;
	private String	couponhold_use_at;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getCoupon_code() {
		return coupon_code;
	}
	public void setCoupon_code(int coupon_code) {
		this.coupon_code = coupon_code;
	}
	public String getCouponhold_use_at() {
		return couponhold_use_at;
	}
	public void setCouponhold_use_at(String couponhold_use_at) {
		this.couponhold_use_at = couponhold_use_at;
	}
}
