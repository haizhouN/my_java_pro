package entity;

import java.util.Date;

public class TeacherEntity {
	private String tno,tname,tsex;
	private Date tbirthday;
	private String tphon;//ְ��
	private String tposition;//���Ŵ���
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTsex() {
		return tsex;
	}
	public void setTsex(String tsex) {
		this.tsex = tsex;
	}
	public Date getTbirthday() {
		return tbirthday;
	}
	public void setTbirthday(Date tbirthday) {
		this.tbirthday = tbirthday;
	}
	public String getTphon() {
		return tphon;
	}
	public void setTphon(String tphon) {
		this.tphon = tphon;
	}
	public String getTposition() {
		return tposition;
	}
	public void setTposition(String tposition) {
		this.tposition = tposition;
	}
	
	
	
}
