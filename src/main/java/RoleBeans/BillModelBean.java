package RoleBeans;

public class BillModelBean {

	private String p_name;
	private String p_id;
	private String de_name;
	private String de_id;
	private double p_cgst;
	private double p_sgst;
	private double p_mrp;
	private double p_amount;
	private int p_qty;
	private double p_discount;
	private double profit;
	private double base_price;
	private double base_gst;
	private double profit_gst;
	private double g_gst;
	private double pr_baseprice;
	private String c_name;
	private String c_phone;
	private String c_email;
	private String bill_no;
	
	public double getPr_baseprice() {
		return pr_baseprice;
	}
	public void setPr_baseprice(double pr_baseprice) {
		this.pr_baseprice = pr_baseprice;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	
	public double getP_cgst() {
		return p_cgst;
	}
	public void setP_cgst(double p_cgst) {
		this.p_cgst = p_cgst;
	}
	public double getP_sgst() {
		return p_sgst;
	}
	public void setP_sgst(double p_sgst) {
		this.p_sgst = p_sgst;
	}
	public double getP_mrp() {
		return p_mrp;
	}
	public void setP_mrp(double p_mrp) {
		this.p_mrp = p_mrp;
	}
	public double getP_amount() {
		return p_amount;
	}
	public void setP_amount(double p_amount) {
		this.p_amount = p_amount;
	}
	public int getP_qty() {
		return p_qty;
	}
	public void setP_qty(int p_qty) {
		this.p_qty = p_qty;
	}
	public double getP_discount() {
		return p_discount;
	}
	public void setP_discount(double p_discount) {
		this.p_discount = p_discount;
	}
	public String getDe_name() {
		return de_name;
	}
	public void setDe_name(String de_name) {
		this.de_name = de_name;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getDe_id() {
		return de_id;
	}
	public void setDe_id(String de_id) {
		this.de_id = de_id;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public double getBase_price() {
		return base_price;
	}
	public void setBase_price(double base_price) {
		this.base_price = base_price;
	}
	public double getBase_gst() {
		return base_gst;
	}
	public void setBase_gst(double base_gst) {
		this.base_gst = base_gst;
	}
	public double getProfit_gst() {
		return profit_gst;
	}
	public void setProfit_gst(double profit_gst) {
		this.profit_gst = profit_gst;
	}
	public double getG_gst() {
		return g_gst;
	}
	public void setG_gst(double g_gst) {
		this.g_gst = g_gst;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_phone() {
		return c_phone;
	}
	public void setC_phone(String c_phone) {
		this.c_phone = c_phone;
	}
	public String getC_email() {
		return c_email;
	}
	public void setC_email(String c_email) {
		this.c_email = c_email;
	}
	public String getBill_no() {
		return bill_no;
	}
	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}
	
}
