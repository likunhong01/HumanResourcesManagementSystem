package ����;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FirstGUI extends JFrame{
	public FirstGUI() {
		
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
		
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width/2; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height/2; // ��ȡ��Ļ�ĸ�
		int height = this.getHeight();
		int width = this.getWidth();
		setLocation(screenWidth-width/2 - 200, screenHeight-height/2 - 120);
		
		setSize(400, 200);
		setTitle("������Դ����ϵͳ");
		add(new firstPanel());
	}
	
	//����
	class firstPanel extends JPanel {
		//��������д
		ActionListener twoButtonListener = new buttonAction();
		
		public firstPanel() {
			// TODO �Զ����ɵĹ��캯�����
			setLayout(new GridLayout(1,1,40,40));
			
			JPanel thispanel = new JPanel();
			thispanel.setLayout(new FlowLayout());
			
			//�����¼button
			JButton ���˵�¼button = new JButton("���˵�¼");
			���˵�¼button.addActionListener(twoButtonListener);
			thispanel.add(���˵�¼button);
			
			
			//����ӦƸbutton
			JButton ����Ա��¼button = new JButton("����Ա��¼");
			����Ա��¼button.addActionListener(twoButtonListener);
			thispanel.add(����Ա��¼button);
			
			//����ӦƸbutton
			JButton ӦƸbutton = new JButton("ӦƸ");
			ӦƸbutton.addActionListener(twoButtonListener);
			thispanel.add(ӦƸbutton);
			
			add(thispanel);
		}
	}
	
	
	//��������д
	class buttonAction implements ActionListener {
		//��д����
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO �Զ����ɵķ������
			String eventName = event.getActionCommand();
			if (eventName.equals("���˵�¼")) {
				//�ر��������
				FirstGUI.this.dispose();
				//�򿪵�¼����
				new ��ͨ��Ա��¼();
			}else if (eventName.equals("ӦƸ")) {
				//�ر��������
				FirstGUI.this.dispose();
				//��ӦƸ����
				new Recruit();
			}else if (eventName.equals("����Ա��¼")) {
				//�ر��������
				FirstGUI.this.dispose();
				//��ӦƸ����
				new ����Ա��¼����();
			}
		}
	}
}

