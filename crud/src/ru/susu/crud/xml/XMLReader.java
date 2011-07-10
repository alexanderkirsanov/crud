package ru.susu.crud.xml;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XMLReader {

    private Map<String, TableDefinition> tables;

    public XMLReader(String fileName) {
        tables = new HashMap<String, TableDefinition>();
        try {
            File file = new File(fileName);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder;
            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document;
                try {
                    document = documentBuilder.parse(file);
                    document.getDocumentElement().normalize();
                    NodeList tableLst = document.getElementsByTagName("column");
                    for (int i = 0; i < tableLst.getLength(); i++) {
                        Column column = new Column();
                        Node firstColumnNode = tableLst.item(i);
                        Node node = firstColumnNode.getParentNode();
                        if (node == null) {
                            continue;
                        }
                        String tableName = ((Element) node).getAttribute("name");
                        if (tables.get(tableName) == null) {
                            TableDefinition tableDefinition = new TableDefinition(tableName);
                            tables.put(tableName, tableDefinition);
                        }

                        if (firstColumnNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element firstColumnElement = (Element) firstColumnNode;

                            NodeList firstNameList = firstColumnElement.getElementsByTagName("name");
                            Element firstNameElement = (Element) firstNameList.item(0);

                            NodeList textNList = firstNameElement.getChildNodes();
                            String name = textNList.item(0).getNodeValue().trim();
                            column.setName(name);

                            NodeList typeList = firstColumnElement.getElementsByTagName("type");
                            Element typeElement = (Element) typeList.item(0);

                            NodeList textTList = typeElement.getChildNodes();
                            String type = ((Node) textTList.item(0)).getNodeValue().trim();
                            column.setType(type);

                            NodeList nullList = firstColumnElement.getElementsByTagName("is_null");
                            Element nullElement = (Element) nullList.item(0);

                            NodeList textNullList = nullElement.getChildNodes();
                            column.setNull(Boolean.parseBoolean(((Node) textNullList.item(0)).getNodeValue().trim()));

                            NodeList sizeList = firstColumnElement.getElementsByTagName("size");
                            Element sizeElement = (Element) sizeList.item(0);

                            NodeList textSizeList = sizeElement.getChildNodes();
                            column.setSize(Integer.parseInt(((Node) textSizeList.item(0)).getNodeValue().trim()));

                            tables.get(tableName).addColumn(column);
                        }
                    }
                } catch (SAXException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (ParserConfigurationException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, TableDefinition> getTables() {
        return this.tables;
    }
}
