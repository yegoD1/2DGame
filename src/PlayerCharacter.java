public class PlayerCharacter extends PhysicalObject{

    private int collisionSubStep;

    private int maxSpeed;
    private float gravity;
    private float acceleration;
    private float friction;
    
    private boolean onGround;
    
    private Map activeMap;

    public PlayerCharacter(Map activeMap)
    {
        super();
        this.activeMap = activeMap;
        x = activeMap.getStartX();
        y = activeMap.getStartY();
        maxSpeed = 20;
        gravity = 1;
        acceleration = 1;
        friction = 0.95f;
        collisionSubStep = 2;
        
    }

    public void move(float strength)
    {
        xVel += acceleration * strength;
        xVel = Math.max(-maxSpeed, Math.min(xVel, maxSpeed));
    }

    public void jump()
    {
        if(onGround())
        {
            yVel = 10;
        }
    }

    public boolean onGround()
    {
        return yVel < 0 && (activeMap.isBlock(collisionX, testY-1) || activeMap.isBlock(collisionX+1, testY-1));
    }

    @Override
    public void tick(double deltaTime) {
    
        int collisionX = (int) x;
        int testY = (int) y;

        x += xVel* deltaTime;
        y += yVel * deltaTime;
        yVel -= gravity;

        float thresholdX = 0.01f + (float)Math.abs(xVel*deltaTime);
        float thresholdY = 0.01f + (float)Math.abs(yVel*deltaTime);

        float xThres = Math.abs(Math.abs(collisionX)-Math.abs(x));

        float yThres = Math.abs(Math.abs(testY)-Math.abs(y));
        
        System.out.println("thresholdY: " + thresholdY + " yThres: " + yThres);

        if(yThres < thresholdY)
        {
            // Block beneath player.
            if(yVel < 0 && (activeMap.isBlock(collisionX, testY-1) || activeMap.isBlock(collisionX+1, testY-1)))
            {
                yVel = 0;
                y = testY;
                xVel *= friction;
                if(Math.abs(xVel) < 0.1)
                {
                    xVel = 0;
                }
            }
            else if(yVel > 0 && activeMap.isBlock(collisionX, testY+1))
            {
                yVel = 0;
                y = testY;
            }
        }
        
        if(xThres < thresholdX)
        {
            if(activeMap.isBlock(collisionX+1, testY) && xVel > 0)
            {
                xVel = 0;
                x = collisionX;
            }
            else if(activeMap.isBlock(collisionX-1, testY) && xVel < 0)
            {
                xVel = 0;
                x = collisionX;
            }
        }
    }

}
