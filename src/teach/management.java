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
		this.setTitle("数据管理");
		this.setLocationRelativeTo(getOwner());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(4,3));
		cont.add(new JLabel("-"));
		cont.add(new JLabel("------管理员界面------",SwingConstants.CENTER));
		cont.add(new JLabel("-"));
		st=new JButton("学生信息管理");
		st.setFocusable(false);
		st.setHorizontalTextPosition(SwingConstants.CENTER);
		st.setVerticalTextPosition(SwingConstants.BOTTOM);
		cont.add(st);
		cont.add(new JLabel(" "));
		te=new JButton("教师管理");
		te.setFocusable(false);
		te.setHorizontalTextPosition(SwingConstants.CENTER);
		te.setVerticalTextPosition(SwingConstants.BOTTOM);
		cont.add(te);
		
		co=new JButton("课程管理");
		co.setFocusable(false);
		co.setHorizontalTextPosition(SwingConstants.CENTER);
		co.setVerticalTextPosition(SwingConstants.BOTTOM);
		cont.add(co);
		cont.add(new JLabel(" "));
		sc=new JButton("选课");
		sc.setFocusable(false);
		sc.setHorizontalTextPosition(SwingConstants.CENTER);
		sc.setVerticalTextPosition(SwingConstants.BOTTOM);
		cont.add(sc);

		tc=new JButton("授课");
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
				// TODO 自动生成的方法存根
				Student s=new Student();
				s.setVisible(true);
			}
			
		});
		te.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Teacher s=new Teacher();
				s.setVisible(true);
			}
			
		});
		co.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Course s=new Course();
				s.setVisible(true);
			}
			
		});
		sc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Select_Course s=new Select_Course();
				s.setVisible(true);
			}
			
		});
		tc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Teach_course s=new Teach_course();
				s.setVisible(true);
			}
			
		});
		
		
		
	}
	

}
