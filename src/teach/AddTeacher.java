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
		this.setTitle("新老师信息");
		this.setLocationRelativeTo(getOwner());
		
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(7,2));
		
		
		//员工号
		cont.add(new JLabel("教师编号"));
		userID=new JTextField(10);
		cont.add(userID);
		//姓名
		cont.add(new JLabel("姓名"));
		userName=new JTextField(10);
		cont.add(userName);
				
		//性别
		cont.add(new JLabel("性别"));
		sexm=new JRadioButton("男",true);
		sexf=new JRadioButton("女");
		ButtonGroup bg=new ButtonGroup();
		bg.add(sexm);
		bg.add(sexf);
		sex=new JPanel(new GridLayout(2,1));
		sex.add(sexm);
		sex.add(sexf);
		cont.add(sex);
				
		//出生日期
		cont.add(new JLabel("出生日期"));
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
		
		//电话
		cont.add(new JLabel("电话"));
		phone=new JTextField(10);
		cont.add(phone);
		
		//职位
		cont.add(new JLabel("职位"));
		prof=new JTextField(10);
		cont.add(prof);
		
		//按钮
		ok=new JButton("确定");
		cancel=new JButton("取消");
		cont.add(ok);
		cont.add(cancel);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// TODO 自动生成的方法存根
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
					JOptionPane.showMessageDialog(null, "插入成功");
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
