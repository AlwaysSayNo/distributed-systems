package org.grynko.nazar.task_1.common.response;

import org.grynko.nazar.task_1.common.constant.ResponseStatus;

import java.util.Map;

public class ParameterizedResponse extends CodeResponse {

    private final Map<String, Object> parameters;
    private ResponseStatus status;

    public ParameterizedResponse(String code, Map<String, Object> parameters) {
        super(code);
        this.parameters = parameters;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
