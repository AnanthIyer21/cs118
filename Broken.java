/*
 * File:    Broken	.java
 * Created: 7 September 2001
 * Author:  Stephen Jarvis
 */

import uk.ac.warwick.dcs.maze.logic.IRobot;

public class Broken 
{
    
  public void controlRobot(IRobot robot) {

    int direction;	
    int randno;

    direction = robot.look(IRobot.EAST);

    do {

       randno = (int) Math.round(Math.random()*3);

       if ( randno == 0)
            direction = IRobot.LEFT;
       else if (randno == 1)
            direction = IRobot.RIGHT;
       else if (randno == 2)
            direction = IRobot.BEHIND;
       else 
            direction = IRobot.AHEAD;
    } while (robot.look(IRobot.AHEAD)==IRobot.WALL);
     
    robot.face(direction);  /* Face the direction */   

  }

}
