package RoleBeans;

public class AddStockBean {
	
	private String pro_name;
	private String dept_name;
	private Double pro_price;
	private int pro_qty;
	private Double pro_cgst;
	private Double pro_sgst;
	private Double base_price;
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public Double getPro_price() {
		return pro_price;
	}
	public void setPro_price(Double pro_price) {
		this.pro_price = pro_price;
	}
	public int getPro_qty() {
		return pro_qty;
	}
	public void setPro_qty(int pr_qty) {
		this.pro_qty = pr_qty;
	}
	public Double getPro_cgst() {
		return pro_cgst;
	}
	public void setPro_cgst(Double pro_cgst) {
		this.pro_cgst = pro_cgst;
	}
	public Double getPro_sgst() {
		return pro_sgst;
	}
	public void setPro_sgst(Double pro_sgst) {
		this.pro_sgst = pro_sgst;
	}
	public Double getBase_price() {
		return base_price;
	}
	public void setBase_price(Double base_price) {
		this.base_price = base_price;
	}
	
}
