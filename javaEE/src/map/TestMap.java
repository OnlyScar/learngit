package map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author sunshine
 *
 */
public class TestMap {
	public static void main(String[] args) {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (int i = 0; i < 100000; i++) 
			map.put(i, i);
			
		/** 增强for循环，keySet迭代 **/
		long start = System.currentTimeMillis();
		for (Integer key : map.keySet()) {
			map.get(key);
		}
		long end = System.currentTimeMillis();
		System.out.println("增强for循环，keySet迭代 -> " + (end - start) + " ms");

		/** 增强for循环，entrySet迭代 */
		start = System.currentTimeMillis();
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			entry.getKey();
			entry.getValue();
		}
		end = System.currentTimeMillis();
		System.out.println("增强for循环，entrySet迭代 -> " + (end - start) + " ms");
		
		/** 迭代器，keySet迭代 **/
		start = System.currentTimeMillis();
		Iterator<Integer> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Integer key = (Integer) iterator.next();
			map.get(key);
		}
		end = System.currentTimeMillis();
	    System.out.println("迭代器，keySet迭代 -> " + (end - start) + " ms");

	    /** 迭代器，entrySet迭代 **/
	    start = System.currentTimeMillis();
	    Iterator<Map.Entry<Integer, Integer>> iterator1 = map.entrySet().iterator();
	    while (iterator1.hasNext()) {
			Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iterator1.next();
			entry.getKey();
			entry.getValue();
		}
	    end = System.currentTimeMillis();
	    System.out.println("迭代器，entrySet迭代 -> " + (end - start) + " ms");
	    
		
	}
}
