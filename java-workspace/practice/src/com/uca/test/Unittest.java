package com.uca.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Unittest {

	Class<? extends Throwable> expected() default Throwable.class;

	String msg() default "";

	Class<? extends FailedMessage> myMessage() default FailedMessage.class;

	public class FailedMessage {

		public void print() {
			System.out.print("  test case was expecting this exception ");

		}
	}
}
