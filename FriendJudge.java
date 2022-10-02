import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FriendJudge extends Judge
{
    public void judgeRow(int row)
	{
	  if (row < 1 || row > 7) {
            System.out.println("Input is invalid, Please input number range in [1,7]");
            return;
          }
	  
          System.out.println("FriendJudge looks at row " + row); //keep this line
        
          MastermindWorld.clearBalloons(row); //keep this line
		
          //part 2: complete the code below
          
          int yellowBalloons = this.getNumberOfYellowBalloons(row);
          int orangeBalloons = 0;
          int start = 4;
          for(int i = 0; i < 3; i ++) {
            // start guessing.
            int targetChar =  MastermindWorld.getObjectChar(i, row);
            int currentSecret =  MastermindWorld.secret.charAt(i);
            if (targetChar == currentSecret) {
                MastermindWorld.addObject(MastermindWorld.ORANGE_BALLOON, start + i, row);
                orangeBalloons ++;
                yellowBalloons --;
            }
          }
        
          start = 4;
          // handle yellowBalloons and show, put them into empty position
          for(int i = 0; i < yellowBalloons; i ++) {
            for(int j = start; j < MastermindWorld.WORLD_HEIGHT; j ++) {
                // if this place have something, put it into next position.
                if (MastermindWorld.NOTHING != MastermindWorld.getObjectChar(j, row)) {
                    continue;
                } 
                MastermindWorld.addObject(MastermindWorld.YELLOW_BALLOON, j, row);
                // put in this one.
                break;
            }
            
          }
    }
}
