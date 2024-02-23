package goldenTime.dto;

public class CongestionDto {
	private int year;
	private double congestion;
	
	public CongestionDto() {
		
	}
	
	public CongestionDto(int year, double congestion) {
		this.year = year;
		this.congestion = congestion;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getCongestion() {
		return congestion;
	}

	public void setCongestion(double congestion) {
		this.congestion = congestion;
	}
	
	
}
