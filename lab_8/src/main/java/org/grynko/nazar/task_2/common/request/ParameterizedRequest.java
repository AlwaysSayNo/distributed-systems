package org.grynko.nazar.task_2.common.request;

import org.grynko.nazar.task_2.common.constant.RequestType;

import java.util.Map;

public class ParameterizedRequest extends CodeRequest {

    private final Map<String, Object> parameters;


    public ParameterizedRequest(String code, RequestType type, Map<String, Object> parameters) {
        super(code, type);
        this.parameters = parameters;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
