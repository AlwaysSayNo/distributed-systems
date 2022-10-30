package org.grynko.nazar.b.constant;

public enum DBConsts {

    URL("jdbc:mysql://localhost:3306/departemnt?serverTimezone=GMT-6"),
    USER("root"),
    PASSWORD("root");

    private final String value;

    DBConsts(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
