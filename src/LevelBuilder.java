import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class LevelBuilder {
    public static void main(String[] args)
    {
        

        saveLevel(); 
    }

    private static void saveLevel()
    {
        Document dom;
         
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // using a factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create an instance of DOM
            dom = db.newDocument();

            // Information element.
            Element informationElement = dom.createElement("Information");
            Element mapInfo = addToNode(dom, informationElement, "MapInformation", "Name", "TestMap1");
            mapInfo.setAttribute("SpawnX", "3");
            mapInfo.setAttribute("SpawnY", "0");
            dom.appendChild(informationElement);

            Element mapDataElement = dom.createElement("MapData");
             
            for(int i = 0; i < 500; i++)
            {
                createBlock(dom, mapDataElement, i, i, "grass");
            }

            informationElement.appendChild(mapDataElement);
            
            writeToDisk(dom);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private static Element addToNode(Document dom, Node owner, String nodeName, String elementName, String textVal)
    {
        Element newElement = dom.createElement(nodeName);
        newElement.setAttribute(elementName, textVal);
        owner.appendChild(newElement);
        return newElement;
    }

    private static void createBlock(Document dom, Node owner, int x, int y, String type)
    {
        Element blockEntry = dom.createElement("Block");

        blockEntry.setAttribute("X", String.valueOf(x));
        blockEntry.setAttribute("Y", String.valueOf(y));
        blockEntry.setAttribute("Type", type);

        /* 
        Element xEle = dom.createElement("X");
        xEle.appendChild(dom.createTextNode(String.valueOf(x)));
        blockEntry.appendChild(xEle);

        Element yEle = dom.createElement("Y");
        yEle.appendChild(dom.createTextNode(String.valueOf(y)));
        blockEntry.appendChild(yEle);

        Element typeEle = dom.createElement("Type");
        typeEle.appendChild(dom.createTextNode(type));
        blockEntry.appendChild(typeEle);
        */

        owner.appendChild(blockEntry);
    }

    private static void writeToDisk(Document dom)
    {
        DOMSource source = new DOMSource(dom);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try{
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            FileWriter writer = new FileWriter(new File("maps/map1.xml"));
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
