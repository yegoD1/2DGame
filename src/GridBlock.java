import java.io.File;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GridBlock {

    private int xLoc;
    private int yLoc;
    private Image image;

    private int gridSize;

    public GridBlock(int xLoc, int yLoc, File spritePath, int gridSize)
    {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        image = null;

        try
        {
            image = ImageIO.read(spritePath);
            image = image.getScaledInstance(gridSize, gridSize, BufferedImage.SCALE_FAST);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public Image getImage()
    {
        return image;
    }
}
