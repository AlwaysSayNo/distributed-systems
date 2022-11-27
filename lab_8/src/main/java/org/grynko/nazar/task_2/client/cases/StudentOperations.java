package org.grynko.nazar.task_2.client.cases;

import org.grynko.nazar.task_2.common.constant.RequestType;
import org.grynko.nazar.task_2.common.constant.StudentQueryConstants;
import org.grynko.nazar.task_2.common.request.ParameterizedRequest;

import java.util.Map;

public class StudentOperations {
    public static ParameterizedRequest selectAllStudents(Map<String, Object> parameters) {
        return new ParameterizedRequest(
                StudentQueryConstants.SELECT_ALL_STUDENTS.name(), RequestType.STUDENT, parameters);
    }

    public static ParameterizedRequest selectAllStudentsByGroupId(Map<String, Object> parameters) {
        return new ParameterizedRequest(
                StudentQueryConstants.SELECT_ALL_STUDENTS_BY_GROUP_ID.name(), RequestType.STUDENT, parameters);
    }

    public static ParameterizedRequest selectStudentById(Map<String, Object> parameters) {
        return new ParameterizedRequest(
                StudentQueryConstants.SELECT_STUDENT_BY_ID.name(), RequestType.STUDENT, parameters);
    }

    public static ParameterizedRequest insertStudent(Map<String, Object> parameters) {
        return new ParameterizedRequest(
                StudentQueryConstants.INSERT_STUDENT.name(), RequestType.STUDENT, parameters);
    }

    public static ParameterizedRequest deleteStudentById(Map<String, Object> parameters) {
        return new ParameterizedRequest(
                StudentQueryConstants.DELETE_STUDENT_BY_ID.name(), RequestType.STUDENT, parameters);
    }

    public static ParameterizedRequest updateStudentByFirstName(Map<String, Object> parameters) {
        return new ParameterizedRequest(
                StudentQueryConstants.UPDATE_STUDENT_FIRST_NAME.name(), RequestType.STUDENT, parameters);
    }

    public static ParameterizedRequest updateStudentByLastName(Map<String, Object> parameters) {
        return new ParameterizedRequest(
                StudentQueryConstants.UPDATE_STUDENT_LAST_NAME.name(), RequestType.STUDENT, parameters);
    }
}
