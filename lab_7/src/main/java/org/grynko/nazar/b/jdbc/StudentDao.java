package org.grynko.nazar.b.jdbc;

import lombok.SneakyThrows;
import org.grynko.nazar.b.constant.QueryConsts;
import org.grynko.nazar.b.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    @SneakyThrows
    public List<Student> getAll() {
        Connection connection = SingleConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(QueryConsts.SELECT_ALL_STUDENTS.toString());

        return getStudentsFromPS(statement);
    }

    @SneakyThrows
    public Student getById(Integer id) {
        Connection connection = SingleConnectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(QueryConsts.SELECT_STUDENT_BY_ID.toString());
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

    @SneakyThrows
    public List<Student> getAllByGroupId(Integer groupId) {
        Connection connection = SingleConnectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(QueryConsts.SELECT_ALL_STUDENTS_BY_GROUP_ID.toString());
        statement.setInt(1, groupId);

        return getStudentsFromPS(statement);
    }

    @SneakyThrows
    public void save(Student student, Integer groupId) {
        Connection connection = SingleConnectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(QueryConsts.INSERT_STUDENT.toString(),
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getDateOfBirth());
        statement.setInt(4, groupId);

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) throw new SQLException();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (!generatedKeys.next()) throw new SQLException();

        student.setId(generatedKeys.getInt(1));
    }

    @SneakyThrows
    public void deleteById(Integer id) {
        Connection connection = SingleConnectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(QueryConsts.DELETE_STUDENT_BY_ID.toString());
        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @SneakyThrows
    public void deleteByFirstName(String firstName) {
        Connection connection = SingleConnectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(QueryConsts.DELETE_STUDENT_BY_FIRST_NAME.toString());
        statement.setString(1, firstName);

        statement.executeUpdate();
    }

    @SneakyThrows
    public void deleteByLastName(String lastName) {
        Connection connection = SingleConnectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(QueryConsts.DELETE_STUDENT_BY_LAST_NAME.toString());
        statement.setString(1, lastName);

        statement.executeUpdate();
    }

    @SneakyThrows
    public void updateFirstName(Integer id, String firstName) {
        Connection connection = SingleConnectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(QueryConsts.UPDATE_STUDENT_FIRST_NAME.toString());
        statement.setString(1, firstName);
        statement.setInt(2, id);

        statement.executeUpdate();
    }

    @SneakyThrows
    public void updateLastName(Integer id, String lastName) {
        Connection connection = SingleConnectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(QueryConsts.UPDATE_STUDENT_LAST_NAME.toString());
        statement.setString(1, lastName);
        statement.setInt(2, id);

        statement.executeUpdate();
    }

    @SneakyThrows
    private List<Student> getStudentsFromPS(PreparedStatement statement) {
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

    @SneakyThrows
    private Student getStudentFromRS(ResultSet resultSet) {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String dateOfBirth = resultSet.getString("date_of_birth");

        return new Student()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setDateOfBirth(dateOfBirth);
    }

}
