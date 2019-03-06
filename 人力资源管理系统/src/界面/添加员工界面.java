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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class ���Ա������ extends JFrame {
	
//	public static void main(String[] args) {
//		new ���Ա������();
//	}
	
	public ���Ա������() {
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
		setTitle("��д����Ա����Ϣ");
		add(new recruitPanel());
	}
	
	
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
	class recruitPanel extends JPanel {
		
		//��������д
		ActionListener twoButtonListener = new buttonAction();
		
		public recruitPanel() {
			// TODO �Զ����ɵĹ��캯�����
			setLayout(new BorderLayout(10,10));
			
			JPanel centerPanel = new JPanel();
			//�м���11��2�еĲ���
			centerPanel.setLayout(new GridLayout(11,2,5,5));
			
			//�����м�
			//���
			addLabelToPanel("��ţ�", centerPanel);
			centerPanel.add(Jnum);
			addLabelToPanel("���ű�ţ�", centerPanel);
			centerPanel.add(Jdeptnum);
			addLabelToPanel("ְλ��ţ�", centerPanel);
			centerPanel.add(Jposnum);
			addLabelToPanel("���", centerPanel);
			centerPanel.add(Jgroup);
			addLabelToPanel("������", centerPanel);
			centerPanel.add(Jname);
			addLabelToPanel("�Ա�", centerPanel);
			centerPanel.add(Jsex);
			addLabelToPanel("���������գ�", centerPanel);
			centerPanel.add(Jbrith);
			addLabelToPanel("��ϵ��ʽ��", centerPanel);
			centerPanel.add(Jtell);
			addLabelToPanel("��ְʱ�䣺", centerPanel);
			centerPanel.add(Jtime);
			addLabelToPanel("��ͥסַ��", centerPanel);
			centerPanel.add(Jadress);
			addLabelToPanel("������ʷ��", centerPanel);
			centerPanel.add(Jpast);
			
			
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
			        String sqlyuju1 = "INSERT INTO ְԱ��Ϣ��(clerkNO,clerkName,deptNO,posNO,teamNO,clerkSex,clerkBirth,clerkEntry,clerkContact,clerkAddress,clerkPast) VALUES";
			        String yuju = "(" + Jnum.getText() + ",'" + Jname.getText() + "'," + Jdeptnum.getText() + "," + Jposnum.getText() + "," + Jgroup.getText() + ",'" + Jsex.getText() + "','" + Jbrith.getText() + "','" + Jtime.getText() + "','" + Jtell.getText() + "','" + Jadress.getText() + "','" + Jpast.getText() + "')";
			        sqlyuju1 += yuju;
			        
			        
			        String sqlyuju2 = "INSERT INTO �û���Ϣ�� " +
			        		"VALUES(" + Jnum.getText() + ",'" + Jtell.getText().substring(0,6)
			        		+ "');";
			        
			        // ����SQL�������
			        Statement stmt = con.createStatement();
			        
			        //����ְԱ��Ϣ
			        stmt.execute(sqlyuju1);
			        
			        //����ְԱ�û���¼
			        stmt.execute(sqlyuju2);
			        
			        JOptionPane.showMessageDialog(null, "�ɹ���ӡ�"+Jname.getText() + "��");
			        ���Ա������.this.dispose();
			        new ����Ա������();
			        System.out.println("�ɹ�����" + yuju);
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        JOptionPane.showMessageDialog(null, "���ʧ�ܣ�������ͬ���");
			    }
			}else if (eventName.equals("ȡ��")) {
				//�ص��������
				���Ա������.this.dispose();
				//����һ������
				new ����Ա������();
			}
		}
		
	}
}
