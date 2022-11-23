package org.grynko.nazar.task_1.constant;

public enum StudentQueryConstants {

    SELECT_ALL_STUDENTS("SELECT * FROM `student`"), // 9
    SELECT_ALL_STUDENTS_BY_GROUP_ID("SELECT * FROM `student` WHERE `group_id` = ?"), // 7
    SELECT_STUDENT_BY_ID("SELECT * FROM `student` WHERE `id` = ?"),

    INSERT_STUDENT("INSERT INTO `student` (`first_name`, `last_name`, `group_id`) VALUES (?, ?, ?)"), // 3

    DELETE_STUDENT_BY_ID("DELETE FROM `student` WHERE `id` = ?"), // 4

    UPDATE_STUDENT_FIRST_NAME("UPDATE `student` SET `first_name` = ? WHERE `id` = ?"), // 6
    UPDATE_STUDENT_LAST_NAME("UPDATE `student` SET `last_name` = ? WHERE `id` = ?"), // 6
    ;

    private final String value;

    StudentQueryConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static StudentQueryConstants getByName(String name) {
        return StudentQueryConstants.valueOf(name);
    }
}
