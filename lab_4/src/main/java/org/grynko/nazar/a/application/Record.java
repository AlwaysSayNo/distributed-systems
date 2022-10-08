package org.grynko.nazar.a.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Record {

    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;

    public String toString() {
        return String.format("%s %s %s %s", firstName, lastName, middleName, phoneNumber);
    }

    public static Record fromString(String str) {
        String[] parts = str.split(" ");
        return new Record(parts[0], parts[1], parts[2], parts[3]);
    }

    public String getFullName() {
        return String.format("%s %s %s", firstName, lastName, middleName);
    }

}
