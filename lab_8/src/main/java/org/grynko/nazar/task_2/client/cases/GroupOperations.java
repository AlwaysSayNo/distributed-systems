package org.grynko.nazar.task_2.client.cases;

import org.grynko.nazar.task_2.common.constant.GroupQueryConstants;
import org.grynko.nazar.task_2.common.constant.RequestType;
import org.grynko.nazar.task_2.common.request.ParameterizedRequest;

import java.util.Map;

public class GroupOperations {

    public static ParameterizedRequest selectAllGroups(Map<String, Object> parameters) {
        return new ParameterizedRequest(
                GroupQueryConstants.SELECT_ALL_GROUPS.name(), RequestType.GROUP, parameters);
    }

    public static ParameterizedRequest selectGroupById(Map<String, Object> parameters) {
        return new ParameterizedRequest(
                GroupQueryConstants.DELETE_GROUP_BY_ID.name(), RequestType.GROUP, parameters);
    }

    public static ParameterizedRequest insertGroup(Map<String, Object> parameters) {
        return new ParameterizedRequest(
                GroupQueryConstants.INSERT_GROUP.name(), RequestType.GROUP, parameters);
    }

    public static ParameterizedRequest deleteGroupById(Map<String, Object> parameters) {
        return new ParameterizedRequest(
                GroupQueryConstants.DELETE_GROUP_BY_ID.name(), RequestType.GROUP, parameters);
    }

}
