package org.nazar.grynko.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnectionPool {

    private Connection connection;
    private final DBConsts dbConsts;

    public SingleConnectionPool(DBConsts dbConsts) {
        this.dbConsts = dbConsts;
    }

    public Connection getConnection() throws SQLException {
        if(connection == null) {
            connection = DriverManager.getConnection(
                    dbConsts.getUrl(), dbConsts.getUser(), dbConsts.getPassword());
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if(connection != null){
            connection.close();
            connection = null;
        }
    }

}
