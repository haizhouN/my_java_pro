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
		this.setTitle("新生信息录入");
		this.setLocationRelativeTo(getOwner());
		
		//设置组件布局
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(7,2));	
		//学号
		cont.add(new JLabel("学号"));
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
		
		//年龄
		cont.add(new JLabel("年龄"));
		age=new JTextField(10);
		cont.add(age);
		
		//班级
		cont.add(new JLabel("班级"));
		cl=new JTextField(10);
		cont.add(cl);
		
		
		//院系
		cont.add(new JLabel("院系"));
		dept=new JTextField(10);
		cont.add(dept);
		
		ok=new JButton("确定");
		cancel=new JButton("取消");
		cont.add(ok);
		cont.add(cancel);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// TODO 自动生成的方法存根
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
					JOptionPane.showMessageDialog(null, "插入成功");
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
				// TODO 自动生成的方法存根
				dispose();
				Student pesl =new Student();
				pesl.setVisible(true);
			}
			
		});
		
		
		
		
		
	}
	

}
