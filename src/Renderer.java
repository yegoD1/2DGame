import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

public class Renderer extends Tickable {
    private Graphics g;
    private int gridSize;
    private Image[][] activeImages;

    private float xCamLoc;
    private float yCamLoc;

    private Map activeMap;
    

    public Renderer(Graphics g, Image[][] activeImages, int gridSize)
    {
        this.g = g;
        this.activeImages = activeImages;
        this.gridSize = gridSize;
        xCamLoc = 0;
        yCamLoc = 0;
        activeMap = new Map(this, new File("maps/map1.xml"), 2);
    }

    @Override
    public void tick(float deltaTime) {
        activeMap.getView(xCamLoc, yCamLoc);
    }

    public void loadMap(String filePath)
    {
       
    }
}
