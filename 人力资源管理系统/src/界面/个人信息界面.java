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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ����.Recruit.buttonAction;
import ����.Recruit.recruitPanel;

//������Ϣ���棨���˵�¼��
public class ������Ϣ���� extends JFrame {
	public ������Ϣ����(Integer number) {
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
		setTitle("������Ϣ");
		add(new PersonalDetailsPanel(number));
	}
	
	int nnumber;
	//�����ı���
	JTextField jtellnumberUP = new JTextField();
	JTextField JadressUP = new JTextField();
	
	//����
	class PersonalDetailsPanel extends JPanel{
		//��������д
		ActionListener twoButtonListener = new buttonAction();
		

		
		public PersonalDetailsPanel(Integer number) {
			// TODO �Զ����ɵĹ��캯�����
			setLayout(new BorderLayout(10,10));
			
			JPanel centerPanel = new JPanel();
			//�м���8��2�еĲ���
			centerPanel.setLayout(new GridLayout(12,2,5,5));

			
			//�����ѯ������ֵ
			int Jnum = number, Jgroup = 0;
			String Jname = "", Jsex = "", Jbrith = "", Jtell = "", 
			Jadress = "", Jtime = "", Jdept = "", Jpos = "", 
			Jrank = "", Jmoney = "";
			
			
			
			//�����ݿ��ȡ������Ϣ
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
		        ResultSet rSet = stmt.executeQuery("SELECT y.clerkNO ���,clerkName ����,clerkSex �Ա�,clerkBirth ��������,clerkContact ��ϵ��ʽ,clerkAddress ��ͥסַ,clerkEntry ��ְʱ��,deptName ����,y.teamNO ���,posName ְλ����,posRank ְλ�ȼ�,posWage н��ˮƽ " + 
		        		"FROM ְԱ��Ϣ�� y,ְλ��Ϣ�� w,������Ϣ�� m "
		        		+ "WHERE y.posNO = w.posNO AND y.deptNO = m.deptNO AND y.clerkNO=" + String.valueOf(number));
		        rSet.next();
		        
		        Jnum = rSet.getInt("���");
		        Jname = rSet.getString("����");
		        Jsex = rSet.getString("�Ա�");
		        Jbrith = rSet.getString("��������");
		        Jtell = rSet.getString("��ϵ��ʽ");
				Jadress = rSet.getString("��ͥסַ");
				Jtime = rSet.getString("��ְʱ��");
				Jdept = rSet.getString("����");
				Jgroup = rSet.getInt("���");
				Jpos = rSet.getString("ְλ����");
				Jrank = rSet.getString("ְλ�ȼ�");
				Jmoney = rSet.getString("н��ˮƽ");
				
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.print(e.getErrorCode());
		        System.out.println("��ȡ����");
		        System.exit(0);
		    }
			
			nnumber = number;
			//�����м�
			//���
			addLabelToPanel("��ţ�", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(String.valueOf(Jnum), centerPanel, SwingConstants.LEFT);
			//����
			addLabelToPanel("������", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jname, centerPanel, SwingConstants.LEFT);
			//�Ա�
			addLabelToPanel("�Ա�", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jsex, centerPanel, SwingConstants.LEFT);
			//����
			addLabelToPanel("���������գ�", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jbrith, centerPanel, SwingConstants.LEFT);
			//�绰(�ɸ�)
			addLabelToPanel("��ϵ��ʽ��", centerPanel, SwingConstants.RIGHT);
			jtellnumberUP.setText(Jtell);
			centerPanel.add(jtellnumberUP);
			//��ͥסַ���ɸģ�
			addLabelToPanel("��ͥסַ��", centerPanel, SwingConstants.RIGHT);
			JadressUP.setText(Jadress);
			centerPanel.add(JadressUP);
			//��ְʱ��
			addLabelToPanel("��ְʱ�䣺", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jtime, centerPanel, SwingConstants.LEFT);
			//����
			addLabelToPanel("���ţ�", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jdept, centerPanel, SwingConstants.LEFT);
			//���
			addLabelToPanel("���", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(String.valueOf(Jgroup), centerPanel, SwingConstants.LEFT);
			//ְλ
			addLabelToPanel("ְλ��", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jpos, centerPanel, SwingConstants.LEFT);
			//����
			addLabelToPanel("ְλ�ȼ���", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jrank, centerPanel, SwingConstants.LEFT);
			//ҵ��
			addLabelToPanel("н��ˮƽ��", centerPanel, SwingConstants.RIGHT);
			addLabelToPanel(Jmoney, centerPanel, SwingConstants.LEFT);
			

			//��������ȷ����ȡ��
			JPanel underPanel = new JPanel();
			underPanel.setLayout(new FlowLayout());
			addButtonToPanel("����", twoButtonListener, underPanel);
			addButtonToPanel("�ر�", twoButtonListener, underPanel);
			
			//�ɽ�����벼�ֹ�����
			add(centerPanel,BorderLayout.CENTER);
			add(underPanel,BorderLayout.SOUTH);
			
		}

	}
	
	
	public void addButtonToPanel(String name ,ActionListener listener, Container panel) {
		JButton button = new JButton(name);
		button.addActionListener(listener);
		panel.add(button);
	}
	
	
	public void addLabelToPanel(String name , Container panel, int duiqi) {
		JLabel label = new JLabel(name, duiqi);
		panel.add(label);
	}
	
	
	class buttonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO �Զ����ɵķ������
			String eventName = event.getActionCommand();
			if (eventName.equals("����")) {
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
			        
			        stmt.execute("UPDATE ְԱ��Ϣ�� SET clerkContact="
			        		+ jtellnumberUP.getText() + " WHERE clerkNO=" + 
					        String.valueOf(nnumber));
			        stmt.execute("UPDATE ְԱ��Ϣ�� SET clerkAddress='"
			        		+ JadressUP.getText() + "' WHERE clerkNO=" + 
			        		String.valueOf(nnumber));
			        JOptionPane.showMessageDialog(null, "����ɹ�");
			    } catch (SQLException e) {
			        e.printStackTrace();
			        System.out.print(e.getErrorCode());
			        JOptionPane.showMessageDialog(null, "�޸Ĵ���");
			    }
			}else if (eventName.equals("�ر�")) {
				������Ϣ����.this.dispose();
				System.exit(0);
			}
		}
	}
}
