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
			
			// ����������Ϣ�߳�
            new SendThread(s).start();
            // ����������Ϣ�߳�
            new RecieveThread(s).start();
			
			/*//�������
			OutputStream os = s.getOutputStream();
			//�������ֵ������
			
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
