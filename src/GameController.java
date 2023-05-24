import java.awt.event.KeyEvent;

public class GameController extends Tickable {

    private boolean rightKey;
    private boolean leftKey;
    private boolean upKey;
    private boolean downKey;

    private PhysicsCharacter player;

    public GameController(PhysicsCharacter player)
    {
        this.player = player;
        rightKey = false;
        leftKey = false;
        upKey = false;
        downKey = false;
    }

    @Override
    public void tick(double deltaTime) {
        if(rightKey)
        {
            player.move(1, deltaTime);
        }
        if(leftKey)
        {
            player.move(-1, deltaTime);
        }
        if(upKey)
        {
            player.jump();
        }
        if(downKey)
        {
            player.slam();
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
                    break;
                case 'w':
                    upKey = true;
                    break;
                case 'd':
                    rightKey = true;
                    break;
                case 's':
                    downKey = true;
                    break;
            }   
        }
        else
        {
            switch (e.getKeyChar()){
                case 'a':
                    leftKey = false;
                    break;
                case 'w':
                    upKey = false;
                    break;
                case 'd':
                    rightKey = false;
                    break;
                case 's':
                    downKey = false;
                    break;
            }
        }
        
    }
    
}
