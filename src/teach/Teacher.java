package teach;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import java.sql.*;
import db.Db;
import entity.StudentEntity;
import entity.TeacherEntity;

import java.util.ArrayList;
public class Teacher extends JFrame{
	private JTable table;
	private MytableModel tablemodel;
	private JButton b1,b2,b3;
	private JToolBar tool;
	private JTextField search_text;
	private JButton search;
	public Teacher() {
		this.setSize(600,300);
		this.setTitle("教师信息管理");
		this.setLocationRelativeTo(getOwner());
		
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		

		b1=new JButton("添加");
		b1.setFocusable(false);
//		b1.setHorizontalTextPosition(SwingConstants.CENTER);
//		b1.setVerticalTextPosition(SwingConstants.BOTTOM);
		b2=new JButton("保存");
		b2.setFocusable(false);
//		b2.setHorizontalTextPosition(SwingConstants.CENTER);
//		b2.setVerticalTextPosition(SwingConstants.BOTTOM);
		b3=new JButton("删除");
		b3.setFocusable(false);
//		b3.setHorizontalTextPosition(SwingConstants.CENTER);
//		b3.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		//创建工具栏，添加按钮
		tool=new JToolBar();
		tool.add(b1);
		tool.add(b2);
		tool.add(b3);
		tool.setRollover(true);//添加滚动条
		
		tool.add(new JLabel("按姓名查找"));
		search_text=new JTextField();
		tool.add(search_text);
		search=new JButton("查找");
		tool.add(search);
		//添加工具栏
		getContentPane().add(tool,BorderLayout.NORTH);
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				AddTeacher adst=new AddTeacher();
				adst.setVisible(true);
				dispose();
			}
			
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int i,index=0,count;
				Db dbcon=new Db();
				if(table.getCellEditor()!=null) {
					table.getCellEditor().stopCellEditing();
					
				}
				try {
					String sql="update teacher set tname=?,tsex=?,tbirthday=?,"
							+ "tphone=?,tposition=? where tno=?";
					PreparedStatement presta=dbcon.PreparedStatement(sql);//获取JTable中修改的行数
					count=tablemodel.getEditedIndex().size();//获取JTable中所修改的行的数据，更新数据库
					if(count>0) {
						for(i=0;i<count;i++) {
							index=tablemodel.getEditedIndex().get(i);
							presta.setString(1, (String)table.getValueAt(index, 1));
							presta.setString(2, (String)table.getValueAt(index, 2));
							String temp=String.valueOf(table.getValueAt(index, 3));
							presta.setString(3, temp.equals("null")?null:temp);
							presta.setString(4, (String)table.getValueAt(index, 4));
							presta.setString(5, (String)table.getValueAt(index, 5));
							presta.setString(6, (String)table.getValueAt(index, 0));
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
		b3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Db dbcon=new Db();
			try {
				if(table.getSelectedRows().length>0) {
					//获得JTable中选中的行的序列
					int[] selRowIndexs=table.getSelectedRows();
					java.sql.PreparedStatement presta=dbcon.PreparedStatement("delete from teacher where tno=?");
					for(int i=0;i<selRowIndexs.length;i++) {
						presta.setString(1, table.getValueAt(selRowIndexs[i], 0).toString());
						presta.addBatch();
					}
					//删除数据库相应的记录
					presta.executeBatch();
					//重新加载数据到表格中
					tablemodel=getModel();
					table.setModel(tablemodel);
					
				}
			}
			catch(SQLException sqle) {
				System.out.println(sqle.toString());
			}
			}
			
		});
		
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Search_teacher t=new Search_teacher(search_text.getText());
				t.setVisible(true);
			}
			
		});
		
		
		
		
		
		
		
		
		
	}
	
	private MytableModel getModel() {
		// TODO 自动生成的方法存根
		MytableModel tablemodel=new MytableModel();
		Db dbcon;
		try {//链接数据库，执行查询语句
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select tno 教师编号,tname 姓名,tsex 性别,tbirthday 出生日期,tphone 电话,tposition 职位 from teacher");
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
				person.setTno(rs.getString("教师编号"));
				person.setTname(rs.getString("姓名"));
				person.setTsex(rs.getString("性别"));
				person.setTbirthday(rs.getDate("出生日期"));
				person.setTphon(rs.getString("电话"));
				person.setTposition(rs.getString("职位"));
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
