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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import db.Db;

public class AddCourse extends JFrame{
//	private String cno,cname,cpno;
//	private double ccredit;
//	private int cperiod;
	private JTextField cno,cname,cpno;
	private JTextField ccredit;
	private JTextField cperiod;
	private JButton ok,cancel;
	public AddCourse() {
		super();
		this.setSize(350,300);
		this.setTitle("��ӿγ���Ϣ");
		this.setLocationRelativeTo(getOwner());
		
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(6,2));
		
		cont.add(new JLabel("�γ̺�"));
		cno=new JTextField(10);
		cont.add(cno);
		
		cont.add(new JLabel("�γ���"));
		cname=new JTextField(10);
		cont.add(cname);
		
		cont.add(new JLabel("���޿γ̺�"));
		cpno=new JTextField(10);
		cont.add(cpno);
		
		cont.add(new JLabel("ѧ��"));
		ccredit=new JTextField(10);
		cont.add(ccredit);
		
		cont.add(new JLabel("ѧʱ"));
		cperiod=new JTextField(10);
		cont.add(cperiod);
		
		ok=new JButton("ȷ��");
		cancel=new JButton("ȡ��");
		cont.add(ok);
		cont.add(cancel);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// TODO �Զ����ɵķ������
				Db dbcon=new Db();
				try {
					String sql="insert into course values(?,?,?,?,?)";
					PreparedStatement prestate=dbcon.PreparedStatement(sql);
					prestate.setString(1, cno.getText());
					prestate.setString(2, cname.getText());
					if(cpno.getText().equals(""))
						prestate.setString(3, null);
					else
						prestate.setString(3, cpno.getText());
					//prestate.setString(3, (cpno.getText()==""?null:cpno.getText()));
					prestate.setString(4, ccredit.getText());
					prestate.setString(5, cperiod.getText());
					prestate.executeUpdate();
					JOptionPane.showMessageDialog(null, "����ɹ�");
					dispose();
					Course pesl =new Course();
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
				Course pesl =new Course();
				pesl.setVisible(true);
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	

}
