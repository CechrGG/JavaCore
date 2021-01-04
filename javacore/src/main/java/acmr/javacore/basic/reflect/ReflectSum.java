package acmr.javacore.basic.reflect;

/**
 * @author guohz
 * @date 2020年9月15日
 * @description ReflectSum
 */
public class ReflectSum {

	public static void testClass() throws ClassNotFoundException {
		Class<?> user = Class.forName("acmr.javacore.basic.reflect.User");
		Class<User> user2 = User.class;
		User user3 = new User("1","guohz", 18);
		Class<? extends User> user4 = user3.getClass();
		System.out.println(user.toString());
		System.out.println(user2.toString());
		System.out.println(user3.toString());
		System.out.println(user4.toString());
		System.out.println(user == user2);
//		System.out.println(user == user3);
		System.out.println(user == user4);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ReflectSum.testClass();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
