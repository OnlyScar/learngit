package reflection;

import java.lang.reflect.Constructor;

import MVC.bean.Hero;

public class TestReflection {
	public static void main(String[] args) {
		/*String className = "MVC.bean.Hero";
		try {
			Class pClass1 = Class.forName(className);
			System.out.println(pClass1);
			Class pClass2 = Hero.class;
			System.out.println(pClass2);
			Class pClass3 = new Hero().getClass();
			System.out.println(pClass3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		//��ͳ��ʹ��new�ķ�ʽ��������
		Hero h1 = new Hero();
		h1.name = "teemo";
		System.out.println(h1);
		
		try {
			//ʹ�÷���ķ�ʽ��������
			String className = "MVC.bean.Hero";
			Class pClass = Class.forName(className);
			//Constructor c = pClass.getConstructor();
			//ͨ��������ʵ����
			Hero h2 = (Hero) pClass.newInstance();
			h2.name = "gareen";
			System.out.println(h2);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
