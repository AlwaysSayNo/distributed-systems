package org.grynko.nazar.b.constant;

public enum QueryConsts {

    SELECT_ALL_GROUPS("SELECT * FROM `group`"),
    SELECT_ALL_STUDENTS("SELECT * FROM `student`"),
    SELECT_GROUP_BY_ID("SELECT * FROM `group` WHERE `id` = ?"),
    SELECT_STUDENT_BY_ID("SELECT * FROM `student` WHERE `id` = ?"),
    SELECT_ALL_STUDENTS_BY_GROUP_ID("SELECT * FROM `student` WHERE `group_id` = ?"),

    INSERT_GROUP("INSERT INTO `group` (`subject`) VALUES (?)"),
    INSERT_STUDENT("INSERT INTO `student` (`first_name`, `last_name`, `date_of_birth`, `group_id`) VALUES (?, ?, ?, ?)"),

    DELETE_STUDENT_BY_ID("DELETE FROM `student` WHERE `id` = ?"),
    DELETE_STUDENT_BY_FIRST_NAME("DELETE FROM `student` WHERE `first_name` = ?"),
    DELETE_STUDENT_BY_LAST_NAME("DELETE FROM `student` WHERE `last_name` = ?"),

    DELETE_GROUP_BY_ID("DELETE FROM `group` WHERE `id` = ?"),

    UPDATE_GROUP_SUBJECT("UPDATE `group` SET `subject` = ? WHERE `id` = ?"),
    UPDATE_STUDENT_FIRST_NAME("UPDATE `student` SET `first_name` = ? WHERE `id` = ?"),
    UPDATE_STUDENT_LAST_NAME("UPDATE `student` SET `last_name` = ? WHERE `id` = ?"),;

    private final String value;

    QueryConsts(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
