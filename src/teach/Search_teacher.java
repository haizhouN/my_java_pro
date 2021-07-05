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
import entity.TeacherEntity;

public class Search_teacher extends JFrame{
	private JTable table;
	private MytableModel tablemodel;
	
	public Search_teacher(String serch) {
		this.setSize(1000,300);
		this.setTitle("信息");
		this.setLocationRelativeTo(getOwner());
		
		tablemodel=getModel(serch);
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
	}
	private MytableModel getModel(String serch) {
		MytableModel tablemodel=new MytableModel();
		Db dbcon;
		try {//链接数据库，执行查询语句
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select * from teacher where tname="+"'"+serch+"'");
			//获取查询结果的列名，填充表格模型列
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum =rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tablemodel.addColumn(rsmd.getColumnName(i));
			//获取查询结果中的元组，填充表格模型行
			ArrayList<TeacherEntity> v=new ArrayList<TeacherEntity>();
			while(rs.next()) {
				TeacherEntity person=new TeacherEntity();
				person.setTno(rs.getString("tno"));
				person.setTname(rs.getString("tname"));
				person.setTsex(rs.getString("tsex"));
				person.setTbirthday(rs.getDate("tbirthday"));
				person.setTphon(rs.getString("tphone"));
				person.setTposition(rs.getString("tposition"));
				v.add(person);
				
			}
			rs.close();
			for(i=0;i<v.size();i++) {
				tablemodel.addRow(new Object[] {
						v.get(i).getTno(),v.get(i).getTname(),v.get(i).getTsex(),v.get(i).getTbirthday(),
						v.get(i).getTphon(),v.get(i).getTposition()
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
