package ua.com.alevel.app.util;

import ua.com.alevel.app.annotation.PropertyKey;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class InitUtils {

    public static void initialize(Object object) throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = InitUtils.class.getClassLoader().getResourceAsStream("app.properties");
        properties.load(inputStream);
        Class<?> aClass = object.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(PropertyKey.class)) {
                PropertyKey annotation = field.getAnnotation(PropertyKey.class);
                String property = properties.getProperty(annotation.key());
                try {
                    field.setAccessible(true);
                    Class<?> typeOfField = field.getType();
                    if (Boolean.class == typeOfField || Boolean.TYPE == typeOfField)
                        field.set(object, Boolean.parseBoolean(property));
                    else if (Byte.class == typeOfField || Byte.TYPE == typeOfField)
                        field.set(object, Byte.parseByte(property));
                    else if (Short.class == typeOfField || Short.TYPE == typeOfField)
                        field.set(object, Short.parseShort(property));
                    else if (Integer.class == typeOfField || Integer.TYPE == typeOfField)
                        field.set(object, Integer.parseInt(property));
                    else if (Long.class == typeOfField || Long.TYPE == typeOfField)
                        field.set(object, Long.parseLong(property));
                    else if (Float.class == typeOfField || Float.TYPE == typeOfField)
                        field.set(object, Float.parseFloat(property));
                    else if (Double.class == typeOfField || Double.TYPE == typeOfField)
                        field.set(object, Double.parseDouble(property));
                    else if (typeOfField.isEnum())
                        field.set(object, Enum.valueOf((Class<? extends Enum>) typeOfField, property));
                    else if (String.class == typeOfField) field.set(object, property);
                } catch (Exception e) {
                    throw new RuntimeException("Field " + field.getName() + " is unsupported");
                }
            }
        }
    }
}