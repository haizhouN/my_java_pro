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
import entity.Student_M_Entity;

public class Sum_Desc extends JFrame{
	private JTable table;
	private MytableModel1 tablemodel;
	public Sum_Desc(String search) {
		this.setSize(600,300);
		this.setTitle("��Ϣ��ѯ");
		this.setLocationRelativeTo(getOwner());
		
		
		tablemodel=getModel(search);
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(900,400));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		
		
		
		
		
		
		
	}
	
	private MytableModel1 getModel(String search) {
		// TODO �Զ����ɵķ������
		MytableModel1 tablemodel=new MytableModel1();
		Db dbcon;
		try {//�������ݿ⣬ִ�в�ѯ���
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select student.sno ѧ��,student.sname ����,student.sclass �༶,student.sdept Ժϵ,sum(cast(totalgrade as int)) as �ܷ� from student left outer join sc on student.sno=sc.sno left outer join course on sc.cno=course.cno group by student.sno,student.sname,student.sclass,student.sdept having student.sclass="+"'"+search+"'"+" order by sum(cast(totalgrade as int)) desc");
			//��ȡ��ѯ����������������ģ����
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum =rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tablemodel.addColumn(rsmd.getColumnName(i));
			//��ȡ��ѯ����е�Ԫ�飬�����ģ����
			ArrayList<Student_M_Entity> v=new ArrayList<Student_M_Entity>();
			while(rs.next()) {
				Student_M_Entity person=new Student_M_Entity();
				person.setSno(rs.getString("ѧ��"));
				person.setSname(rs.getString("����"));
				person.setClas(rs.getString("�༶"));
				person.setSdept(rs.getString("Ժϵ"));
				person.setSum(rs.getInt("�ܷ�"));
				v.add(person);
				
			}
			rs.close();
			for(i=0;i<v.size();i++) {
				tablemodel.addRow(new Object[] {
						v.get(i).getSno(),v.get(i).getSname(),v.get(i).getClas(),v.get(i).getSdept(),
						v.get(i).getSum()
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
