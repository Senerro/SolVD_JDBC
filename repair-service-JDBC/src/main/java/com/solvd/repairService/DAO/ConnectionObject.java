package com.solvd.repairService.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConnectionObject {
    private final String driver;
    private final String url;
    private final String password;
    private final String username;
    private final String path;

    public ConnectionObject(String path) {
        this.path = path;
        var props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(this.path))) {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.driver = props.getProperty("driver");
        this.url = props.getProperty("url");
        this.username = props.getProperty("username");
        this.password = props.getProperty("password");
    }

    public String driver() {
        return driver;
    }

    public String password() {
        return password;
    }

    public String path() {
        return path;
    }

    public String url() {
        return url;
    }

    public String username() {
        return username;
    }
}
