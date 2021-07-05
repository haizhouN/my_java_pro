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

public class ForStudent extends JFrame{
	private JToolBar tool;
	private JTextField search_text;
	private JButton search;
	private MytableModel1 tablemodel;
	private JTable table;
	public ForStudent() {
		this.setSize(600,300);
		this.setTitle("ѧ����Ϣ");
		this.setLocationRelativeTo(getOwner());
		
		tablemodel=getModel();
		table=new JTable(tablemodel);
		table.setPreferredScrollableViewportSize(new Dimension(500,250));
		JScrollPane scroll=new JScrollPane(table);
		getContentPane().add(scroll,BorderLayout.CENTER);
		
		tool=new JToolBar();
		tool.add(new JLabel("���γ�������"));
		search_text=new JTextField();
		tool.add(search_text);
		search=new JButton("����");
		tool.add(search);
		
		
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				According_course s=new According_course(search_text.getText());
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
			ResultSet rs=dbcon.executeQuery("select * from student");
			//��ȡ��ѯ����������������ģ����
			ResultSetMetaData rsmd=rs.getMetaData();
			int Colnum =rsmd.getColumnCount();
			int i;
			for(i=1;i<=Colnum;i++)
				tablemodel.addColumn(rsmd.getColumnName(i));
			//��ȡ��ѯ����е�Ԫ�飬�����ģ����
			ArrayList<StudentEntity> v=new ArrayList<StudentEntity>();
			while(rs.next()) {
				StudentEntity person=new StudentEntity();
				person.setSno(rs.getString("sno"));
				person.setSname(rs.getString("sname"));
				person.setSsex(rs.getString("ssex"));
				person.setAge(rs.getInt("sage"));
				person.setSclassr(rs.getString("sclass"));
				person.setSdept(rs.getString("sdept"));
				v.add(person);
				
			}
			rs.close();
			for(i=0;i<v.size();i++) {
				tablemodel.addRow(new Object[] {
						v.get(i).getSno(),v.get(i).getSname(),v.get(i).getSsex(),v.get(i).getAge(),
						v.get(i).getSclassr(),v.get(i).getSdept()
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
