package com.uca.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.uca.cards.TestPoker;

public class UnittestRunner {

	public static void main(String[] args) throws SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException, IOException {

		Class<?>[] array = findAllClasses("com.uca");

		for (Class<?> testClass : array) {
			TestPoker t = new TestPoker();
			Method[] methods = testClass.getMethods();
			for (Method m : methods) {
				Unittest annotation = m.getAnnotation(Unittest.class);
				boolean isException = false;
				if (annotation != null) {
					System.out.print(testClass.getName()+ " : executing test case " + m.getName() + " : ");
					try {
						m.invoke(t);
					} catch (InvocationTargetException | IllegalAccessException e) {
						if (e.getCause() != null) {
							isException = true;
							if (!e.getCause().getClass().getName().equalsIgnoreCase(annotation.expected().getName())) {
								throw new AssertionError(String.format("expected %s but actual is %s",
										annotation.expected().getName(), e.getClass().getName()));
							}
							if (!annotation.msg().equals("") && !e.getCause().getMessage().equals(annotation.msg())) {
								throw new AssertionError(String.format("expected %s but actual is %s", annotation.msg(),
										e.getMessage()));
							}
						}
					} catch (Exception e) {
						throw e;
					}
					if (!annotation.expected().getName().equals("java.lang.Throwable") && !isException)
						throw new AssertionError("exception expected here");
					System.out.println("Test case passed");
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
