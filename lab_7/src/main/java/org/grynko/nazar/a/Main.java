package org.grynko.nazar.a;

import org.grynko.nazar.a.dom.DomController;
import org.grynko.nazar.a.model.Group;
import org.grynko.nazar.a.model.Student;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String xmlInputFile = "lab_7/src/main/resources/xml/input.xml";
    private static final String xsdSchemaFile = "lab_7/src/main/resources/xml/schema.xsd";
    private static final String xmlOutputFile = "lab_7/src/main/resources/xml/output.xml";

    public static void main(String[] args) {

        scenario4();
    }

    // get + print + save
    private static void scenario1() {
        DomController dc = new DomController(xmlInputFile, xsdSchemaFile);

        List<Group> groups = dc.getGroups();
        groups.forEach(System.out::println);

        dc.saveGroups(groups, xmlOutputFile);
    }

    // get + add (group + student) + save
    private static void scenario2() {
        DomController dc = new DomController(xmlInputFile, xsdSchemaFile);

        List<Group> groups = dc.getGroups();

        List<Student> students = createStudentsList();
        Group group = new Group()
                .setId(10)
                .setSubject("Subject")
                .setStudents(students);

        dc.addGroup(groups, group);

        dc.saveGroups(groups, xmlOutputFile);
    }

    // get + add to group (by id, subject) + save
    private static void scenario3() {
        DomController dc = new DomController(xmlInputFile, xsdSchemaFile);

        List<Group> groups = dc.getGroups();

        Group groupById = dc.getGroupById(groups, 2);
        createStudentsList().forEach(s -> dc.addStudent(groupById, s));

        Group groupBySubject = dc.getGroupBySubject(groups, "Mathematica");
        createStudentsList().forEach(s -> dc.addStudent(groupBySubject, s));

        dc.saveGroups(groups, xmlOutputFile);
    }

    // get + add to group (by id, subject) + save
    private static void scenario4() {
        DomController dc = new DomController(xmlInputFile, xsdSchemaFile);

        List<Group> groups = dc.getGroups();

        dc.deleteGroupById(groups, 2);

        Group groupById = dc.getGroupById(groups, 0);
        while(!groupById.getStudents().isEmpty()) {
            Student s = groupById.getStudents().get(0);
            dc.deleteStudentById(groupById, s.getId());
        }

        dc.saveGroups(groups, xmlOutputFile);
    }

    private static List<Student> createStudentsList() {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Student student = new Student()
                    .setId(10 + i)
                    .setFirstName("FirstName_" + i)
                    .setLastName("LastName_" + i)
                    .setDateOfBirth("BirthDay_" + i);

            students.add(student);
        }

        return students;
    }

}
