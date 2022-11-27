package org.grynko.nazar.task_1.server.dao;

import lombok.SneakyThrows;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;

@Component
public class DBInitializer {

    private final SingleConnectionPool connectionPool;
    @Value("${sql.create.tables.script}")
    private String createScript;
    @Value("${sql.insert.groups.students.script}")
    private String insertScript;

    public DBInitializer(SingleConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @SneakyThrows
    @PostConstruct
    private void init() {
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
