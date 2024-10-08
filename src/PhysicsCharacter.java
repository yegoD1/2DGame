public class PhysicsCharacter extends PlayerCharacter{

    private int collisionSubStep;

    private int maxSpeed;
    private float gravity;
    private float acceleration;
    private float friction;

    // Out of bounds Y value.
    private int oobY;
    
    private boolean onGround;
    
    private Map activeMap;

    public PhysicsCharacter(Map activeMap)
    {
        super();
        this.activeMap = activeMap;
        x = activeMap.getStartX();
        y = activeMap.getStartY();
        maxSpeed = 35;
        gravity = 30;
        acceleration = 20;
        friction = 2f;
        collisionSubStep = 4;
        oobY = -100;
    }

    public void move(float strength, double deltaTime)
    {
        xVel += acceleration * strength * deltaTime;
        xVel = Math.max(-maxSpeed, Math.min(xVel, maxSpeed));
    }

    public void jump()
    {
        if(onGround)
        {
            yVel = 15;
        }
    }

    public void slam()
    {
        if(!onGround)
        {
            yVel -= 1;
        }
    }

    public boolean isOnGround()
    {
        return onGround;
    }

    @Override
    public void tick(double deltaTime) {
    
        float beforeMoveX = x;
        float beforeMoveY = y;

        x += xVel* deltaTime;
        y += yVel * deltaTime;
        yVel -= gravity * deltaTime;

        // Collision substeps. Prevents phasing through blocks when moving fast.
        for(int i = 0; i <= collisionSubStep; i++)
        {
            // collisionSubStep breaks the deltaX and deltaY into segments. This is done to prevent cases that the player
            // is moving so fast that it skips over value thresholds.
            collisionCheck(deltaTime, beforeMoveX + (x-beforeMoveX)/collisionSubStep*i, beforeMoveY + (y-beforeMoveY)/collisionSubStep*i);
        }

        if(y < oobY)
        {
            x = activeMap.getStartX();
            y = activeMap.getStartY();
            xVel = 0;
            yVel = 0;
        }
    }

    private void collisionCheck(double deltaTime, float testX, float testY)
    {
        int collisionX = (int)(testX);
        int collisionY = (int)(testY);

        float thresholdX = 0.01f + (float)Math.abs(xVel*deltaTime);
        float thresholdY = 0.01f + (float)Math.abs(yVel*deltaTime);

        float xThres = Math.abs(Math.abs(collisionX)-Math.abs(testX));

        float yThres = Math.abs(Math.abs(collisionY)-Math.abs(testY));

        if(yThres < thresholdY)
        {
            // Block beneath player.
            if(yVel < 0 && (activeMap.isBlock(collisionX, collisionY-1) || activeMap.isBlock(collisionX+1, collisionY-1)))
            {
                yVel = 0;
                y = collisionY;
                xVel *= 1 / (1 + (deltaTime * friction));
                if(Math.abs(xVel) < 0.2)
                {
                    xVel = 0;
                }
                onGround = true;
            }
            else if(yVel > 0 && activeMap.isBlock(collisionX, collisionY+1))
            {
                yVel = 0;
                y = collisionY;
            }
        }
        else
        {
            onGround = false;
        }
        
        if(xThres <= thresholdX || xThres - thresholdX <= thresholdX)
        {
            if(activeMap.isBlock(collisionX+1, collisionY) && xVel > 0)
            {
                xVel = 0;
                x = collisionX;
            }
            else if(activeMap.isBlock(collisionX-1, collisionY) && xVel < 0)
            {
                xVel = 0;
                x = collisionX;
            }
        }
    }
}