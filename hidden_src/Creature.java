import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creature - An actor which can get/set the current direction 
 * and move by the give number of steps along the current direction
 * 
 * @author Peter (cspeter@cse.ust.hk)
 * @version 1.0
 */
public class Creature extends Actor
{

    protected Creature() {
    }

    // Constants for MovableActor class
    public static final int EAST = 0;
    public static final int WEST = 1;
    public static final int NORTH = 2;
    public static final int SOUTH = 3;
        
    
    // Instance variable for MovableActor object
    private int direction = Creature.EAST; 
    
    
  
    /**
     * Move the object by the given number of steps
     * Pre-requesite: canMove(numOfSteps) returns true
     */
    public void move(int numOfSteps) {
        int x = getX();
        int y = getY();        
                      
        switch(direction) {
            case SOUTH :
                y = y + numOfSteps;
                break;
            case EAST :
                x = x + numOfSteps;
                break;
            case NORTH :
                y = y - numOfSteps;
                break;
            case WEST :
                x = x - numOfSteps;
                break;
            default :
                break;
        }
        
        if ( x >= 0 && x < GWorld.width() &&
            y >= 0 && y < GWorld.height() ) {
              setLocation(x,y);
              
        }       
    }
    
    /**
     * Check whether the object can be moved or not
     * Return true if movement is success
     * Return false otherwise
     */
    public boolean canMove(int numOfSteps) {
        int x = getX();
        int y = getY();        
                      
        switch(direction) {
            case SOUTH :
                y = y + numOfSteps;
                break;
            case EAST :
                x = x + numOfSteps;
                break;
            case NORTH :
                y = y - numOfSteps;
                break;
            case WEST :
                x = x - numOfSteps;
                break;
            default :
                break;
        }
        
        if ( x >= 0 && x < GWorld.width() &&
            y >= 0 && y < GWorld.height() ) {
              //setLocation(x,y);
              return true;
        } 
        
        return false;
        
    }
    
    /**
     * Get the current direction of the Creature object
     */
    public int getDirection() {
        return direction ;
    }
    
    /**
     * Set the direction of the Creature object
     * Possible values:
     * 
     * MovableActor.EAST = 0;
     * MovableActor.WEST = 1;
     * MovableActor.NORTH = 2;
     * MovableActor.SOUTH = 3;
     */
    public void setDirection(int newDirection)
    {
        direction = newDirection;
        switch(direction) {
            case SOUTH :
                setRotation(90);
                break;
            case EAST :
                setRotation(0);
                break;
            case NORTH :
                setRotation(270);
                break;
            case WEST :
                setRotation(180);
                break;
            default :
                break;
        }
    }
    
    
    /**
     * Act - do whatever the Creature wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
    }    
}
