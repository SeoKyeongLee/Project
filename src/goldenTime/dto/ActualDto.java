package goldenTime.dto;

public class ActualDto {
	private int year;
	private int numOfEm;
	
	public ActualDto() {
		
	}
	
	public ActualDto(int year, int numOfEm) {
		this.year = year;
		this.numOfEm = numOfEm;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getNumOfEm() {
		return numOfEm;
	}

	public void setNumOfEm(int numOfEm) {
		this.numOfEm = numOfEm;
	}
}
