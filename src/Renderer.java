import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

public class Renderer extends Tickable {
    private Graphics g;
    private float gridSize;
    private GridBlock[][] activeBlocks;
    private int overDraw;

    // Camera location data.
    private float xCamLoc;
    private float yCamLoc;

    private int oldXCoord;
    private int oldYCoord;

    private Map activeMap;

    private PhysicalObject focusedObject;

    private final boolean DEBUG = true;
    private final boolean SMOOTHMOVE = true;

    public Renderer(Graphics g, GridBlock[][] activeBlocks, int gridSize, int overDraw)
    {
        this.g = g;
        this.activeBlocks = activeBlocks;
        this.gridSize = gridSize;
        this.overDraw = overDraw;
        xCamLoc = 0;
        yCamLoc = 0;
        oldXCoord = (int)xCamLoc;
        oldYCoord = (int)yCamLoc;
    }

    @Override
    public void tick(double deltaTime) {
        if(shouldGetNewView())
        {
            if(xCamLoc < oldXCoord)
            {
                oldXCoord = (int) (xCamLoc + 1);
            }
            else
            {
                oldXCoord = (int) xCamLoc;
            }
            if(yCamLoc < oldYCoord)
            {
                oldYCoord = (int) (yCamLoc + 1);
            }
            else
            {
                oldYCoord = (int) yCamLoc;
            }
            
            activeBlocks = activeMap.getView(oldXCoord, oldYCoord);
        }
        
        updateCam();

        float offsetX = 0;
        float offsetY = 0;
        if(SMOOTHMOVE)
        {
            // Pull decimals.
            offsetX = xCamLoc - oldXCoord;
            offsetY = yCamLoc - oldYCoord;
        }

        for(int r = 0; r < activeBlocks.length; r++)
        {
            for(int c = 0; c < activeBlocks[0].length; c++)
            {
                if(activeBlocks[r][c] != null)
                {
                    g.drawImage(activeBlocks[r][c].getImage(), 
                    (int) (r*gridSize - offsetX*gridSize - gridSize/2 - overDraw*gridSize/2), 
                    (int) (c*gridSize + offsetY*gridSize - gridSize/2 - overDraw*gridSize/2), 
                    null);
                }
            }
        }

        if(DEBUG)
        {
            g.setColor(Color.WHITE);
            g.drawRect(400-32, 400-32, 32, 32);
            g.drawString("Character location: x: " + xCamLoc + " y: " + yCamLoc, 25, 50);
            g.drawString("Offsets. X: " + offsetX + " Y: " + offsetY, 25, 75);
            g.drawString("Character Velocity: x: " + focusedObject.getXVel() + " y: " + focusedObject.getYVel() , 25, 100);
        }
    }

    private void updateCam()
    {
        xCamLoc = focusedObject.getX();
        yCamLoc = focusedObject.getY();

        //gridSize = Math.min(32, Math.max(focusedObject.getXVel() + focusedObject.getYVel() * 5, 64));
    }

    public boolean shouldGetNewView()
    {
        return Math.abs(xCamLoc- oldXCoord) >= 1 || Math.abs(yCamLoc- oldYCoord) >= 1;
    }

    public GridBlock[][] getDesiredView()
    {
        return activeBlocks;
    }

    public float getGridSize()
    {
        return gridSize;
    }

    public int getOverDraw()
    {
        return overDraw;
    }

    public PhysicalObject getFocusedObject()
    {
        return focusedObject;
    }

    public void loadMap(String filePath)
    {
        activeMap = new Map(this, new File(filePath), gridSize);

        focusedObject = new PhysicsCharacter(activeMap);
        activeBlocks = activeMap.getView((int)xCamLoc, (int)yCamLoc);
    }
}
