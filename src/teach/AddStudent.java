package teach;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.*;
import db.Db;
public class AddStudent extends JFrame{
	
	private JTextField userID,userName;
	private JRadioButton sexf,sexm;
	private JPanel sex;
	private JTextField dept;
	private JTextField cl,age;
	private JButton ok,cancel;
	
	public AddStudent() {
		super();
		this.setSize(350,300);
		this.setTitle("������Ϣ¼��");
		this.setLocationRelativeTo(getOwner());
		
		//�����������
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(7,2));	
		//ѧ��
		cont.add(new JLabel("ѧ��"));
		userID=new JTextField(10);
		cont.add(userID);
		//����
		cont.add(new JLabel("����"));
		userName=new JTextField(10);
		cont.add(userName);
		
		//�Ա�
		cont.add(new JLabel("�Ա�"));
		sexm=new JRadioButton("��",true);
		sexf=new JRadioButton("Ů");
		ButtonGroup bg=new ButtonGroup();
		bg.add(sexm);
		bg.add(sexf);
		sex=new JPanel(new GridLayout(2,1));
		sex.add(sexm);
		sex.add(sexf);
		cont.add(sex);
		
		//����
		cont.add(new JLabel("����"));
		age=new JTextField(10);
		cont.add(age);
		
		//�༶
		cont.add(new JLabel("�༶"));
		cl=new JTextField(10);
		cont.add(cl);
		
		
		//Ժϵ
		cont.add(new JLabel("Ժϵ"));
		dept=new JTextField(10);
		cont.add(dept);
		
		ok=new JButton("ȷ��");
		cancel=new JButton("ȡ��");
		cont.add(ok);
		cont.add(cancel);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// TODO �Զ����ɵķ������
				Db dbcon=new Db();
				try {
					String sql="insert into student values(?,?,?,?,?,?)";
					PreparedStatement prestate=dbcon.PreparedStatement(sql);
					prestate.setString(1, userID.getText());
					prestate.setString(2, userName.getText());
					prestate.setString(3, (sexm.isSelected()?sexm.getText():sexf.getText()));
					prestate.setString(4, age.getText());
					prestate.setString(5, cl.getText());
					prestate.setString(6, dept.getText());
					prestate.executeUpdate();
					JOptionPane.showMessageDialog(null, "����ɹ�");
					dispose();
					Student pesl =new Student();
					pesl.setVisible(true);
					
					
					
					
				}
				catch(SQLException e) {
					System.out.println(e.toString());
				}
		
			}
			
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				dispose();
				Student pesl =new Student();
				pesl.setVisible(true);
			}
			
		});
		
		
		
		
		
	}
	

}
