import java.awt.Color;
import java.awt.Graphics;

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
    private static final int GRIDSIZE = 32;
    // How many grid spaces to draw offscreen. Reduces pop-in on the edges of the screen (must be divisible by 2).
    private static final int OVERDRAW = 2;
    
    private BufferedImage image;
	private Graphics g;
    private Renderer renderer;
    private GameController controller;

    public Timer timer;

    public Core()
    {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();

        addMouseListener(this);
        addKeyListener(this);

        renderer = new Renderer(g, new GridBlock[WIDTH/GRIDSIZE + OVERDRAW][HEIGHT/GRIDSIZE + OVERDRAW], GRIDSIZE, OVERDRAW);
        controller = new GameController(renderer.getPlayer());

        renderer.loadMap("maps/map1.xml");

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
        frame.setFocusable(true);
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
        controller.recieveInput(e, true);
    }


    @Override
    public void keyReleased(KeyEvent e) {
        controller.recieveInput(e, false);
    }
}
