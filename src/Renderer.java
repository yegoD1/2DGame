import java.awt.Graphics;
import java.awt.Image;

public class Renderer extends Tickable {
    private Graphics g;
    private int gridSize;
    private Image[][] activeImages;

    private float xCamLoc;
    private float yCamLoc;

    private Map activeMap;
    

    public Renderer(Graphics g, Image[][] activeImages)
    {
        this.g = g;
        this.activeImages = activeImages;
        xCamLoc = 0;
        yCamLoc = 0;
    }

    @Override
    public void tick(float deltaTime) {

    }

    public void loadMap()
    {
        
    }
}
