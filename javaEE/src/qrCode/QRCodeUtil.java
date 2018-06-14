package qrCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import com.swetake.util.Qrcode;

public class QRCodeUtil {
	
	public static void qrCodeEncode(String data,File destFile) throws IOException {
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeErrorCorrect('M');//纠错级别（L 7%、M 15%、Q 25%、H 30%）和版本有关 
		qrcode.setQrcodeEncodeMode('B');
		qrcode.setQrcodeVersion(7);
		
		byte[] d = data.getBytes("GBK");//字符集
		BufferedImage bi = new BufferedImage(139, 139, BufferedImage.TYPE_INT_RGB);
		//createGraphics 创建图层
		Graphics2D g = bi.createGraphics();
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, 139, 139);
		g.setColor(Color.BLACK);
		if (d.length>0&&d.length<123) {
			boolean[][] b = qrcode.calQrcode(d);
			for (int i = 0; i < b.length; i++) {
				for (int j = 0; j < b.length; j++) {
					if (b[j][i]) {
						g.fillRect(j*3+2, i*3+2, 3, 3);
					}
				}
			}
		}
		//Image img = ImageIO.read(new File("D:/tt.png"));  //logo 
		//g.drawImage(img, 40, 50,55,55, null); 
		// 释放此图形的上下文以及它使用的所有系统资源。调用 dispose 之后，就不能再使用 Graphics 对象 
		g.dispose();
		// 刷新此 Image 对象正在使用的所有可重构的资源 
		bi.flush();
		
		
		ImageIO.write(bi, "png", destFile);
		System.out.println("Input Encoded data is: "+ data);
			
	}
	/**
	 * 解析二维码，返回解析内容
	 * @param imageFile
	 * @return
	 */
	public static String qrCodeDecode(File imageFile) {
		String decodedData = null;
		QRCodeDecoder decoder = new QRCodeDecoder();
		BufferedImage image = null;
		try {
			image = ImageIO.read(imageFile);
			decodedData = new String(decoder.decode(new J2SEImage(image)),"GBK");
		} catch (IOException|DecodingFailedException e) {
			if (e instanceof IOException) {
				((IOException) e).printStackTrace();
			}
			if (e instanceof DecodingFailedException) {
				System.out.println("");
			}
		}
		
		return decodedData;
	}
	
	static class J2SEImage implements QRCodeImage{

		BufferedImage image;
		public J2SEImage(BufferedImage image) {
			this.image = image;
		}

		@Override
		public int getHeight() {
		
			return image.getHeight();
		}

		@Override
		public int getPixel(int x, int y) {
			return image.getRGB(x, y);
		}

		@Override
		public int getWidth() {
			return image.getWidth();
		}
		
	}
	
	public static void main(String[] args) {
		String filePath = "d:/qrcode.png";
		File qrFile = new File(filePath);
		//二维码内容
		String encodedData = "http://how2j.cn";
		try {
			QRCodeUtil.qrCodeEncode(encodedData, qrFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//解码
		String reText = QRCodeUtil.qrCodeDecode(qrFile);
		System.out.println(reText);
		
	}
	
}
