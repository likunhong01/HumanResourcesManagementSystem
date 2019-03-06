package 界面;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class 普通人员登录 extends JFrame {
	public 普通人员登录() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width/2; // 获取屏幕的宽
		int screenHeight = screenSize.height/2; // 获取屏幕的高
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth-width/2 - 200, screenHeight-height/2 - 120);
		
		setSize(300, 150);
		setTitle("登录");
		add(new registerPanel());
	}
	
	
	//界面
	class registerPanel extends JPanel{
		
		JPanel panelWithEnsureAndCancel;
		JPanel panelWithUserNameAndPassword;
		String userName;
		String userPassword;
		
		//文本框全局变量
		JTextField Jusername;
		JTextField Jpassword;
		
		//监听器重写
		ActionListener SureAndCancelListener = new buttonAction();
		
		public registerPanel() {
			
			setLayout(new BorderLayout(5,5));
			
			//确定和取消按钮
			//确定和取消，用流式布局管理器
			panelWithEnsureAndCancel = new JPanel();
			panelWithEnsureAndCancel.setLayout(new FlowLayout());
			
			//把按钮加入监听器和画板
			addButtonToPanel("确定", SureAndCancelListener, panelWithEnsureAndCancel);
			addButtonToPanel("取消", SureAndCancelListener, panelWithEnsureAndCancel);
			//吧画板一加入总的south部分
			add(panelWithEnsureAndCancel, BorderLayout.SOUTH);
			
			
			//设置中间的用户名和密码登录框子
			setCenter();
			
		}
		
		public void addButtonToPanel(String label ,ActionListener listener, Container panel) {
			JButton button = new JButton(label);
			button.addActionListener(listener);
			panel.add(button);
		}
		
		public void setCenter() {
			
			//中间区域用网格布局管理器
			panelWithUserNameAndPassword = new JPanel();
			panelWithUserNameAndPassword.setLayout(new GridLayout(2, 2, 5, 5));
			
			//账号
			JLabel number = new JLabel("账号：",SwingConstants.RIGHT);
			panelWithUserNameAndPassword.add(number);
			//账号输入框
			Jusername = new JTextField("");
			panelWithUserNameAndPassword.add(Jusername);
			
			//密码：
			JLabel password = new JLabel("密码：",SwingConstants.RIGHT);
			panelWithUserNameAndPassword.add(password);
			//密码输入框
			Jpassword = new JTextField("");
			panelWithUserNameAndPassword.add(Jpassword);
			
			//把中间网格加入整体
			add(panelWithUserNameAndPassword, BorderLayout.CENTER);
		}

		
		
		//按确定和取消的监听器
		class buttonAction implements ActionListener {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO 自动生成的方法存根
				String eventName = event.getActionCommand();
				if (eventName.equals("确定")) {
					
					//把文本框提取
					userName = "'" + Jusername.getText() + "'";
					userPassword = Jpassword.getText();
					
					System.out.println(userName);
					System.out.println(userPassword);
					
					try {
						
						//用户名和密码
				        String connectDB = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;DatabaseName=人力资源管理系统";
				        String user = "sa";
				        String password = "123456";
				        // 连接数据库对象
				        Connection con = DriverManager.getConnection(connectDB, user,
				                password);

				        // 创建SQL命令对象
				        Statement stmt = con.createStatement();
				        
				        // 读取数据
				        ResultSet rs = stmt.executeQuery("SELECT Mypassword FROM 用户信息表 WHERE Myaccount=" + userName);// 返回SQL语句查询的密码
				        rs.next();
				        String mima = rs.getString("Mypassword");
				        mima = mima.substring(0, 6);
				        System.out.println(mima);
				        
				        if (mima.equals(userPassword)) {
				        	JOptionPane.showMessageDialog(null, "登录成功");
							普通人员登录.this.dispose();
							new 个人信息界面(Integer.valueOf(Jusername.getText()));
						}else {
							JOptionPane.showMessageDialog(null, "账号或密码错误，请重新输入！");
							Jpassword.setText("");
						}
				        
				    } catch (SQLException e) {
				        e.printStackTrace();
				        System.out.print(e.getErrorCode());
				        String error = "";
				        if (e.getErrorCode() == 0) {
							error = "用户名不存在";
						}
				        JOptionPane.showMessageDialog(null, "数据库连接错误\n错误：" + e.getErrorCode() + "," + error);
				    }
				}else if (eventName.equals("取消")) {
					普通人员登录.this.dispose();
					new FirstGUI();
				}
			}
		}
	}
}

