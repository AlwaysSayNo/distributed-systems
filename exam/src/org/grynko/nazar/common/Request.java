package org.grynko.nazar.common;

import java.io.Serializable;
import java.util.Map;

public class Request implements Serializable {

    private final OperationType operationType;
    private final Map<String, Object> attributes;

    public Request(OperationType operationType, Map<String, Object> attributes) {
        this.operationType = operationType;
        this.attributes = attributes;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

}
