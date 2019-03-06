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



public class ��Ӳ��Ž��� extends JFrame {
	
//	public static void main(String[] args) {
//		new ��Ӳ��Ž���();
//	}
	
	public ��Ӳ��Ž���() {
		// TODO �Զ����ɵĹ��캯�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
		
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width/2; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height/2; // ��ȡ��Ļ�ĸ�
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth-width/2 - 100, screenHeight-height/2 - 120);
		
		setSize(350, 250);
		setTitle("�༭������Ϣ");
		add(new recruitPanel());
	}
	
	
	//����һ����Ϣ
	JTextField Jdeptnum = new JTextField();//
	JTextField Jclerknum = new JTextField();//
	JTextField Jname = new JTextField();//
	//����ְ��
	JTextArea Jduty = new JTextArea();
	
	
	//����
	class recruitPanel extends JPanel {
		
		//��������д
		ActionListener twoButtonListener = new buttonAction();
		
		public recruitPanel() {
			// TODO �Զ����ɵĹ��캯�����
			setLayout(new BorderLayout(20,20));
			
			JPanel centerPanel = new JPanel();
			//�м���11��2�еĲ���
			centerPanel.setLayout(new GridLayout(4,2,2,8));
			
			//�����м�
			//���ű��
			addLabelToPanel("���ű�ţ�", centerPanel);
			centerPanel.add(Jdeptnum);
			//���ž���ְԱ��
			addLabelToPanel("�����ţ�", centerPanel);
			centerPanel.add(Jclerknum);
			//��������
			addLabelToPanel("�������ƣ�", centerPanel);
			centerPanel.add(Jname);
			//����ְ��
			addLabelToPanel("����ְ��", centerPanel);
			centerPanel.add(Jduty);
			
			
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
			        
			        //�ϳ�sql���
			        
			        String sqlyuju1 = "INSERT INTO ������Ϣ�� VALUES";
			        String yuju = "(" + Jdeptnum.getText() + ",'" + Jname.getText() + "'," + Jclerknum.getText() + ",'" + Jduty.getText() + "')";
			        sqlyuju1 += yuju;
			        
			        String sqlyuju2 = "INSERT INTO ������Ϣ��(clerkNO,deptNO) VALUES";
			        String yuju2 = "(" + Jclerknum.getText() + "," + Jdeptnum.getText() + ")";
			        sqlyuju2 += yuju2;
			        
			        String sqlyuju3 ="UPDATE ְԱ��Ϣ�� SET deptNO=" + Jdeptnum.getText() + " WHERE clerkNO=" +Jclerknum.getText() ;
			        // ����SQL�������
			        Statement stmt = con.createStatement();
			        
			        //����ְԱ��Ϣ
			        stmt.execute(sqlyuju3);
			        stmt.execute(sqlyuju2);
			        stmt.execute(sqlyuju1);
			        

			        JOptionPane.showMessageDialog(null, "�ɹ���ӡ�"+Jname.getText() + "������");
			        
			        ��Ӳ��Ž���.this.dispose();
			        new ������Ϣ��ʾ();
			        System.out.println("�ɹ�����" + yuju);
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        
			        JOptionPane.showMessageDialog(null, "���ʧ�ܡ�"+Jname.getText() + "������");
			    }
				
			}else if (eventName.equals("ȡ��")) {
				//�ص��������
				��Ӳ��Ž���.this.dispose();
				new ������Ϣ��ʾ();
			}
		}
		
	}
}
