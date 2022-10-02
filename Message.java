import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Message here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Message extends Actor
{
    public Message(String message)
    {
        GreenfootImage gi= new greenfoot.GreenfootImage(300,40);
        gi.setColor(Color.WHITE);
        gi.drawString(message, 60, 20);
        setImage(gi);
    }   
}
