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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class 添加员工界面 extends JFrame {
	
//	public static void main(String[] args) {
//		new 添加员工界面();
//	}
	
	public 添加员工界面() {
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
		setTitle("填写新添员工信息");
		add(new recruitPanel());
	}
	
	
	//定义一堆信息
	JTextField Jnum = new JTextField();//
	JTextField Jdeptnum = new JTextField();//
	JTextField Jposnum = new JTextField();//
	JTextField Jgroup = new JTextField();//
	JTextField Jname = new JTextField();//
	JTextField Jsex = new JTextField();//
	JTextField Jbrith = new JTextField();//
	JTextField Jtell = new JTextField();//
	JTextField Jtime = new JTextField();//
	JTextField Jadress = new JTextField();//
	//工作经历
	JTextArea Jpast = new JTextArea();
	
	
	//界面
	class recruitPanel extends JPanel {
		
		//监听器重写
		ActionListener twoButtonListener = new buttonAction();
		
		public recruitPanel() {
			// TODO 自动生成的构造函数存根
			setLayout(new BorderLayout(10,10));
			
			JPanel centerPanel = new JPanel();
			//中间是11行2列的布局
			centerPanel.setLayout(new GridLayout(11,2,5,5));
			
			//布局中间
			//编号
			addLabelToPanel("编号：", centerPanel);
			centerPanel.add(Jnum);
			addLabelToPanel("部门编号：", centerPanel);
			centerPanel.add(Jdeptnum);
			addLabelToPanel("职位编号：", centerPanel);
			centerPanel.add(Jposnum);
			addLabelToPanel("组别：", centerPanel);
			centerPanel.add(Jgroup);
			addLabelToPanel("姓名：", centerPanel);
			centerPanel.add(Jname);
			addLabelToPanel("性别：", centerPanel);
			centerPanel.add(Jsex);
			addLabelToPanel("出生年月日：", centerPanel);
			centerPanel.add(Jbrith);
			addLabelToPanel("联系方式：", centerPanel);
			centerPanel.add(Jtell);
			addLabelToPanel("入职时间：", centerPanel);
			centerPanel.add(Jtime);
			addLabelToPanel("家庭住址：", centerPanel);
			centerPanel.add(Jadress);
			addLabelToPanel("工作历史：", centerPanel);
			centerPanel.add(Jpast);
			
			
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
			        String sqlyuju1 = "INSERT INTO 职员信息表(clerkNO,clerkName,deptNO,posNO,teamNO,clerkSex,clerkBirth,clerkEntry,clerkContact,clerkAddress,clerkPast) VALUES";
			        String yuju = "(" + Jnum.getText() + ",'" + Jname.getText() + "'," + Jdeptnum.getText() + "," + Jposnum.getText() + "," + Jgroup.getText() + ",'" + Jsex.getText() + "','" + Jbrith.getText() + "','" + Jtime.getText() + "','" + Jtell.getText() + "','" + Jadress.getText() + "','" + Jpast.getText() + "')";
			        sqlyuju1 += yuju;
			        
			        
			        String sqlyuju2 = "INSERT INTO 用户信息表 " +
			        		"VALUES(" + Jnum.getText() + ",'" + Jtell.getText().substring(0,6)
			        		+ "');";
			        
			        // 创建SQL命令对象
			        Statement stmt = con.createStatement();
			        
			        //新增职员信息
			        stmt.execute(sqlyuju1);
			        
			        //新增职员用户登录
			        stmt.execute(sqlyuju2);
			        
			        JOptionPane.showMessageDialog(null, "成功添加‘"+Jname.getText() + "’");
			        添加员工界面.this.dispose();
			        new 管理员主界面();
			        System.out.println("成功保存" + yuju);
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        JOptionPane.showMessageDialog(null, "添加失败，存在相同编号");
			    }
			}else if (eventName.equals("取消")) {
				//关掉这个界面
				添加员工界面.this.dispose();
				//打开上一个界面
				new 管理员主界面();
			}
		}
		
	}
}
