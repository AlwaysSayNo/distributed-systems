package org.grynko.nazar.task_1.constant;

public enum GroupParamsConstants {

    GROUPS("groups"),
    GROUP("group"),
    ID("id"),
    NAME("name");


    private final String value;

    GroupParamsConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
