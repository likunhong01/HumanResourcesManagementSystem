package ����;

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



public class ӦƸ��Ϣ�༭���� extends JFrame {
	
//	public static void main(String[] args) {
//		new ӦƸ��Ϣ�༭����();
//	}
	
	public ӦƸ��Ϣ�༭����(String string) {
		// TODO �Զ����ɵĹ��캯�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
				
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width/2; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height/2; // ��ȡ��Ļ�ĸ�
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth-width/2 - 200, screenHeight-height/2 - 120);
		
		setSize(400, 400);
		setTitle("��ѯ�޸Ļ�ɾ��ӦƸ��Ա��Ϣ");
		add(new updateAndDeletePanel());
	}
	
	public ӦƸ��Ϣ�༭����() {
		// TODO �Զ����ɵĹ��캯�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
		
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width/2; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height/2; // ��ȡ��Ļ�ĸ�
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth-width/2 - 200, screenHeight-height/2 - 120);
		
		setSize(400, 400);
		setTitle("��ѯ�޸Ļ�ɾ��ӦƸ��Ա��Ϣ");
		add(new updateAndDeletePanel());
	}
	
	//�����ѯ���
	JTextField ��ѯnum = new JTextField("\t");
	
	
	//����һ����Ϣ
	JTextField Jnum = new JTextField();//
	JTextField Jdeptnum = new JTextField();//
	JTextField Jposnum = new JTextField();//
	JTextField Jname = new JTextField();//
	JTextField Jsex = new JTextField();//
	JTextField Jbrith = new JTextField();//
	JTextField Jtell = new JTextField();//
	JTextField JManager = new JTextField();//

	//��������
	JTextArea Jpast = new JTextArea();
	JTextArea Jhistory = new JTextArea();
	
	//����
	class updateAndDeletePanel extends JPanel {
		
		//��������д
		ActionListener ButtonListener = new buttonAction();
		
		public updateAndDeletePanel(String string) {
			��ѯnum.setText(string);
			
		}
		
		public updateAndDeletePanel() {
			// TODO �Զ����ɵĹ��캯�����
			setLayout(new BorderLayout(10,10));
			
			//�ϱ���壬˳���������3������
			JPanel NorthPanel = new JPanel();
			NorthPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			JLabel �����ű��� = new JLabel("��������:");
			NorthPanel.add(�����ű���);
			
			��ѯnum.setHorizontalAlignment(JTextField.LEFT);			
			NorthPanel.add(��ѯnum);
			
			//��ѯ��ť
			addButtonToPanel("��ѯ", ButtonListener, NorthPanel);
			//���������뱱��
			add(NorthPanel, BorderLayout.NORTH);
			
			
			//�м���11��2�еĲ���
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new GridLayout(9,2,5,5));
			
			//�����м�
			//���
			addLabelToPanel("������", centerPanel);
			centerPanel.add(Jname);
			//���ű��
			addLabelToPanel("�Ա�", centerPanel);
			centerPanel.add(Jsex);
			//ְλ���
			addLabelToPanel("�������£�", centerPanel);
			centerPanel.add(Jbrith);
			//���
			addLabelToPanel("�绰��", centerPanel);
			centerPanel.add(Jtell);
			//����
			addLabelToPanel("ӦƸ���ţ�", centerPanel);
			centerPanel.add(Jdeptnum);
			//�Ա�
			addLabelToPanel("ӦƸְλ��", centerPanel);
			centerPanel.add(Jposnum);
			//����
			addLabelToPanel("����������", centerPanel);
			centerPanel.add(Jpast);
			//�绰
			addLabelToPanel("��ʷҵ����", centerPanel);
			centerPanel.add(Jhistory);
			
			addLabelToPanel("���Ծ���", centerPanel);
			centerPanel.add(JManager);

			
			
			//��������ȷ����ȡ��
			JPanel underPanel = new JPanel();
			underPanel.setLayout(new FlowLayout());
			addButtonToPanel("ɾ��", ButtonListener, underPanel);
			addButtonToPanel("�޸Ĳ�����", ButtonListener, underPanel);
			addButtonToPanel("�ر�", ButtonListener, underPanel);
			
			//�ɽ�����벼�ֹ�����
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
	
	
	//���ύ��ȡ���ļ�����
	class buttonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO �Զ����ɵķ������
			String eventName = event.getActionCommand();
			if (eventName.equals("��ѯ")) {
				try {
					//�û���������
			        String connectDB = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;DatabaseName=������Դ����ϵͳ";
			        String user = "sa";
			        String password = "123456";
			        // �������ݿ����
			        Connection con = DriverManager.getConnection(connectDB, user,
			                password);
			        
			        //�ϳ�sql���(��ѯ���)
			        String name = ��ѯnum.getText();
			        if (name.charAt(0) == '\t') {
						name = name.substring(1,name.length());
					}
			        String sqlyuju = "SELECT * FROM ӦƸ��Ա�� WHERE applicantName='" + name + "'";
			        
			        System.out.println("��ѯ���Ϊ��" + sqlyuju);
			        
			        // ����SQL�������
			        Statement stmt = con.createStatement();
			        //��ò�ѯ����
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
			        System.out.println("��ѯ����");
			        System.exit(0);
			    }
			}else if (eventName.equals("ɾ��")) {
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
			        String name = ��ѯnum.getText();
			        if (name.charAt(0) == '\t') {
						name = name.substring(1,name.length());
					}
			        stmt.execute("DELETE FROM ӦƸ��Ա�� WHERE applicantName='" + name + "'");
			        
			        JOptionPane.showMessageDialog(null, "�ɹ�ɾ����"+Jname.getText() + "��");
			        
			        
				} catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        System.out.println("ɾ������");
			        System.exit(0);
			    }
				
				
				
			}else if (eventName.equals("�޸Ĳ�����")) {
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
			        //UPDATE �� SET clerkNO=2,clerkName='����',
			        String name = ��ѯnum.getText();
			        if (name.charAt(0) == '\t') {
						name = name.substring(1,name.length());
					}
			        String sql = "UPDATE ӦƸ��Ա�� SET applicantName='" + Jname.getText() + "',applicantSex='"
			        		+ Jsex.getText() + "',applicantBirth='" + Jbrith.getText() + "',applicantContact='" + Jtell.getText()
			        		+ "',applicantDept='" + Jdeptnum.getText() + "',applicantPos='" + Jposnum.getText()
			        		+ "',applicantPast='" + Jpast.getText() + "',pastPerformance='" + Jhistory.getText()
			        		+ "',applicantManager=" + JManager.getText()
			        		+ " WHERE applicantName='" + name + "'";
			        stmt.execute(sql);
			        JOptionPane.showMessageDialog(null, "�ɹ����桮"+Jname.getText() + "������Ϣ");
			    
				} catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        System.out.println("д�����");
			        System.exit(0);
			    }
			}else if (eventName.equals("�ر�")) {
				ӦƸ��Ϣ�༭����.this.dispose();
				new ӦƸ��Ϣ��ʾ();
			}
		}
		
	}
}
