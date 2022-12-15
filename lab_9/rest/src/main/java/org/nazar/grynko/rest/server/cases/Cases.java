package org.nazar.grynko.rest.server.cases;

import org.nazar.grynko.rest.common.constant.GroupParamsConstants;
import org.nazar.grynko.rest.common.constant.StudentParamsConstants;
import org.nazar.grynko.rest.common.model.Group;
import org.nazar.grynko.rest.common.model.Student;
import org.nazar.grynko.rest.common.request.ParameterizedRequest;
import org.nazar.grynko.rest.server.processor.RequestProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Cases {
    
    private final RequestProcessor requestProcessor;

    public Cases(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    public List<Group> selectAllGroup() {
        ParameterizedRequest selectAllRequest = GroupOperations.selectAllGroups(new HashMap<>());
        Map<String, Object> selectAllParameters = requestProcessor.getResponse(selectAllRequest).getParameters();

        return (List<Group>) selectAllParameters.get(GroupParamsConstants.GROUPS.toString());
    }

    // insert group
    public List<Group> case1() {
        List<Student> students = Arrays.asList(
                new Student().setFirstName("FIRST_NAME_1").setLastName("LAST_NAME_1"),
                new Student().setFirstName("FIRST_NAME_2").setLastName("LAST_NAME_2"),
                new Student().setFirstName("FIRST_NAME_3").setLastName("LAST_NAME_3")
        );

        Group group = new Group()
                .setName("NEW GROUP")
                .setStudents(students);


        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(GroupParamsConstants.GROUP.toString(), group);

        ParameterizedRequest request = GroupOperations.insertGroup(requestParameters);
        requestProcessor.getResponse(request);

        return selectAllGroup();
    }

    // delete group
    public List<Group> case2() {
        Integer deleteGroupId = 2;

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(GroupParamsConstants.ID.toString(), deleteGroupId);

        ParameterizedRequest request = GroupOperations.deleteGroupById(requestParameters);
        Map<String, Object> responseParameters = requestProcessor.getResponse(request).getParameters();

        return selectAllGroup();
    }

    // insert student
    public List<Group> case3() {
        Student student = new Student()
                .setFirstName("FIRST_NAME_1")
                .setLastName("LAST_NAME_1");
        Integer groupId = 2;

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(StudentParamsConstants.STUDENT.toString(), student);
        requestParameters.put(StudentParamsConstants.GROUP_ID.toString(), groupId);

        ParameterizedRequest request = StudentOperations.insertStudent(requestParameters);
        Map<String, Object> responseParameters = requestProcessor.getResponse(request).getParameters();

        // output
        return selectAllGroup();
    }

    // delete student
    public List<Group> case4() {
        Integer deleteStudentId = 2;

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(StudentParamsConstants.ID.toString(), deleteStudentId);

        ParameterizedRequest request = StudentOperations.deleteStudentById(requestParameters);
        Map<String, Object> responseParameters = requestProcessor.getResponse(request).getParameters();

        // check db
        return selectAllGroup();
    }

    // update student
    public List<Group> case5() {
        Integer studentId = 2;
        String firstName = "FIRST_NAME_1";
        String lastName = "LAST_NAME_1";

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(StudentParamsConstants.ID.toString(), studentId);
        requestParameters.put(StudentParamsConstants.FIRST_NAME.toString(), firstName);
        requestParameters.put(StudentParamsConstants.LAST_NAME.toString(), lastName);

        ParameterizedRequest request = null;
        request = StudentOperations.updateStudentByFirstName(requestParameters);
        requestProcessor.getResponse(request).getParameters();

        request = StudentOperations.updateStudentByLastName(requestParameters);
        requestProcessor.getResponse(request).getParameters();

        // check db
        return selectAllGroup();
    }

    // get all students by id
    public List<Group> case6() {
        Integer groupId = 2;

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(StudentParamsConstants.GROUP_ID.toString(), groupId);

        ParameterizedRequest request = StudentOperations.selectAllStudentsByGroupId(requestParameters);
        Map<String, Object> responseParameters = requestProcessor.getResponse(request).getParameters();

        return selectAllGroup();
    }


    // get all groups
    public List<Group> case7() {
        Map<String, Object> requestParameters = new HashMap<>();

        ParameterizedRequest request = GroupOperations.selectAllGroups(requestParameters);
        Map<String, Object> responseParameters = requestProcessor.getResponse(request).getParameters();

        List<Group> groups = (List<Group>) responseParameters.get(GroupParamsConstants.GROUPS.toString());

        return selectAllGroup();
    }


}
