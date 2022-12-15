package org.nazar.grynko.common.response;

import java.io.Serializable;

public enum ResponseStatus implements Serializable {

    OK("OK"),
    EXCEPTION("EXCEPTION");

    private final String value;

    ResponseStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
