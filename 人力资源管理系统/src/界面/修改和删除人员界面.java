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



public class �޸ĺ�ɾ����Ա���� extends JFrame {
	
//	public static void main(String[] args) {
//		new �޸ĺ�ɾ����Ա����();
//	}
	
	public �޸ĺ�ɾ����Ա����(String string) {
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
		setTitle("��ѯ�޸Ļ�ɾ��Ա����Ϣ");
		add(new updateAndDeletePanel());
	}
	
	public �޸ĺ�ɾ����Ա����() {
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
		setTitle("��ѯ�޸Ļ�ɾ��Ա����Ϣ");
		add(new updateAndDeletePanel());
	}
	
	//�����ѯ���
	JTextField ��ѯnum = new JTextField("\t");
	
	
	//����һ����Ϣ
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
	//��������
	JTextArea Jpast = new JTextArea();
	
	
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
			JLabel �����ű��� = new JLabel("������:");
			NorthPanel.add(�����ű���);
			
			��ѯnum.setHorizontalAlignment(JTextField.LEFT);			
			NorthPanel.add(��ѯnum);
			
			//��ѯ��ť
			addButtonToPanel("��ѯ", ButtonListener, NorthPanel);
			//���������뱱��
			add(NorthPanel, BorderLayout.NORTH);
			
			
			//�м���11��2�еĲ���
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new GridLayout(11,2,5,5));
			
			//�����м�
			//���
			addLabelToPanel("��ţ�", centerPanel);
			centerPanel.add(Jnum);
			//���ű��
			addLabelToPanel("���ű�ţ�", centerPanel);
			centerPanel.add(Jdeptnum);
			//ְλ���
			addLabelToPanel("ְλ��ţ�", centerPanel);
			centerPanel.add(Jposnum);
			//���
			addLabelToPanel("���", centerPanel);
			centerPanel.add(Jgroup);
			//����
			addLabelToPanel("������", centerPanel);
			centerPanel.add(Jname);
			//�Ա�
			addLabelToPanel("�Ա�", centerPanel);
			centerPanel.add(Jsex);
			//����
			addLabelToPanel("���������գ�", centerPanel);
			centerPanel.add(Jbrith);
			//�绰
			addLabelToPanel("��ϵ��ʽ��", centerPanel);
			centerPanel.add(Jtell);
			//��ְʱ��
			addLabelToPanel("��ְʱ�䣺", centerPanel);
			centerPanel.add(Jtime);
			//סַ
			addLabelToPanel("��ͥסַ��", centerPanel);
			centerPanel.add(Jadress);
			//������ʷ
			addLabelToPanel("������ʷ��", centerPanel);
			centerPanel.add(Jpast);
			
			
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
			        
			        //�ϳ�sql���
			        String sqlyuju = "SELECT * FROM ְԱ��Ϣ�� WHERE clerkNO=" + ��ѯnum.getText();
			        
			        System.out.println("��ѯ���Ϊ��" + sqlyuju);
			        
			        // ����SQL�������
			        Statement stmt = con.createStatement();
			        //��ò�ѯ����
			        ResultSet rSet = stmt.executeQuery(sqlyuju);
			        rSet.next();
			        
			        Jnum.setText(rSet.getString("clerkNO"));
			        Jname.setText(rSet.getString("clerkName"));
			        Jposnum.setText(rSet.getString("posNO"));
			        Jdeptnum.setText(rSet.getString("deptNO"));
			        Jgroup.setText(rSet.getString("teamNO"));
			        Jsex.setText(rSet.getString("clerkSex"));
			        Jbrith.setText(rSet.getString("clerkBirth"));
			        Jtime.setText(rSet.getString("clerkEntry"));
			        Jtell.setText(rSet.getString("clerkContact"));
			        Jadress.setText(rSet.getString("clerkAddress"));
			        Jpast.setText(rSet.getString("clerkPast"));
			        
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        System.out.println("д�����");
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
			        stmt.execute("DELETE FROM ְԱ��Ϣ�� WHERE clerkNO =" + Jnum.getText());
			        
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
			        String sql = "UPDATE ְԱ��Ϣ�� SET clerkNO=" + Jnum.getText() + ",clerkName='"
			        		+ Jname.getText() + "',posNO=" + Jposnum.getText() + ",deptNO=" + Jdeptnum.getText()
			        		+ ",teamNO=" + Jgroup.getText() + ",clerkSex='" + Jsex.getText()
			        		+ "',clerkBirth='" + Jbrith.getText() + "',clerkEntry='" + Jtime.getText()
			        		+ "',clerkContact='" + Jtell.getText() + "',clerkAddress='"
			        		+ Jadress.getText() + "',clerkPast='" + Jpast.getText()
			        		+ "' WHERE clerkNO=" + ��ѯnum.getText();
//			        System.out.println(sql);
			        stmt.execute(sql);
			        JOptionPane.showMessageDialog(null, "�ɹ����桮"+Jname.getText() + "������Ϣ");
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        System.out.println("д�����");
			        System.exit(0);
			    }
			}else if (eventName.equals("�ر�")) {
				�޸ĺ�ɾ����Ա����.this.dispose();
				new ����Ա������();
			}
		}
		
	}
}
