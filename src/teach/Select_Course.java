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
		this.setTitle("ѡ��ϵͳ");
		this.setLocationRelativeTo(getOwner());

		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		
		b1=new JButton("ѡ�γ�");
		b1.setFocusable(false);
		b2=new JButton("ѡ�����");
		tool=new JToolBar();
		tool.add(b1);
		tool.add(b2);
		b2.setFocusable(false);
		tool.setRollover(true);//��ӹ�����
		getContentPane().add(tool,BorderLayout.NORTH);
		
		
		
		b1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				AddSC sc=new AddSC();
				sc.setVisible(true);
			}
		});
		
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				S_Condition s=new S_Condition();
				s.setVisible(true);
			}
			
		});
		
			
		
	}
	private MytableModel1 getModel() {
		MytableModel1 tablemodel=new MytableModel1();
		Db dbcon;
		try {//�������ݿ⣬ִ�в�ѯ���
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select cno �γ̺�,cname �γ���,cpno ���޺�,ccredit ѧ��,cperiod ѧʱ from course");
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
