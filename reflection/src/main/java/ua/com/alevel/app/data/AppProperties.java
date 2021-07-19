package ua.com.alevel.app.data;

import ua.com.alevel.app.annotation.PropertyKey;

public class AppProperties {

    @PropertyKey(key = "application.name")
    public String appName;
    @PropertyKey(key = "connections.limit")
    public int maxConnections;
    @PropertyKey(key = "user.login")
    public String login;
    @PropertyKey(key = "user.password")
    public String password;

    @Override
    public String toString() {
        return "AppProperties{" +
                "appName='" + appName + '\'' +
                ", maxConnections=" + maxConnections +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
