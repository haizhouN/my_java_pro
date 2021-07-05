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
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import db.Db;
import entity.StudentEntity;
import entity.Student_M_Entity;

public class LogShowStudent extends JFrame{
	private JToolBar tool;
	private JTextField search_text;
	private JButton search;
	private JTable table;
	private MytableModel1 tablemodel;
	public LogShowStudent() {
		this.setSize(1000,300);
		this.setTitle("信息查询");
		this.setLocationRelativeTo(getOwner());
		
		
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(900,400));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		
		search_text=new JTextField();
		search=new JButton("查询");
		tool=new JToolBar();
		tool.add(new JLabel("按照课程名查询"));
		tool.add(search_text);
		tool.add(search);
		tool.setRollover(true);
		getContentPane().add(tool,BorderLayout.NORTH);
		
		
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Select s=new Select("course.cname",search_text.getText());
				s.setVisible(true);
				
			}
			
		});
		
		
		
		
		
		
		
		
	}
	
	private MytableModel1 getModel() {
		// TODO 自动生成的方法存根
		MytableModel1 tablemodel=new MytableModel1();
		Db dbcon;
		try {//链接数据库，执行查询语句
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select student.sno 学号,student.sname 姓名,student.sclass 班级,student.sdept 院系,course.cname 课程名,sc.dailygrade 平时分,sc.finalgrade 期末分,sc.totalgrade 综合成绩,sc.relearn 是否重修,sc.relearngrade 重修成绩,sc.academicyear 学年 from student,course,sc where student.sno=sc.sno and sc.cno=course.cno");
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
				person.setSno(rs.getString("学号"));
				person.setSname(rs.getString("姓名"));
				person.setClas(rs.getString("班级"));
				person.setSdept(rs.getString("院系"));
				person.setCname(rs.getString("课程名"));
				person.setDailygrade(rs.getString("平时分"));
				person.setFinalgrade(rs.getString("期末分"));
				person.setTotalgrade(rs.getString("综合成绩"));
				person.setRelearn(rs.getString("是否重修"));
				person.setRelearngrade(rs.getString("重修成绩"));
				person.setAcademicyear(rs.getString("学年"));
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
