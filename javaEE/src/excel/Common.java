package excel;

public class Common {
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_NAME = "cart";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "1234";
	public static final String IP = "127.0.0.1";
	public static final String PORT = "3306";
	public static final String URL = "jdbc:mysql://"+IP+":"+PORT+"/"+DB_NAME;
	
	public static final String EXCEL_PATH = "D:\\project\\javaEE\\student_info.xls";
	
	public static final String INSERT_STUDENT_SQL = "insert into student_info(no, name, age, score) values(?, ?, ?, ?)";
	
	public static final String SELECT_STUDENT_ALL_SQL = "select id,no,name,age,score from student_info";
	
	public static final String UPDATE_STUDENT_SQL = "update student_info set no = ?, name = ?, age= ?, score = ? where id = ? ";
	
	public static final String SELECT_STUDENT_SQL = "select * from student_info where name like ";
	
}
