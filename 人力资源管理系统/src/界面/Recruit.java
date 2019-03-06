package 界面;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class Recruit extends JFrame {
	

	public Recruit() {
		// TODO 自动生成的构造函数存根
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
		
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width/2; // 获取屏幕的宽
		int screenHeight = screenSize.height/2; // 获取屏幕的高
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth-width/2 - 200, screenHeight-height/2 - 120);
		
		setSize(400, 400);
		setTitle("填写应聘信息");
		add(new recruitPanel());
	}
	
	
	//定义一堆信息
	JTextField Jname = new JTextField();//姓名
	JTextField Jsex = new JTextField();//
	JTextField Jbrith = new JTextField();//
	JTextField Jtell = new JTextField();//
	JTextField Jdept = new JTextField();//
	JTextField Jpos = new JTextField();//
	//工作经历
	JTextArea Jpast = new JTextArea();
	//历史业绩
	JTextArea Jperformance = new JTextArea();
	
	
	//界面
	class recruitPanel extends JPanel {
		
		//监听器重写
		ActionListener twoButtonListener = new buttonAction();
		
		public recruitPanel() {
			// TODO 自动生成的构造函数存根
			setLayout(new BorderLayout(10,10));
			
			JPanel centerPanel = new JPanel();
			//中间是8行2列的布局
			centerPanel.setLayout(new GridLayout(8,2,5,5));
			
			//布局中间
			//姓名
			addLabelToPanel("姓名：", centerPanel);
			centerPanel.add(Jname);
			
			//性别
			addLabelToPanel("性别：", centerPanel);
			centerPanel.add(Jsex);
			//生日
			addLabelToPanel("出生年月日：", centerPanel);
			centerPanel.add(Jbrith);
			//电话
			addLabelToPanel("联系方式：", centerPanel);
			centerPanel.add(Jtell);
			//部门
			addLabelToPanel("应聘部门：", centerPanel);
			centerPanel.add(Jdept);
			//职位
			addLabelToPanel("应聘职位：", centerPanel);
			centerPanel.add(Jpos);
			//经历
			addLabelToPanel("工作经历：", centerPanel);
			centerPanel.add(Jpast);
			//业绩
			addLabelToPanel("历史业绩：", centerPanel);
			centerPanel.add(Jperformance);
			
			//布局下面确定和取消
			JPanel underPanel = new JPanel();
			underPanel.setLayout(new FlowLayout());
			addButtonToPanel("提交", twoButtonListener, underPanel);
			addButtonToPanel("取消", twoButtonListener, underPanel);
			
			//吧界面加入布局管理器
			add(centerPanel,BorderLayout.CENTER);
			add(underPanel,BorderLayout.SOUTH);
		}
	}
	
	public void addLabelToPanel(String name , Container panel) {
		JLabel label = new JLabel(name, SwingConstants.RIGHT);
		panel.add(label);
	}
	
	public void addButtonToPanel(String name ,ActionListener listener, Container panel) {
		JButton button = new JButton(name);
		button.addActionListener(listener);
		panel.add(button);
	}
	
	
	//按提交和取消的监听器
	class buttonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO 自动生成的方法存根
			String eventName = event.getActionCommand();
			if (eventName.equals("提交")) {
				
				try {
					
					//用户名和密码
			        String connectDB = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;DatabaseName=人力资源管理系统";
			        String user = "sa";
			        String password = "123456";
			        // 连接数据库对象
			        Connection con = DriverManager.getConnection(connectDB, user,
			                password);
			        
			        //合成sql语句
			        String sqlyuju = "insert into 应聘人员表 values";
			        String yuju = "('" + Jname.getText() + "','" + Jsex.getText() + "','" + Jpast.getText() + "','" + Jtell.getText() + "','" + Jdept.getText() + "','" + Jpos.getText() + "','" + Jperformance.getText() + "','" + Jbrith.getText() + "','')";
			        sqlyuju += yuju;
			        System.out.println("成功保存" + yuju);
			        
			        // 创建SQL命令对象
			        Statement stmt = con.createStatement();
			        stmt.execute(sqlyuju);
			        JOptionPane.showMessageDialog(null, "成功提交");
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        JOptionPane.showMessageDialog(null, "写入错误");
			    }
			}else if (eventName.equals("取消")) {
				Recruit.this.dispose();
				new FirstGUI();
			}
		}
		
	}
}
