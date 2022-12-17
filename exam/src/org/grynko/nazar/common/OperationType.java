package org.grynko.nazar.common;

import java.io.Serializable;

public enum OperationType implements Serializable {

    FIND_BOOKS_BY_AUTHOR("find books by author"),
    FIND_BOOKS_BY_PUBLISHER("find books by publisher"),
    FIND_BOOKS_AFTER_YEAR("find books after year"),
    CLOSE("close")
    ;

    private final String value;

    OperationType(String value) {
        this.value = value;
    }

}
