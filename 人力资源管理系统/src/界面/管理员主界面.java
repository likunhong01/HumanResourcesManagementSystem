package 界面;

import java.awt.event.*;
import java.security.KeyRep;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableColumn;


public class 管理员主界面 extends JFrame {
	
//	public static void main(String[] args) {
//		new 管理员主界面();
//	}
	
	public 管理员主界面()  {
		// TODO 自动生成的构造函数存根
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width/2; // 获取屏幕的宽
		int screenHeight = screenSize.height/2; // 获取屏幕的高
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth-width/2 - 500, screenHeight-height/2 - 300);
		
		setSize(1000, 600);
		setTitle("管理员查看界面");
		add(new morderGui());
		
	}
	
	class morderGui extends JPanel {
		
		
		TextField serchNum = new TextField(10);
		
		
		public morderGui() {
			// TODO 自动生成的构造函数存根
			
			setLayout(null);
			setBackground(Color.LIGHT_GRAY);
			
			//定义每列名称
			Vector<String> colName = new Vector<String>();
			colName.add("编号");
			colName.add("姓名");
			colName.add("性别");
			colName.add("出生年月日");
			colName.add("联系方式");
			colName.add("家庭住址");
			colName.add("入职时间");
			colName.add("部门");
			colName.add("组别");
			colName.add("职位名称");
			colName.add("职位等级");
			colName.add("薪资水平");
			//定义表内数据向量
			Vector rows = new Vector();
			
			//连接数据库获取表内数据
			int row = 0;
			int col = 0;
			
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
		        String sql = "SELECT y.clerkNO 编号,clerkName 姓名,clerkSex 性别,"
		        		+ "clerkBirth 出生年月,clerkContact 联系方式,clerkAddress 家庭住址,"
		        		+ "clerkEntry 入职时间,deptName 部门,y.teamNO 组别,posName 职位名称,"
		        		+ "posRank 职位等级,posWage 薪资水平 FROM 职员信息表 y,职位信息表 w," 
		        		+ "部门信息表 m WHERE y.posNO = w.posNO and "
		        		+ "y.deptNO = m.deptNO";
		        System.out.println(sql);
		        
		        ResultSet rSet = stmt.executeQuery(sql);
		        rSet.next();
		        
		        //获取每列名称
		        ResultSetMetaData rData = rSet.getMetaData();
		        col = rData.getColumnCount();
		        
		        do {
					rows.addAll(getNextRow(rSet, rData));
					row++;
				} while (rSet.next());
		        
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.print(e.getErrorCode());
		        System.out.println("写入错误");
		        System.exit(0);
		    }
			
			System.out.println(colName);
			String[] colname = new String[col];
			String[][] neirong = new String[row][col];
			for (int i = 0; i < colname.length; i++) {
				colname[i] = colName.get(i);
			}
			
			int k = 0;
			for (int i = 0; i < neirong.length; i++) {
				for (int j = 0; j < neirong[0].length; j++) {
					neirong[i][j] = (String) rows.get(k);
					k++;
				}
			}

			
			setLayout(new BorderLayout(10,10));
			
			//定义表格
			JPanel p0 =new JPanel(); 
			JTable tf56 = new JTable(neirong,colname);
			JScrollPane tf57 = new JScrollPane(tf56);;
			p0.setSize(800, 300);
			p0.setLocation(100, 200);
			p0.setLayout(new BorderLayout());
			p0.add(tf57);  
			p0.setVisible(true);
			add(p0);
  
			//定义按钮
			//添加刷新按钮
			Panel 刷新panel = new Panel(new BorderLayout(0,8));
			刷新panel.setLocation(850, 160);
			刷新panel.setSize(50, 30);
			Button 刷新 = new Button("刷新");
			刷新panel.add(刷新);
			add(刷新panel);
			
			//添加员工按钮
			Panel 添加员工panel = new Panel(new BorderLayout(0,8));
			添加员工panel.setLocation(250, 80);
			添加员工panel.setSize(50, 30);
			Button 添加职员 = new Button("添加职员");
			添加员工panel.add(添加职员);
			add(添加员工panel);
			
			//编辑职员按钮
			Panel p2 = new Panel(new BorderLayout(0,8));
			p2.setLocation(320, 80);
			p2.setSize(50, 30);
			Button 编辑职员 = new Button("编辑职员");
			p2.add(编辑职员);
			add(p2);
			
			//经理按钮
			Panel p4 = new Panel(new BorderLayout(0,8));
			p4.setLocation(390, 80);
			p4.setSize(50, 30);
			Button 经理 = new Button("经理");
			p4.add(经理);
			add(p4);
			
			//部门按钮
			Panel p5 = new Panel(new BorderLayout(0,8));
			p5.setLocation(460, 80);
			p5.setSize(50, 30);
			Button 部门 = new Button("部门");
			p5.add(部门);
			add(p5);
			
			//组别按钮
			Panel p6 = new Panel(new BorderLayout(0,8));
			p6.setLocation(530, 80);
			p6.setSize(50, 30);
			Button 组别 = new Button("组别");
			p6.add(组别);
			add(p6);
			
			//职位按钮
			Panel p7 = new Panel(new BorderLayout(0,8));
			p7.setLocation(600, 80);
			p7.setSize(50, 30);
			Button 职位 = new Button("职位");
			p7.add(职位);
			add(p7);
			
			//用户信息
			Panel p8 = new Panel(new BorderLayout(0,8));
			p8.setLocation(670, 80);
			p8.setSize(50, 30);
			Button 用户信息 = new Button("用户信息");
			p8.add(用户信息);
			add(p8);
			
			//应聘人员
			Panel p9 = new Panel(new BorderLayout(0,8));
			p9.setLocation(740, 80);
			p9.setSize(50, 30);
			Button 应聘人员 = new Button("应聘人员");
			p9.add(应聘人员);
			add(p9);
			
			//查询框
			Panel p1 = new Panel();
			p1.setLocation(750, 40);
			p1.setSize(200,80);
			add(p1);
			Button 查询 = new Button("查询");
			p1.add(查询);
			
			//定义编辑框
			
			p1.add(serchNum);
			  
			//按钮事件
			
			刷新.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO 自动生成的方法存根
					管理员主界面.this.dispose();
					new 管理员主界面();
				}
			});
			
			查询.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO 自动生成的方法存根
					管理员主界面.this.dispose();
					new 修改和删除人员界面(serchNum.getText());
				}
			});
			
			//添加职员事件
			添加职员.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO 自动生成的方法存根
					管理员主界面.this.dispose();
					new 添加员工界面();
				}
			});
			
			
			  //编辑职员事件
			编辑职员.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO 自动生成的方法存根
					管理员主界面.this.dispose();
					new 修改和删除人员界面();
				}
				  
			  });
			  
			  //经理事件
			  经理.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					管理员主界面.this.dispose();
					new 经理界面显示();
				}
				  
			  });
			  
			  //部门按钮事件
			  部门.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					管理员主界面.this.dispose();
					new 部门信息显示();
				}
				  
			  });
			  
			  //组别按钮事件
			  组别.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					
				}
				  
			  });
			  
			  //职位按钮事件
			  职位.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					管理员主界面.this.dispose();
					new 职位信息显示();
				}
				  
			  });
			  
			  //用户信息按钮事件
			  用户信息.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					
				}
				  
			  });
			  
			  //应聘人员按钮信息
			  应聘人员.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					管理员主界面.this.dispose();
					new 应聘信息显示();
				}
				  
			  });
			  //退出窗口监听器 
			  addWindowListener(new WindowAdapter() {
				  public void windowClosing(WindowEvent e) {
					  System.exit(0);
				  }
			  });
			  setVisible(true);
		}
	}
	
	
	public Vector getNextRow (ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
	    Vector currentRow = new Vector(); // 定义一个向量,用于存放记录
	    
	    for (int i = 1; i <= rsmd.getColumnCount(); ++i)
	        currentRow.addElement(rs.getString(i)); // 获取记录
	    
	    return currentRow; // 返回记录
	}

}
