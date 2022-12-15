package org.nazar.grynko.rest.common.constant;

public enum StudentParamsConstants {

    STUDENT("student"),
    STUDENTS("students"),

    ID("id"),
    GROUP_ID("group_id"),
    FIRST_NAME("first_name"),
    LAST_NAME("last_name");


    private final String value;

    StudentParamsConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
