public class PlayerCharacter extends Tickable{
    private float xLoc;
    private float yLoc;

    private float xVel;
    private float yVel;

    private int maxSpeed;
    private float gravity;
    private float acceleration;

    public PlayerCharacter(int startXLoc, int startYLoc)
    {
        xLoc = startXLoc;
        yLoc = startYLoc;
        maxSpeed = 20;
        gravity = 4;
        acceleration = 5;
    }

    public float getXLoc()
    {
        return xLoc;
    }
    
    public float getYLoc()
    {
        return yLoc;
    }

    public void move(float strength)
    {
        xVel += acceleration * strength;
        xVel = Math.max(-maxSpeed, Math.min(xVel, maxSpeed));
    }

    public void jump()
    {
        yVel = 5;
    }

    @Override
    public void tick(float deltaTime) {
        xLoc += xVel* deltaTime;
        yLoc += yVel * deltaTime;
        yVel -= gravity;
    }
}
