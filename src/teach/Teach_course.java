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
		this.setTitle("�ڿι���");
		this.setLocationRelativeTo(getOwner());
		
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		

		b2=new JButton("����");
		b2.setFocusable(false);


		tool=new JToolBar();
		tool.add(b2);
		tool.setRollover(true);//��ӹ�����
		
		tool.add(new JLabel("��ѧ�Ų�"));
		search_text1=new JTextField();
		tool.add(search_text1);
		search1=new JButton("��ѧ��");
		tool.add(search1);
		
		tool.add(new JLabel("��������"));
		search_text2=new JTextField();
		tool.add(search_text2);
		search2=new JButton("������");
		tool.add(search2);
		
		tool.add(new JLabel("���γ�����"));
		search_text3=new JTextField();
		tool.add(search_text3);
		search3=new JButton("��γ�");
		tool.add(search3);
		getContentPane().add(tool,BorderLayout.NORTH);
		
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				int i,index=0,count;
				Db dbcon=new Db();
				if(table.getCellEditor()!=null) {
					table.getCellEditor().stopCellEditing();
					
				}
				try {
					String sql="update sc set dailygrade=?,finalgrade=?,"
							+ "totalgrade=?,relearn=?,relearngrade=? where sno=? and cno=?";
					PreparedStatement presta=dbcon.PreparedStatement(sql);//��ȡJTable���޸ĵ�����
					count=tablemodel.getEditedIndex().size();//��ȡJTable�����޸ĵ��е����ݣ��������ݿ�
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
				// TODO �Զ����ɵķ������
				Select s=new Select("student.sno",search_text1.getText());
				s.setVisible(true);
			}
			
		});
		search2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				Select s=new Select("student.sname",search_text2.getText());
				s.setVisible(true);
			}
			
		});
		search3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				Select s=new Select("course.cname",search_text3.getText());
				s.setVisible(true);
			}
			
		});
		
		
		
	}
	private MytableModel getModel() {
		// TODO �Զ����ɵķ������
		MytableModel tablemodel=new MytableModel();
		Db dbcon;
		try {//�������ݿ⣬ִ�в�ѯ���
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select sc.sno ѧ��,student.sname ����,sc.cno �γ̺�,course.cname �γ���,sc.dailygrade ƽʱ�ɼ�,sc.finalgrade ��ĩ�ɼ�,sc.totalgrade �ۺϳɼ�,sc.relearn �Ƿ�����,sc.relearngrade ���޳ɼ�,sc.academicyear ѧ�� from sc,student,course,tc,teacher where sc.sno=student.sno and sc.cno=course.cno and course.cno=tc.cno and teacher.tno=tc.tno");
			//��ȡ��ѯ����������������ģ����
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum =rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tablemodel.addColumn(rsmd.getColumnName(i));
			//��ȡ��ѯ����е�Ԫ�飬�����ģ����
			ArrayList<TCEntity> v=new ArrayList<TCEntity>();
			while(rs.next()) {
				TCEntity person=new TCEntity();
				person.setSno(rs.getString("ѧ��"));
				person.setSname(rs.getString("����"));
				person.setCno(rs.getString("�γ̺�"));
				person.setCname(rs.getString("�γ���"));
				person.setDailygrade(rs.getString("ƽʱ�ɼ�"));
				person.setFinalgrade(rs.getString("��ĩ�ɼ�"));
				person.setTotalgrade(rs.getString("�ۺϳɼ�"));
				
				person.setRelearn(rs.getString("�Ƿ�����"));
				person.setRelearngrade(rs.getString("���޳ɼ�"));
				person.setAcademicyear(rs.getInt("ѧ��"));
				
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
