package org.grynko.nazar.b.jdbc;

import lombok.SneakyThrows;
import org.grynko.nazar.b.constant.DBConsts;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionPool {

    private static Connection connection;

    @SneakyThrows
    public static Connection getConnection() {
        if(connection == null) {
            connection = DriverManager.getConnection(DBConsts.URL.toString(), DBConsts.USER.toString(), DBConsts.PASSWORD.toString());
        }
        return connection;
    }

    @SneakyThrows
    public static void closeConnection() {
        if(connection != null){
            connection.close();
        }
    }

}
