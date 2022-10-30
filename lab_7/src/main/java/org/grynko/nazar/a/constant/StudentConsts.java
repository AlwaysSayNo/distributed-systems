package org.grynko.nazar.a.constant;

public enum StudentConsts {

    STUDENT("student"),
    ID("id"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    DATE_OF_BIRTH("dateOfBirth");

    private final String value;

    StudentConsts(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
