package teach;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LogStudent extends JFrame{
	private JButton search_course,exit,message;
	public LogStudent() {
		this.setSize(600, 300);
		this.setTitle("学生界面");
		this.setLocationRelativeTo(getOwner());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(2,3));
		
		cont.add(new JLabel("----------------欢迎登录----------------"));
		cont.add(new JLabel(""));
		cont.add(new JLabel(""),SwingConstants.CENTER);
		
		
		search_course=new JButton("选课");
		message=new JButton("成绩查询查询");
		exit=new JButton("退出");
		cont.add(search_course);
		cont.add(message);
		cont.add(exit);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				dispose();
			}
		});
		search_course.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Select_Course s=new Select_Course();
				s.setVisible(true);
			}
			
		});
		message.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				LogShowStudent l=new LogShowStudent();
				l.setVisible(true);
			}
			
		});
		
		
		
		
	}
	
	
	

}
