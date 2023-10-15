package com.example;

import java.lang.reflect.Method;
import java.time.temporal.Temporal;

public class JsonObject {
    public static <T> String toJson(Object object, Class<T> clazz)
            throws Exception {
        StringBuilder stringBuilder = new StringBuilder("{");

        Method[] declaredMethods = clazz.getDeclaredMethods();
        boolean appendComma = true;

        if (object == null) {
            return "null";
        } else if (isSimpleClass(clazz)) {
            return object.toString();
        } else if (CharSequence.class.isAssignableFrom(clazz)) {
            return "\"" + object.toString() + "\"";
        }

        for (Method method : declaredMethods) {
            if (isAccessor(method)) {
                if (!appendComma) {
                    stringBuilder.append(",");
                }

                Class<?> returnType = method.getReturnType();
                String propertyName = getPropertyName(method);
                Object invoke = method.invoke(object);
                String propertyValue = invoke != null ? invoke.toString() : "null";

                String format;
                if (CharSequence.class.isAssignableFrom(returnType)
                        || Temporal.class.isAssignableFrom(returnType)
                        || java.util.Date.class.isAssignableFrom(returnType)) {
                    format = "\"%s\": \"%s\"";
                } else if (isSimpleClass(returnType)) {
                    format = "\"%s\": %s";
                } else if (returnType.isArray()) {
                    StringBuilder builder = new StringBuilder();
                    format = "\"%s\": [%s]";
                    Class<?> componentType = returnType.getComponentType();
                    Object[] objs = (Object[]) method.invoke(object);
                    boolean appendComma4List = true;
                    if (objs == null) {
                        format = "\"%s\": %s";
                        builder.append("null");
                    } else {
                        for (Object obj : objs) {
                            if (!appendComma4List) {
                                builder.append(",");
                            }
                            builder.append(toJson(obj, componentType));
                            appendComma4List = false;
                        }
                    }

                    propertyValue = builder.toString();
                } else {
                    format = "\"%s\": %s";
                    propertyValue = toJson(method.invoke(object), returnType);
                }
                stringBuilder.append(String.format(format, propertyName, propertyValue));
                appendComma = false;
            }
        }

        return stringBuilder.append("}").toString();
    }

    private static String getPropertyName(Method method) {
        String name = method.getName();
        if (name.startsWith("get")) {
            return Character.toLowerCase(name.charAt(3)) + name.substring(4);
        }
        return Character.toLowerCase(name.charAt(2)) + name.substring(3);
    }

    private static boolean isAccessor(Method method) {
        String name = method.getName();
        return name.startsWith("get") || name.startsWith("is");
    }

    private static <T> boolean isSimpleClass(Class<T> clazz) {
        return (Number.class.isAssignableFrom(clazz)
                || Boolean.class.isAssignableFrom(clazz)
                || clazz.getName().equals("char")
                || clazz.getName().equals("byte")
                || clazz.getName().equals("short")
                || clazz.getName().equals("int")
                || clazz.getName().equals("long")
                || clazz.getName().equals("float")
                || clazz.getName().equals("double")
                || clazz.getName().equals("boolean"));
    }

}
