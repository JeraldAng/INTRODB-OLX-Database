package body;

public class promodata {
	private String packagename, discount;
	public String getPackagename() {
		return packagename;
	}
	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	private double price;
	private int slotnum;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSlotnum() {
		return slotnum;
	}
	public void setSlotnum(int slotnum) {
		this.slotnum = slotnum;
	}
}
