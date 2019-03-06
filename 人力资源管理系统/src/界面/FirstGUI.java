package 界面;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FirstGUI extends JFrame{
	public FirstGUI() {
		
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
		
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width/2; // 获取屏幕的宽
		int screenHeight = screenSize.height/2; // 获取屏幕的高
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth-width/2 - 200, screenHeight-height/2 - 120);
		
		setSize(400, 200);
		setTitle("人力资源管理系统");
		add(new firstPanel());
	}
	
	//界面
	class firstPanel extends JPanel {
		//监听器重写
		ActionListener twoButtonListener = new buttonAction();
		
		public firstPanel() {
			// TODO 自动生成的构造函数存根
			setLayout(new GridLayout(1,1,40,40));
			
			JPanel thispanel = new JPanel();
			thispanel.setLayout(new FlowLayout());
			
			//加入登录button
			JButton 个人登录button = new JButton("个人登录");
			个人登录button.addActionListener(twoButtonListener);
			thispanel.add(个人登录button);
			
			
			//加入应聘button
			JButton 管理员登录button = new JButton("管理员登录");
			管理员登录button.addActionListener(twoButtonListener);
			thispanel.add(管理员登录button);
			
			//加入应聘button
			JButton 应聘button = new JButton("应聘");
			应聘button.addActionListener(twoButtonListener);
			thispanel.add(应聘button);
			
			add(thispanel);
		}
	}
	
	
	//监听器重写
	class buttonAction implements ActionListener {
		//重写方法
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO 自动生成的方法存根
			String eventName = event.getActionCommand();
			if (eventName.equals("个人登录")) {
				//关闭这个窗口
				FirstGUI.this.dispose();
				//打开登录界面
				new 普通人员登录();
			}else if (eventName.equals("应聘")) {
				//关闭这个窗口
				FirstGUI.this.dispose();
				//打开应聘界面
				new Recruit();
			}else if (eventName.equals("管理员登录")) {
				//关闭这个窗口
				FirstGUI.this.dispose();
				//打开应聘界面
				new 管理员登录界面();
			}
		}
	}
}

