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
			//每隔1-3秒就产生一个新价格
			int duration = 1000 + new Random().nextInt(2000);
			try {
				Thread.sleep(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//新价格围绕100000左右50%波动
			float random = 1+(float) (Math.random()-0.5);
			int newPrice = (int) (bitPrice*random);
			
			//查看的人越多，价格越高
			int total = ServerManager.getTotal();
			newPrice = newPrice*total;
			//{price:%d,total:%d}
			String messageFormat = "{\"price\":\"%d\",\"total\":%d}";
			String message = String.format(messageFormat,newPrice,total);
			//广播出去
			ServerManager.broadCast(message);
		}
			
	}

}
