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



public class 添加职位界面 extends JFrame {
	
//	public static void main(String[] args) {
//		new 添加职位界面();
//	}
	
	public 添加职位界面() {
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
		
		setSize(400, 350);
		setTitle("填写信息");
		add(new recruitPanel());
	}

	//定义一堆信息
	JTextField posnum = new JTextField();//
	JTextField deptnum = new JTextField();//
	JTextField posname = new JTextField();//
	JTextField posrank = new JTextField();//
	JTextField posduty = new JTextField();//
	JTextField poswage = new JTextField();//
	JTextField posdetail = new JTextField();//
	JTextField workload = new JTextField();//
	
	
	//界面
	class recruitPanel extends JPanel {
		
		//监听器重写
		ActionListener twoButtonListener = new buttonAction();
		
		public recruitPanel() {
			// TODO 自动生成的构造函数存根
			setLayout(new BorderLayout(10,10));
			
			JPanel centerPanel = new JPanel();
			//中间是11行2列的布局
			centerPanel.setLayout(new GridLayout(8,2,5,5));
			
			//布局中间
			//编号
			addLabelToPanel("职位编号：", centerPanel);
			centerPanel.add(posnum);
			//部门编号
			addLabelToPanel("部门编号：", centerPanel);
			centerPanel.add(deptnum);
			//职位编号
			addLabelToPanel("职位名称：", centerPanel);
			centerPanel.add(posname);
			//组别
			addLabelToPanel("职位等级：", centerPanel);
			centerPanel.add(posrank);
			//姓名
			addLabelToPanel("职位职务：", centerPanel);
			centerPanel.add(posduty);
			//性别
			addLabelToPanel("薪资水平：", centerPanel);
			centerPanel.add(poswage);
			//生日
			addLabelToPanel("职位备注：", centerPanel);
			centerPanel.add(posdetail);
			//电话
			addLabelToPanel("工作量：", centerPanel);
			centerPanel.add(workload);
			//入职时间
			
			
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
			        
			        //合成sql语句INSERT INTO 职位信息表 VALUES(102,1,'java研发','高级',15000,'算法编码','8','五');
			        String sqlyuju1 = "INSERT INTO 职位信息表 VALUES";
			        String yuju = "(" + posnum.getText() + "," + deptnum.getText() + ",'" + posname.getText() + "','" + posrank.getText() + "'," + poswage.getText() + ",'" + posduty.getText() + "','" + workload.getText() + "','" + posdetail.getText() + "')";
			        sqlyuju1 += yuju;
			        
			        // 创建SQL命令对象
			        Statement stmt = con.createStatement();
			        
			        //新增职位信息
			        stmt.execute(sqlyuju1);
			        

			        JOptionPane.showMessageDialog(null, "成功添加‘"+posname.getText() + "’职位");
			        
			        添加职位界面.this.dispose();
			        new 职位信息显示();
			        System.out.println("成功保存" + yuju);
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        JOptionPane.showMessageDialog(null, "添加失败");
			    }
			}else if (eventName.equals("取消")) {
				//关掉这个界面
				添加职位界面.this.dispose();
				new 职位信息显示();
			}
		}
		
	}
}