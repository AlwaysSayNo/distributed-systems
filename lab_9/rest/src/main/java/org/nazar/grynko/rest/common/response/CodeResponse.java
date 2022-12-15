package org.nazar.grynko.rest.common.response;

import java.io.Serializable;

public class CodeResponse implements Response, Serializable {

    private final String code;

    public CodeResponse(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
