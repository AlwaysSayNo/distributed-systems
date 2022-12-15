package org.nazar.grynko.common.model;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {

    private Integer id;
    private String name;
    private List<Student> students;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(id).append(") ")
                .append("Group (").append(name).append(") ")
                .append("amount (").append(students.size()).append("):\n");

        students.forEach(s -> sb.append("\t-")
                .append(s.toString()).append("\n"));

        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
