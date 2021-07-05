package teach;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import db.Db;
import entity.According_courseEntity;

public class Select extends JFrame{

	private JTable table;
	private MytableModel tablemodel;
	
	public Select(String t,String serch) {
		this.setSize(1000,300);
		this.setTitle("搜索信息");
		this.setLocationRelativeTo(getOwner());
		
		tablemodel=getModel(t,serch);
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
	}
	
	private MytableModel getModel(String t,String serch) {
		MytableModel tablemodel=new MytableModel();
		Db dbcon;
		String m=new String();
		if(t.equals("student.sclass"))
			m=" order by totalgrade desc";
		else
			m="";
		try {//链接数据库，执行查询语句
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select course.*,student.*,sc.academicyear ,sc.dailygrade ,sc.finalgrade ,sc.relearn ,sc.relearngrade ,sc.totalgrade"
					+ " from student,course,sc where student.sno=sc.sno and course.cno=sc.cno and "+t+"="+"'"+serch+"'"+m);
			//获取查询结果的列名，填充表格模型列
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum =rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tablemodel.addColumn(rsmd.getColumnName(i));
			//获取查询结果中的元组，填充表格模型行
			ArrayList<According_courseEntity> v=new ArrayList<According_courseEntity>();
			while(rs.next()) {
				According_courseEntity person=new According_courseEntity();
				person.setCno(rs.getString("cno"));
				person.setCname(rs.getString("cname"));
				person.setCpno(rs.getString("cpno"));
				person.setCcredit(rs.getString("ccredit"));
				person.setCperiod(rs.getString("cperiod"));
				
				person.setSno(rs.getString("sno"));
				person.setSname(rs.getString("sname"));
				person.setSsex(rs.getString("ssex"));
				person.setAge(rs.getInt("sage"));
				person.setSclassr(rs.getString("sclass"));
				person.setSdept(rs.getString("sdept"));
				
				person.setAcademicyear(rs.getString("academicyear"));
				person.setDailygrade(rs.getString("dailygrade"));
				person.setFinalgrade(rs.getString("finalgrade"));
				person.setRelearn(rs.getString("relearn"));
				person.setRelearngrade(rs.getString("relearngrade"));
				person.setTotalgrade(rs.getString("totalgrade"));
				v.add(person);
				
			}
			rs.close();
			for(i=0;i<v.size();i++) {
				
				tablemodel.addRow(new Object[] {
						v.get(i).getCno(),v.get(i).getCname(),v.get(i).getCpno(),v.get(i).getCcredit(),
						v.get(i).getCperiod(),v.get(i).getSno(),v.get(i).getSname(),v.get(i).getSsex(),
						v.get(i).getAge(),v.get(i).getSclassr(),v.get(i).getSdept(),
						v.get(i).getAcademicyear(),v.get(i).getDailygrade(),
						v.get(i).getFinalgrade(),v.get(i).getRelearn(),v.get(i).getRelearngrade(),
						v.get(i).getTotalgrade()
				});
				
			}
			dbcon.closeConn();
		}
			catch(SQLException sqle) {
				System.out.println(sqle.toString());
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			return tablemodel;
	}
	

}
