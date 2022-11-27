package org.grynko.nazar.task_1.common.constant;

public enum GroupQueryConstants {

    SELECT_ALL_GROUPS("SELECT * FROM `group`"), // 8
    SELECT_GROUP_BY_ID("SELECT * FROM `group` WHERE `id` = ?"),

    INSERT_GROUP("INSERT INTO `group` (`name`) VALUES (?)"), // 1

    DELETE_GROUP_BY_ID("DELETE FROM `group` WHERE `id` = ?"), // 2
    ;

    private final String value;

    GroupQueryConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static GroupQueryConstants getByName(String name) {
        return GroupQueryConstants.valueOf(name);
    }
}
