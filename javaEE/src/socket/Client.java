package socket;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1",8888);
			//System.out.println(s);
			
			// 启动发送消息线程
            new SendThread(s).start();
            // 启动接受消息线程
            new RecieveThread(s).start();
			
			/*//打开输出流
			OutputStream os = s.getOutputStream();
			//发送数字到服务端
			
			DataOutputStream dos = new DataOutputStream(os);
			
			Scanner sc = new Scanner(System.in);
			String str = sc.next();					
			dos.writeUTF(str);
			dos.close();
				
			s.close();*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
