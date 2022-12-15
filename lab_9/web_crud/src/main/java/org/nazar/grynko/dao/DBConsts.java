package org.nazar.grynko.dao;


public class DBConsts {

    private final String url = "jdbc:mysql://localhost:3306/study-groups?serverTimezone=GMT-6";
    private final String user = "root";
    private final String password = "root";

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
