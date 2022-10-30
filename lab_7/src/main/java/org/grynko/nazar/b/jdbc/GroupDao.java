package org.grynko.nazar.b.jdbc;

import lombok.SneakyThrows;
import org.grynko.nazar.b.constant.QueryConsts;
import org.grynko.nazar.b.model.Group;
import org.grynko.nazar.b.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {

    private final StudentDao studentDao;

    public GroupDao() {
        this.studentDao = new StudentDao();
    }

    @SneakyThrows
    public List<Group> getAll() {
        Connection connection = SingleConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(QueryConsts.SELECT_ALL_GROUPS.toString());

        List<Group> groups = new ArrayList<>();
        boolean hasResult = statement.execute();

        if(!hasResult) return groups;

        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()) {
            Group group = getGroupFromRS(resultSet);

            groups.add(group);
        }

        return groups;
    }

    @SneakyThrows
    public Group getById(Integer id) {
        Connection connection = SingleConnectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(QueryConsts.SELECT_GROUP_BY_ID.toString());
        statement.setInt(1, id);

        Group group = null;
        boolean hasResult = statement.execute();

        if(!hasResult) return group;

        ResultSet resultSet = statement.getResultSet();
        if(resultSet.next()) {
            group = getGroupFromRS(resultSet);
        }

        return group;
    }

    @SneakyThrows
    public void save(Group group) {
        Connection connection = SingleConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(QueryConsts.INSERT_GROUP.toString(),
                Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, group.getSubject());

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) throw new SQLException();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (!generatedKeys.next()) throw new SQLException();

        group.setId(generatedKeys.getInt(1));

        for(Student student: group.getStudents()) {
            studentDao.save(student, group.getId());
        }
    }

    @SneakyThrows
    public void deleteById(Integer id) {
        Connection connection = SingleConnectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(QueryConsts.DELETE_GROUP_BY_ID.toString());
        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @SneakyThrows
    public void updateSubject(Integer id, String subject) {
        Connection connection = SingleConnectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(QueryConsts.UPDATE_GROUP_SUBJECT.toString());
        statement.setString(1, subject);
        statement.setInt(2, id);

        statement.executeUpdate();
    }

    @SneakyThrows
    private Group getGroupFromRS(ResultSet resultSet) {
        int id = resultSet.getInt("id");
        String subject = resultSet.getString("subject");
        List<Student> students = studentDao.getAllByGroupId(id);

        return new Group()
                .setId(id)
                .setSubject(subject)
                .setStudents(students);
    }

}
