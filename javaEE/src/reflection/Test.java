package reflection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

public class Test {
	@SuppressWarnings({"rawtypes","unchecked"})
	public static void main(String[] args) throws Exception, IOException {
		//new Service2().doService2();
		
		//��spring.txt�л�ȡ�����ƺͷ�������
		File springConfigFile = new File("d:\\project\\javaEE\\src\\spring.txt");
		Properties springConfig = new Properties();
		springConfig.load(new FileInputStream(springConfigFile));
		String className = (String) springConfig.get("class");
		String methodName = (String) springConfig.get("method");
		//���������ƻ�ȡ�����
		Class clazz = Class.forName(className);
		//���ݷ������ƣ���ȥ��������
		Method m = clazz.getMethod(methodName);
		//��ȡ������
		Constructor c = clazz.getConstructor();
		//���ݹ�������ʵ����������
		Object service = c.newInstance();
		//���ö����ָ������
		m.invoke(service);	
		
	}
}
