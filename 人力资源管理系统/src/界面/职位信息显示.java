package ����;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
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

public class ְλ��Ϣ��ʾ {
	public ְλ��Ϣ��ʾ(){
		  JFrame jf = new JFrame("ְλ��Ϣһ����");
	        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        // �������ʾ�����ݡ�
	        Object rows[][] = null;
	        String columns[] = { "ְλ���", "���ű��", "ְλ����","ְλ�ȼ�","ְλְ��","н��ˮƽ","ְλ��ע","������" };
	        
	        Vector neirong = new Vector();
	        int row = 0;
	        
	        //�����ݿ��ȡ������
	        try {
				//�û���������
		        String connectDB = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;DatabaseName=������Դ����ϵͳ";
		        String user = "sa";
		        String password = "123456";
		        // �������ݿ����
		        Connection con = DriverManager.getConnection(connectDB, user,
		                password);
		        
		        // ����SQL�������
		        Statement stmt = con.createStatement();
		        //���Ͳ�ѯ���
		        String sql = "SELECT * FROM ְλ��Ϣ��";
		        
		        ResultSet rSet = stmt.executeQuery(sql);
		        rSet.next();
		        
		        //��ȡÿ������
		        ResultSetMetaData rData = rSet.getMetaData();
		        
		        do {
					neirong.addAll(getNextRow(rSet, rData));
					row++;
				} while (rSet.next());
		        System.out.println(neirong);
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.print(e.getErrorCode());
		        System.out.println("д�����");
		        System.exit(0);
		    }
	        
	        int k = 0;
	        String[][] manager��Ϣ = new String[row][columns.length];
	        for (int i = 0; i < manager��Ϣ.length; i++) {
				for (int j = 0; j < manager��Ϣ[0].length; j++) {
					manager��Ϣ[i][j] = (String) neirong.get(k);
					k++;
				}
			}
	        
	        
	        
	        
	        
	        TableModel model = new DefaultTableModel(manager��Ϣ,columns);
	        JTable table = new JTable(model);
	        JScrollPane pane = new JScrollPane(table);
	        jf.add(pane, BorderLayout.CENTER);
	        
	        jf.setLocation(250, 150);
	        jf.setSize(800, 400);
	        jf.setVisible(true);

	        
	        
	      //�����滬����flowlayout
	        JPanel southpanel = new JPanel();
	        southpanel.setLayout(new FlowLayout());
	        JButton ���Button = new JButton("���ְλ");
	        JButton �ر�Button = new JButton("�ر�");
	        southpanel.add(���Button);
	        southpanel.add(�ر�Button);
	        jf.add(southpanel,BorderLayout.SOUTH);
	        
	        ���Button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO �Զ����ɵķ������
					jf.dispose();
					new ���ְλ����();
				}
			});
	        
	        �ر�Button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO �Զ����ɵķ������
					jf.dispose();
					new ����Ա������();
				}
			});
	}
	
	
	public Vector getNextRow (ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
	    Vector currentRow = new Vector(); // ����һ������,���ڴ�ż�¼
	    
	    for (int i = 1; i <= rsmd.getColumnCount(); ++i)
	        currentRow.addElement(rs.getString(i)); // ��ȡ��¼
	    
	    return currentRow; // ���ؼ�¼
	}
	
	
	public static void main(String[] args) {
		  new ְλ��Ϣ��ʾ();
	}

}
