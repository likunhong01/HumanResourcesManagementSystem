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



public class Recruit extends JFrame {
	

	public Recruit() {
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
		setTitle("��дӦƸ��Ϣ");
		add(new recruitPanel());
	}
	
	
	//����һ����Ϣ
	JTextField Jname = new JTextField();//����
	JTextField Jsex = new JTextField();//
	JTextField Jbrith = new JTextField();//
	JTextField Jtell = new JTextField();//
	JTextField Jdept = new JTextField();//
	JTextField Jpos = new JTextField();//
	//��������
	JTextArea Jpast = new JTextArea();
	//��ʷҵ��
	JTextArea Jperformance = new JTextArea();
	
	
	//����
	class recruitPanel extends JPanel {
		
		//��������д
		ActionListener twoButtonListener = new buttonAction();
		
		public recruitPanel() {
			// TODO �Զ����ɵĹ��캯�����
			setLayout(new BorderLayout(10,10));
			
			JPanel centerPanel = new JPanel();
			//�м���8��2�еĲ���
			centerPanel.setLayout(new GridLayout(8,2,5,5));
			
			//�����м�
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
			//����
			addLabelToPanel("ӦƸ���ţ�", centerPanel);
			centerPanel.add(Jdept);
			//ְλ
			addLabelToPanel("ӦƸְλ��", centerPanel);
			centerPanel.add(Jpos);
			//����
			addLabelToPanel("����������", centerPanel);
			centerPanel.add(Jpast);
			//ҵ��
			addLabelToPanel("��ʷҵ����", centerPanel);
			centerPanel.add(Jperformance);
			
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
			        String sqlyuju = "insert into ӦƸ��Ա�� values";
			        String yuju = "('" + Jname.getText() + "','" + Jsex.getText() + "','" + Jpast.getText() + "','" + Jtell.getText() + "','" + Jdept.getText() + "','" + Jpos.getText() + "','" + Jperformance.getText() + "','" + Jbrith.getText() + "','')";
			        sqlyuju += yuju;
			        System.out.println("�ɹ�����" + yuju);
			        
			        // ����SQL�������
			        Statement stmt = con.createStatement();
			        stmt.execute(sqlyuju);
			        JOptionPane.showMessageDialog(null, "�ɹ��ύ");
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        JOptionPane.showMessageDialog(null, "д�����");
			    }
			}else if (eventName.equals("ȡ��")) {
				Recruit.this.dispose();
				new FirstGUI();
			}
		}
		
	}
}
