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

          // set another integer for north or south and east or west
          int north_south;
          int east_west;

    // get whether target is north or south of the robot (1 = N, -1 = S, 0 = Neither)
          north_south = isTargetNorth(robot);
          // get whether target is east or west of the robot (1 = E, -1 = W, 0 = neither)
          east_west = isTargetEast(robot);

          if (north_south == 1 && lookHeading(robot, IRobot.NORTH) != IRobot.WALL) {
               robot.setHeading(IRobot.NORTH);
          } else if (north_south == -1 && lookHeading(robot, IRobot.SOUTH) != IRobot.WALL) {
               robot.setHeading(IRobot.SOUTH);
          } else if (east_west == 1 && lookHeading(robot, IRobot.EAST) != IRobot.WALL) {
               robot.setHeading(IRobot.EAST);
          } else if (east_west == -1 && lookHeading(robot, IRobot.WEST) != IRobot.WALL) {
               robot.setHeading(IRobot.WEST);
          } else {
               do {

                    randno = (int) Math.floor(Math.random()*4);

                    if (randno == 0) {
                         robot.setHeading(IRobot.WEST);
                    } else if (randno == 1) {
                         robot.setHeading(IRobot.EAST);
                    } else if (randno == 2) {
                         robot.setHeading(IRobot.SOUTH);
                    } else {
                         robot.setHeading(IRobot.NORTH);
                    }
               } while (robot.look(IRobot.AHEAD) == IRobot.WALL);
          }

          robot.face(IRobot.AHEAD);  // Face the direction


  }

  private int isTargetNorth(IRobot robot) {
     // get y coordinates of robot and target as integers
     int robot_y = robot.getLocation().y;
     System.out.println(robot_y);

     int target_y = robot.getTargetLocation().y;
     System.out.println(target_y);

     // compute whether target is below or above
     if (target_y < robot_y) {
          return 1;
     } else if (target_y > robot_y) {
          return -1;
     } else {
          return 0;
     }
}

  private int isTargetEast(IRobot robot) {
     // get x coordinates of robot and target as integers
     int robot_x = robot.getLocation().x;
     System.out.println(robot_x);

     int target_x = robot.getTargetLocation().x;
     System.out.println(target_x);

     // compute whether target is left or right
     if (target_x > robot_x) {
          return 1;
     } else if (target_x < robot_x) {
          return -1;
     } else {
          return 0;
     }

  }

  private int lookHeading(IRobot robot, int heading) {
     // get current direction that the robot facing
     int current_facing = robot.getHeading();

     // set heading to the direction you want to look in
     robot.setHeading(heading);

     // get status of what is in that direction
     int status = robot.look(IRobot.AHEAD);

     // set heading of robot to original direction
     robot.setHeading(current_facing);

     return status;
  }

}








