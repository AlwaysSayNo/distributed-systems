package org.grynko.nazar.b;

import org.grynko.nazar.b.jdbc.GroupDao;
import org.grynko.nazar.b.jdbc.StudentDao;
import org.grynko.nazar.b.model.Group;
import org.grynko.nazar.b.model.Student;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final GroupDao groupDao = new GroupDao();
    private static final StudentDao studentDao = new StudentDao();

    public static void main(String[] args) {
        scenario4();
    }

    public static void scenario1() {
        List<Group> groups = groupDao.getAll();
        List<Student> students = studentDao.getAll();

        System.out.printf("==> All groups (%d):\n", groups.size());
        groups.forEach(System.out::println);

        System.out.printf("==> All students (%d):\n", students.size());
        students.forEach(System.out::println);
    }

    public static void scenario2() {
        Student[] students1 = new Student[] {
                new Student().setFirstName("FN1").setLastName("LN1").setDateOfBirth("DOB1"),
                new Student().setFirstName("FN2").setLastName("LN2").setDateOfBirth("DOB2")
        };

        Group group = new Group().setSubject("subject").setStudents(Arrays.asList(students1));

        groupDao.save(group);

        Group theSameGroup = groupDao.getById(group.getId());

        System.out.println("Group:");
        System.out.println(group);
        System.out.println("The Same Group:");
        System.out.println(theSameGroup);

        groupDao.deleteById(group.getId());
    }

    public static void scenario3() {
        Student[] students1 = new Student[] {
                new Student().setFirstName("FN1").setLastName("LN1").setDateOfBirth("DOB1"),
                new Student().setFirstName("FN2").setLastName("LN2").setDateOfBirth("DOB2")
        };

        Group group = new Group().setSubject("Some").setStudents(Arrays.asList(students1));

        groupDao.save(group);

        studentDao.updateFirstName(students1[0].getId(), "FN_1");
        System.out.println("students1[0]_UPDATED:");
        System.out.println(studentDao.getById(students1[0].getId()));

        studentDao.updateLastName(students1[1].getId(), "LN_2");
        System.out.println("students1[1]_UPDATED:");
        System.out.println(studentDao.getById(students1[1].getId()));

        groupDao.updateSubject(group.getId(), "SOME");
        System.out.println("group_UPDATED:");
        System.out.println(groupDao.getById(group.getId()));

        groupDao.deleteById(group.getId());
    }

    private static void scenario4() {
        Student[] students1 = new Student[] {
                new Student().setFirstName("FN1").setLastName("LN1").setDateOfBirth("DOB1"),
                new Student().setFirstName("FN2").setLastName("LN2").setDateOfBirth("DOB2")
        };

        Group group = new Group().setSubject("Some").setStudents(Arrays.asList(students1));

        groupDao.save(group);

        studentDao.deleteByFirstName("FN1");
        studentDao.deleteByLastName("LN2");
        System.out.println(groupDao.getById(group.getId()));

        groupDao.deleteById(group.getId());
    }

}
