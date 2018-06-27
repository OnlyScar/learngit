package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author sunshine
 *  https://baike.xsoftlab.net/view/250.html
 *  Java map ��� - �÷������������򡢳���API��
 */
public class MapStudy {
	static int hashMapW = 0;
	static int hashMapR = 0;
    static int linkMapW = 0;
    static int linkMapR = 0;
    static int treeMapW = 0;
    static int treeMapR = 0;
    static int hashTableW = 0;
    static int hashTableR = 0;

	public static void main(String[] args) {
		/*Map<String,String> map = new HashMap<String,String>();
		map.put("key", "value");
		map.get("key");
		map.remove("key");
		map.clear();*/
		
		//���ֳ���Map�������ȡ���ܱȽ�
		for (int i = 0; i < 5; i++) {
			MapStudy map = new MapStudy();
			map.test(100*10000);
			System.out.println();
		}
		System.out.println("hashMapW = " + hashMapW / 10);
        System.out.println("hashMapR = " + hashMapR / 10);
        System.out.println("linkMapW = " + linkMapW / 10);
        System.out.println("linkMapR = " + linkMapR / 10);
        System.out.println("treeMapW = " + treeMapW / 10);
        System.out.println("treeMapR = " + treeMapR / 10);
        System.out.println("hashTableW = " + hashTableW / 10);
        System.out.println("hashTableR = " + hashTableR / 10);
		
	}

	private void test(int size) {
		int index;
		Random random = new Random();
		String[] key = new String[size];
		//HashMap ����
		Map<String,String> map = new HashMap<String,String>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			key[i] = UUID.randomUUID().toString();
			map.put(key[i], UUID.randomUUID().toString());
		}
		long end = System.currentTimeMillis();
		hashMapW += (end - start);
		System.out.println("HashMap�����ʱ = " + (end - start) + " ms");
		
		//HashMap ��ȡ
		start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			index = random.nextInt(size);
			map.get(key[index]);
		}
		end = System.currentTimeMillis();
		System.out.println("HashMap��ȡ��ʱ = " + (end - start) + " ms");
		
		
		
	}
}
