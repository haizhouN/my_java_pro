package teach;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Register extends JFrame{
	private JButton login,exit;
	private JTextField account;
	private JPasswordField password,rpassword;
	private JComboBox<String> identity;
	
	public Register() {
		this.setSize(500,250);
		this.setTitle("注册信息");
		this.setLocationRelativeTo(getOwner());
		
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(4,2));
		cont.add(new JLabel("选择注册身份",SwingConstants.CENTER));
		
		identity=new JComboBox<String>();
		identity.addItem("学生");
		identity.addItem("教师");
		identity.addItem("管理员");
		cont.add(identity);
		
		cont.add(new JLabel("账号：",SwingConstants.RIGHT));
		account=new JTextField();
		cont.add(account);
		
		cont.add(new JLabel("密码：",SwingConstants.RIGHT));
		password=new JPasswordField();
		cont.add(password);
		
		login=new JButton("注册");
		login.setFocusable(false);
		exit=new JButton("退出");
		exit.setFocusable(false);
		cont.add(login);
		cont.add(exit);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
}
