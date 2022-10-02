import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class NiceJudge extends Judge
{
    public void judgeRow(int row)
    {
        if (row < 1 || row > 7) {
            System.out.println("Input is invalid, Please input number range in [1,7]");
            return;
        }
        
        System.out.println("NiceJudge looks at row " + row); //keep this line
        
        MastermindWorld.clearBalloons(row); //keep this line
        
        //part 2: complete the code below

        int start = 4;
        
        int yellowBalloons = this.getNumberOfYellowBalloons(row);
        // right count is zero
        int orangeBalloons = 0;
        
        for(int i = 0; i < 3; i ++) {
            // start guessing.
            int targetChar =  MastermindWorld.getObjectChar(i, row);
            int currentSecret =  MastermindWorld.secret.charAt(i);
            if (targetChar == currentSecret) {
                // orange increase
                orangeBalloons ++;
                // and yellow decrease
                yellowBalloons --;
                // generate orange balloon
                MastermindWorld.addObject(MastermindWorld.ORANGE_BALLOON, start, row);
                
                start ++;
            }
        }
        
        // handle yellowBalloons and show
        for(int i = 0; i < yellowBalloons; i ++) {
            MastermindWorld.addObject(MastermindWorld.YELLOW_BALLOON, start, row);
            start ++;
        }
    }
        
}
