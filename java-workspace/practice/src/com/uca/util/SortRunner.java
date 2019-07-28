package com.uca.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class SortRunner {

	public static void main(String[] args)
			throws SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, IOException, InstantiationException, NoSuchMethodException {

		Class<?>[] array = findAllClasses("com.uca");

		for (Class<?> testClass : array) {

			Method[] methods = testClass.getMethods();
			for (Method m : methods) {
				if (m.getAnnotation(Sort.class) != null) {
					Object o = testClass.newInstance();
					System.out.print(testClass.getName() + " : executing test case " + m.getName() + " : ");
					testClass.getMethod("sort").invoke(o);
					m.invoke(o);
				}
			}
		}
	}

	private static Class<?>[] findAllClasses(String packageName) throws IOException, ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class<?>> classes = new ArrayList<>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		Class<?>[] array = classes.toArray(new Class[classes.size()]);
		return array;
	}

	private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(
						Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

}
