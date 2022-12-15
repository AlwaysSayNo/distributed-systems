package org.nazar.grynko.rest.common.constant;

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
