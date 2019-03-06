package 界面;

public class mainhanshu {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//驱动数据库
		try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    } catch (ClassNotFoundException e) {
	        System.err.println("未找到驱动");
	    }
	    System.out.println("数据库驱动成功");
	    //第一个选择界面
	    new FirstGUI();
	}
}