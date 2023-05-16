// These are objects that can be "owned" by another class. Usefull for getting the owner's properties.
public class Ownable {
    Object owningObject;

    public Ownable(Object owningObject)
    {
        this.owningObject = owningObject;
    }

    public <T> T getOwner()
    {
        return (T) owningObject;
    }
}
