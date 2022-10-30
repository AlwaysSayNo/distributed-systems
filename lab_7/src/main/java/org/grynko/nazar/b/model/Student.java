package org.grynko.nazar.b.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Student {

    private Integer id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    @Override
    public String toString() {
        return id + ") " + firstName + " " + lastName + " (" + dateOfBirth + ")";
    }
}
