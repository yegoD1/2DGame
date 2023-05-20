public class PhysicalObject extends Tickable{

    protected float x;
    protected float y;

    protected float xVel;
    protected float yVel;

    public PhysicalObject()
    {
        x = 0;
        y = 0;
        xVel = 0;
        yVel = 0;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public void setX(float x)
    {
       this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getXVel()
    {
        return xVel;
    }

    public float getYVel()
    {
        return yVel;
    }

    @Override
    public void tick(double deltaTime) {
        
    }
}
