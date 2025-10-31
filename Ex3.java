/*
 * File:    Broken	.java
 * Created: 7 September 2001
 * Author:  Stephen Jarvis
 */

/*
Preamble

Having re-read the guide again, I found functions (getHeading and setHeading) which would take inputs in terms of NORTH, SOUTH, EAST, WEST. This made me think about how I can implement them into the method alongside the robot.look function to get the status of what exists in that direction (wall, beenbefore, passage).

The method ensures that the robot moves closer to the target because it goes through each direction (N,S,E,W) and checks if the target is in that direction and whether there is a wall there. If the target is in that direction and there is no wall, the robot moves that way. Otherwise a random direction with no wall is chosen. This ensures that as far as possible, the robot moves towards the target.

The Homing robot cannot be expected to find the target always because the robot can get stuck at a point where there is a wall in both directions in which the robot wants to move. This forces the robot to move in a direction which moves the robot away from the target, freeing up the square where the robot just was. This means on the next move, the robot is going to move in a direction which moves it towards the target, ie the robot goes back to the square where there is a wall in both directions in which the robot wants to move. This leads to a never ending cycle where the robot gets stuck in 2/3 squares - 2 if it is a dead end and 3 if its a situation where the wall is only in both directions the robot wants to move.

The robot can be improved by suggesting the robot to also not move to a position where it has already been before, to be able to escape this never ending cycle.Ex
*/





import uk.ac.warwick.dcs.maze.logic.IRobot;

public class Ex3
{

     public void controlRobot(IRobot robot) {

          int direction;
          int randno;

          // set another integer for north or south and east or west
          int north_south;
          int east_west;
          // set a variable for the direction the robot will be heading
          int heading;

          // get whether target is north or south of the robot (1 = N, -1 = S, 0 = Neither)
          north_south = isTargetNorth(robot);
          // get whether target is east or west of the robot (1 = E, -1 = W, 0 = neither)
          east_west = isTargetEast(robot);

          // if north/south/east/west has no wall and in correct direction - set heading to be that way else do random selection
          if (north_south == 1 && lookHeading(robot, IRobot.NORTH) != IRobot.WALL) {
               heading = IRobot.NORTH;
          } else if (north_south == -1 && lookHeading(robot, IRobot.SOUTH) != IRobot.WALL) {
               heading = IRobot.SOUTH;
          } else if (east_west == 1 && lookHeading(robot, IRobot.EAST) != IRobot.WALL) {
               heading = IRobot.EAST;
          } else if (east_west == -1 && lookHeading(robot, IRobot.WEST) != IRobot.WALL) {
               heading = IRobot.WEST;
          } else {
               do {

                    randno = (int) Math.floor(Math.random()*4);

                    if (randno == 0) {
                         heading = IRobot.NORTH;
                    } else if (randno == 1) {
                         heading = IRobot.EAST;
                    } else if (randno == 2) {
                         heading = IRobot.SOUTH;
                    } else {
                         heading = IRobot.WEST;
                    }
               } while (lookHeading(robot, heading) == IRobot.WALL);


          }


          ControlTest.test(heading, robot);

          robot.setHeading(heading);

          robot.face(IRobot.AHEAD);  // Face the direction


  }
  public void reset() {
     ControlTest.printResults();
  }

  private int isTargetNorth(IRobot robot) {
     // get y coordinates of robot and target as integers
     int robot_y = robot.getLocation().y;

     int target_y = robot.getTargetLocation().y;

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

     int target_x = robot.getTargetLocation().x;

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























