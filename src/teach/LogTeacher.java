package teach;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LogTeacher extends JFrame{
	private JButton exit,teach,grade;
	
	public LogTeacher() {
		this.setSize(600,300);
		this.setTitle("��ʦ����");
		this.setLocationRelativeTo(getOwner());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(2,3));
		
		
		cont.add(new JLabel(" ",SwingConstants.CENTER));
		cont.add(new JLabel("��ӭ��ʦ��¼",SwingConstants.CENTER));
		cont.add(new JLabel(" ",SwingConstants.CENTER));
		exit=new JButton("�˳�");
		grade=new JButton("�ɼ���ѯ");
		teach=new JButton("�ڿι����ɼ�¼��");
		
		cont.add(teach);
		cont.add(grade);
		cont.add(exit);
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				dispose();
			}
			
		});
		teach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				Teach_course t=new Teach_course();
				t.setVisible(true);
			}
			
		});
		grade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				Calculate_grade c=new Calculate_grade();
				c.setVisible(true);
			}
			
		});
		
	}
	
	
	
}
