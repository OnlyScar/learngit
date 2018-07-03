package MVC.bean;

/**
 * @author pc007
 *	how2j.cn学习	
 */
public class Hero {
	public int id;
    public String name;
    public float hp;
    public int damage;
    
    static String copyright;
    static{
    	System.out.println("初始化 copyright");
    	copyright = "版权由 Riot Games公司所有";
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getHp() {
		return hp;
	}
	public void setHp(float hp) {
		this.hp = hp;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", hp=" + hp + ", damage="
				+ damage + "]";
	}
    
	
}
