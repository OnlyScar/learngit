package excel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SaveData2DB {
	@SuppressWarnings({"rawtypes" })
	public void save() throws IOException, SQLException {
	         ReadExcel xlsMain = new ReadExcel();
	         Student student = null;
	         List<Student> list = xlsMain.readXls();
	          for (int i = 0; i < list.size(); i++) {
	             student = list.get(i);
	             List l = DBUtil.selectOne(Common.SELECT_STUDENT_SQL + "'%" + student.getName() + "%'", student);
	             if (!l.contains(1)) {
	                 DBUtil.insert(Common.INSERT_STUDENT_SQL, student);
	             } else {
	                System.out.println("The Record was Exist : No. = " + student.getNo() + " , Name = " + student.getName() + ", Age = " + student.getAge() + ", and has been throw away!");
	             }
	         }
	     }

    public void save(String string) {
        // TODO Auto-generated method stub
        
    }
}
