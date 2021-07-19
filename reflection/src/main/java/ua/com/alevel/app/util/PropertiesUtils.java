package ua.com.alevel.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertiesUtils {

    public Properties loadProperties(String name){
        Properties properties = new Properties();
        try(InputStream in = getClass().getClassLoader().getResourceAsStream(name)){
            properties.load(in);
            if (in != null) {
                in.close();
            }
        } catch (IOException ex){
            throw new UncheckedIOException(ex);
        }
        return properties;
    }
}
