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
			System.out.println("�����ڶ˿ںţ�8888");
			Socket s = ss.accept();
			//System.out.println("�����ӹ���" + s);
			
			new SendThread(s).start();			
			new RecieveThread(s).start();
			
			
/*			//��������
			InputStream is = s.getInputStream();
			
			DataInputStream dis = new DataInputStream(is);
			
			
			//��ȡ�ͻ��˷��͵�����
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
