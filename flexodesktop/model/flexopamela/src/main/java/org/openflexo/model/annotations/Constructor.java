package org.openflexo.model.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface Constructor {

	public static final Parameter[] NO_ARGS = new Parameter[] {};
	public static final Constructor NO_ARG_CONSTRUCTOR = new Constructor() {


		@Override
		public Class<? extends Annotation> annotationType() {
			return Constructor.class;
		}

		@Override
		public Parameter[] value() {
			return NO_ARGS;
		}
	};

	public Parameter[] value();

}
