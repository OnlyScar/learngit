package map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author sunshine
 * Map ����
 */
public class MapIterator {
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		
		//��ǿforѭ��
		//1.ʹ��keySet()����
		for (String key : map.keySet()) {
			System.out.println(key + " : " + map.get(key));
		}
		
		//2.ʹ��entrySet()����
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		//������
		//3.ʹ��keySet()
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.println(key + "����" + map.get(key));
		}
		
		//4.ʹ��entrySet()
		Iterator<Map.Entry<String, String>> iterator1 = map.entrySet().iterator();
		while (iterator1.hasNext()) {
			Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) iterator1.next();
			System.out.println(entry.getKey() + "����" + entry.getValue());
		}
		
		
		
	}
}
