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
		this.setTitle("教师界面");
		this.setLocationRelativeTo(getOwner());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container cont=getContentPane();
		cont.setLayout(new GridLayout(2,3));
		
		
		cont.add(new JLabel(" ",SwingConstants.CENTER));
		cont.add(new JLabel("欢迎老师登录",SwingConstants.CENTER));
		cont.add(new JLabel(" ",SwingConstants.CENTER));
		exit=new JButton("退出");
		grade=new JButton("成绩查询");
		teach=new JButton("授课管理及成绩录入");
		
		cont.add(teach);
		cont.add(grade);
		cont.add(exit);
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				dispose();
			}
			
		});
		teach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Teach_course t=new Teach_course();
				t.setVisible(true);
			}
			
		});
		grade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				Calculate_grade c=new Calculate_grade();
				c.setVisible(true);
			}
			
		});
		
	}
	
	
	
}
