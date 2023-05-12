// Contains all the data for a map.

import java.awt.Image;
import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Map {

    private int gridSize;
    private String name;
    private Image[][] entireMap;

    public Map(File mapDir, int gridSize)
    {
        this.gridSize = gridSize;

        loadMapData(mapDir);
    }

    private void loadMapData(File mapDir)
    {
        Document dom;

        // Going to try and load map file.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(mapDir);
            Element doc = dom.getDocumentElement();
        
            Node mapNode = doc.getElementsByTagName("MapName").item(0);
            name = mapNode.getTextContent();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // Gets all images that should be loaded for this coordinate.
    public Image[][] getView(float x, float y)
    {
        return null;
    }

    public static void main(String[] args)
    {
        try{
            Map testMap = new Map(new File("maps/map1.xml"), 4);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
