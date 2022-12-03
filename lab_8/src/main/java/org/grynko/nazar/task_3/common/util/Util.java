package org.grynko.nazar.task_3.common.util;

import lombok.SneakyThrows;
import org.grynko.nazar.task_3.common.request.ParameterizedRequest;

import java.io.*;

public class Util {

    @SneakyThrows
    public static byte[] serializeObject(ParameterizedRequest request) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(request);
            return byteArrayOutputStream.toByteArray();
        }
    }

    @SneakyThrows
    public static ParameterizedRequest deserializeObject(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return (ParameterizedRequest) objectInputStream.readObject();
        }
    }
}
