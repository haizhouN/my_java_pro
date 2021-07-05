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
		this.setTitle("��Ϣ��ѯ");
		this.setLocationRelativeTo(getOwner());
		
		
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(900,400));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		
		search_text=new JTextField();
		search=new JButton("��ѯ");
		tool=new JToolBar();
		tool.add(new JLabel("���տγ�����ѯ"));
		tool.add(search_text);
		tool.add(search);
		tool.setRollover(true);
		getContentPane().add(tool,BorderLayout.NORTH);
		
		
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				Select s=new Select("course.cname",search_text.getText());
				s.setVisible(true);
				
			}
			
		});
		
		
		
		
		
		
		
		
	}
	
	private MytableModel1 getModel() {
		// TODO �Զ����ɵķ������
		MytableModel1 tablemodel=new MytableModel1();
		Db dbcon;
		try {//�������ݿ⣬ִ�в�ѯ���
			dbcon=new Db();
			ResultSet rs=dbcon.executeQuery("select student.sno ѧ��,student.sname ����,student.sclass �༶,student.sdept Ժϵ,course.cname �γ���,sc.dailygrade ƽʱ��,sc.finalgrade ��ĩ��,sc.totalgrade �ۺϳɼ�,sc.relearn �Ƿ�����,sc.relearngrade ���޳ɼ�,sc.academicyear ѧ�� from student,course,sc where student.sno=sc.sno and sc.cno=course.cno");
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
				person.setCname(rs.getString("�γ���"));
				person.setDailygrade(rs.getString("ƽʱ��"));
				person.setFinalgrade(rs.getString("��ĩ��"));
				person.setTotalgrade(rs.getString("�ۺϳɼ�"));
				person.setRelearn(rs.getString("�Ƿ�����"));
				person.setRelearngrade(rs.getString("���޳ɼ�"));
				person.setAcademicyear(rs.getString("ѧ��"));
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
