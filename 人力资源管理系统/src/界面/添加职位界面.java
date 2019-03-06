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



public class ���ְλ���� extends JFrame {
	
//	public static void main(String[] args) {
//		new ���ְλ����();
//	}
	
	public ���ְλ����() {
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
		
		setSize(400, 350);
		setTitle("��д��Ϣ");
		add(new recruitPanel());
	}

	//����һ����Ϣ
	JTextField posnum = new JTextField();//
	JTextField deptnum = new JTextField();//
	JTextField posname = new JTextField();//
	JTextField posrank = new JTextField();//
	JTextField posduty = new JTextField();//
	JTextField poswage = new JTextField();//
	JTextField posdetail = new JTextField();//
	JTextField workload = new JTextField();//
	
	
	//����
	class recruitPanel extends JPanel {
		
		//��������д
		ActionListener twoButtonListener = new buttonAction();
		
		public recruitPanel() {
			// TODO �Զ����ɵĹ��캯�����
			setLayout(new BorderLayout(10,10));
			
			JPanel centerPanel = new JPanel();
			//�м���11��2�еĲ���
			centerPanel.setLayout(new GridLayout(8,2,5,5));
			
			//�����м�
			//���
			addLabelToPanel("ְλ��ţ�", centerPanel);
			centerPanel.add(posnum);
			//���ű��
			addLabelToPanel("���ű�ţ�", centerPanel);
			centerPanel.add(deptnum);
			//ְλ���
			addLabelToPanel("ְλ���ƣ�", centerPanel);
			centerPanel.add(posname);
			//���
			addLabelToPanel("ְλ�ȼ���", centerPanel);
			centerPanel.add(posrank);
			//����
			addLabelToPanel("ְλְ��", centerPanel);
			centerPanel.add(posduty);
			//�Ա�
			addLabelToPanel("н��ˮƽ��", centerPanel);
			centerPanel.add(poswage);
			//����
			addLabelToPanel("ְλ��ע��", centerPanel);
			centerPanel.add(posdetail);
			//�绰
			addLabelToPanel("��������", centerPanel);
			centerPanel.add(workload);
			//��ְʱ��
			
			
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
			        
			        //�ϳ�sql���INSERT INTO ְλ��Ϣ�� VALUES(102,1,'java�з�','�߼�',15000,'�㷨����','8','��');
			        String sqlyuju1 = "INSERT INTO ְλ��Ϣ�� VALUES";
			        String yuju = "(" + posnum.getText() + "," + deptnum.getText() + ",'" + posname.getText() + "','" + posrank.getText() + "'," + poswage.getText() + ",'" + posduty.getText() + "','" + workload.getText() + "','" + posdetail.getText() + "')";
			        sqlyuju1 += yuju;
			        
			        // ����SQL�������
			        Statement stmt = con.createStatement();
			        
			        //����ְλ��Ϣ
			        stmt.execute(sqlyuju1);
			        

			        JOptionPane.showMessageDialog(null, "�ɹ���ӡ�"+posname.getText() + "��ְλ");
			        
			        ���ְλ����.this.dispose();
			        new ְλ��Ϣ��ʾ();
			        System.out.println("�ɹ�����" + yuju);
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        JOptionPane.showMessageDialog(null, "���ʧ��");
			    }
			}else if (eventName.equals("ȡ��")) {
				//�ص��������
				���ְλ����.this.dispose();
				new ְλ��Ϣ��ʾ();
			}
		}
		
	}
}