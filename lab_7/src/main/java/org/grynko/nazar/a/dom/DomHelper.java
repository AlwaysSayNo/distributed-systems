package org.grynko.nazar.a.dom;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class DomHelper {

    private final String xmlFileName;
    private final String xsdFileName;
    private DocumentBuilderFactory documentBuilderFactory;

    public DomHelper(String xmlFileName, String xsdFileName) {
        this.xmlFileName = xmlFileName;
        this.xsdFileName = xsdFileName;
        this.documentBuilderFactory = null;
    }

    public DocumentBuilder getDB() throws ParserConfigurationException, SAXException {
        DocumentBuilderFactory dbf = getDBF();

        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        documentBuilder.setErrorHandler(new SimpleErrorHandler());

        return documentBuilder;
    }

    public DocumentBuilderFactory getDBF() throws ParserConfigurationException, SAXException {
        if(this.documentBuilderFactory == null){
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(xsdFileName));

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            dbf.setNamespaceAware(true);
            dbf.setValidating(false);
            dbf.setSchema(schema);

            this.documentBuilderFactory = dbf;
        }

        return this.documentBuilderFactory;
    }

    public Transformer getTransformer() throws TransformerConfigurationException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        return transformer;
    }

    public String getXmlFileName() {
        return xmlFileName;
    }

    public String getXsdFileName() {
        return xsdFileName;
    }
}
