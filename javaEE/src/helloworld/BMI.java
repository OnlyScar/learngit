package helloworld;

import java.util.Scanner;

public class BMI {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("����������(kg):");
		double weight = s.nextDouble();
		System.out.println("���������(m)");
		float height = s.nextFloat();
		double b = weight/(height*height);
		System.out.println("BMI : "+b);
		if (b<18.5) {
			System.out.println("���ع���");
		}
		else if (18.5 <= b && b < 24){
			System.out.println("����");
		}
		else if (24 <= b && b < 27){
			System.out.println("���ع���");		
		}
		else if (27 <= b && b < 30){
			System.out.println("����");
		}
		else if (b >= 30){
			System.out.println("�ضȷ���");
		}
	}
}
