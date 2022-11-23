package org.grynko.nazar.task_1.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
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
}
