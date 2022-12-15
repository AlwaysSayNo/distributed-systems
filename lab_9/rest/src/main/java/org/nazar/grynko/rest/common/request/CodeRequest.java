package org.nazar.grynko.rest.common.request;

import java.io.Serializable;

public class CodeRequest implements Request, Serializable {

    private final String code;
    private final RequestType type;

    public CodeRequest(String code, RequestType type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public RequestType getType() {
        return type;
    }
}
