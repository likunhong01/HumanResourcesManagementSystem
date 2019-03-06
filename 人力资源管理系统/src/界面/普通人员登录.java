package ����;

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ��ͨ��Ա��¼ extends JFrame {
	public ��ͨ��Ա��¼() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width/2; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height/2; // ��ȡ��Ļ�ĸ�
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth-width/2 - 200, screenHeight-height/2 - 120);
		
		setSize(300, 150);
		setTitle("��¼");
		add(new registerPanel());
	}
	
	
	//����
	class registerPanel extends JPanel{
		
		JPanel panelWithEnsureAndCancel;
		JPanel panelWithUserNameAndPassword;
		String userName;
		String userPassword;
		
		//�ı���ȫ�ֱ���
		JTextField Jusername;
		JTextField Jpassword;
		
		//��������д
		ActionListener SureAndCancelListener = new buttonAction();
		
		public registerPanel() {
			
			setLayout(new BorderLayout(5,5));
			
			//ȷ����ȡ����ť
			//ȷ����ȡ��������ʽ���ֹ�����
			panelWithEnsureAndCancel = new JPanel();
			panelWithEnsureAndCancel.setLayout(new FlowLayout());
			
			//�Ѱ�ť����������ͻ���
			addButtonToPanel("ȷ��", SureAndCancelListener, panelWithEnsureAndCancel);
			addButtonToPanel("ȡ��", SureAndCancelListener, panelWithEnsureAndCancel);
			//�ɻ���һ�����ܵ�south����
			add(panelWithEnsureAndCancel, BorderLayout.SOUTH);
			
			
			//�����м���û����������¼����
			setCenter();
			
		}
		
		public void addButtonToPanel(String label ,ActionListener listener, Container panel) {
			JButton button = new JButton(label);
			button.addActionListener(listener);
			panel.add(button);
		}
		
		public void setCenter() {
			
			//�м����������񲼾ֹ�����
			panelWithUserNameAndPassword = new JPanel();
			panelWithUserNameAndPassword.setLayout(new GridLayout(2, 2, 5, 5));
			
			//�˺�
			JLabel number = new JLabel("�˺ţ�",SwingConstants.RIGHT);
			panelWithUserNameAndPassword.add(number);
			//�˺������
			Jusername = new JTextField("");
			panelWithUserNameAndPassword.add(Jusername);
			
			//���룺
			JLabel password = new JLabel("���룺",SwingConstants.RIGHT);
			panelWithUserNameAndPassword.add(password);
			//���������
			Jpassword = new JTextField("");
			panelWithUserNameAndPassword.add(Jpassword);
			
			//���м������������
			add(panelWithUserNameAndPassword, BorderLayout.CENTER);
		}

		
		
		//��ȷ����ȡ���ļ�����
		class buttonAction implements ActionListener {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO �Զ����ɵķ������
				String eventName = event.getActionCommand();
				if (eventName.equals("ȷ��")) {
					
					//���ı�����ȡ
					userName = "'" + Jusername.getText() + "'";
					userPassword = Jpassword.getText();
					
					System.out.println(userName);
					System.out.println(userPassword);
					
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
				        
				        // ��ȡ����
				        ResultSet rs = stmt.executeQuery("SELECT Mypassword FROM �û���Ϣ�� WHERE Myaccount=" + userName);// ����SQL����ѯ������
				        rs.next();
				        String mima = rs.getString("Mypassword");
				        mima = mima.substring(0, 6);
				        System.out.println(mima);
				        
				        if (mima.equals(userPassword)) {
				        	JOptionPane.showMessageDialog(null, "��¼�ɹ�");
							��ͨ��Ա��¼.this.dispose();
							new ������Ϣ����(Integer.valueOf(Jusername.getText()));
						}else {
							JOptionPane.showMessageDialog(null, "�˺Ż�����������������룡");
							Jpassword.setText("");
						}
				        
				    } catch (SQLException e) {
				        e.printStackTrace();
				        System.out.print(e.getErrorCode());
				        String error = "";
				        if (e.getErrorCode() == 0) {
							error = "�û���������";
						}
				        JOptionPane.showMessageDialog(null, "���ݿ����Ӵ���\n����" + e.getErrorCode() + "," + error);
				    }
				}else if (eventName.equals("ȡ��")) {
					��ͨ��Ա��¼.this.dispose();
					new FirstGUI();
				}
			}
		}
	}
}

