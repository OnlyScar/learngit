package bitcoin;

import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name="BitCoinDataCenter",urlPatterns="/BitCoinDataCenter",loadOnStartup=1)
public class BitCoinDataCenter extends HttpServlet implements Runnable {

	@Override
	public void init(ServletConfig config) throws ServletException {
		startup();
	}
	
	private void startup() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		int bitPrice = 100000;
		while (true) {
			//ÿ��1-3��Ͳ���һ���¼۸�
			int duration = 1000 + new Random().nextInt(2000);
			try {
				Thread.sleep(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//�¼۸�Χ��100000����50%����
			float random = 1+(float) (Math.random()-0.5);
			int newPrice = (int) (bitPrice*random);
			
			//�鿴����Խ�࣬�۸�Խ��
			int total = ServerManager.getTotal();
			newPrice = newPrice*total;
			//{price:%d,total:%d}
			String messageFormat = "{\"price\":\"%d\",\"total\":%d}";
			String message = String.format(messageFormat,newPrice,total);
			//�㲥��ȥ
			ServerManager.broadCast(message);
		}
			
	}

}
