package teach;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
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
import entity.TCEntity;

public class Teach_course extends JFrame{
	private JTable table;
	private MytableModel tablemodel;
	private JButton b1,b2,b3,search1,search2,search3;
	private JToolBar tool;
	private JTextField search_text1,search_text2,search_text3;
	
	public Teach_course() {
		this.setSize(900,500);
		this.setTitle("授课管理");
		this.setLocationRelativeTo(getOwner());
		
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		

		b2=new JButton("保存");
		b2.setFocusable(false);


		tool=new JToolBar();
		tool.add(b2);
		tool.setRollover(true);//添加滚动条
		
		tool.add(new JLabel("按学号查"));
		search_text1=new JTextField();
		tool.add(search_text1);
		search1=new JButton("查学号");
		tool.add(search1);
		
		tool.add(new JLabel("按姓名查"));
		search_text2=new JTextField();
		tool.add(search_text2);
		search2=new JButton("查姓名");
		tool.add(search2);
		
		tool.add(new JLabel("按课程名查"));
		search_text3=new JTextField();
		tool.add(search_text3);
		search3=new JButton("查课程");
		tool.add(search3);
		getContentPane().add(tool,BorderLayout.NORTH);
		
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int i,index=0,count;
				Db dbcon=new Db();
				if(table.getCellEditor()!=null) {
					table.getCellEditor().stopCellEditing();
					
				}
				try {
					String sql="update sc set dailygrade=?,finalgrade=?,"
							+ "totalgrade=?,relearn=?,relearngrade=? where sno=? and cno=?";
					PreparedStatement presta=dbcon.PreparedStatement(sql);//获取JTable中修改的行数
					count=tablemodel.getEditedIndex().size();//获取JTable中所修改的行的数据，更新数据库
					if(count>0) {
						for(i=0;i<count;i++) {
							index=tablemodel.getEditedIndex().get(i);
							//presta.setString(1, (String)table.getValueAt(index, 2));
							presta.setString(1, (String)table.getValueAt(index, 4));
							presta.setString(2, (String)table.getValueAt(index, 5));
							presta.setString(3, (String)table.getValueAt(index, 6));
							presta.setString(4, (String)table.getValueAt(index, 7));
							presta.setString(5, (String)table.getValueAt(index, 8));
							//presta.setString(7, (String) table.getValueAt(index, 9));
							presta.setString(6, (String)table.getValueAt(index, 0));
							presta.setString(7, (String)table.getValueAt(index, 2));
							
							
							presta.addBatch();
						}
					}
					presta.executeBatch();
					tablemodel.clearEditedIndex();
				}
				catch(SQLException sqle) {
					System.out.println(sqle.toString());
				}
			}
			
		});
		search1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Select s=new Select("student.sno",search_text1.getText());
				s.setVisible(true);
			}
			
		});
		search2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Select s=new Select("student.sname",search_text2.getText());
				s.setVisible(true);
			}
			
		});
		search3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Select s=new Select("course.cname",search_text3.getText());
				s.setVisible(true);
			}
			
		});
		
		
		
	}
	private MytableModel getModel() {
		// TODO 自动生成的方法存根
		MytableModel tablemodel=new MytableModel();
		Db dbcon;
		try {//链接数据库，执行查询语句
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select sc.sno 学号,student.sname 姓名,sc.cno 课程号,course.cname 课程名,sc.dailygrade 平时成绩,sc.finalgrade 期末成绩,sc.totalgrade 综合成绩,sc.relearn 是否重修,sc.relearngrade 重修成绩,sc.academicyear 学年 from sc,student,course,tc,teacher where sc.sno=student.sno and sc.cno=course.cno and course.cno=tc.cno and teacher.tno=tc.tno");
			//获取查询结果的列名，填充表格模型列
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum =rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tablemodel.addColumn(rsmd.getColumnName(i));
			//获取查询结果中的元组，填充表格模型行
			ArrayList<TCEntity> v=new ArrayList<TCEntity>();
			while(rs.next()) {
				TCEntity person=new TCEntity();
				person.setSno(rs.getString("学号"));
				person.setSname(rs.getString("姓名"));
				person.setCno(rs.getString("课程号"));
				person.setCname(rs.getString("课程名"));
				person.setDailygrade(rs.getString("平时成绩"));
				person.setFinalgrade(rs.getString("期末成绩"));
				person.setTotalgrade(rs.getString("综合成绩"));
				
				person.setRelearn(rs.getString("是否重修"));
				person.setRelearngrade(rs.getString("重修成绩"));
				person.setAcademicyear(rs.getInt("学年"));
				
				v.add(person);
			}
			rs.close();
			for(i=0;i<v.size();i++) {
				tablemodel.addRow(new Object[] {
						v.get(i).getSno(),v.get(i).getSname(),v.get(i).getCno(),v.get(i).getCname(),v.get(i).getDailygrade(),v.get(i).getFinalgrade(),
						v.get(i).getTotalgrade(),
						v.get(i).getRelearn(),v.get(i).getRelearngrade(),v.get(i).getAcademicyear()
						
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
