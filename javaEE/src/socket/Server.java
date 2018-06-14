package socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8888);
			System.out.println("监听在端口号：8888");
			Socket s = ss.accept();
			//System.out.println("有连接过来" + s);
			
			new SendThread(s).start();			
			new RecieveThread(s).start();
			
			
/*			//打开输入流
			InputStream is = s.getInputStream();
			
			DataInputStream dis = new DataInputStream(is);
			
			
			//读取客户端发送的数据
			String msg = dis.readUTF();
			System.out.println(msg);
			dis.close();			
			s.close();
			ss.close();*/
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
