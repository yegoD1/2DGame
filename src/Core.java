import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


// This class is the lowest level needed for the game. Handles everything else.
public class Core extends JPanel implements MouseInputListener, KeyListener{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    // Time between ticks in seconds.
    private static final float DELTATIME = 0.010f;

    // Size for each grid block.
    private static final int GRIDSIZE = 4;
    // How many grid spaces to draw offscreen. Improves movement on edges of the screen.
    private static final int OVERDRAW = 2;
    
    private BufferedImage image;
	private Graphics g;
    private Renderer renderer;

    public Timer timer;

    public Core()
    {
        image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();

        addMouseListener(this);
        addKeyListener(this);

        renderer = new Renderer(g, new Image[WIDTH/GRIDSIZE + OVERDRAW][HEIGHT/GRIDSIZE + OVERDRAW]);

        timer = new Timer((int)(DELTATIME * 1000), new TimerListener());
		timer.start();
    }

    private class TimerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            renderer.tick(DELTATIME);

            repaint();
        }
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Top Down Game");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Core());
		frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}


    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}
