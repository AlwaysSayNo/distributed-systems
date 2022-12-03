package org.grynko.nazar.task_3.client.cases;

import org.grynko.nazar.task_3.common.constant.GroupParamsConstants;
import org.grynko.nazar.task_3.common.constant.StudentParamsConstants;
import org.grynko.nazar.task_3.common.model.Group;
import org.grynko.nazar.task_3.common.model.Student;
import org.grynko.nazar.task_3.common.request.ParameterizedRequest;
import org.grynko.nazar.task_3.client.controller.ClientFrontController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cases {

    // insert group
    public static void case1(ClientFrontController controller) {
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
        controller.process(request);
    }

    // delete group
    public static void case2(ClientFrontController controller) {
        Integer deleteGroupId = 2;

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(GroupParamsConstants.ID.toString(), deleteGroupId);

        ParameterizedRequest request = GroupOperations.deleteGroupById(requestParameters);
        controller.process(request);
    }

    // insert student
    public static void case3(ClientFrontController controller) {
        Student student = new Student()
                .setFirstName("FIRST_NAME_1")
                .setLastName("LAST_NAME_1");
        Integer groupId = 2;

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(StudentParamsConstants.STUDENT.toString(), student);
        requestParameters.put(StudentParamsConstants.GROUP_ID.toString(), groupId);

        ParameterizedRequest request = StudentOperations.insertStudent(requestParameters);
        controller.process(request);
        }

    // delete student
    public static void case4(ClientFrontController controller) {
        Integer deleteStudentId = 2;

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(StudentParamsConstants.ID.toString(), deleteStudentId);

        ParameterizedRequest request = StudentOperations.deleteStudentById(requestParameters);
        controller.process(request);
        }

    // update student
    public static void case5(ClientFrontController controller) {
        Integer studentId = 2;
        String firstName = "FIRST_NAME_1";
        String lastName = "LAST_NAME_1";

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(StudentParamsConstants.ID.toString(), studentId);
        requestParameters.put(StudentParamsConstants.FIRST_NAME.toString(), firstName);
        requestParameters.put(StudentParamsConstants.LAST_NAME.toString(), lastName);

        ParameterizedRequest request = null;
        request = StudentOperations.updateStudentByFirstName(requestParameters);
        controller.process(request);

        request = StudentOperations.updateStudentByLastName(requestParameters);
        controller.process(request);
    }

    // get all students by id
    public static void case6(ClientFrontController controller) {
        Integer groupId = 2;

        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put(StudentParamsConstants.GROUP_ID.toString(), groupId);

        ParameterizedRequest request = StudentOperations.selectAllStudentsByGroupId(requestParameters);
        controller.process(request);
        }


    // get all groups
    public static void case7(ClientFrontController controller) {
        Map<String, Object> requestParameters = new HashMap<>();

        ParameterizedRequest request = GroupOperations.selectAllGroups(requestParameters);
        controller.process(request);
    }


}
