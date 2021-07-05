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
		this.setTitle("ע����Ϣ");
		this.setLocationRelativeTo(getOwner());
		
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(4,2));
		cont.add(new JLabel("ѡ��ע�����",SwingConstants.CENTER));
		
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
		
		login=new JButton("ע��");
		login.setFocusable(false);
		exit=new JButton("�˳�");
		exit.setFocusable(false);
		cont.add(login);
		cont.add(exit);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
}
