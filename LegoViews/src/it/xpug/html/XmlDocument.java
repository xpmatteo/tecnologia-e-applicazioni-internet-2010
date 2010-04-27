package it.xpug.html;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static java.lang.String.*;

public class XmlDocument {

	private final String xml;

	public XmlDocument(String xml) {
		this.xml = xml;
	}
	
	public boolean matchesXPath(String xpath) {
		return numberOfMatches(xpath) > 0;
	}

	public int numberOfMatches(String xpath) {
		return getNodeList(xpath).getLength();
	}

	public String nodeText(String xpath) throws ElementNotFoundException {
		Node node = getNode(xpath);
		return node.getTextContent().trim();
	}

	public Node getNode(String xpath) throws ElementNotFoundException {
		NodeList nodes = getNodeList(xpath);
		int length = nodes.getLength();
		if (length != 1) {
			throw new ElementNotFoundException(format("\"%s\": expected 1 node, found %d", xpath, length));
		} 
		return nodes.item(0);
	}

	private Document getW3CDocument() {
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setValidating(false);
	    factory.setNamespaceAware(true);
	    try {
			ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes());
			return factory.newDocumentBuilder().parse(is);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private NodeList getNodeList(String xpathString) {
        try {
        	// see http://www.ibm.com/developerworks/library/x-javaxpathapi.html
        	XPathFactory factory = XPathFactory.newInstance();
        	XPath xpath = factory.newXPath();
			return (NodeList) xpath.evaluate(xpathString, getW3CDocument(), XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
    }
}
