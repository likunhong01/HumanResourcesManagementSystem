package ����;

public class mainhanshu {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		//�������ݿ�
		try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    } catch (ClassNotFoundException e) {
	        System.err.println("δ�ҵ�����");
	    }
	    System.out.println("���ݿ������ɹ�");
	    //��һ��ѡ�����
	    new FirstGUI();
	}
}