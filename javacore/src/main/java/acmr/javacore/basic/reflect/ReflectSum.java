package acmr.javacore.basic.reflect;

import acmr.springframework.util.StringUtil;

import java.lang.reflect.*;
import java.util.Scanner;

/**
 * @author guohz
 * @date 2020年9月15日
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

	public static void printClass(String className) throws ClassNotFoundException {
		if(StringUtil.isEmpty(className)) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("输入一个类名（比如：acmr.javacore.basic.reflect.User）：");
			className = scanner.next();
		}

		Class<?> aClass = Class.forName(className);
		Class<?> superclass = aClass.getSuperclass();
		String modifiers = Modifier.toString(aClass.getModifiers());
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(aClass.getPackage().getName());
		sb.append("\n\r\n\r");
		if(!StringUtil.isEmpty(modifiers)) {
			sb.append(modifiers).append(" class ").append(aClass.getSimpleName());
		}
		if(superclass != null && Object.class != superclass) {
			sb.append(" extends ").append(superclass.getSimpleName());
		}
		sb.append(" {\n\r\n\r");
		sb.append(printFields(aClass));
		sb.append(printConstructors(aClass));
		sb.append(printMethods(aClass));
		sb.append("}");
		System.out.println(sb);
	}
	
	public static StringBuilder printFields(Class<?> aClass) {
		Field[] fields = aClass.getDeclaredFields();
		StringBuilder sb = new StringBuilder();
		for(Field field : fields) {
			String modifiers = Modifier.toString(field.getModifiers());
			if(!StringUtil.isEmpty(modifiers)) {
				sb.append("\t").append(modifiers);
			}
			sb.append(" ").append(field.getType().getSimpleName()).append(" ").append(field.getName())
				.append(";\n\r");
		}
		return sb;
	}

	public static StringBuilder printConstructors(Class<?> aClass) {
		Constructor<?>[] constructors = aClass.getDeclaredConstructors();
		StringBuilder sb = new StringBuilder();
		for(Constructor<?> constructor : constructors) {
			String modifiers = Modifier.toString(constructor.getModifiers());
			if(!StringUtil.isEmpty(modifiers)) {
				sb.append("\t").append(modifiers);
			}
			sb.append(" ").append(constructor.getName().substring(constructor.getName().lastIndexOf(".") + 1)).append(" (");
			Parameter[] parameters = constructor.getParameters();
			if(parameters != null && parameters.length > 0) {
				for(Parameter parameter : parameters) {
					sb.append(parameter.getType().getSimpleName()).append(" ").append(parameter.getName()).append(", ");
				}
				int pos = sb.lastIndexOf(",");
				sb.replace(pos, pos + 1, ")");
			} else {
				sb.append(")");
			}
			sb.append(";\n\r");
		}
		return sb;
	}

	public static StringBuilder printMethods(Class<?> aClass) {
		Method[] methods = aClass.getDeclaredMethods();
		StringBuilder sb = new StringBuilder();
		for(Method method : methods) {
			String modifiers = Modifier.toString(method.getModifiers());
			if(!StringUtil.isEmpty(modifiers)) {
				sb.append("\t").append(modifiers);
			}
			sb.append(" ").append(method.getReturnType().getSimpleName())
					.append(" ").append(method.getName()).append("(");
			Parameter[] parameters = method.getParameters();
			if(parameters != null && parameters.length > 0) {
				for(Parameter parameter : parameters) {
					sb.append(parameter.getType().getSimpleName()).append(" ").append(parameter.getName()).append(", ");
				}
				int pos = sb.lastIndexOf(",");
				sb.replace(pos, pos + 2, ")");
			} else {
				sb.append(")");
			}
			sb.append(";\n\r");
		}
		return sb;
	}

	public static String objToString(Object obj) throws IllegalAccessException {
		if(obj == null) {
			return "null";
		}
		if(obj instanceof String) {
			return (String) obj;
		}
		Class<?> aClass = obj.getClass();
		if(aClass.isArray()) {
			StringBuilder sb = new StringBuilder(aClass.getTypeName()).append("[]{");
			for(int i = 0; i < Array.getLength(obj); i++) {
				Object val = Array.get(obj, i);
				if(aClass.getComponentType().isPrimitive()) {
					sb.append(val);
				} else {
					sb.append(objToString(val));
				}
				sb.append(", ");
			}
			int pos = sb.lastIndexOf(",");
			sb.replace(pos, pos + 2, "}");
			return sb.toString();
		}

		StringBuilder sb = new StringBuilder(aClass.getName()).append("[");
		Field[] fields = aClass.getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);
		for(Field field : fields) {
			sb.append(field.getName()).append(" = ");
			Object val = field.get(obj);
			if(field.getType().isPrimitive()) {
				sb.append(val);
			} else {
				sb.append(objToString(val));
			}
			sb.append(", ");
		}
		int pos = sb.lastIndexOf(", ");
		sb.replace(pos, pos + 2, "]");
		return sb.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			ReflectSum.testClass();
//			ReflectSum.printClass("");
			System.out.println(ReflectSum.objToString(new User("1", "guohz", 31)));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
