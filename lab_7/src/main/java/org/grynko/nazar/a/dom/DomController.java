package org.grynko.nazar.a.dom;

import lombok.SneakyThrows;
import org.grynko.nazar.a.constant.DepartmentConsts;
import org.grynko.nazar.a.constant.GroupConsts;
import org.grynko.nazar.a.constant.StudentConsts;
import org.grynko.nazar.a.constant.XMLConsts;
import org.grynko.nazar.a.model.Group;
import org.grynko.nazar.a.model.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DomController {

    private final DomHelper domHelper;

    public DomController(String xmlFileName, String xsdFileName) {
        this.domHelper = new DomHelper(xmlFileName, xsdFileName);
    }

    @SneakyThrows
    public List<Group> getGroups() {
        DocumentBuilder db = domHelper.getDB();
        Document document = db.parse(new File(domHelper.getXmlFileName()));

        Element root = document.getDocumentElement();
        if(!root.getTagName().equals(DepartmentConsts.DEPARTMENT.toString())) return null;

        List<Group> result = new ArrayList<>();

        NodeList listGroups = root.getElementsByTagName(GroupConsts.GROUP.toString());
        for (int i = 0; i < listGroups.getLength(); i++) {
            Element groupElement = (Element) listGroups.item(i);

            Group group = getGroupFromElement(groupElement);
            result.add(group);
        }

        return result;
    }

    @SneakyThrows
    public void saveGroups(List<Group> groups, String outputFileName) {
        Document document = createXMLDocument(groups);
        try(FileOutputStream output = new FileOutputStream(outputFileName)){
            writeXML(document, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addGroup(List<Group> groups, Group newGroup) {
        groups.add(newGroup);
    }

    public void addStudent(Group group, Student newStudent) {
        group.getStudents().add(newStudent);
    }

    public Group getGroupById(List<Group> groups, Integer id) {
        Optional<Group> group = groups.stream().filter(g -> Objects.equals(g.getId(), id)).findAny();
        return group.orElse(null);
    }

    public Group getGroupBySubject(List<Group> groups, String subject) {
        Optional<Group> group = groups.stream().filter(g -> Objects.equals(g.getSubject(), subject)).findAny();
        return group.orElse(null);
    }

    public void deleteGroupById(List<Group> groups, Integer id) {
        groups.removeIf(g -> g.getId().equals(id));
    }

    public void deleteStudentById(Group group, Integer id) {
        group.getStudents().removeIf(s -> s.getId().equals(id));
    }

    public void changeGroupSubject(Group group, String newSubject) {
        group.setSubject(newSubject);
    }

    private void writeXML(Document document, FileOutputStream output) throws TransformerException {
        Transformer transformer = domHelper.getTransformer();

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }

    private Document createXMLDocument(List<Group> groups) throws ParserConfigurationException, SAXException {
        DocumentBuilder builder = domHelper.getDB();

        Document document = builder.newDocument();

        Element root = document.createElement(DepartmentConsts.DEPARTMENT.toString());
        root.setAttribute( "xmlns", XMLConsts.XMLNS.toString());
        root.setAttribute("xmlns:xsi", XMLConsts.XMLNS_XSI.toString());
        root.setAttribute("xsi:schemaLocation", XMLConsts.XSI_LOCATION.toString());
        document.appendChild(root);

        for (Group group: groups) {
            Element groupElement = createGroupElement(document, group);

            root.appendChild(groupElement);
        }

        return document;
    }

    private Group getGroupFromElement(Element groupElement) {
        Integer groupId = Integer.parseInt(groupElement.getAttribute(GroupConsts.ID.toString()));
        String groupNameSubject = groupElement.getAttribute(GroupConsts.SUBJECT.toString());
        List<Student> groupStudents = new ArrayList<>();

        NodeList listStudents = groupElement.getElementsByTagName(StudentConsts.STUDENT.toString());
        for (int i = 0; i < listStudents.getLength(); i++) {
            Element studentElement = (Element) listStudents.item(i);

            Student student = getStudentFromElement(studentElement);
            groupStudents.add(student);
        }

        Group group = new Group();
        group.setId(groupId);
        group.setSubject(groupNameSubject);
        group.setStudents(groupStudents);

        return group;
    }

    private Student getStudentFromElement(Element studentElement) {
        Integer studentId = Integer.parseInt(studentElement.getAttribute(StudentConsts.ID.toString()));
        String studentFirstName = studentElement.getAttribute(StudentConsts.FIRST_NAME.toString());
        String studentLastName = studentElement.getAttribute(StudentConsts.LAST_NAME.toString());
        String studentDateOfBirth = studentElement.getAttribute(StudentConsts.DATE_OF_BIRTH.toString());

        Student student = new Student();
        student.setId(studentId);
        student.setFirstName(studentFirstName);
        student.setLastName(studentLastName);
        student.setDateOfBirth(studentDateOfBirth);

        return student;
    }

    private Element createGroupElement(Document document, Group group) {
        Element groupElement = document.createElement(GroupConsts.GROUP.toString());

        groupElement.setAttribute(GroupConsts.ID.toString(), group.getId().toString());
        groupElement.setAttribute(GroupConsts.SUBJECT.toString(), group.getSubject());

        for(Student student: group.getStudents()){
            Element studentElement = createStudentElement(document, student);

            groupElement.appendChild(studentElement);
        }

        return groupElement;
    }

    private Element createStudentElement(Document document, Student student) {
        Element studentElement = document.createElement(StudentConsts.STUDENT.toString());

        studentElement.setAttribute(StudentConsts.ID.toString(), student.getId().toString());
        studentElement.setAttribute(StudentConsts.FIRST_NAME.toString(), student.getFirstName());
        studentElement.setAttribute(StudentConsts.LAST_NAME.toString(), student.getLastName());
        studentElement.setAttribute(StudentConsts.DATE_OF_BIRTH.toString(), student.getDateOfBirth());

        return studentElement;
    }

}
