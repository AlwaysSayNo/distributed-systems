package org.grynko.nazar.task_3.server.dao;

import lombok.SneakyThrows;
import org.grynko.nazar.task_3.common.constant.GroupParamsConstants;
import org.grynko.nazar.task_3.common.constant.GroupQueryConstants;
import org.grynko.nazar.task_3.common.model.Group;
import org.grynko.nazar.task_3.common.model.Student;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupDao {

    private final StudentDao studentDao;
    private final SingleConnectionPool connectionPool;

    public GroupDao(StudentDao studentDao, SingleConnectionPool connectionPool) {
        this.studentDao = studentDao;
        this.connectionPool = connectionPool;
    }

    @SneakyThrows
    public List<Group> getAll() {
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                GroupQueryConstants.SELECT_ALL_GROUPS.toString());

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
        Connection connection = connectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                GroupQueryConstants.SELECT_GROUP_BY_ID.toString());

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
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                GroupQueryConstants.INSERT_GROUP.toString(), Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, group.getName());

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
        Connection connection = connectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                GroupQueryConstants.DELETE_GROUP_BY_ID.toString());

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @SneakyThrows
    private Group getGroupFromRS(ResultSet resultSet) {
        int id = resultSet.getInt(GroupParamsConstants.ID.toString());
        String name = resultSet.getString(GroupParamsConstants.NAME.toString());
        List<Student> students = studentDao.getAllByGroupId(id);

        return new Group()
                .setId(id)
                .setName(name)
                .setStudents(students);
    }

}
