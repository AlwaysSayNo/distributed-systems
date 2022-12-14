package org.grynko.nazar.a.dom;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException e) {
        System.out.println("Row" + e.getLineNumber() + ":");
        e.printStackTrace();
    }

    @Override
    public void error(SAXParseException e) {
        System.out.println("Row" + e.getLineNumber() + ":");
        e.printStackTrace();
    }

    @Override
    public void fatalError(SAXParseException e) {
        System.out.println("Row" + e.getLineNumber() + ":");
        e.printStackTrace();
    }
}
