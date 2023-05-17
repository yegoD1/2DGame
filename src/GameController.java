import java.awt.event.KeyEvent;

public class GameController extends Tickable {

    private boolean rightKey;
    private boolean leftKey;
    private boolean upKey;
    private boolean downKey;

    private PlayerCharacter player;

    public GameController(PlayerCharacter player)
    {
        this.player = player;
    }

    @Override
    public void tick(float deltaTime) {
        if(rightKey)
        {
            player.move(1);
        }
        if(leftKey)
        {
            player.move(-1);
        }

        player.tick(deltaTime);
    }

    public void recieveInput(KeyEvent e, boolean isDown)
    {
        if(isDown)
        {
            switch (e.getKeyChar()){
                case 'a':
                    leftKey = true;
                case 'w':
                    upKey = true;
                case 'd':
                    rightKey = true;
                case 's':
                    downKey = true;
            }   
        }
        else
        {
            switch (e.getKeyChar()){
                case 'a':
                    leftKey = false;
                case 'w':
                    upKey = false;
                case 'd':
                    rightKey = false;
                case 's':
                    downKey = false;
            }
        }
        
    }
    
}
