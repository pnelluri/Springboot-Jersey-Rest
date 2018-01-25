package com.nisum.supporters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
@Provider
public class MyConverter implements ParamConverterProvider{

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if(rawType.getName().equals(MyDate.class.getName())){
			return new ParamConverter<T>() {

				@Override
				public T fromString(String value) {
					Calendar cal = Calendar.getInstance();
					if("tomorrow".equalsIgnoreCase(value))
						cal.add(Calendar.DATE, 1);
					if("yesterday".equalsIgnoreCase(value))
						cal.add(Calendar.DATE, -1);
					MyDate md = new MyDate();
					md.setDate(cal.get(Calendar.DATE));
					md.setMonth(cal.get(Calendar.MONTH));
					md.setYear(cal.get(Calendar.YEAR));
					return rawType.cast(md);
				}

				@Override
				public String toString(T value) {
					if(value==null)
						return null;
					return value.toString();
				}
				
			};
		}
		return null;
	}

}
