package com.example;

import java.lang.reflect.Method;
import java.time.temporal.Temporal;

public class JsonObject {

    public static <T> String toJson(Object object)
            throws Exception {

        StringBuilder stringBuilder = new StringBuilder("{");
        boolean appendComma = true;

        if (object == null) {
            return "null";
        }
        Class<?> clazz = object.getClass();

        if (isSimpleClass(clazz)) {
            return object.toString();
        } else if (CharSequence.class.isAssignableFrom(clazz)
                || Temporal.class.isAssignableFrom(clazz)
                || java.util.Date.class.isAssignableFrom(clazz)) {
            return "\"" + object.toString() + "\"";
        } else if (clazz.isArray()) {
            return serializeArray2Json((Object[]) object);
        }

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (isAccessor(method)) {
                if (!appendComma) {
                    stringBuilder.append(",");
                }
                String propertyName = getPropertyName(method);
                String propertyValue = toJson(method.invoke(object));
                stringBuilder.append(
                    String.format("\"%s\": %s", propertyName, propertyValue));
                appendComma = false;
            }
        }

        return stringBuilder.append("}").toString();
    }

    private static String serializeArray2Json(Object[] objs) throws Exception {
        if (!objs.getClass().isArray()) {
            throw new RuntimeException("Expect an Array, but " + objs.getClass().getName() + " was given.");
        }
        StringBuilder builder = new StringBuilder("[");
        boolean appendComma4List = true;
        for (Object obj : objs) {
            if (!appendComma4List) {
                builder.append(",");
            }
            builder.append(toJson(obj));
            appendComma4List = false;
        }

        return builder.append("]").toString();
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
                || clazz.isPrimitive());
    }

}
