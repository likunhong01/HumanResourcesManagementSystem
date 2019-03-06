package 界面;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class 经理界面显示 {
	
	public 经理界面显示 (){
		JFrame jf = new JFrame("经理信息一览表");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 表格中显示的数据、
        Object rows[][] = null;
        String columns[] = { "经理姓名", "所属部门", "经理备注" };
        
        Vector neirong = new Vector();
        int row = 0;
        
        //从数据库获取经理表格
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
	        String sql = "SELECT clerkName,deptName,managerDetail FROM 经理信息表 m,职员信息表 y,部门信息表 b WHERE m.clerkNO=y.clerkNO and m.deptNO=b.deptNO";
	        
	        ResultSet rSet = stmt.executeQuery(sql);
	        rSet.next();
	        
	        //获取每列名称
	        ResultSetMetaData rData = rSet.getMetaData();
	        
	        do {
				neirong.addAll(getNextRow(rSet, rData));
				row++;
			} while (rSet.next());
	        
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.print(e.getErrorCode());
	        System.out.println("写入错误");
	        System.exit(0);
	    }
        
        int k = 0;
        String[][] manager信息 = new String[row][columns.length];
        for (int i = 0; i < manager信息.length; i++) {
			for (int j = 0; j < manager信息[0].length; j++) {
				manager信息[i][j] = (String) neirong.get(k);
				k++;
			}
		}
        jf.setLayout(new BorderLayout(20,20));
        
        TableModel model = new DefaultTableModel(manager信息, columns);
        JTable table = new JTable(model);
//        RowSorter sorter = new TableRowSorter(model);
//        table.setRowSorter(sorter);
        JScrollPane pane = new JScrollPane(table);
        jf.add(pane, BorderLayout.CENTER);
        jf.setLocation(400,175);
        jf.setSize(400, 400);
        jf.setVisible(true);
        
        
        //最下面滑板是flowlayout
        JPanel southpanel = new JPanel();
        southpanel.setLayout(new FlowLayout());
        JButton 添加Button = new JButton("添加经理");
        JButton 关闭Button = new JButton("关闭");
        southpanel.add(添加Button);
        southpanel.add(关闭Button);
        jf.add(southpanel,BorderLayout.SOUTH);
        
        添加Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				jf.dispose();
				new 添加经理界面();
			}
		});
        
        关闭Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				jf.dispose();
				new 管理员主界面();
			}
		});
	}
	
	public Vector getNextRow (ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
	    Vector currentRow = new Vector(); // 定义一个向量,用于存放记录
	    
	    for (int i = 1; i <= rsmd.getColumnCount(); ++i)
	        currentRow.addElement(rs.getString(i)); // 获取记录
	    
	    return currentRow; // 返回记录
	}
	
	
	public static void main(String[] args) {
		  new 经理界面显示();
	}

}
