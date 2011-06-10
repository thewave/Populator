package br.com.wave.populator.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReflectionUtil {

	private ReflectionUtil() {

	}

	public static boolean isStatic(Field field) {
		return Modifier.isStatic(field.getModifiers());
	}

	public static boolean isTransient(Field field) {
		return Modifier.isTransient(field.getModifiers());
	}

	public static boolean isPersistent(Field field) {
		return !isStatic(field) && !isTransient(field);
	}

	public static List<Field> getPersistentFields(Class<?> klass) {
		List<Field> fields = new ArrayList<Field>();

		for (Field field : klass.getDeclaredFields()) {
			if (isPersistent(field)) {
				fields.add(field);
			}
		}

		return fields;
	}

	public static boolean implementz(Class<?> klass, Class<?> interfaceClass) {
		Class<?>[] interfaces = klass.getInterfaces();
		for (Class<?> i : interfaces) {
			if (i.equals(interfaceClass)) {
				return true;
			}
		}

		return false;
	}

	public static <T> Object get(Field field, T instance) {
		try {
			boolean accessible = field.isAccessible();
			field.setAccessible(Boolean.TRUE);

			Object value = field.get(instance);

			field.setAccessible(accessible);
			return value;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> void set(Object value, Field field, T instance) {
		try {
			boolean accessible = field.isAccessible();
			field.setAccessible(Boolean.TRUE);

			field.set(instance, value);

			field.setAccessible(accessible);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static <T> void copy(T origin, T target) {
		Class<?> originClass = origin.getClass();
		Class<?> targetClass = target.getClass();

		if (!originClass.equals(targetClass)) {
			throw new IllegalArgumentException();
		}

		List<Field> fields = getPersistentFields(targetClass);
		for (Field field : fields) {
			Object value = get(field, origin);
			set(value, field, target);
		}
	}

	public static Class<?> getTypeOfElements(Field field) {
		boolean isNotCollection = !field.getType().equals(Collection.class);
		if (isNotCollection) {
			throw new IllegalArgumentException();
		}

		ParameterizedType type = (ParameterizedType) field.getGenericType();

		return (Class<?>) type.getActualTypeArguments()[0];
	}

	public static boolean isCollection(Class<?> klass) {
		boolean isArrayList = klass.equals(ArrayList.class);
		boolean isList = klass.equals(List.class);
		boolean isCollection = klass.equals(Collection.class);

		return isArrayList || isList || isCollection;
	}

}
