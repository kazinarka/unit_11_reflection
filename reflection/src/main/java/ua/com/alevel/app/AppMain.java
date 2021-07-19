package ua.com.alevel.app;

import ua.com.alevel.app.util.InitUtils;
import ua.com.alevel.app.data.AppProperties;

public class AppMain {

    public static void main(String[] args) throws Exception {
        AppProperties properties = new AppProperties();
        InitUtils.initialize(properties);
        System.out.println(properties);
    }
}
