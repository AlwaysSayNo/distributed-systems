package org.grynko.nazar.a.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class LibraryRepository {

    private final Path path;

    public boolean deleteRecord(Record record) throws IOException {
        List<String> lines = Files.readAllLines(path);
        String recordString = record.toString();

        boolean isRemoved = false;

        List<String> toRemove = new ArrayList<>();
        for(String line: lines) {
            if(recordString.equals(line)) toRemove.add(recordString);
        }

        if(!toRemove.isEmpty()) {
            isRemoved = true;
            lines.removeAll(toRemove);
        }
        if(isRemoved) Files.write(path, lines);

        return isRemoved;
    }

    public void saveRecord(Record record) throws IOException {
        String recordString = record.toString() + "\n";
        Files.write(path, recordString.getBytes(), StandardOpenOption.APPEND);
    }

    public List<String> getPhoneNumbersByFirstName(String lastName) throws IOException {
        List<String> result = new ArrayList<>();

        for(String line : Files.readAllLines(path)) {
            Record record = Record.fromString(line);
            if(record.getLastName().equals(lastName)) result.add(record.getPhoneNumber());
        }

        return result;
    }

    public String getFullNameByPhoneNumber(String phoneNumber) throws IOException {
        String result = null;

        for(String line : Files.readAllLines(path)) {
            Record record = Record.fromString(line);
            if(record.getPhoneNumber().equals(phoneNumber)) {
                result = record.getLastName();
                break;
            }
        }

        return result;
    }

}
