package org.grynko.nazar.a.constant;

public enum DepartmentConsts {

    DEPARTMENT("department");

    private final String value;

    DepartmentConsts(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
