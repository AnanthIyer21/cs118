/*
 * File:    Broken	.java
 * Created: 7 September 2001
 * Author:  Stephen Jarvis
 */

import uk.ac.warwick.dcs.maze.logic.IRobot;

public class Broken
{
     public void controlRobot(IRobot robot) {
          int heading;
          heading = headingController(robot);
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
    private int headingController(IRobot robot) {
    // for when i want a random number
    int randno;
    // result of using the functions created earlier
    int north_south;
    int east_west;
    // create an output variable and set it to something standard that i can output
    int heading = IRobot.NORTH;
    // array for all possible directions that could be achieved using my logic
    int[] possible_directions = new int[2];
    // count the number of directions added to the array of possible directions
    int count = 0;

    // check relative target position
    north_south = isTargetNorth(robot);
    east_west = isTargetEast(robot);

    // north/south direction checks
    if (north_south == 1 && lookHeading(robot, IRobot.NORTH) != IRobot.WALL) {
        possible_directions[count++] = IRobot.NORTH;
    } else if (north_south == -1 && lookHeading(robot, IRobot.SOUTH) != IRobot.WALL) {
        possible_directions[count++] = IRobot.SOUTH;
    }

    // east/west direction checks
    if (east_west == 1 && lookHeading(robot, IRobot.EAST) != IRobot.WALL) {
        possible_directions[count++] = IRobot.EAST;
    } else if (east_west == -1 && lookHeading(robot, IRobot.WEST) != IRobot.WALL) {
        possible_directions[count++] = IRobot.WEST;
    }

    // if there are two possible directions, pick one randomly
    if (count == 2) {
        randno = (int) Math.round(Math.random());
        heading = possible_directions[randno];

    // if there is one possible direction go that way
    } else if (count == 1) {
        heading = possible_directions[0];

    // if there are no good directions pick a random non-wall direction
    } else {
        do {
            randno = (int) Math.floor(Math.random() * 4);
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

    return heading;
    }

  }























