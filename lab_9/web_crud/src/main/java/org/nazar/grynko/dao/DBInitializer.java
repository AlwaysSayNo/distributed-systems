package org.nazar.grynko.dao;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class DBInitializer {

    private final SingleConnectionPool connectionPool;
    private final String createScript = "C:/Users/Admin/source/java-repos/distributed-systems/lab_9/web_crud/src/main/resources/sql/create-tables.sql";
    private final String insertScript = "C:/Users/Admin/source/java-repos/distributed-systems/lab_9/web_crud/src/main/resources/sql/insert-groups-students.sql";

    public DBInitializer(SingleConnectionPool connectionPool) {
        this.connectionPool = connectionPool;

        try {
            init();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void init() throws SQLException, FileNotFoundException {
        System.out.println("==> Init db start");
        Connection connection = connectionPool.getConnection();
        ScriptRunner sr = new ScriptRunner(connection);
        sr.setLogWriter(null);

        Reader createReader = new BufferedReader(new FileReader(createScript));
        sr.runScript(createReader);

        Reader insertReader = new BufferedReader(new FileReader(insertScript));
        sr.runScript(insertReader);

        connectionPool.closeConnection();
        System.out.println("==> Init db finish");
    }


}
