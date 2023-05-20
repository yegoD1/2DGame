import java.io.File;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GridBlock{

    private int xLoc;
    private int yLoc;
    private Image image;

    public GridBlock(Object owner, int xLoc, int yLoc, File spritePath, float gridSize)
    {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        image = null;

        try
        {
            image = ImageIO.read(spritePath);
            image = image.getScaledInstance((int)gridSize, (int)gridSize, BufferedImage.SCALE_FAST);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public int getX()
    {
        return xLoc;
    }

    public int getY()
    {
        return yLoc;
    }

    public Image getImage()
    {
        return image;
    }
}
