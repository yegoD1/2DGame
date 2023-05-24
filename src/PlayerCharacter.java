public class PlayerCharacter extends PhysicalObject{
    
    public void move(float strength, double deltaTime)
    {
        
    }

    public void jump()
    {
        x += 1;
    }

    public void slam()
    {
        y -= 1;
    }

}
