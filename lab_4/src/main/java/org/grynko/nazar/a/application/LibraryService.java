package org.grynko.nazar.a.application;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class LibraryService {

    private LibraryRepository libraryRepository;
    private ReadWriteLock readWriteLock;

    public List<String> getPhoneNumbers(String lastName) {
        List<String> result = null;

        try {
            readWriteLock.lockRead();
            result = libraryRepository.getPhoneNumbersByFirstName(lastName);
            readWriteLock.unlockRead();
        } catch (InterruptedException | IllegalAccessException e) {
            System.out.println("Something went wrong while work with lock.");
            throw new IllegalStateException();
        } catch (IOException e) {
            System.out.println("Something went wrong while work with file.");
            throw new IllegalStateException();
        }

        return result;
    }

    public String getFullName(String phoneNumber) {
        String result = null;

        try {
            readWriteLock.lockRead();
            result = libraryRepository.getFullNameByPhoneNumber(phoneNumber);
            readWriteLock.unlockRead();
        } catch (InterruptedException | IllegalAccessException e) {
            System.out.println("Something went wrong while work with lock.");
            throw new IllegalStateException();
        } catch (IOException e) {
            System.out.println("Something went wrong while work with file.");
            throw new IllegalStateException();
        }

        return result;
    }

    public void addRecord(Record record) {
        try {
            readWriteLock.lockWrite();
            libraryRepository.saveRecord(record);
            readWriteLock.unlockWrite();
        } catch (InterruptedException | IllegalAccessException e) {
            System.out.println("Something went wrong while work with lock.");
            throw new IllegalStateException();
        } catch (IOException e) {
            System.out.println("Something went wrong while work with file.");
            throw new IllegalStateException();
        }
    }

    public boolean deleteRecord(Record record) {
        boolean isSuccess = false;

        try {
            readWriteLock.lockWrite();
            isSuccess = libraryRepository.deleteRecord(record);
            readWriteLock.unlockWrite();
        } catch (InterruptedException | IllegalAccessException e) {
            System.out.println("Something went wrong while work with lock.");
            throw new IllegalStateException();
        } catch (IOException e) {
            System.out.println("Something went wrong while work with file.");
            throw new IllegalStateException();
        }

        return isSuccess;
    }

}
