package RoleBeans;

public class BillRoleBean {

	private String cus_name;
	private String cus_phone;
	private String cus_email;
	
	private String pro_selected;
	private int pro_quantity;
	private double pro_discount;
	
	
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_phone() {
		return cus_phone;
	}
	public void setCus_phone(String cus_phone) {
		this.cus_phone = cus_phone;
	}
	public String getCus_email() {
		return cus_email;
	}
	public void setCus_email(String cus_email) {
		this.cus_email = cus_email;
	}
	public String getPro_selected() {
		return pro_selected;
	}
	public void setPro_selected(String pro_selected) {
		this.pro_selected = pro_selected;
	}
	public int getPro_quantity() {
		return pro_quantity;
	}
	public void setPro_quantity(int pro_quantity) {
		this.pro_quantity = pro_quantity;
	}
	public double getPro_discount() {
		return pro_discount;
	}
	public void setPro_discount(double pro_discount) {
		this.pro_discount = pro_discount;
	}
	
}
