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
		this.setTitle("��ʦ��Ϣ����");
		this.setLocationRelativeTo(getOwner());
		
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		

		b1=new JButton("���");
		b1.setFocusable(false);
//		b1.setHorizontalTextPosition(SwingConstants.CENTER);
//		b1.setVerticalTextPosition(SwingConstants.BOTTOM);
		b2=new JButton("����");
		b2.setFocusable(false);
//		b2.setHorizontalTextPosition(SwingConstants.CENTER);
//		b2.setVerticalTextPosition(SwingConstants.BOTTOM);
		b3=new JButton("ɾ��");
		b3.setFocusable(false);
//		b3.setHorizontalTextPosition(SwingConstants.CENTER);
//		b3.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		//��������������Ӱ�ť
		tool=new JToolBar();
		tool.add(b1);
		tool.add(b2);
		tool.add(b3);
		tool.setRollover(true);//��ӹ�����
		
		tool.add(new JLabel("����������"));
		search_text=new JTextField();
		tool.add(search_text);
		search=new JButton("����");
		tool.add(search);
		//��ӹ�����
		getContentPane().add(tool,BorderLayout.NORTH);
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				AddTeacher adst=new AddTeacher();
				adst.setVisible(true);
				dispose();
			}
			
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				int i,index=0,count;
				Db dbcon=new Db();
				if(table.getCellEditor()!=null) {
					table.getCellEditor().stopCellEditing();
					
				}
				try {
					String sql="update teacher set tname=?,tsex=?,tbirthday=?,"
							+ "tphone=?,tposition=? where tno=?";
					PreparedStatement presta=dbcon.PreparedStatement(sql);//��ȡJTable���޸ĵ�����
					count=tablemodel.getEditedIndex().size();//��ȡJTable�����޸ĵ��е����ݣ��������ݿ�
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
				// TODO �Զ����ɵķ������
				Db dbcon=new Db();
			try {
				if(table.getSelectedRows().length>0) {
					//���JTable��ѡ�е��е�����
					int[] selRowIndexs=table.getSelectedRows();
					java.sql.PreparedStatement presta=dbcon.PreparedStatement("delete from teacher where tno=?");
					for(int i=0;i<selRowIndexs.length;i++) {
						presta.setString(1, table.getValueAt(selRowIndexs[i], 0).toString());
						presta.addBatch();
					}
					//ɾ�����ݿ���Ӧ�ļ�¼
					presta.executeBatch();
					//���¼������ݵ������
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
				// TODO �Զ����ɵķ������
				Search_teacher t=new Search_teacher(search_text.getText());
				t.setVisible(true);
			}
			
		});
		
		
		
		
		
		
		
		
		
	}
	
	private MytableModel getModel() {
		// TODO �Զ����ɵķ������
		MytableModel tablemodel=new MytableModel();
		Db dbcon;
		try {//�������ݿ⣬ִ�в�ѯ���
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select tno ��ʦ���,tname ����,tsex �Ա�,tbirthday ��������,tphone �绰,tposition ְλ from teacher");
			//��ȡ��ѯ����������������ģ����
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum =rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tablemodel.addColumn(rsmd.getColumnName(i));
			//��ȡ��ѯ����е�Ԫ�飬�����ģ����
			ArrayList<TeacherEntity> v=new ArrayList<TeacherEntity>();
			while(rs.next()) {
				TeacherEntity person=new TeacherEntity();
				person.setTno(rs.getString("��ʦ���"));
				person.setTname(rs.getString("����"));
				person.setTsex(rs.getString("�Ա�"));
				person.setTbirthday(rs.getDate("��������"));
				person.setTphon(rs.getString("�绰"));
				person.setTposition(rs.getString("ְλ"));
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
