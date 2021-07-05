package entity;

public class TCEntity {
	private String sno,cno,sname,cname;
	private String dailygrade,finalgrade,relearngrade,totalgrade;
	public String getRelearngrade() {
		return relearngrade;
	}
	public void setRelearngrade(String relearngrade) {
		this.relearngrade = relearngrade;
	}
	private String relearn;//职称
	private int academicyear;//部门代码
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getDailygrade() {
		return dailygrade;
	}
	public void setDailygrade(String dailygrade) {
		this.dailygrade = dailygrade;
	}
	public String getFinalgrade() {
		return finalgrade;
	}
	public void setFinalgrade(String finalgrade) {
		this.finalgrade = finalgrade;
	}
	public String getRelearn() {
		return relearn;
	}
	public void setRelearn(String relearn) {
		this.relearn = relearn;
	}
	public int getAcademicyear() {
		return academicyear;
	}
	public void setAcademicyear(int academicyear) {
		this.academicyear = academicyear;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getTotalgrade() {
		return totalgrade;
	}
	public void setTotalgrade(String totalgrade) {
		this.totalgrade = totalgrade;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	

}
