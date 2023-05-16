import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

// Defines all the data and functions needed for a map. Map "starts" at (0,0) and can continue infinetely with positive coords only.
public class Map extends Ownable{

    private int gridSize;
    private String name;
    private GridBlock[][] entireMap;
    private Renderer renderer;
    private int xStart;
    private int yStart;

    public Map(Object owningObject, File mapDir, int gridSize)
    {
        super(owningObject);
        this.gridSize = gridSize;

        renderer = getOwner();

        loadMapData(mapDir);
    }

    private void loadMapData(File mapDir)
    {
        Document xmlFile;

        double startTime = System.currentTimeMillis();

        // Going to try and load map file.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            xmlFile = db.parse(mapDir);
            
            Element doc = xmlFile.getDocumentElement();
        
            NamedNodeMap mapNode = doc.getElementsByTagName("MapInformation").item(0).getAttributes();
            name = mapNode.getNamedItem("Name").getNodeValue();
            xStart = mapNode.getNamedItem("SpawnX").getNodeType();
            yStart = mapNode.getNamedItem("SpawnY").getNodeType();
            
            System.out.println("Loading map: " + name);

            NodeList blockList = doc.getElementsByTagName("Block");

            int maxXPos = 0;
            int maxYPos = 0;
            for(int i = 0; i < blockList.getLength(); i++)
            {
                NamedNodeMap curBlock = blockList.item(i).getAttributes();
                int xPos = Integer.parseInt(curBlock.getNamedItem("X").getNodeValue());
                int yPos = Integer.parseInt(curBlock.getNamedItem("Y").getNodeValue());
                if(maxXPos < xPos)
                {
                    maxXPos = xPos;                    
                }
                if(maxYPos < yPos)
                {
                    maxYPos = yPos;
                }
            }

            entireMap = new GridBlock[maxXPos + 1][maxYPos + 1];

            for(int i = 0; i < blockList.getLength(); i++)
            {
                NamedNodeMap curBlock = blockList.item(i).getAttributes();
                int xPos = Integer.parseInt(curBlock.getNamedItem("X").getNodeValue());
                int yPos = Integer.parseInt(curBlock.getNamedItem("Y").getNodeValue());
                String type = curBlock.getNamedItem("Type").getNodeValue();
                entireMap[xPos][yPos] = new GridBlock(xPos, yPos, new File("sprites/" + type + ".png"), gridSize);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        System.out.println("Map finished loading in " + ((System.currentTimeMillis() - startTime) / 1000) + " seconds");
    }

    public int getStartX()
    {
        return xStart;
    }

    public int getStartY()
    {
        return yStart;
    }

    // Gets all images that should be loaded for this coordinate.
    public GridBlock[][] getView(float x, float y)
    {
        GridBlock[][] viewWindow = new GridBlock[][];

        return null;
    }

    public static void main(String[] args)
    {
        try{
            Map testMap = new Map(null, new File("maps/map1.xml"), 4);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
