package org.nazar.grynko.rest.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class Student implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return id + ") " + firstName + " " + lastName;
    }
}
