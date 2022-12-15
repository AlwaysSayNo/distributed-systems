package org.nazar.grynko.dao;

import org.nazar.grynko.common.constant.StudentQueryConstants;
import org.nazar.grynko.common.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private final SingleConnectionPool connectionPool;

    public StudentDao(SingleConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Student> getAll()  throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                StudentQueryConstants.SELECT_ALL_STUDENTS.toString());

        return getStudentsFromPS(statement);
    }

    public Student getById(Integer id)  throws SQLException {
        Connection connection = connectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                StudentQueryConstants.SELECT_STUDENT_BY_ID.toString());
        statement.setInt(1, id);

        Student student = null;
        boolean hasResult = statement.execute();

        if(!hasResult) return student;

        ResultSet resultSet = statement.getResultSet();
        if(resultSet.next()) {
            student = getStudentFromRS(resultSet);
        }

        return student;
    }

    public List<Student> getAllByGroupId(Integer groupId)  throws SQLException {
        Connection connection = connectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                StudentQueryConstants.SELECT_ALL_STUDENTS_BY_GROUP_ID.toString());
        statement.setInt(1, groupId);

        return getStudentsFromPS(statement);
    }

    public void save(Student student, Integer groupId)  throws SQLException {
        Connection connection = connectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                StudentQueryConstants.INSERT_STUDENT.toString(), Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setInt(3, groupId);

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) throw new SQLException();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (!generatedKeys.next()) throw new SQLException();

        student.setId(generatedKeys.getInt(1));
    }

    public void deleteById(Integer id)  throws SQLException {
        Connection connection = connectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                StudentQueryConstants.DELETE_STUDENT_BY_ID.toString());
        statement.setInt(1, id);

        statement.executeUpdate();
    }

     public void updateFirstName(Integer id, String firstName)  throws SQLException {
        Connection connection = connectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                StudentQueryConstants.UPDATE_STUDENT_FIRST_NAME.toString());
        statement.setString(1, firstName);
        statement.setInt(2, id);

        statement.executeUpdate();
    }

     public void updateLastName(Integer id, String lastName)  throws SQLException {
        Connection connection = connectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                StudentQueryConstants.UPDATE_STUDENT_LAST_NAME.toString());
        statement.setString(1, lastName);
        statement.setInt(2, id);

        statement.executeUpdate();
    }

     private List<Student> getStudentsFromPS(PreparedStatement statement)  throws SQLException {
        List<Student> students = new ArrayList<>();
        boolean hasResult = statement.execute();

        if(!hasResult) return students;

        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()) {
            Student student = getStudentFromRS(resultSet);

            students.add(student);
        }

        return students;
    }

     private Student getStudentFromRS(ResultSet resultSet)  throws SQLException {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");

        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);

        return student;
    }

}
