package org.nazar.grynko.cases;

import org.nazar.grynko.common.constant.GroupQueryConstants;
import org.nazar.grynko.common.request.ParameterizedRequest;
import org.nazar.grynko.common.request.RequestType;

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
