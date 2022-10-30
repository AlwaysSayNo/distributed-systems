package org.grynko.nazar.b.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class Group {

    private Integer id;
    private String subject;
    private List<Student> students;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(id).append(") ").append("Group (").append(subject).append(") ").append("amount (").append(students.size()).append("):\n");
        students.forEach(s -> sb.append("\t-").append(s.toString()).append("\n"));

        return sb.toString();
    }
}
