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



public class ��Ӿ������ extends JFrame {
//	
//	public static void main(String[] args) {
//		new ��Ӿ������();
//	}
	
	public ��Ӿ������() {
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
		
		setSize(400, 250);
		setTitle("��д�¾�����Ϣ");
		add(new recruitPanel());
	}
	
	
	//����һ����Ϣ
	JTextField clerknum = new JTextField();//������
	JTextField deptnum = new JTextField();//������
	JTextArea managerdetail = new JTextArea();//��������
	
	
	//����
	class recruitPanel extends JPanel {
		
		//��������д
		ActionListener twoButtonListener = new buttonAction();
		
		public recruitPanel() {
			// TODO �Զ����ɵĹ��캯�����
			setLayout(new BorderLayout(20,20));
			
			JPanel centerPanel = new JPanel();
			//�м���11��2�еĲ���
			centerPanel.setLayout(new GridLayout(3,2,20,20));
			
			//�����м�
			//���
			addLabelToPanel("������ ��", centerPanel);
			centerPanel.add(clerknum);
			//���ű��
			addLabelToPanel("���ű�ţ�", centerPanel);
			centerPanel.add(deptnum);
			//ְλ���
			addLabelToPanel("����ע��", centerPanel);
			centerPanel.add(managerdetail);
			
			//��������ȷ����ȡ��
			JPanel underPanel = new JPanel();
			underPanel.setLayout(new FlowLayout());
			addButtonToPanel("�ύ", twoButtonListener, underPanel);
			addButtonToPanel("ȡ��", twoButtonListener, underPanel);
			
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
			if (eventName.equals("�ύ")) {
				try {
					//�û���������
			        String connectDB = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;DatabaseName=������Դ����ϵͳ";
			        String user = "sa";
			        String password = "123456";
			        // �������ݿ����
			        Connection con = DriverManager.getConnection(connectDB, user,
			                password);
			        
			        //�ϳ�sql���INSERT INTO ������Ϣ�� VALUES(1,1,'Э��Ա��');
			        String sqlyuju1 = "INSERT INTO ������Ϣ�� VALUES";
			        String yuju = "(" + clerknum.getText() + "," + deptnum.getText() + ",'" + managerdetail.getText() + "')";
			        sqlyuju1 += yuju;
			        
			        String sqlyuju2 ="UPDATE ������Ϣ�� SET deptMar=" + clerknum.getText() + " WHERE deptNO=" +deptnum.getText() ;
			        
			        String sqlyuju3 ="UPDATE ְԱ��Ϣ�� SET deptNO=" + deptnum.getText() + " WHERE clerkNO=" +clerknum.getText() ;
			        
			        // ����SQL�������
			        Statement stmt = con.createStatement();
			        
			        //����ְԱ
			        stmt.execute(sqlyuju1);
			        stmt.execute(sqlyuju2);
			        stmt.execute(sqlyuju3);
			        
			        

			        JOptionPane.showMessageDialog(null, "�ɹ���ӡ�"+clerknum.getText() + "���ž���");
			        
			        ��Ӿ������.this.dispose();
			        new ���������ʾ();
			        System.out.println("�ɹ�����" + yuju);
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        JOptionPane.showMessageDialog(null, "���ʧ��");
			    }
			}else if (eventName.equals("ȡ��")) {
				//�ص��������
				��Ӿ������.this.dispose();
				//����һ������
				new ���������ʾ();
			}
		}
		
	}
}