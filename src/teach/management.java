package teach;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JFrame;
public class management extends JFrame{
	private JButton st,te,co,sc,tc;
	private JToolBar tool;
	public management() {
		this.setSize(600, 300);
		this.setTitle("���ݹ���");
		this.setLocationRelativeTo(getOwner());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(4,3));
		cont.add(new JLabel("-"));
		cont.add(new JLabel("------����Ա����------",SwingConstants.CENTER));
		cont.add(new JLabel("-"));
		st=new JButton("ѧ����Ϣ����");
		st.setFocusable(false);
		st.setHorizontalTextPosition(SwingConstants.CENTER);
		st.setVerticalTextPosition(SwingConstants.BOTTOM);
		cont.add(st);
		cont.add(new JLabel(" "));
		te=new JButton("��ʦ����");
		te.setFocusable(false);
		te.setHorizontalTextPosition(SwingConstants.CENTER);
		te.setVerticalTextPosition(SwingConstants.BOTTOM);
		cont.add(te);
		
		co=new JButton("�γ̹���");
		co.setFocusable(false);
		co.setHorizontalTextPosition(SwingConstants.CENTER);
		co.setVerticalTextPosition(SwingConstants.BOTTOM);
		cont.add(co);
		cont.add(new JLabel(" "));
		sc=new JButton("ѡ��");
		sc.setFocusable(false);
		sc.setHorizontalTextPosition(SwingConstants.CENTER);
		sc.setVerticalTextPosition(SwingConstants.BOTTOM);
		cont.add(sc);

		tc=new JButton("�ڿ�");
		tc.setFocusable(false);
		tc.setHorizontalTextPosition(SwingConstants.CENTER);
		tc.setVerticalTextPosition(SwingConstants.BOTTOM);
		cont.add(new JLabel(" "));
		cont.add(tc);
//		tool=new JToolBar();
//		tool.add(st);
//		tool.add(te);
//		tool.add(co);
//		tool.add(sc);
//		tool.add(tc);
//		tool.setRollover(true);
//		getContentPane().add(tool,BorderLayout.NORTH);
		
		st.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				Student s=new Student();
				s.setVisible(true);
			}
			
		});
		te.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				Teacher s=new Teacher();
				s.setVisible(true);
			}
			
		});
		co.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				Course s=new Course();
				s.setVisible(true);
			}
			
		});
		sc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				Select_Course s=new Select_Course();
				s.setVisible(true);
			}
			
		});
		tc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				Teach_course s=new Teach_course();
				s.setVisible(true);
			}
			
		});
		
		
		
	}
	

}
