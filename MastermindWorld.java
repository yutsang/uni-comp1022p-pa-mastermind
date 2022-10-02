import greenfoot.*;  
import java.util.Random;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class MastermindWorld extends GWorld
{
    //the dimensions of the game board
    public static final int WORLD_WIDTH = 7;
    public static final int WORLD_HEIGHT = 8;
    public static final int NUMBER_OF_PLAY_ROWS = WORLD_HEIGHT - 1;

    //the constants that represent different objects (or nothing) that present on the game board
    public static final char NOTHING = 'x';
    public static final char BLOCK = 'b';
    public static final char JUDGE = 'j';
    public static final char NICE_JUDGE = 'n';
    public static final char FRIEND_JUDGE = 'f';
    public static final char NAUGHTY_GUY = 'c';
    public static final char WOMBAT = 'w';
    public static final char DOLPHIN = 'd';
    public static final char ELEPHANT = 'e';
    public static final char PIG = 'p';
    public static final char YELLOW_BALLOON = 'y';
    public static final char ORANGE_BALLOON = 'o';

    //the secret, which should be randomized by you at the beginning
    public static String secret = "wde";

    public static int highScore[] = {0, 0, 0}; //you may use this in part 2, or you can ignore it and use your own variables

    static 
    {      
        GWorld.setWidth(WORLD_WIDTH);   
        GWorld.setHeight(WORLD_HEIGHT);
        GWorld.setCellSize(80);
    }

    public MastermindWorld() throws Exception
    {           
        initialize(); 
        generateSecret(); //you may comment out this line temporarily to keep the secret "wde" for testing purpose
    }

    public static void initialize()
    {  
        removeAllObjects();
        
        //part 1: complete the code below
        int width = GWorld.width();
        int i = 0;
        while (i < width){
            //Fill the first line with 
            Block newBlock = new Block();
            GWorld.addOneObject(newBlock, i, 0);
            
            //Add the loop body in the next step
            i+=1;
        }
        int height = GWorld.height();
        int j1= 1;
        while (j1 < height){
            //Create a new chess
            Block newBlock = new Block();
            GWorld.addOneObject(newBlock, 3, j1);
            
            //Add the loop body in the next step
            j1+=1;
        }
        //Reset the first line with judges
        MastermindWorld.addObject(MastermindWorld.JUDGE, 0, 0);
        
        MastermindWorld.addObject(MastermindWorld.NICE_JUDGE, 1 , 0);
        
        MastermindWorld.addObject(MastermindWorld.FRIEND_JUDGE, 2 , 0);

        MastermindWorld.addObject(MastermindWorld.NAUGHTY_GUY, 3 , 0);
    }

    public static void generateSecret()
    {  
        //part 1: complete the code below
        // reset secret
        secret="";
        //int[] arr = new int[4];
        char[] randomArray = new char[]{WOMBAT, DOLPHIN, ELEPHANT, PIG};
        
        Random randomNumberGenerator = new Random();
        for(int i = 0; i < 3; i ++) {
            int index = getRandomNumber(0, randomArray.length - 1);
            // append data.
            secret = secret + randomArray[index];
        }
        
        System.out.println("The secret is " + secret);
        
    }

    public static void clearBalloons(int row)
    {  
        //part 1: complete the code below
        for(int i = 0; i < 3; i ++) {
            // start with 5
            removeBalloons(4 + i, row);
        }
    }

    public static void clearAllBalloons()
    {  
        //part 1: complete the code below
        for(int i = 1; i < WORLD_HEIGHT; i ++) {
            clearBalloons(i);
        }
    }
    
    public static int calculateRowScore(int row)
    {
        //part 2: complete the code below
        int yellowBalloons = 0;
        int orangeBalloons = 0;
        
        if (row > 0 && row < WORLD_HEIGHT) {
            for(int i = 4; i < WORLD_WIDTH; i ++) {
                char current = getObjectChar(i, row);
                if (current == YELLOW_BALLOON) {
                    yellowBalloons ++;
                } else if (current == ORANGE_BALLOON) {
                    orangeBalloons ++;
                }
            }
            // calculate
            return orangeBalloons * 3 + yellowBalloons;
        }
        
        
        return 0; //replace this...
    }

    public static int calculateMaxScore()
    {
        //part 2: complete the code below
        int max = 0;
        for(int i = 1; i < WORLD_WIDTH; i ++) {
            int score = calculateRowScore(i);
            max = Math.max(score, max);
        }

        return max; //replace this...
    }
    
    public static void showScore()
    {
        //part 2: complete the code below
        int score = calculateMaxScore();
        String message = "Get good!";
        if (score >= 4 && score <= 6) {
            message = "Good!";
        } else if(score >= 7 && score<= 9) {
            message = "Excellent!";
        }
        showMessage("You scored " + score + "! " + message);
        
    }

    public static void saveGame() throws Exception
    {
        //part 2: complete the code below
        File saveFile = new File("save.txt");
        PrintWriter writer = new PrintWriter(saveFile);
        writer.println(secret);
        for(int i = 0; i < WORLD_WIDTH; i ++) {
            for(int j = 0; j < WORLD_HEIGHT; j ++) {
                char current = getObjectChar(i, j);
                if (current != BLOCK) {
                    writer.println(current + " " + i + " " + j);
                } else {
                    writer.println("x " + i + " " + j);
                }
            }
        }
        writer.close();
        
    }

    public static void loadGame() throws Exception
    {
        //part 2: complete the code below
        removeAllNonBlockObjects();
        File inputFile = new File("save.txt");
        Scanner scanner = new Scanner(inputFile);
        secret = scanner.nextLine();
        for(int i = 0; i < WORLD_WIDTH; i ++) {
            for(int j = 0; j < WORLD_HEIGHT; j ++) {
                // get first char
                String current = scanner.next();
                // get x
                int x = scanner.nextInt();
                // get y
                int y = scanner.nextInt();
                // add object 
                addObject(current.charAt(0), x, y);
            }
        }
        
    } 

    public static void updateHighscores() throws Exception
    {
        //part 2 optional: complete the code below
        int score = calculateMaxScore();
        File inputFile = new File("highscore.txt");
        Scanner scanner = new Scanner(inputFile);
        highScore[0] = scanner.nextInt();
        highScore[1] = scanner.nextInt();
        highScore[2] = scanner.nextInt();
        
        // if score more than largest
        if (score >= highScore[0] && score >= highScore[1]) {
            highScore[2] = highScore[1];
            highScore[1] = highScore[0];
            highScore[0] = score;
        } else if (score >= highScore[1] && score <= highScore[0]) {
            highScore[2] = highScore[1];
            highScore[1] = score;
        } else if (score >= highScore[2]) {
            highScore[2] = score;
        }
        
        
        File saveFile = new File("highscore.txt");
        PrintWriter writer = new PrintWriter(saveFile);
        writer.print(highScore[0] + " " + highScore[1] + " " + highScore[2]);
        writer.close();
        scanner.close();
    }

    /*
     * Parameters:
     * lowerBound - the (inclusive) lower bound of the random number
     * upperBound - the (inclusive) upper bound of the random number
     * 
     * Example:
     * getRandomNumber(1, 10) will return you a random number that is in range of [1, 10] (including 1 and 10).
     */
    public static int getRandomNumber(int lowerBound, int upperBound)
    {
        Random randomNumberGenerator = new Random();
        return lowerBound + randomNumberGenerator.nextInt(upperBound - lowerBound + 1);
    }

    /*
     * Parameters:
     * objectChar - a character that represents the object that you want to add; you are suggested to use the constants defined in this class, e.g., MastermindWorld.JUDGE
     * x - the x coordinate of the object to be placed (the top-left corner of the world is (0, 0)
     * y - the y coordinate
     * 
     * Example:
     * MastermindWorld.addObject(MastermindWorld.NICE_JUDGE, 2, 3); 
     * will create and place a NiceJudge object to the location (2,3) in the world
     */
    public static void addObject(char objectChar, int x, int y)
    {
        //create the object base on the objectChar
        Actor object = null;
        switch(objectChar)
        {
            case MastermindWorld.JUDGE: 
            object = new Judge();
            break;
            case MastermindWorld.NICE_JUDGE: 
            object = new NiceJudge();
            break;
            case MastermindWorld.FRIEND_JUDGE: 
            object = new FriendJudge();
            break;
            case MastermindWorld.NAUGHTY_GUY: 
            object = new NaughtyGuy();
            break;
            case MastermindWorld.WOMBAT: 
            object = new Wombat();
            break;
            case MastermindWorld.DOLPHIN: 
            object = new Dolphin();
            break;
            case MastermindWorld.ELEPHANT: 
            object = new Elephant();
            break;
            case MastermindWorld.PIG: 
            object = new Pig();
            break;
            case MastermindWorld.YELLOW_BALLOON: 
            object = new YellowBalloon();
            break;
            case MastermindWorld.ORANGE_BALLOON: 
            object = new OrangeBalloon();
            break;
            case MastermindWorld.BLOCK: 
            object = new Block();
            break;
            default:
            return; //just exit the method if nothing should be added

        }   
        GWorld.addOneObject(object, x, y); //add the object to the world
    }

    /*
     * Parameters:
     * x - the x coordinate of the location that you want to check
     * y - the y coordinate
     * 
     * Example:
     * MastermindWorld.getObjectChar(2, 3) will return a character that represents the object that is located at the location (2,3) in the world.
     * If there is a DOLPHIN object there at location (2,3), then the character MastermindWorld.DOLPHIN (which is defined as 'd') will be returned.
     * 
     * If there is nothing at the location, then the character MastermindWorld.NOTHING (which is defined as 'x') will be returned.
     * 
     * We assume that there is always at most one object at each location, except for the case of a Judge/NaughtyGuy sitting on a Block. 
     * For such case, only the Judge/NaughtyGuy will be returned as the result.
     */
    public static char getObjectChar(int x, int y)
    {
        //return the character that represents the object at (x, y)
        if(GWorld.getOneObjectAt(x, y, "NiceJudge") != null) return MastermindWorld.NICE_JUDGE;
        else if(GWorld.getOneObjectAt(x, y, "FriendJudge") != null) return MastermindWorld.FRIEND_JUDGE;
        else if(GWorld.getOneObjectAt(x, y, "Judge") != null) return MastermindWorld.JUDGE;
        else if(GWorld.getOneObjectAt(x, y, "NaughtyGuy") != null) return MastermindWorld.NAUGHTY_GUY;
        else if(GWorld.getOneObjectAt(x, y, "Wombat") != null) return MastermindWorld.WOMBAT;
        else if(GWorld.getOneObjectAt(x, y, "Dolphin") != null) return MastermindWorld.DOLPHIN;
        else if(GWorld.getOneObjectAt(x, y, "Elephant") != null) return MastermindWorld.ELEPHANT;
        else if(GWorld.getOneObjectAt(x, y, "Pig") != null) return MastermindWorld.PIG;
        else if(GWorld.getOneObjectAt(x, y, "YellowBalloon") != null) return MastermindWorld.YELLOW_BALLOON;
        else if(GWorld.getOneObjectAt(x, y, "OrangeBalloon") != null) return MastermindWorld.ORANGE_BALLOON;
        else if(GWorld.getOneObjectAt(x, y, "Block") != null) return MastermindWorld.BLOCK;
        else return MastermindWorld.NOTHING;
    }

    /*
     * Parameters:
     * x - the x coordinate
     * y - the y coordinate
     * 
     * Example:
     * Calling removeBaloons(2, 3) will remove any balloon at the position (2,3). 
     * It is safe to call the method even if the specified position has nothing in it - it just does nothing in that case.
     * 
     * It is able to remove mutliple balloons that exist at the same position at once.
     */
    public static void removeBalloons(int x, int y)
    {
        removeObjectsFromWorld(getAllObjectsAt(x, y, "Balloon"));
    }

    /*
     * Example:
     * Calling removeAllObjects() will remove all objects in the world. It should be used at the beginning of the initialize method.
     */
    private static void removeAllObjects()
    {   
        removeAllNonBlockObjects();
        GWorld.removeObjectsFromWorld(GWorld.getAllObjects("Block"));
    }

    /*
     * Example:
     * Calling removeAllNonBlockObjects() will remove all objects that are not blocks in the world. It should be used at the beginning of the loadGame method.
     */
    private static void removeAllNonBlockObjects()
    {   
        GWorld.removeObjectsFromWorld(GWorld.getAllObjects("Peg"));
        GWorld.removeObjectsFromWorld(GWorld.getAllObjects("SpecialCharacter"));
        GWorld.removeObjectsFromWorld(GWorld.getAllObjects("Balloon"));
        GWorld.removeObjectsFromWorld(GWorld.getAllObjects("Message"));
    }

    /*
     * It puts the given message at the top-right corner.
     */
    public static void showMessage(String message)
    {
        GWorld.removeObjectsFromWorld(GWorld.getAllObjects("Message"));
        Message m = new Message(message);
        GWorld.addOneObject(m, WORLD_WIDTH-2, 0);
    }
    
    /*
     * It might be used by yourself to place random pegs on the board for testing.
     */
    public static void test()
    {
        GWorld.removeObjectsFromWorld( GWorld.getAllObjects("Peg") );
        GWorld.removeObjectsFromWorld( GWorld.getAllObjects("Balloon") );
        Random randomNumberGenerator = new Random();
        char numberToPegCharacterMapping[] = {'w', 'd', 'e', 'p'};
        for(int i=1; i<=NUMBER_OF_PLAY_ROWS; i++)
        {
            MastermindWorld.addObject(numberToPegCharacterMapping[randomNumberGenerator.nextInt(4)], 0, i);
            MastermindWorld.addObject(numberToPegCharacterMapping[randomNumberGenerator.nextInt(4)], 1, i);
            MastermindWorld.addObject(numberToPegCharacterMapping[randomNumberGenerator.nextInt(4)], 2, i);
        }
    }
}
