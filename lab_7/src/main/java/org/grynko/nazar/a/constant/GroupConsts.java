package org.grynko.nazar.a.constant;

public enum GroupConsts {

    GROUP("group"),
    ID("id"),
    SUBJECT("subject");

    private final String value;

    GroupConsts(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
