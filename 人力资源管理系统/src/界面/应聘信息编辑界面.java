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



public class 应聘信息编辑界面 extends JFrame {
	
//	public static void main(String[] args) {
//		new 应聘信息编辑界面();
//	}
	
	public 应聘信息编辑界面(String string) {
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
		setTitle("查询修改或删除应聘人员信息");
		add(new updateAndDeletePanel());
	}
	
	public 应聘信息编辑界面() {
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
		setTitle("查询修改或删除应聘人员信息");
		add(new updateAndDeletePanel());
	}
	
	//定义查询编号
	JTextField 查询num = new JTextField("\t");
	
	
	//定义一堆信息
	JTextField Jnum = new JTextField();//
	JTextField Jdeptnum = new JTextField();//
	JTextField Jposnum = new JTextField();//
	JTextField Jname = new JTextField();//
	JTextField Jsex = new JTextField();//
	JTextField Jbrith = new JTextField();//
	JTextField Jtell = new JTextField();//
	JTextField JManager = new JTextField();//

	//工作经历
	JTextArea Jpast = new JTextArea();
	JTextArea Jhistory = new JTextArea();
	
	//界面
	class updateAndDeletePanel extends JPanel {
		
		//监听器重写
		ActionListener ButtonListener = new buttonAction();
		
		public updateAndDeletePanel(String string) {
			查询num.setText(string);
			
		}
		
		public updateAndDeletePanel() {
			// TODO 自动生成的构造函数存根
			setLayout(new BorderLayout(10,10));
			
			//上边面板，顺序管理器，3个容器
			JPanel NorthPanel = new JPanel();
			NorthPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			JLabel 输入编号标题 = new JLabel("输入姓名:");
			NorthPanel.add(输入编号标题);
			
			查询num.setHorizontalAlignment(JTextField.LEFT);			
			NorthPanel.add(查询num);
			
			//查询按钮
			addButtonToPanel("查询", ButtonListener, NorthPanel);
			//吧上面板加入北边
			add(NorthPanel, BorderLayout.NORTH);
			
			
			//中间是11行2列的布局
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new GridLayout(9,2,5,5));
			
			//布局中间
			//编号
			addLabelToPanel("姓名：", centerPanel);
			centerPanel.add(Jname);
			//部门编号
			addLabelToPanel("性别：", centerPanel);
			centerPanel.add(Jsex);
			//职位编号
			addLabelToPanel("出生年月：", centerPanel);
			centerPanel.add(Jbrith);
			//组别
			addLabelToPanel("电话：", centerPanel);
			centerPanel.add(Jtell);
			//姓名
			addLabelToPanel("应聘部门：", centerPanel);
			centerPanel.add(Jdeptnum);
			//性别
			addLabelToPanel("应聘职位：", centerPanel);
			centerPanel.add(Jposnum);
			//生日
			addLabelToPanel("工作经历：", centerPanel);
			centerPanel.add(Jpast);
			//电话
			addLabelToPanel("历史业绩：", centerPanel);
			centerPanel.add(Jhistory);
			
			addLabelToPanel("面试经理：", centerPanel);
			centerPanel.add(JManager);

			
			
			//布局下面确定和取消
			JPanel underPanel = new JPanel();
			underPanel.setLayout(new FlowLayout());
			addButtonToPanel("删除", ButtonListener, underPanel);
			addButtonToPanel("修改并保存", ButtonListener, underPanel);
			addButtonToPanel("关闭", ButtonListener, underPanel);
			
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
			if (eventName.equals("查询")) {
				try {
					//用户名和密码
			        String connectDB = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;DatabaseName=人力资源管理系统";
			        String user = "sa";
			        String password = "123456";
			        // 连接数据库对象
			        Connection con = DriverManager.getConnection(connectDB, user,
			                password);
			        
			        //合成sql语句(查询语句)
			        String name = 查询num.getText();
			        if (name.charAt(0) == '\t') {
						name = name.substring(1,name.length());
					}
			        String sqlyuju = "SELECT * FROM 应聘人员表 WHERE applicantName='" + name + "'";
			        
			        System.out.println("查询语句为：" + sqlyuju);
			        
			        // 创建SQL命令对象
			        Statement stmt = con.createStatement();
			        //获得查询表集合
			        ResultSet rSet = stmt.executeQuery(sqlyuju);
			        rSet.next();
			        
			        Jname.setText(rSet.getString("applicantName"));
			        Jposnum.setText(rSet.getString("applicantPos"));
			        Jdeptnum.setText(rSet.getString("applicantDept"));
			        Jsex.setText(rSet.getString("applicantSex"));
			        Jbrith.setText(rSet.getString("applicantBirth"));
			        Jtell.setText(rSet.getString("applicantContact"));
			        Jpast.setText(rSet.getString("applicantPast"));
			        Jhistory.setText(rSet.getString("pastPerformance"));
			        JManager.setText(rSet.getString("applicantManager"));
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        System.out.println("查询错误");
			        System.exit(0);
			    }
			}else if (eventName.equals("删除")) {
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
			        
			        //发送查询语句
			        String name = 查询num.getText();
			        if (name.charAt(0) == '\t') {
						name = name.substring(1,name.length());
					}
			        stmt.execute("DELETE FROM 应聘人员表 WHERE applicantName='" + name + "'");
			        
			        JOptionPane.showMessageDialog(null, "成功删除‘"+Jname.getText() + "’");
			        
			        
				} catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        System.out.println("删除错误");
			        System.exit(0);
			    }
				
				
				
			}else if (eventName.equals("修改并保存")) {
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
			        //发送查询语句
			        //UPDATE 表 SET clerkNO=2,clerkName='赵鑫',
			        String name = 查询num.getText();
			        if (name.charAt(0) == '\t') {
						name = name.substring(1,name.length());
					}
			        String sql = "UPDATE 应聘人员表 SET applicantName='" + Jname.getText() + "',applicantSex='"
			        		+ Jsex.getText() + "',applicantBirth='" + Jbrith.getText() + "',applicantContact='" + Jtell.getText()
			        		+ "',applicantDept='" + Jdeptnum.getText() + "',applicantPos='" + Jposnum.getText()
			        		+ "',applicantPast='" + Jpast.getText() + "',pastPerformance='" + Jhistory.getText()
			        		+ "',applicantManager=" + JManager.getText()
			        		+ " WHERE applicantName='" + name + "'";
			        stmt.execute(sql);
			        JOptionPane.showMessageDialog(null, "成功保存‘"+Jname.getText() + "’的信息");
			    
				} catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        System.out.println("写入错误");
			        System.exit(0);
			    }
			}else if (eventName.equals("关闭")) {
				应聘信息编辑界面.this.dispose();
				new 应聘信息显示();
			}
		}
		
	}
}
