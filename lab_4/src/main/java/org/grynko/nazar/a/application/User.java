package org.grynko.nazar.a.application;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.interrupted;

@Component
@RequiredArgsConstructor
public class User implements Runnable {

    @Value("${read.probability}")
    private Double readProbability;

    private final LibraryService libraryService;
    private final AllOperations allOperations;

    @Override
    public void run() {
        Random random = new Random();

        while(!interrupted()) {
            justSleep();
            if(allOperations.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " is over.");
                break;
            }

            Double probability = random.nextDouble();

            if(readProbability > probability) {
                if(random.nextInt() % 2 == 0) {
                    String lastName = allOperations.getLastName();
                    if(noSuchOperation(lastName)) {
                        sayNoSuchOperation(Operation.READ_PHONE_NUMBERS.name());
                        continue;
                    }

                    String result = readPhoneNumbers(lastName);
                    System.out.println(result);
                }
                else {
                    String phoneNumber = allOperations.getPhoneNumber();
                    if(noSuchOperation(phoneNumber)) {
                        sayNoSuchOperation(Operation.READ_FULL_NAME.name());
                        continue;
                    }

                    String result = readFullName(phoneNumber);
                    System.out.println(result);
                }
            }
            else {
                if(random.nextInt() % 2 == 0) {
                    Record record = allOperations.getAddRecord();
                    if(noSuchOperation(record)) {
                        sayNoSuchOperation(Operation.ADD_RECORD.name());
                        continue;
                    }

                    String result = addRecord(record);
                    System.out.println(result);
                }
                else {
                    Record record = allOperations.getDeleteRecord();
                    if(noSuchOperation(record)) {
                        sayNoSuchOperation(Operation.DELETE_RECORD.name());
                        continue;
                    }

                    String result = deleteRecord(record);
                    System.out.println(result);
                }
            }
        }
    }

    private String readPhoneNumbers(String lastName) {
        List<String> phoneNumbers = libraryService.getPhoneNumbers(lastName);

        StringBuilder sb = new StringBuilder();

        if(phoneNumbers == null || phoneNumbers.isEmpty()) {
//            allOperations.putBackLastName(lastName);

            sb.append(Thread.currentThread().getName())
                    .append( " does not find any number by name (")
                    .append(lastName)
                    .append(")");
        }
        else {
            sb.append(Thread.currentThread().getName())
                    .append(" found so many phone numbers (")
                    .append(phoneNumbers.size())
                    .append(") by name (")
                    .append(lastName)
                    .append("): ");

            phoneNumbers.forEach((phone) -> sb.append("[").append(phone).append("] "));
        }

        return sb.toString();
    }

    private String readFullName(String phoneNumber) {
        String fullName = libraryService.getFullName(phoneNumber);

        StringBuilder sb = new StringBuilder();

        if(fullName == null) {
//            allOperations.putBackPhoneNumber(phoneNumber);

            sb.append(Thread.currentThread().getName())
                    .append( " does not find any person by phone number (")
                    .append(phoneNumber)
                    .append(")");
        }
        else {
            sb.append(Thread.currentThread().getName())
                    .append(" found such person (")
                    .append(fullName)
                    .append(") by phone number (")
                    .append(phoneNumber)
                    .append(")");
        }

        return sb.toString();
    }

    private String addRecord(Record record) {
        libraryService.addRecord(record);

        StringBuilder sb = new StringBuilder();

        sb.append(Thread.currentThread().getName())
                .append(" added record (")
                .append(record)
                .append(")");

        return sb.toString();
    }

    private String deleteRecord(Record record) {
        boolean isDeleted = libraryService.deleteRecord(record);

        StringBuilder sb = new StringBuilder();

        if(isDeleted) {
            sb.append(Thread.currentThread().getName())
                    .append(" deleted record (")
                    .append(record)
                    .append(")");
        }
        else {
            allOperations.putBackDeleteRecord(record);

            sb.append(Thread.currentThread().getName())
                    .append(" couldn't find the record (")
                    .append(record)
                    .append(") to delete it");
        }

        return sb.toString();
    }

    private void sayNoSuchOperation(String operation) {
        String message = String.format("%s tried to execute the operation (%s), which has already ended.",
                Thread.currentThread().getName(), operation);
        //System.out.println(message);
    }

    private boolean noSuchOperation(Object value) {
        return value == null;
    }

    @SneakyThrows
    private void justSleep() {
        TimeUnit.SECONDS.sleep(1);
    }

}
