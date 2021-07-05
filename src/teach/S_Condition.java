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
import entity.TCEntity;

public class S_Condition extends JFrame{
	private JTable table;
	private MytableModel1 tablemodel;
	
	public S_Condition() {
		this.setSize(600,300);
		this.setTitle("��ѡ��ѧ�����");
		this.setLocationRelativeTo(getOwner());
		
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		
		
	}
	private MytableModel1 getModel() {
		// TODO �Զ����ɵķ������
		MytableModel1 tablemodel=new MytableModel1();
		Db dbcon;
		try {//�������ݿ⣬ִ�в�ѯ���
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("execute select_course");
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
				person.setAcademicyear(rs.getInt("ѧ��"));
				v.add(person);
			}
			rs.close();
			for(i=0;i<v.size();i++) {
				tablemodel.addRow(new Object[] {
						v.get(i).getSno(),v.get(i).getSname(),
						v.get(i).getCno(),v.get(i).getCname(),v.get(i).getAcademicyear()
						
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
