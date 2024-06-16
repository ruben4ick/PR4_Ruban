package org.example;

import java.lang.reflect.Method;

public class YearValidator {
    public static void validate(Object obj) throws Exception {
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(ValidateYear.class)) {
                ValidateYear validateYear = method.getAnnotation(ValidateYear.class);
                int year = (int) method.invoke(obj);
                if (year < validateYear.min() || year > validateYear.max()) {
                    throw new RuntimeException("Year " + year + " is not within the allowed range (" + validateYear.min() + "-" + validateYear.max() + ")");
                }
            }
        }
    }
}
