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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import db.Db;
import entity.CourseEntity;
import entity.StudentEntity;

public class Course extends JFrame{
	private JTable table;
	private MytableModel tablemodel;
	private JButton b1,b2,b3;
	private JToolBar tool;
	private JTextField search_text;
	private JButton search;
	
	public Course() {
		this.setSize(600,300);
		this.setTitle("�γ���Ϣ����");
		this.setLocationRelativeTo(getOwner());
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
		getContentPane().add(tool,BorderLayout.NORTH);
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				AddCourse adst=new AddCourse();
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
					String sql="update course set cname=?,cpno=?,ccredit=?,"
							+ "cperiod=? where cno=?";
					PreparedStatement presta=dbcon.PreparedStatement(sql);//��ȡJTable���޸ĵ�����
					count=tablemodel.getEditedIndex().size();//��ȡJTable�����޸ĵ��е����ݣ��������ݿ�
					if(count>0) {
						for(i=0;i<count;i++) {
							index=tablemodel.getEditedIndex().get(i);
							presta.setString(1, (String)table.getValueAt(index, 1));
							presta.setString(2, (String)table.getValueAt(index, 2));
							presta.setString(3, (String)table.getValueAt(index, 3));
							presta.setString(4, (String)table.getValueAt(index, 4));
							//presta.setString(4, (String)table.getValueAt(index, 4));
							presta.setString(5, (String)table.getValueAt(index, 0));
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
					java.sql.PreparedStatement presta=dbcon.PreparedStatement("delete from course where cno=?");
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
		
		
		
	}
	
	private MytableModel getModel() {
		MytableModel tablemodel=new MytableModel();
		Db dbcon;
		try {//�������ݿ⣬ִ�в�ѯ���
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select cno �γ̺�,cname �γ���,cpno ���޺�,ccredit ѧ��,cperiod ѧʱ  from course");
			//��ȡ��ѯ����������������ģ����
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum =rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tablemodel.addColumn(rsmd.getColumnName(i));
			//��ȡ��ѯ����е�Ԫ�飬�����ģ����
			ArrayList<CourseEntity> v=new ArrayList<CourseEntity>();
			while(rs.next()) {
				CourseEntity person=new CourseEntity();
				person.setCno(rs.getString("�γ̺�"));
				person.setCname(rs.getString("�γ���"));
				person.setCpno(rs.getString("���޺�"));
				person.setCcredit(rs.getString("ѧ��"));
				person.setCperiod(rs.getString("ѧʱ"));
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
