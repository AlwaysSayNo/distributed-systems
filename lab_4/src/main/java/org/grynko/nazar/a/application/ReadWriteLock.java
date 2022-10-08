package org.grynko.nazar.a.application;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReadWriteLock {

    private final Map<Thread, Integer> readingThreads = new HashMap<>();
    private Integer writeRequests = 0;
    private Integer writerAccess = 0;
    private Thread writingThread = null;

    public synchronized void lockRead() throws InterruptedException {
        Thread callingTread = Thread.currentThread();
        while(!hasReadAccess(callingTread)) wait();

        readingThreads.put(callingTread, getAccessCount(callingTread) + 1);
    }

    public synchronized void unlockRead() throws IllegalAccessException {
        Thread callingThread = Thread.currentThread();
        if(!isReader(callingThread))
            throw new IllegalMonitorStateException("Calling Thread does not hold a read lock on this ReadWriteLock");

        Integer count = readingThreads.get(callingThread);


        if(count == 1) readingThreads.remove(callingThread);
        else readingThreads.put(callingThread, getAccessCount(callingThread) - 1);

        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        writeRequests++;

        while(!hasWriteAccess(callingThread)) wait();

        writeRequests--;
        writerAccess++;
        writingThread = callingThread;
    }

    public synchronized void unlockWrite() throws InterruptedException, IllegalAccessException {
        Thread callingThread = Thread.currentThread();
        if(!isWriter(callingThread))
            throw new IllegalMonitorStateException("Calling Thread does not hold a read lock on this ReadWriteLock");

        writerAccess--;
        if(writerAccess == 0) writingThread = null;

        notifyAll();
    }

    private boolean hasWriteAccess(Thread callingThread) {
        if(isWriter(callingThread)) return true;
        else if(hasReaders()) return false;
        else if(writingThread == null) return true;
        else if(!isWriter(callingThread)) return false;
        return false;
    }

    private boolean hasReadAccess(Thread callingThread) {
        if(isOnlyReader(callingThread)) return true;
        else if(writerAccess != 0) return false;
        else if(isReader(callingThread)) return true;
        else if(writeRequests != 0) return false;
        return true;
    }

    private boolean isReader(Thread callingThread) {
        return getAccessCount(callingThread) != 0;
    }

    private Integer getAccessCount(Thread callingTread) {
        Integer count = readingThreads.get(callingTread);

        if(count == null) return 0;
        return count;
    }

    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }

    private boolean hasReaders() {
        return readingThreads.size() != 0;
    }

    private boolean isOnlyReader(Thread callingThread){
        return readingThreads.size() == 1 && readingThreads.get(callingThread) != null;
    }

}
