package goldenTime.dto;

public class DDto {
	private String region;
	private int man;
	private int woman;
	private int total;
	
	public DDto(int man, int woman) {
	
		this.man = man;
		this.woman = woman;
	
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getMan() {
		return man;
	}
	public void setMan(int man) {
		this.man = man;
	}
	public int getWoman() {
		return woman;
	}
	public void setWoman(int woman) {
		this.woman = woman;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
