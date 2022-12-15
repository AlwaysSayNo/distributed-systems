package org.nazar.grynko.dao;

import org.nazar.grynko.common.constant.GroupParamsConstants;
import org.nazar.grynko.common.constant.GroupQueryConstants;
import org.nazar.grynko.common.model.Group;
import org.nazar.grynko.common.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {

    private final StudentDao studentDao;
    private final SingleConnectionPool connectionPool;

    public GroupDao(StudentDao studentDao, SingleConnectionPool connectionPool) {
        this.studentDao = studentDao;
        this.connectionPool = connectionPool;
    }

    public List<Group> getAll() throws SQLException {
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

    public Group getById(Integer id) throws SQLException {
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

    public void save(Group group) throws SQLException {
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

    public void deleteById(Integer id) throws SQLException {
        Connection connection = connectionPool.getConnection();

        PreparedStatement statement = connection.prepareStatement(
                GroupQueryConstants.DELETE_GROUP_BY_ID.toString());

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    private Group getGroupFromRS(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(GroupParamsConstants.ID.toString());
        String name = resultSet.getString(GroupParamsConstants.NAME.toString());
        List<Student> students = studentDao.getAllByGroupId(id);

        Group group = new Group();
        group.setId(id);
        group.setName(name);
        group.setStudents(students);

        return group;
    }

}
