package excel;

import java.io.IOException;
import java.sql.SQLException;

public class Client {
	public static void main(String[] args) throws IOException, SQLException {
		SaveData2DB save = new SaveData2DB();
		save.save();
		System.out.println("end");
	}
}
