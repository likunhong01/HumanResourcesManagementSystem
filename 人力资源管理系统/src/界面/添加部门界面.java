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



public class 添加部门界面 extends JFrame {
	
//	public static void main(String[] args) {
//		new 添加部门界面();
//	}
	
	public 添加部门界面() {
		// TODO 自动生成的构造函数存根
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
		
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width/2; // 获取屏幕的宽
		int screenHeight = screenSize.height/2; // 获取屏幕的高
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth-width/2 - 100, screenHeight-height/2 - 120);
		
		setSize(350, 250);
		setTitle("编辑部门信息");
		add(new recruitPanel());
	}
	
	
	//定义一堆信息
	JTextField Jdeptnum = new JTextField();//
	JTextField Jclerknum = new JTextField();//
	JTextField Jname = new JTextField();//
	//部门职务
	JTextArea Jduty = new JTextArea();
	
	
	//界面
	class recruitPanel extends JPanel {
		
		//监听器重写
		ActionListener twoButtonListener = new buttonAction();
		
		public recruitPanel() {
			// TODO 自动生成的构造函数存根
			setLayout(new BorderLayout(20,20));
			
			JPanel centerPanel = new JPanel();
			//中间是11行2列的布局
			centerPanel.setLayout(new GridLayout(4,2,2,8));
			
			//布局中间
			//部门编号
			addLabelToPanel("部门编号：", centerPanel);
			centerPanel.add(Jdeptnum);
			//部门经理职员号
			addLabelToPanel("经理编号：", centerPanel);
			centerPanel.add(Jclerknum);
			//部门名称
			addLabelToPanel("部门名称：", centerPanel);
			centerPanel.add(Jname);
			//部门职务
			addLabelToPanel("部门职务：", centerPanel);
			centerPanel.add(Jduty);
			
			
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
			        
			        String sqlyuju1 = "INSERT INTO 部门信息表 VALUES";
			        String yuju = "(" + Jdeptnum.getText() + ",'" + Jname.getText() + "'," + Jclerknum.getText() + ",'" + Jduty.getText() + "')";
			        sqlyuju1 += yuju;
			        
			        String sqlyuju2 = "INSERT INTO 经理信息表(clerkNO,deptNO) VALUES";
			        String yuju2 = "(" + Jclerknum.getText() + "," + Jdeptnum.getText() + ")";
			        sqlyuju2 += yuju2;
			        
			        String sqlyuju3 ="UPDATE 职员信息表 SET deptNO=" + Jdeptnum.getText() + " WHERE clerkNO=" +Jclerknum.getText() ;
			        // 创建SQL命令对象
			        Statement stmt = con.createStatement();
			        
			        //新增职员信息
			        stmt.execute(sqlyuju3);
			        stmt.execute(sqlyuju2);
			        stmt.execute(sqlyuju1);
			        

			        JOptionPane.showMessageDialog(null, "成功添加‘"+Jname.getText() + "’部门");
			        
			        添加部门界面.this.dispose();
			        new 部门信息显示();
			        System.out.println("成功保存" + yuju);
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        
			        JOptionPane.showMessageDialog(null, "添加失败‘"+Jname.getText() + "’部门");
			    }
				
			}else if (eventName.equals("取消")) {
				//关掉这个界面
				添加部门界面.this.dispose();
				new 部门信息显示();
			}
		}
		
	}
}
