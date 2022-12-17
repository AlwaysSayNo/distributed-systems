package org.nazar.grynko.cases;

import org.nazar.grynko.common.constant.GroupParamsConstants;
import org.nazar.grynko.common.constant.StudentParamsConstants;
import org.nazar.grynko.common.model.Group;
import org.nazar.grynko.common.model.Student;
import org.nazar.grynko.common.request.ParameterizedRequest;
import org.nazar.grynko.processor.RequestProcessor;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Cases {
    
    private RequestProcessor requestProcessor;
    
    public Cases(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    public String selectAllGroup() {
        ParameterizedRequest selectAllRequest = GroupOperations.selectAllGroups(new HashMap<>());
        Map<String, Object> selectAllParameters = requestProcessor.getResponse(selectAllRequest).getParameters();

        List<Group> groups = (List<Group>) selectAllParameters.get(GroupParamsConstants.GROUPS.toString());

        StringBuilder result = new StringBuilder();

        groups.forEach(
                g -> {
                    result.append(g.toString());
                }
        );

        return result.toString();
    }

    // insert group
    public String case1() {
        List<Student> students = Arrays.asList(
                new Student("FIRST_NAME_1", "LAST_NAME_1"),
                new Student("FIRST_NAME_2", "LAST_NAME_2"),
                new Student("FIRST_NAME_3", "LAST_NAME_3")
        );

        Group group = new Group();
        group.setName("NEW GROUP");
        group.setStudents(students);


        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(GroupParamsConstants.GROUP.toString(), group);

        ParameterizedRequest request = GroupOperations.insertGroup(requestParameters);
        Map<String, Object> responseParameters = requestProcessor.getResponse(request).getParameters();

        // output
        return selectAllGroup();
    }

    // delete group
    public String case2() {
        Integer deleteGroupId = 2;

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(GroupParamsConstants.ID.toString(), deleteGroupId);

        ParameterizedRequest request = GroupOperations.deleteGroupById(requestParameters);
        Map<String, Object> responseParameters = requestProcessor.getResponse(request).getParameters();

        // check db
        return selectAllGroup();
    }

    // insert student
    public String case3() {
        Student student = new Student("FIRST_NAME_1", "LAST_NAME_1");
        Integer groupId = 2;

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(StudentParamsConstants.STUDENT.toString(), student);
        requestParameters.put(StudentParamsConstants.GROUP_ID.toString(), groupId);

        ParameterizedRequest request = StudentOperations.insertStudent(requestParameters);
        Map<String, Object> responseParameters = requestProcessor.getResponse(request).getParameters();

        // output
        Student resultStudent = (Student) responseParameters.get(StudentParamsConstants.STUDENT.toString());
        System.out.println(resultStudent.toString());
        return selectAllGroup();
    }

    // delete student
    public String case4() {
        Integer deleteStudentId = 2;

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(StudentParamsConstants.ID.toString(), deleteStudentId);

        ParameterizedRequest request = StudentOperations.deleteStudentById(requestParameters);
        Map<String, Object> responseParameters = requestProcessor.getResponse(request).getParameters();

        // check db
        return selectAllGroup();
    }

    // update student
    public String case5() {
        Integer studentId = 2;
        String firstName = "FIRST_NAME_1";
        String lastName = "LAST_NAME_1";

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(StudentParamsConstants.ID.toString(), studentId);
        requestParameters.put(StudentParamsConstants.FIRST_NAME.toString(), firstName);
        requestParameters.put(StudentParamsConstants.LAST_NAME.toString(), lastName);

        ParameterizedRequest request = null;
        request = StudentOperations.updateStudentByFirstName(requestParameters);
        requestProcessor.getResponse(request);

        request = StudentOperations.updateStudentByLastName(requestParameters);
        requestProcessor.getResponse(request);

        // check db
        return selectAllGroup();
    }

    // get all students by id
    public String case6() {
        Integer groupId = 2;

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(StudentParamsConstants.GROUP_ID.toString(), groupId);

        ParameterizedRequest request = StudentOperations.selectAllStudentsByGroupId(requestParameters);
        Map<String, Object> responseParameters = requestProcessor.getResponse(request).getParameters();

        List<Student> students = (List<Student>) responseParameters.get(StudentParamsConstants.STUDENTS.toString());

        // output
        StringBuilder result = new StringBuilder();
        students.forEach(s -> result.append(s.toString()));

        return result.toString();
    }


    // get all groups
    public String case7() {
        Map<String, Object> requestParameters = new HashMap<>();

        ParameterizedRequest request = GroupOperations.selectAllGroups(requestParameters);
        Map<String, Object> responseParameters = requestProcessor.getResponse(request).getParameters();

        List<Group> groups = (List<Group>) responseParameters.get(GroupParamsConstants.GROUPS.toString());

        // output
        StringBuilder result = new StringBuilder();
        groups.forEach(g -> result.append(g.toString()));

        return result.toString();
    }


}
