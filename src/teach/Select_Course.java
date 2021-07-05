package teach;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import db.Db;
import entity.CourseEntity;

public class Select_Course extends JFrame{
	private JTable table;
	private MytableModel1 tablemodel;
	private JButton b1,b2,b3;
	private JToolBar tool;
	public Select_Course() {
		this.setSize(600,300);
		this.setTitle("选课系统");
		this.setLocationRelativeTo(getOwner());

		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		
		b1=new JButton("选课程");
		b1.setFocusable(false);
		b2=new JButton("选课情况");
		tool=new JToolBar();
		tool.add(b1);
		tool.add(b2);
		b2.setFocusable(false);
		tool.setRollover(true);//添加滚动条
		getContentPane().add(tool,BorderLayout.NORTH);
		
		
		
		b1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				AddSC sc=new AddSC();
				sc.setVisible(true);
			}
		});
		
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				S_Condition s=new S_Condition();
				s.setVisible(true);
			}
			
		});
		
			
		
	}
	private MytableModel1 getModel() {
		MytableModel1 tablemodel=new MytableModel1();
		Db dbcon;
		try {//链接数据库，执行查询语句
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select cno 课程号,cname 课程名,cpno 先修号,ccredit 学分,cperiod 学时 from course");
			//获取查询结果的列名，填充表格模型列
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum =rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tablemodel.addColumn(rsmd.getColumnName(i));
			//获取查询结果中的元组，填充表格模型行
			ArrayList<CourseEntity> v=new ArrayList<CourseEntity>();
			while(rs.next()) {
				CourseEntity person=new CourseEntity();
				person.setCno(rs.getString("课程号"));
				person.setCname(rs.getString("课程名"));
				person.setCpno(rs.getString("先修号"));
				person.setCcredit(rs.getString("学分"));
				person.setCperiod(rs.getString("学时"));
				v.add(person);
				
			}
			rs.close();
			for(i=0;i<v.size();i++) {
				
				tablemodel.addRow(new Object[] {
						v.get(i).getCno(),v.get(i).getCname(),v.get(i).getCpno(),v.get(i).getCcredit(),
						v.get(i).getCperiod()
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
