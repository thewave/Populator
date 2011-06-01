package br.com.wave.populator.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionUtil {
	
	private ReflectionUtil() {
		
	}

	public static boolean isStatic(Field field) {
		return Modifier.isStatic(field.getModifiers());
	}

	public static boolean isTransient(Field field) {
		return Modifier.isTransient(field.getModifiers());
	}

	public static boolean isSerializable(Class<?> klass) {
		Class<?>[] interfaces = klass.getInterfaces();
		for (Class<?> i : interfaces) {
			if (i.equals(Serializable.class)) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean contains(Field field, Class<?> klass) {
		Field[] fields = field.getType().getDeclaredFields();
		for (Field f : fields) {
			if (f.getType().equals(klass)) {
				return true;
			}
		}
		
		return false;
	}
	
}
