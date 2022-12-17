package org.grynko.nazar.common;

import java.io.Serializable;

public enum AttributeType implements Serializable {

    YEAR("year"),
    AUTHOR("author"),
    PUBLISHER("publisher"),
    ;

    private final String value;

    AttributeType(String value) {
        this.value = value;
    }
}
