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
import entity.Student_M_Entity;

public class NoPass extends JFrame{
	private JTable table;
	private MytableModel1 tablemodel;
	
	
	public NoPass() {
		this.setSize(600, 300);
		this.setTitle("不及格学生信息");
		this.setLocationRelativeTo(getOwner());
		
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(900,400));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
	}
	private MytableModel1 getModel() {
		// TODO 自动生成的方法存根
		MytableModel1 tablemodel=new MytableModel1();
		Db dbcon;
		try {//链接数据库，执行查询语句
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("execute no_pass");
			//获取查询结果的列名，填充表格模型列
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum =rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tablemodel.addColumn(rsmd.getColumnName(i));
			//获取查询结果中的元组，填充表格模型行
			ArrayList<Student_M_Entity> v=new ArrayList<Student_M_Entity>();
			while(rs.next()) {
				Student_M_Entity person=new Student_M_Entity();
				person.setSno(rs.getString("sno"));
				person.setSname(rs.getString("sname"));
				person.setClas(rs.getString("sclass"));
				person.setSdept(rs.getString("sdept"));
				person.setCname(rs.getString("cname"));
				person.setDailygrade(rs.getString("dailygrade"));
				person.setFinalgrade(rs.getString("finalgrade"));
				person.setTotalgrade(rs.getString("totalgrade"));
				person.setRelearn(rs.getString("relearn"));
				person.setRelearngrade(rs.getString("relearngrade"));
				person.setAcademicyear(rs.getString("academicyear"));
				v.add(person);
				
			}
			rs.close();
			for(i=0;i<v.size();i++) {
				tablemodel.addRow(new Object[] {
						v.get(i).getSno(),v.get(i).getSname(),v.get(i).getClas(),v.get(i).getSdept(),
						v.get(i).getCname(),v.get(i).getDailygrade(),v.get(i).getFinalgrade(),
						v.get(i).getTotalgrade(),v.get(i).getRelearn(),v.get(i).getRelearngrade(),
						v.get(i).getAcademicyear()
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
