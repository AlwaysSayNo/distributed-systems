package org.grynko.nazar.task_2.server.dao;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class SingleConnectionPool {

    private static Connection connection;
    private final DBConsts dbConsts;

    public SingleConnectionPool(DBConsts dbConsts) {
        this.dbConsts = dbConsts;
    }

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

    @PreDestroy
    private void destroy() {
        System.out.println("==> Server#SingleConnectionPool#PreDestroy");
        closeConnection();
    }

}
