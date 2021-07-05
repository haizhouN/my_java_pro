package teach;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


import db.Db;

public class AddTeacher extends JFrame{
	private JTextField userID,userName;
	private JRadioButton sexf,sexm;
	private JPanel sex,birth;
	private JTextField year,phone;
	private JComboBox <Integer> month,day;
	private JTextField prof;
	private JButton ok,cancel;
	
	public AddTeacher() {
		super();
		this.setSize(350,400);
		this.setTitle("����ʦ��Ϣ");
		this.setLocationRelativeTo(getOwner());
		
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(7,2));
		
		
		//Ա����
		cont.add(new JLabel("��ʦ���"));
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
				
		//��������
		cont.add(new JLabel("��������"));
		year=new JTextField(4);
		month=new JComboBox<Integer>();
		for(int i=1;i<=12;i++)
			month.addItem(i);
		day=new JComboBox<Integer>();
		for(int i=1;i<=12;i++)
			day.addItem(i);
		birth=new JPanel();
		birth.add(year);
		birth.add(new JLabel("-"));
		birth.add(month);
		birth.add(new JLabel("-"));
		birth.add(day);
		birth.add(new JLabel("-"));
		cont.add(birth);
		
		//�绰
		cont.add(new JLabel("�绰"));
		phone=new JTextField(10);
		cont.add(phone);
		
		//ְλ
		cont.add(new JLabel("ְλ"));
		prof=new JTextField(10);
		cont.add(prof);
		
		//��ť
		ok=new JButton("ȷ��");
		cancel=new JButton("ȡ��");
		cont.add(ok);
		cont.add(cancel);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// TODO �Զ����ɵķ������
				Db dbcon=new Db();
				try {
					String sql="insert into teacher values(?,?,?,?,?,?)";
					PreparedStatement prestate=dbcon.PreparedStatement(sql);
					prestate.setString(1, userID.getText());
					prestate.setString(2, userName.getText());
					prestate.setString(3, (sexm.isSelected()?sexm.getText():sexf.getText()));
					prestate.setString(4, year.getText()+"-"+month.getSelectedItem()+"-"+day.getSelectedItem());
					prestate.setString(5, phone.getText());
					prestate.setString(6, prof.getText());
					prestate.executeUpdate();
					JOptionPane.showMessageDialog(null, "����ɹ�");
					dispose();
					Teacher pesl =new Teacher();
					pesl.setVisible(true);
					
				}
				catch(SQLException e) {
					System.out.println(e.toString());
				}
	
			}
					
		});
				
				
				
		
	}
	
	
	
	
	
}
