package ����;

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


public class ����Ա������ extends JFrame {
	
//	public static void main(String[] args) {
//		new ����Ա������();
//	}
	
	public ����Ա������()  {
		// TODO �Զ����ɵĹ��캯�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width/2; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height/2; // ��ȡ��Ļ�ĸ�
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth-width/2 - 500, screenHeight-height/2 - 300);
		
		setSize(1000, 600);
		setTitle("����Ա�鿴����");
		add(new morderGui());
		
	}
	
	class morderGui extends JPanel {
		
		
		TextField serchNum = new TextField(10);
		
		
		public morderGui() {
			// TODO �Զ����ɵĹ��캯�����
			
			setLayout(null);
			setBackground(Color.LIGHT_GRAY);
			
			//����ÿ������
			Vector<String> colName = new Vector<String>();
			colName.add("���");
			colName.add("����");
			colName.add("�Ա�");
			colName.add("����������");
			colName.add("��ϵ��ʽ");
			colName.add("��ͥסַ");
			colName.add("��ְʱ��");
			colName.add("����");
			colName.add("���");
			colName.add("ְλ����");
			colName.add("ְλ�ȼ�");
			colName.add("н��ˮƽ");
			//���������������
			Vector rows = new Vector();
			
			//�������ݿ��ȡ��������
			int row = 0;
			int col = 0;
			
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
		        String sql = "SELECT y.clerkNO ���,clerkName ����,clerkSex �Ա�,"
		        		+ "clerkBirth ��������,clerkContact ��ϵ��ʽ,clerkAddress ��ͥסַ,"
		        		+ "clerkEntry ��ְʱ��,deptName ����,y.teamNO ���,posName ְλ����,"
		        		+ "posRank ְλ�ȼ�,posWage н��ˮƽ FROM ְԱ��Ϣ�� y,ְλ��Ϣ�� w," 
		        		+ "������Ϣ�� m WHERE y.posNO = w.posNO and "
		        		+ "y.deptNO = m.deptNO";
		        System.out.println(sql);
		        
		        ResultSet rSet = stmt.executeQuery(sql);
		        rSet.next();
		        
		        //��ȡÿ������
		        ResultSetMetaData rData = rSet.getMetaData();
		        col = rData.getColumnCount();
		        
		        do {
					rows.addAll(getNextRow(rSet, rData));
					row++;
				} while (rSet.next());
		        
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.print(e.getErrorCode());
		        System.out.println("д�����");
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
			
			//������
			JPanel p0 =new JPanel(); 
			JTable tf56 = new JTable(neirong,colname);
			JScrollPane tf57 = new JScrollPane(tf56);;
			p0.setSize(800, 300);
			p0.setLocation(100, 200);
			p0.setLayout(new BorderLayout());
			p0.add(tf57);  
			p0.setVisible(true);
			add(p0);
  
			//���尴ť
			//���ˢ�°�ť
			Panel ˢ��panel = new Panel(new BorderLayout(0,8));
			ˢ��panel.setLocation(850, 160);
			ˢ��panel.setSize(50, 30);
			Button ˢ�� = new Button("ˢ��");
			ˢ��panel.add(ˢ��);
			add(ˢ��panel);
			
			//���Ա����ť
			Panel ���Ա��panel = new Panel(new BorderLayout(0,8));
			���Ա��panel.setLocation(250, 80);
			���Ա��panel.setSize(50, 30);
			Button ���ְԱ = new Button("���ְԱ");
			���Ա��panel.add(���ְԱ);
			add(���Ա��panel);
			
			//�༭ְԱ��ť
			Panel p2 = new Panel(new BorderLayout(0,8));
			p2.setLocation(320, 80);
			p2.setSize(50, 30);
			Button �༭ְԱ = new Button("�༭ְԱ");
			p2.add(�༭ְԱ);
			add(p2);
			
			//����ť
			Panel p4 = new Panel(new BorderLayout(0,8));
			p4.setLocation(390, 80);
			p4.setSize(50, 30);
			Button ���� = new Button("����");
			p4.add(����);
			add(p4);
			
			//���Ű�ť
			Panel p5 = new Panel(new BorderLayout(0,8));
			p5.setLocation(460, 80);
			p5.setSize(50, 30);
			Button ���� = new Button("����");
			p5.add(����);
			add(p5);
			
			//���ť
			Panel p6 = new Panel(new BorderLayout(0,8));
			p6.setLocation(530, 80);
			p6.setSize(50, 30);
			Button ��� = new Button("���");
			p6.add(���);
			add(p6);
			
			//ְλ��ť
			Panel p7 = new Panel(new BorderLayout(0,8));
			p7.setLocation(600, 80);
			p7.setSize(50, 30);
			Button ְλ = new Button("ְλ");
			p7.add(ְλ);
			add(p7);
			
			//�û���Ϣ
			Panel p8 = new Panel(new BorderLayout(0,8));
			p8.setLocation(670, 80);
			p8.setSize(50, 30);
			Button �û���Ϣ = new Button("�û���Ϣ");
			p8.add(�û���Ϣ);
			add(p8);
			
			//ӦƸ��Ա
			Panel p9 = new Panel(new BorderLayout(0,8));
			p9.setLocation(740, 80);
			p9.setSize(50, 30);
			Button ӦƸ��Ա = new Button("ӦƸ��Ա");
			p9.add(ӦƸ��Ա);
			add(p9);
			
			//��ѯ��
			Panel p1 = new Panel();
			p1.setLocation(750, 40);
			p1.setSize(200,80);
			add(p1);
			Button ��ѯ = new Button("��ѯ");
			p1.add(��ѯ);
			
			//����༭��
			
			p1.add(serchNum);
			  
			//��ť�¼�
			
			ˢ��.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO �Զ����ɵķ������
					����Ա������.this.dispose();
					new ����Ա������();
				}
			});
			
			��ѯ.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO �Զ����ɵķ������
					����Ա������.this.dispose();
					new �޸ĺ�ɾ����Ա����(serchNum.getText());
				}
			});
			
			//���ְԱ�¼�
			���ְԱ.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO �Զ����ɵķ������
					����Ա������.this.dispose();
					new ���Ա������();
				}
			});
			
			
			  //�༭ְԱ�¼�
			�༭ְԱ.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO �Զ����ɵķ������
					����Ա������.this.dispose();
					new �޸ĺ�ɾ����Ա����();
				}
				  
			  });
			  
			  //�����¼�
			  ����.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					����Ա������.this.dispose();
					new ���������ʾ();
				}
				  
			  });
			  
			  //���Ű�ť�¼�
			  ����.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					����Ա������.this.dispose();
					new ������Ϣ��ʾ();
				}
				  
			  });
			  
			  //���ť�¼�
			  ���.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					
				}
				  
			  });
			  
			  //ְλ��ť�¼�
			  ְλ.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					����Ա������.this.dispose();
					new ְλ��Ϣ��ʾ();
				}
				  
			  });
			  
			  //�û���Ϣ��ť�¼�
			  �û���Ϣ.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					
				}
				  
			  });
			  
			  //ӦƸ��Ա��ť��Ϣ
			  ӦƸ��Ա.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					����Ա������.this.dispose();
					new ӦƸ��Ϣ��ʾ();
				}
				  
			  });
			  //�˳����ڼ����� 
			  addWindowListener(new WindowAdapter() {
				  public void windowClosing(WindowEvent e) {
					  System.exit(0);
				  }
			  });
			  setVisible(true);
		}
	}
	
	
	public Vector getNextRow (ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
	    Vector currentRow = new Vector(); // ����һ������,���ڴ�ż�¼
	    
	    for (int i = 1; i <= rsmd.getColumnCount(); ++i)
	        currentRow.addElement(rs.getString(i)); // ��ȡ��¼
	    
	    return currentRow; // ���ؼ�¼
	}

}
