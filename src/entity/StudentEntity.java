package entity;

import java.util.Date;

public class StudentEntity {
	private String sno,sname,ssex;
	private int age;
	private String sclassr;
	private String sdept;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSclassr() {
		return sclassr;
	}
	public void setSclassr(String sclassr) {
		this.sclassr = sclassr;
	}
	public String getSdept() {
		return sdept;
	}
	public void setSdept(String sdept) {
		this.sdept = sdept;
	}
}
