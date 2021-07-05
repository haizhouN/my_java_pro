package teach;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.Db;
import entity.StudentEntity;
import entity.User;

public class Enter extends JFrame{
	private JButton login,exit;
	private JTextField account;
	private JPasswordField password;
	private JComboBox<String> identity;
	
	public Enter() {
		this.setSize(500,250);
		this.setTitle("��¼ϵͳ");
		this.setLocationRelativeTo(getOwner());
		
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(4,2));
		cont.add(new JLabel("��ѡ���¼���",SwingConstants.CENTER));
		
		identity=new JComboBox<String>();
		identity.addItem("ѧ��");
		identity.addItem("��ʦ");
		identity.addItem("����Ա");
		cont.add(identity);
		
		cont.add(new JLabel("�˺ţ�",SwingConstants.RIGHT));
		account=new JTextField();
		cont.add(account);
		
		cont.add(new JLabel("���룺",SwingConstants.RIGHT));
		password=new JPasswordField();
		cont.add(password);
		login=new JButton("��¼");
		login.setFocusable(false);
		exit=new JButton("�˳�");
		exit.setFocusable(false);
		cont.add(login);
		cont.add(exit);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		login.addActionListener(new ActionListener() {
			String password1=new String();
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String key=new String();
				key=identity.getSelectedItem().toString();
				
				if(key.equals("ѧ��")) {
					
					Db dbcon;
					try {//�������ݿ⣬ִ�в�ѯ���
						dbcon=new Db();
						ResultSet rs=dbcon.executeQuery("select * from user1");
						
						ArrayList<User> v=new ArrayList<User>();
						while(rs.next()) {
							User person=new User();
							person.setUsername(rs.getString("username"));
							person.setUserpassword(rs.getString("userpassword"));
							person.setPosition(rs.getString("position"));
							v.add(person);
							
						}
						rs.close();
						for(int i=0;i<v.size();i++) {
							if(v.get(i).getUsername().trim().equals(account.getText())&&v.get(i).getPosition().trim().equals(identity.getSelectedItem())) {
								password1=v.get(i).getUserpassword();
							}
						}
						if(password1.equals(password.getText())) {
							LogStudent s=new LogStudent();
							s.setVisible(true);
							dispose();
						}
						else
						JOptionPane.showMessageDialog(null,"�û��������������");
						
					}
					catch(SQLException sqle) {
						System.out.println(sqle.toString());
					}
					catch(Exception e1) {
						System.out.println(e1.getMessage());
					}
//					if(account.getText().equals("202190631")&&password.getPassword().equals("123456")) {
//						LogStudent s=new LogStudent();
//						s.setVisible(true);
//						dispose();
//					}
					
				}
				if(key.equals("��ʦ")) {
					Db dbcon;
					try {//�������ݿ⣬ִ�в�ѯ���
						dbcon=new Db();
						ResultSet rs=dbcon.executeQuery("select * from user1");
						
						ArrayList<User> v=new ArrayList<User>();
						while(rs.next()) {
							User person=new User();
							person.setUsername(rs.getString("username"));
							person.setUserpassword(rs.getString("userpassword"));
							person.setPosition(rs.getString("position"));
							v.add(person);
							
						}
						rs.close();
						for(int i=0;i<v.size();i++) {
							if(v.get(i).getUsername().trim().equals(account.getText())&&v.get(i).getPosition().trim().equals(identity.getSelectedItem())) {
								password1=v.get(i).getUserpassword();
							}
						}
						if(password1.equals(password.getText())) {
							LogTeacher t=new LogTeacher();
							t.setVisible(true);
							dispose();
						}
						else
						JOptionPane.showMessageDialog(null,"�û��������������");
						
					}
					catch(SQLException sqle) {
						System.out.println(sqle.toString());
					}
					catch(Exception e1) {
						System.out.println(e1.getMessage());
					}			
					
//					
//					if(account.getText().equals("00000001")&&password.getText().equals("123456")) {
//						LogTeacher t=new LogTeacher();
//						t.setVisible(true);
//						dispose();
//					}
//					else
//						JOptionPane.showMessageDialog(null,"�˺Ż����������");
				}
				if(key.equals("����Ա")) {
					Db dbcon;
					try {//�������ݿ⣬ִ�в�ѯ���
						dbcon=new Db();
						ResultSet rs=dbcon.executeQuery("select * from user1");
						
						ArrayList<User> v=new ArrayList<User>();
						while(rs.next()) {
							User person=new User();
							person.setUsername(rs.getString("username"));
							person.setUserpassword(rs.getString("userpassword"));
							person.setPosition(rs.getString("position"));
							v.add(person);
							
						}
						rs.close();
						for(int i=0;i<v.size();i++) {
							if(v.get(i).getUsername().trim().equals(account.getText())&&v.get(i).getPosition().trim().equals(identity.getSelectedItem())) {
								password1=v.get(i).getUserpassword();
							}
						}
						if(password1.equals(password.getText())) {
							management m=new management();
							m.setVisible(true);
							dispose();
						}
						else
						JOptionPane.showMessageDialog(null,"�û��������������");
						
					}
					catch(SQLException sqle) {
						System.out.println(sqle.toString());
					}
					catch(Exception e1) {
						System.out.println(e1.getMessage());
					}	
//					if(account.getText().equals("66666666")&&password.getText().equals("88888888")) {
//						management m=new management();
//						m.setVisible(true);
//						dispose();
//					}
//					else
//						JOptionPane.showMessageDialog(null,"�˺Ż����������");
				}
			}
			
		});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				dispose();
			}
		});		
	}
}
