package org.grynko.nazar.a.application;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class AllOperations {

    private final List<String> allLastNames;
    private final List<String> allPhoneNumbers;
    private final List<Record> allAddRecords;
    private final List<Record> allDeleteRecords;

    public AllOperations() {
        this.allLastNames = new LinkedList<>(Arrays.asList("L1",
                                                           "L2",
                                                           "L3",
                                                           "L4",
                                                           "L5",
                                                           "L6",
                                                           "L7",
                                                           "L8",
                                                           "L9",
                                                           "L1",
                                                           "L2",
                                                           "L3",
                                                           "L4",
                                                           "L5",
                                                           "L6",
                                                           "L7",
                                                           "L8",
                                                           "L9"));
        this.allPhoneNumbers = new LinkedList<>(Arrays.asList("+30501111111",
                                                              "+30502222222",
                                                              "+30505555555",
                                                              "+30505555555",
                                                              "+30507777777",
                                                              "+30967777777",
                                                              "+30509999999",
                                                              "+30969999999"));
        this.allAddRecords = new LinkedList<>(Arrays.asList(
                new Record("N2", "L2", "M2", "+30502222222"),
                new Record("N5", "L5", "M5", "+30505555555"),
                new Record("N7", "L7", "M7", "+30507777777"),
                new Record("N7", "L7", "M7", "+30967777777"),
                new Record("N9", "L9", "M9", "+30509999999"),
                new Record("N9", "L9", "M9", "+30969999999")
        ));

        this.allDeleteRecords = new LinkedList<>(Arrays.asList(
                new Record("N1", "L1", "M1", "+30501111111"),
                new Record("N4", "L4", "M4", "+30504444444"),
                new Record("N5", "L5", "M5", "+30505555555"),
                new Record("N6", "L6", "M6", "+30506666666"),
                new Record("N7", "L7", "M7", "+30507777777"),
                new Record("N7", "L7", "M7", "+30967777777")
        ));

    }

    public synchronized String getLastName() {
        if(allLastNames.size() < 1) return null;
        return allLastNames.remove(0);
    }

    public synchronized void putBackLastName(String lastName) {
        allLastNames.add(lastName);
    }

    public synchronized String getPhoneNumber() {
        if(allPhoneNumbers.size() < 1) return null;
        return allPhoneNumbers.remove(0);
    }

    public synchronized void putBackPhoneNumber(String phoneNumber) {
        allPhoneNumbers.add(phoneNumber);
    }

    public synchronized Record getAddRecord() {
        if(allAddRecords.size() < 1) return null;
        return allAddRecords.remove(0);
    }

    public synchronized Record getDeleteRecord() {
        if(allDeleteRecords.size() < 1) return null;
        return allDeleteRecords.remove(0);
    }

    public synchronized void putBackDeleteRecord(Record record) {
        allDeleteRecords.add(record);
    }

    public synchronized boolean isEmpty() {
        return allLastNames.isEmpty() && allPhoneNumbers.isEmpty()
                && allAddRecords.isEmpty() && allDeleteRecords.isEmpty();
    }

}
