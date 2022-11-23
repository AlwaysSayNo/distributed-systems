package org.grynko.nazar.task_1.server;

import org.grynko.nazar.task_1.constant.*;
import org.grynko.nazar.task_1.dao.GroupDao;
import org.grynko.nazar.task_1.dao.StudentDao;
import org.grynko.nazar.task_1.model.Group;
import org.grynko.nazar.task_1.model.Student;
import org.grynko.nazar.task_1.request.ParameterizedRequest;
import org.grynko.nazar.task_1.response.ParameterizedResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestProcessor {

    private GroupDao groupDao;
    private StudentDao studentDao;

    public ParameterizedResponse getResponse(ParameterizedRequest request) {
        RequestType requestType = request.getType();

        // Is this correct type
        ParameterizedResponse response;
        try {
            switch (requestType) {
                case GROUP: {
                    response = processGroupRequest(request);
                    break;
                }
                case STUDENT: {
                    response = processStudentRequest(request);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unknown RequestType");
                }
            }

            response.setStatus(ResponseStatus.OK);
        } catch (IllegalStateException e) {
            response = new ParameterizedResponse(null, null);
            response.setStatus(ResponseStatus.EXCEPTION);
        }

        return response;
    }

    private ParameterizedResponse processGroupRequest(ParameterizedRequest request) {
        GroupQueryConstants query = GroupQueryConstants.getByName(request.getCode());
        Map<String, Object> requestParams = request.getParameters();

        ParameterizedResponse response;
        Map<String, Object> responseParams = new HashMap<>();

        switch (query) {

            case SELECT_ALL_GROUPS: {
                List<Group> groups = groupDao.getAll();

                responseParams.put(GroupParamsConstants.GROUPS.toString(), groups);

                response = new ParameterizedResponse(
                        GroupQueryConstants.SELECT_ALL_GROUPS.toString(), responseParams);
                break;
            }

            case SELECT_GROUP_BY_ID: {
                Integer id = (Integer) requestParams.get(GroupParamsConstants.ID.toString());
                Group group = groupDao.getById(id);

                responseParams.put(GroupParamsConstants.GROUP.toString(), group);

                response = new ParameterizedResponse(
                        GroupQueryConstants.SELECT_GROUP_BY_ID.toString(), requestParams);
                break;
            }

            case INSERT_GROUP: {
                Group group = (Group) requestParams.get(GroupParamsConstants.GROUP.toString());
                groupDao.save(group);

                responseParams.put(GroupParamsConstants.GROUP.toString(), group);

                response = new ParameterizedResponse(
                        GroupQueryConstants.INSERT_GROUP.toString(), responseParams);
                break;
            }

            case DELETE_GROUP_BY_ID: {
                Integer id = (Integer) requestParams.get(GroupParamsConstants.ID.toString());
                groupDao.deleteById(id);

                response = new ParameterizedResponse(
                        GroupQueryConstants.DELETE_GROUP_BY_ID.toString(), responseParams);
                break;
            }

            default: {
                throw new IllegalArgumentException("Unknown GroupRequest");
            }
        }

        return response;
    }

    private ParameterizedResponse processStudentRequest(ParameterizedRequest request) {
        StudentQueryConstants query = StudentQueryConstants.getByName(request.getCode());
        Map<String, Object> requestParams = request.getParameters();

        ParameterizedResponse response;
        Map<String, Object> responseParams = new HashMap<>();

        switch (query) {

            case SELECT_ALL_STUDENTS: {
                List<Student> students = studentDao.getAll();

                responseParams.put(StudentParamsConstants.STUDENTS.toString(), students);

                response = new ParameterizedResponse(
                        StudentQueryConstants.SELECT_ALL_STUDENTS.toString(), responseParams);
                break;
            }

            case SELECT_ALL_STUDENTS_BY_GROUP_ID: {
                Integer groupId = (Integer) requestParams.get(StudentParamsConstants.GROUP_ID.toString());
                List<Student> students = studentDao.getAllByGroupId(groupId);

                responseParams.put(StudentParamsConstants.STUDENTS.toString(), students);

                response = new ParameterizedResponse(
                        StudentQueryConstants.SELECT_ALL_STUDENTS_BY_GROUP_ID.toString(), responseParams);
                break;
            }

            case SELECT_STUDENT_BY_ID: {
                Integer id = (Integer) requestParams.get(StudentParamsConstants.ID.toString());
                Student student = studentDao.getById(id);

                responseParams.put(StudentParamsConstants.STUDENT.toString(), student);

                response = new ParameterizedResponse(
                        StudentQueryConstants.SELECT_STUDENT_BY_ID.toString(), responseParams);
                break;
            }

            case INSERT_STUDENT: {
                Student student = (Student) requestParams.get(StudentParamsConstants.STUDENT.toString());
                Integer groupId = (Integer) requestParams.get(StudentParamsConstants.GROUP_ID.toString());
                studentDao.save(student, groupId);

                requestParams.put(StudentParamsConstants.STUDENT.toString(), student);

                response = new ParameterizedResponse(
                        StudentQueryConstants.INSERT_STUDENT.toString(), requestParams);
                break;
            }

            case DELETE_STUDENT_BY_ID: {
                Integer id = (Integer) requestParams.get(StudentParamsConstants.ID.toString());
                studentDao.deleteById(id);

                response = new ParameterizedResponse(
                        StudentQueryConstants.DELETE_STUDENT_BY_ID.toString(), responseParams);
                break;
            }

            case UPDATE_STUDENT_FIRST_NAME: {
                Integer id = (Integer) requestParams.get(StudentParamsConstants.ID.toString());
                String firstName = (String) requestParams.get(StudentParamsConstants.FIRST_NAME.toString());

                studentDao.updateFirstName(id, firstName);

                response = new ParameterizedResponse(
                        StudentQueryConstants.UPDATE_STUDENT_FIRST_NAME.toString(), responseParams);
                break;
            }

            case UPDATE_STUDENT_LAST_NAME: {
                Integer id = (Integer) requestParams.get(StudentParamsConstants.ID.toString());
                String lastName = (String) requestParams.get(StudentParamsConstants.LAST_NAME.toString());

                studentDao.updateLastName(id, lastName);

                response = new ParameterizedResponse(
                        StudentQueryConstants.UPDATE_STUDENT_LAST_NAME.toString(), responseParams);
                break;
            }

            default: {
                throw new IllegalArgumentException("Unknown StudentRequest");
            }

        }

        return response;
    }

}
