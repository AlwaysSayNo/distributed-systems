package org.grynko.nazar.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface PBBuilder {

    static ProcessBuilder build(Class clazz, List<String> jvmArgs, List<String> args) {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classPath = System.getProperty("java.class.path");
        String className = clazz.getName();

        List<String> command = new ArrayList<>();
        command.add(javaBin);
        command.addAll(jvmArgs);
        command.add("-cp");
        command.add(classPath);
        command.add(className);
        command.addAll(args);

        return new ProcessBuilder(command);
    }

}
