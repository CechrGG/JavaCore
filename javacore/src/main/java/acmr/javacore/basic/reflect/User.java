/**
 * 
 */
package acmr.javacore.basic.reflect;

/**
 * @author guohz
 * @date 2020年9月15日
 * @description User
 */
public class User {
	private static final String TAG = "tuhao";
	
	private String id;
	private String name;
	private String email;
	private String telphone;
	private int age;
	private boolean close = false;
	
	public User() {}
	
	public User(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.close = false;
	}
	
	@Override
	public String toString() {
		return "I\'m " + name + ", " + age + " years old. My dream is" + dream();
	}
	
	private String dream() {
		return "实现一个小目标吧，比如赚他个一个亿";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isClose() {
		return close;
	}
	public void setClose(boolean close) {
		this.close = close;
	}
	public static String getTag() {
		return TAG;
	}

}
