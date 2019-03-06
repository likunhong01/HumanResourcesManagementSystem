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



public class 添加经理界面 extends JFrame {
//	
//	public static void main(String[] args) {
//		new 添加经理界面();
//	}
	
	public 添加经理界面() {
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
		
		setSize(400, 250);
		setTitle("填写新经理信息");
		add(new recruitPanel());
	}
	
	
	//定义一堆信息
	JTextField clerknum = new JTextField();//经理编号
	JTextField deptnum = new JTextField();//经理部门
	JTextArea managerdetail = new JTextArea();//工作经历
	
	
	//界面
	class recruitPanel extends JPanel {
		
		//监听器重写
		ActionListener twoButtonListener = new buttonAction();
		
		public recruitPanel() {
			// TODO 自动生成的构造函数存根
			setLayout(new BorderLayout(20,20));
			
			JPanel centerPanel = new JPanel();
			//中间是11行2列的布局
			centerPanel.setLayout(new GridLayout(3,2,20,20));
			
			//布局中间
			//编号
			addLabelToPanel("经理编号 ：", centerPanel);
			centerPanel.add(clerknum);
			//部门编号
			addLabelToPanel("部门编号：", centerPanel);
			centerPanel.add(deptnum);
			//职位编号
			addLabelToPanel("经理备注：", centerPanel);
			centerPanel.add(managerdetail);
			
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
			        
			        //合成sql语句INSERT INTO 经理信息表 VALUES(1,1,'协调员工');
			        String sqlyuju1 = "INSERT INTO 经理信息表 VALUES";
			        String yuju = "(" + clerknum.getText() + "," + deptnum.getText() + ",'" + managerdetail.getText() + "')";
			        sqlyuju1 += yuju;
			        
			        String sqlyuju2 ="UPDATE 部门信息表 SET deptMar=" + clerknum.getText() + " WHERE deptNO=" +deptnum.getText() ;
			        
			        String sqlyuju3 ="UPDATE 职员信息表 SET deptNO=" + deptnum.getText() + " WHERE clerkNO=" +clerknum.getText() ;
			        
			        // 创建SQL命令对象
			        Statement stmt = con.createStatement();
			        
			        //新增职员
			        stmt.execute(sqlyuju1);
			        stmt.execute(sqlyuju2);
			        stmt.execute(sqlyuju3);
			        
			        

			        JOptionPane.showMessageDialog(null, "成功添加‘"+clerknum.getText() + "’号经理");
			        
			        添加经理界面.this.dispose();
			        new 经理界面显示();
			        System.out.println("成功保存" + yuju);
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        JOptionPane.showMessageDialog(null, "添加失败");
			    }
			}else if (eventName.equals("取消")) {
				//关掉这个界面
				添加经理界面.this.dispose();
				//打开上一个界面
				new 经理界面显示();
			}
		}
		
	}
}