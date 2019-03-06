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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import 界面.Recruit.buttonAction;
import 界面.Recruit.recruitPanel;

//个人信息界面（个人登录）
public class 个人信息界面 extends JFrame {
	public 个人信息界面(Integer number) {
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
		setTitle("个人信息");
		add(new PersonalDetailsPanel(number));
	}
	
	int nnumber;
	//两个文本框
	JTextField jtellnumberUP = new JTextField();
	JTextField JadressUP = new JTextField();
	
	//界面
	class PersonalDetailsPanel extends JPanel{
		//监听器重写
		ActionListener twoButtonListener = new buttonAction();
		

		
		public PersonalDetailsPanel(Integer number) {
			// TODO 自动生成的构造函数存根
			setLayout(new BorderLayout(10,10));
			
			JPanel centerPanel = new JPanel();
			//中间是8行2列的布局
			centerPanel.setLayout(new GridLayout(12,2,5,5));

			
			//定义查询出来的值
			int Jnum = number, Jgroup = 0;
			String Jname = "", Jsex = "", Jbrith = "", Jtell = "", 
			Jadress = "", Jtime = "", Jdept = "", Jpos = "", 
			Jrank = "", Jmoney = "";
			
			
			
			//从数据库获取个人信息
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
		        ResultSet rSet = stmt.executeQuery("SELECT y.clerkNO 编号,clerkName 姓名,clerkSex 性别,clerkBirth 出生年月,clerkContact 联系方式,clerkAddress 家庭住址,clerkEntry 入职时间,deptName 部门,y.teamNO 组别,posName 职位名称,posRank 职位等级,posWage 薪资水平 " + 
		        		"FROM 职员信息表 y,职位信息表 w,部门信息表 m "
		        		+ "WHERE y.posNO = w.posNO AND y.deptNO = m.deptNO AND y.clerkNO=" + String.valueOf(number));
		        rSet.next();
		        
		        Jnum = rSet.getInt("编号");
		        Jname = rSet.getString("姓名");
		        Jsex = rSet.getString("性别");
		        Jbrith = rSet.getString("出生年月");
		        Jtell = rSet.getString("联系方式");
				Jadress = rSet.getString("家庭住址");
				Jtime = rSet.getString("入职时间");
				Jdept = rSet.getString("部门");
				Jgroup = rSet.getInt("组别");
				Jpos = rSet.getString("职位名称");
				Jrank = rSet.getString("职位等级");
				Jmoney = rSet.getString("薪资水平");
				
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.print(e.getErrorCode());
		        System.out.println("读取错误");
		        System.exit(0);
		    }
			
			nnumber = number;
			//布局中间
			//编号
			addLabelToPanel("编号：", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(String.valueOf(Jnum), centerPanel, SwingConstants.LEFT);
			//姓名
			addLabelToPanel("姓名：", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jname, centerPanel, SwingConstants.LEFT);
			//性别
			addLabelToPanel("性别：", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jsex, centerPanel, SwingConstants.LEFT);
			//生日
			addLabelToPanel("出生年月日：", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jbrith, centerPanel, SwingConstants.LEFT);
			//电话(可改)
			addLabelToPanel("联系方式：", centerPanel, SwingConstants.RIGHT);
			jtellnumberUP.setText(Jtell);
			centerPanel.add(jtellnumberUP);
			//家庭住址（可改）
			addLabelToPanel("家庭住址：", centerPanel, SwingConstants.RIGHT);
			JadressUP.setText(Jadress);
			centerPanel.add(JadressUP);
			//入职时间
			addLabelToPanel("入职时间：", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jtime, centerPanel, SwingConstants.LEFT);
			//部门
			addLabelToPanel("部门：", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jdept, centerPanel, SwingConstants.LEFT);
			//组别
			addLabelToPanel("组别：", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(String.valueOf(Jgroup), centerPanel, SwingConstants.LEFT);
			//职位
			addLabelToPanel("职位：", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jpos, centerPanel, SwingConstants.LEFT);
			//经历
			addLabelToPanel("职位等级：", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jrank, centerPanel, SwingConstants.LEFT);
			//业绩
			addLabelToPanel("薪资水平：", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jmoney, centerPanel, SwingConstants.LEFT);
			

			//布局下面确定和取消
			JPanel underPanel = new JPanel();
			underPanel.setLayout(new FlowLayout());
			addButtonToPanel("保存", twoButtonListener, underPanel);
			addButtonToPanel("关闭", twoButtonListener, underPanel);
			
			//吧界面加入布局管理器
			add(centerPanel,BorderLayout.CENTER);
			add(underPanel,BorderLayout.SOUTH);
			
		}

	}
	
	
	public void addButtonToPanel(String name ,ActionListener listener, Container panel) {
		JButton button = new JButton(name);
		button.addActionListener(listener);
		panel.add(button);
	}
	
	
	public void addLabelToPanel(String name , Container panel, int duiqi) {
		JLabel label = new JLabel(name, duiqi);
		panel.add(label);
	}
	
	
	class buttonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO 自动生成的方法存根
			String eventName = event.getActionCommand();
			if (eventName.equals("保存")) {
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
			        
			        stmt.execute("UPDATE 职员信息表 SET clerkContact="
			        		+ jtellnumberUP.getText() + " WHERE clerkNO=" + 
					        String.valueOf(nnumber));
			        stmt.execute("UPDATE 职员信息表 SET clerkAddress='"
			        		+ JadressUP.getText() + "' WHERE clerkNO=" + 
			        		String.valueOf(nnumber));
			        JOptionPane.showMessageDialog(null, "保存成功");
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        JOptionPane.showMessageDialog(null, "修改错误");
			    }
			}else if (eventName.equals("关闭")) {
				个人信息界面.this.dispose();
				System.exit(0);
			}
		}
	}
}
