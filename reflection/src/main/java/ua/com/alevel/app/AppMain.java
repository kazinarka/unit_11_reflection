package ua.com.alevel.app;

import ua.com.alevel.app.util.InitUtils;
import ua.com.alevel.app.data.AppProperties;
import ua.com.alevel.app.util.PropertiesUtils;

import java.util.Properties;

public class AppMain {

    public static void main(String[] args) throws Exception {
        PropertiesUtils propertiesUtils = new PropertiesUtils();
        Properties properties = propertiesUtils.loadProperties("app.properties");
        InitUtils initUtils = new InitUtils();
        AppProperties appProperties = initUtils.initialize(AppProperties.class, properties);
        System.out.println(properties);
    }
}
