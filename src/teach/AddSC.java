package teach;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import db.Db;

public class AddSC extends JFrame{
	private JTextField sno,cno,academicyear;
	private JButton ok,cancel;
	public AddSC() {
		super();
		this.setSize(350,300);
		this.setTitle("ѡ����Ϣ");
		this.setLocationRelativeTo(getOwner());
		
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(4,2));
		
		cont.add(new JLabel("ѧ��"));
		sno=new JTextField(10);
		cont.add(sno);
		
		cont.add(new JLabel("��ѡ�γ̺�"));
		cno=new JTextField(10);
		cont.add(cno);
		
		cont.add(new JLabel("ѧ��"));
		academicyear=new JTextField(10);
		cont.add(academicyear);
		
		ok=new JButton("ȷ��");
		cancel=new JButton("ȡ��");
		cont.add(ok);
		cont.add(cancel);
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				Db dbcon=new Db();
				try {
					String sql="insert into sc values(?,?,?,?,?,?,?,?)";
					PreparedStatement prestate=dbcon.PreparedStatement(sql);
					//prestate.setString(0, sno.getText());
					prestate.setString(1, sno.getText());
					prestate.setString(2, cno.getText());
					prestate.setString(3, null);
					//prestate.setString(3, (cpno.getText()==""?null:cpno.getText()));
					prestate.setString(4, null);
					prestate.setString(5, null);
					prestate.setString(6, null);
					prestate.setString(7, null);
					prestate.setString(8, academicyear.getText());
					prestate.executeUpdate();
					JOptionPane.showMessageDialog(null, "����ɹ�");
					dispose();
				}
				catch(SQLException e1) {
					System.out.println(e1.toString());
				}
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				dispose();
			}
		});
		
		
		
		
		
		
	}
}
