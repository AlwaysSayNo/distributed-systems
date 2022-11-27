package org.grynko.nazar.task_2.common.constant;

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
