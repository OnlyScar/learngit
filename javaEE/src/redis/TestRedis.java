package redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class TestRedis {
	JedisPool pool;
	Jedis jedis;
	
	@Before
	public void setUp(){
		jedis = new Jedis("localhost");
	}
	
	/**
	 * Redis�洢�������ַ���
	 * CRUD
	 */
	@Test
	public void testBasicString(){
		jedis.set("name", "meepo");
		System.out.println(jedis.get("name"));
		
		jedis.append("name", "dota");
		System.out.println(jedis.get("name"));
		
		jedis.set("name", "poofu");
		System.out.println(jedis.get("name"));
		
		jedis.del("name");
		System.out.println(jedis.get("name"));
		
		jedis.mset("name","meepo","dota","poofu");
		System.out.println(jedis.mget("name","dota"));
		
	}
	
	/**
     * jedis����Map
     */ 
    @Test 
    public void testMap(){ 
        Map<String,String> user=new HashMap<String,String>(); 
        user.put("name","meepo"); 
        user.put("pwd","password"); 
        jedis.hmset("user",user); 
        //ȡ��user�е�name��ִ�н��:[meepo]-->ע������һ�����͵�List 
        //��һ�������Ǵ���redis��map�����key����������Ƿ���map�еĶ����key�������key���Ը�������ǿɱ���� 
        List<String> rsmap = jedis.hmget("user", "name"); 
        System.out.println(rsmap); 
 
        //ɾ��map�е�ĳ����ֵ 
//      jedis.hdel("user","pwd"); 
        System.out.println(jedis.hmget("user", "pwd")); //��Ϊɾ���ˣ����Է��ص���null 
        System.out.println(jedis.hlen("user")); //����keyΪuser�ļ��д�ŵ�ֵ�ĸ���1 
        System.out.println(jedis.exists("user"));//�Ƿ����keyΪuser�ļ�¼ ����true 
        System.out.println(jedis.hkeys("user"));//����map�����е�����key  [pwd, name] 
        System.out.println(jedis.hvals("user"));//����map�����е�����value  [meepo, password] 
 
        Iterator<String> iter=jedis.hkeys("user").iterator(); 
        while (iter.hasNext()){ 
            String key = iter.next(); 
            System.out.println(key+":"+jedis.hmget("user",key)); 
        } 
    } 
}



