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
import entity.Student_M_Entity;

public class Calculate_grade extends JFrame{
	private JToolBar tool;
	private JTextField search_text,course_text;
	private JButton search1,course,nopass;
	private JTable table;
	private MytableModel1 tablemodel;
	public Calculate_grade() {
		this.setSize(600, 300);
		this.setTitle("成绩统计");
		this.setLocationRelativeTo(getOwner());
		
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(900,400));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		
		search_text=new JTextField();
//		course_text=new JTextField();
		search1=new JButton("降序查询");
		course=new JButton("课程平均成绩");
		nopass=new JButton("不及格学生信息");
		tool=new JToolBar();
		tool.add(new JLabel("班级名称"));
		tool.add(search_text);
		
		tool.add(search1);
		tool.add(nopass);
//		tool.add(new JLabel("课程名"));
//		tool.add(course_text);
//		tool.add(course);
		tool.setRollover(true);
		getContentPane().add(tool,BorderLayout.NORTH);
		
		
		search1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Sum_Desc s=new Sum_Desc(search_text.getText());
				s.setVisible(true);
				
			}
			
		});
		nopass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				NoPass n=new NoPass();
				n.setVisible(true);
				
			}
			
		});
		
		
		
		
		
	}
	private MytableModel1 getModel() {
		// TODO 自动生成的方法存根
		MytableModel1 tablemodel=new MytableModel1();
		Db dbcon;
		try {//链接数据库，执行查询语句
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select student.sno,student.sname,student.sclass,student.sdept,course.cname,sc.dailygrade,sc.finalgrade,sc.totalgrade,sc.relearn,sc.relearngrade,sc.academicyear from student,course,sc where student.sno=sc.sno and sc.cno=course.cno");
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
