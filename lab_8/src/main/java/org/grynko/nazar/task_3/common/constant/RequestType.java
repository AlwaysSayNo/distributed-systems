package org.grynko.nazar.task_3.common.constant;

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
