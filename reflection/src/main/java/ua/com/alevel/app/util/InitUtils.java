package ua.com.alevel.app.util;

import ua.com.alevel.app.annotation.PropertyKey;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class InitUtils {

    public <T> T initialize(Class<T> c, Properties properties) {
        try{
            Constructor<T> constructor = c.getConstructor();
            T instance = constructor.newInstance();

            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                if(!field.isAnnotationPresent(PropertyKey.class)){
                    continue;
                }
                PropertyKey propertyKey = field.getAnnotation(PropertyKey.class);

                String key = propertyKey.key();
                if(!properties.containsKey(key)){
                    continue;
                }
                String property = properties.getProperty(key);
                Class<?> typeOfField = field.getType();

                if(typeOfField == boolean.class || typeOfField == Boolean.class){
                    field.set(instance, Boolean.parseBoolean(property));
                } else if(typeOfField == byte.class || typeOfField == Byte.class){
                    field.set(instance, Byte.parseByte(property));
                } else if(typeOfField == short.class || typeOfField == Short.class){
                    field.set(instance, Short.parseShort(property));
                } else if(typeOfField == int.class || typeOfField == Integer.class){
                    field.set(instance, Integer.parseInt(property));
                } else if(typeOfField == long.class || typeOfField == Long.class){
                    field.set(instance, Long.parseLong(property));
                } else if(typeOfField == float.class || typeOfField == Float.class){
                    field.set(instance, Float.parseFloat(property));
                } else if(typeOfField == double.class || typeOfField == Double.class) {
                    field.set(instance, Double.parseDouble(property));
                } else if(typeOfField == String.class) {
                    field.set(instance, property);
                } else if(typeOfField == char.class || typeOfField == Character.class){
                    field.set(instance, property.charAt(0));
                } else if(typeOfField.isEnum()){
                    field.set(instance, Enum.valueOf((Class<? extends Enum>) typeOfField, property));
                } else {
                    throw new RuntimeException("Field " + field.getName() + " is unsupported");
                }
            }
            return instance;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}