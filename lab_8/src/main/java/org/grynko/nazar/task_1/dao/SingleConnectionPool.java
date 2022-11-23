package org.grynko.nazar.task_1.dao;

import lombok.SneakyThrows;
import org.grynko.nazar.task_1.constant.DBConsts;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class SingleConnectionPool {

    private static Connection connection;
    private DBConsts dbConsts;

    @SneakyThrows
    public Connection getConnection() {
        if(connection == null) {
            connection = DriverManager.getConnection(
                    dbConsts.getUrl(), dbConsts.getUser(), dbConsts.getPassword());
        }
        return connection;
    }

    @SneakyThrows
    public void closeConnection() {
        if(connection != null){
            connection.close();
            connection = null;
        }
    }

}
