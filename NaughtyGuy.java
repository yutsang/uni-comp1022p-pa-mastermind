import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class NaughtyGuy extends SpecialCharacter
{
    public void cheat()
    {
        int originX = 3;
        int originY = 0;
        //part 2: complete the code below
        int x = this.getX();
        int y = this.getY();
        char currentSecret = MastermindWorld.secret.charAt(x);
        MastermindWorld.addObject(currentSecret, x, y);
        this.setLocation(originX, originY);
        
    }
    
    public void superCheat()
    {
        //part 2: complete the code below
        int originX = 3;
        int originY = 0;
        int x = this.getX();
        int y = this.getY();
        this.setLocation(originX, originY);
        
        for(int i = 0; i < 3; i ++) {
            if (MastermindWorld.getObjectChar(i, y) == MastermindWorld.NOTHING) {
                char currentSecret = MastermindWorld.secret.charAt(i);
                MastermindWorld.addObject(currentSecret, i, y);
            }
        }
        
    }
}
