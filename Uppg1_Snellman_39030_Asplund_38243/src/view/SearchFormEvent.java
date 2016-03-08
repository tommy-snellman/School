package view;

public class SearchFormEvent {
	private String t1;
	private String t2;
	private String dateF;
	private String dateT;
	
	public SearchFormEvent(String t1, String t2, String dateF, String dateT) {
		this.t1 = t1;
		this.t2 = t2;
		this.dateF = dateF;
		this.dateT = dateT;
	}
	
	public String getT1() {
		return t1;
	}
	
	public String getT2() {
		return t2;
	}
	
	public String getDate1() {
		return dateF;
	}
	
	public String getDate2() {
		return dateT;
	}
	
	public void setT1(String t1) {
		this.t1 = t1;
	}
	
	public void setT2(String t2) {
		this.t2 = t2;
	}
	
	public void setDateFrom(String date) {
		this.dateF = date;
	}
	
	public void setDateTo(String date) {
		this.dateT = date;
	}
}
