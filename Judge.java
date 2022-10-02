import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.*;

public class Judge extends SpecialCharacter
{ 
    public void judgeRow(int row)
    {
        // judge input's validation
        if (row < 1 || row > 7) {
            System.out.println("Input is invalid, Please input number range in [1,7]");
            return;
        }
        
        System.out.println("Judge looks at row " + row); //keep this line
        
	MastermindWorld.clearBalloons(row); //keep this line
		
	//part 1: complete the code below
	int start = 4;

        for(int i = 0; i < 3; i ++) {
            // start -> end
            int targetChar =  MastermindWorld.getObjectChar(i, row);
            int currentSecret =  MastermindWorld.secret.charAt(i);
            // judge wether they're the same or not.
            if (targetChar == currentSecret) {
                // put an ballon in this position.
                MastermindWorld.addObject(MastermindWorld.ORANGE_BALLOON, start, row);
                // move to next.
                start ++;
            }
        }
    }   
    
    public void judgCurrentRow()
    {
	//part 1: complete the code below
	int current = this.getY();
        judgeRow(current);	
        
    }

    public void judgeAllRows()
    {
	//part 1: complete the code below
	for(int i = 1; i < MastermindWorld.WORLD_HEIGHT; i ++) {
            judgeRow(i);
        }
        
    }
    
    /*
     * It returns the number of yellow balloons, assuming no orange balloons would be awarded. Read assignment description on how to make use of it.
     * It is used in part 2 only.
     */
    public int getNumberOfYellowBalloons(int row)
    {
        return Math.min(getNumberOfSpecificPegsInSecret('w'), getNumberOfSpecificPegsInAnswer('w', row))
            + Math.min(getNumberOfSpecificPegsInSecret('d'), getNumberOfSpecificPegsInAnswer('d', row))
            + Math.min(getNumberOfSpecificPegsInSecret('e'), getNumberOfSpecificPegsInAnswer('e', row))
            + Math.min(getNumberOfSpecificPegsInSecret('p'), getNumberOfSpecificPegsInAnswer('p', row));
    }
    
    /*
     * It is used by getNumberOfYellowBalloons.
     */
    public int getNumberOfSpecificPegsInAnswer(char type, int row)
    {
        int count = 0;
        for(int i=0; i<3; i++)
        {
            if(MastermindWorld.getObjectChar(i, row) == type)
                count++;
        }
        return count;
    }

    /*
     * It is used by getNumberOfYellowBalloons.
     */
    public int getNumberOfSpecificPegsInSecret(char type)
    {
        int count = 0;
        for(int i=0; i<3; i++)
        {
            if(MastermindWorld.secret.charAt(i) == type)
                count++;
        }
        return count;
    }
}
