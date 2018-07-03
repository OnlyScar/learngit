package helloworld;

import java.util.Scanner;

public class BMI {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入体重(kg):");
		double weight = s.nextDouble();
		System.out.println("请输入身高(m)");
		float height = s.nextFloat();
		double b = weight/(height*height);
		System.out.println("BMI : "+b);
		if (b<18.5) {
			System.out.println("体重过轻");
		}
		else if (18.5 <= b && b < 24){
			System.out.println("正常");
		}
		else if (24 <= b && b < 27){
			System.out.println("体重过重");		
		}
		else if (27 <= b && b < 30){
			System.out.println("肥胖");
		}
		else if (b >= 30){
			System.out.println("重度肥胖");
		}
	}
}
