package org.grynko.nazar.a.constant;

public enum XMLConsts {

    XMLNS("http://www.grynko.nazar/study/department"),
    XMLNS_XSI("http://www.w3.org/2001/XMLSchema-instance"),
    XSI_LOCATION("http://www.grynko.nazar/study/department schema.xsd");

    private final String value;

    XMLConsts(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
