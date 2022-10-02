import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TestWorld here.
 * 
 * @author Peter (cspeter@cse.ust.hk)
 * @version 1.0
 */
public class GWorld extends World
{

    private static int width = 1;
    private static int height = 1;
    private static int cellSize = 60;
    
    public static void setWidth(int w) { width=w; }
    public static void setHeight(int h) { height=h; }
    public static void setCellSize(int s) { cellSize = s; }

    /**
     * Constructor for objects of class TestWorld.
     * 
     */
    protected GWorld() {    
        // Create a new world with 8x8 cells with a cell size of 60x60 pixels.
        super(width, height, cellSize);         
      // Set the world to the "G" class
        GWorld.setWorld(this);       
    }
    
    
      
    
    
    
     /**
     * Set the world object to wobj
     */
    public static void setWorld(World w) {
        wobj = w;
    }
    
    
    /**
     * Get the width of the World
     */
    public static int width() {
        if ( wobj == null)
            return -1;            
        return wobj.getWidth();
    }
    
    /**
     * Get the height of the World
     */
    public static int height() {
        if ( wobj == null )
            return -1;
        return wobj.getHeight();
    }
    
    /**
     * Remove one object from the world. 
     * 
     * Usage:
     *    G.removeOneObjectFromWorld( actor );
     * 
     */
    public static void removeOneObject(Actor obj) {
        if (wobj != null ) {
            wobj.removeObject(obj);
        }
    }
    
    /**
     * Add one object to the world. 
     * 
     * Usage:
     * 
     *    Picobot p = new Picobot(); 
     *    G.addOneObjectToWorld( p, 0, 0);
     */
    public static void addOneObject(Actor obj, int x, int y) {
        if (wobj != null ) {
            wobj.addObject(obj, x, y);
        }
    }
    
    /**
     * Get one object at the given location and class name. Return null if that object can't be found
     * 
     * Usage:
     * 
     *    Picobot p = (Picobot) G.getOneObjectAt(0,0,"Picobot");
     * 
     */
    public static Actor getOneObjectAt(int x, int y, String actorClassName) {
        Actor [] listActors = getAllObjectsAt(x,y,actorClassName);
        if ( listActors == null || listActors.length == 0 )
            return null;            
        return listActors[0];        
    }
    
    /**
     * Get ALL objects by the given class name
     * 
     * Usage:
     * 
     *    Actor [] plist = G.getAllObjects("Picobot");
     *    if ( plist != null && plist.length > 0 ) {     *        
     *        Picobot first = (Picobot) plist[0];
     *    }
     * 
     */
    public static  Actor[] getAllObjects(String actorClassName) {   
        if ( wobj == null )
            return null ;
            
        Class c = getClassByName(actorClassName);
        if ( c == null )
            return null ;
            
        java.util.List<Actor> list = wobj.getObjects(c);
                            
        return cloneActorArrayFromList(list);

    }
    
    
    /**
     * Remove all objects from the world
     * 
     * Usage:
     * 
     *    Actor [] plist = G.getAllObjects("Picobot");
     *    G.removeObjectsFromWorld( plist );
     * 
     */
    public static void removeObjectsFromWorld(Actor[] objArray) {
        if ( wobj != null && objArray != null) {
            for (int i=0; i< objArray.length; i++)
                wobj.removeObject( objArray[i] );
        }
    }

    /**
     * Return a random number between lowerLimit (inclusive) and upperLimit (inclusive).
     * Return 0 if lowerLimit is greater than upperLimit
     */
    public static int random(int lowerLimit, int upperLimit) {        
        int diff = upperLimit - lowerLimit ;
        if ( diff < 0 )
            return 0;            
        int randNum = Greenfoot.getRandomNumber( diff + 1 );
        return lowerLimit + randNum ;
    }
    
       
   // ====================================
   //
   //  Private section
   //
   // ====================================
 
   
    /**
     * [Private] Get the java.lang.Class object based on the given className
     * Example:
     *     Class c = getClassByName("Picobot");
     */
    private static Class getClassByName(String className) {
        try {
            return Class.forName(className);    
        } catch (ClassNotFoundException e) {
            System.err.println("G Error: Unable to find the class " + className);
            e.printStackTrace();
        }       
        return null;
    }
    
    /**
     * [Private] Given an instance of List<Actor>, convert it to an Actor[]
     */
    private static Actor[] cloneActorArrayFromList(java.util.List<Actor> list) {        
        if ( list == null )
            return null ;
        int sz = list.size();
        if ( sz == 0 )
            return null ;            
        Actor[] retActors = new Actor[sz] ;
        for (int i=0; i<sz; i++)
            retActors[i] = list.get(i);         
        return retActors;  
    }
    
    /**
     * [Private] Get All objects at (x,y) with the given actorClassName. For example:
     * 
     * Actor[] list = getAllObjectsAt(0,0,"Picobot");
     * 
     */
    private static Actor[] getAllObjectsAt(int x, int y, String actorClassName) {
         if ( wobj == null )
            return null ;
            
        Class c = getClassByName(actorClassName);
        if ( c == null )
            return null ;
            
        java.util.List<Actor> list = wobj.getObjectsAt(x, y, c);
        
        return cloneActorArrayFromList(list);
      
    }
    
    // Reference to World 
    private static World wobj = null;
 

}
