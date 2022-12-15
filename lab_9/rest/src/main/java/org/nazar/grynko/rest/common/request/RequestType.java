package org.nazar.grynko.rest.common.request;

import java.io.Serializable;

public enum RequestType implements Serializable {

    GROUP("GROUP"),
    STUDENT("STUDENT");

    private final String value;

    RequestType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
