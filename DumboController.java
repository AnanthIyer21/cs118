/*
* File: DumboController.java
* Created: 17 September 2002, 00:34
* Author: Stephen Jarvis
*/

import uk.ac.warwick.dcs.maze.logic.IRobot;

public class DumboController
{

	public void controlRobot(IRobot robot) {

		int randno;
		int direction;
		// set a variable for the direction that the robot will move
		String robot_direction = "";
		// set a variable for the number of walls around the robot
		int walls = 0;
		// set a variable for the location of the robot (junction, corridor, dead end)
		String robot_location = "";
		// create new variable for selecting random direction every 1/8 moves
		int randdir;

		// make robot choose a random direction every 1/8 moves - use the same method as used for selecting the random number
		randdir = (int) Math.floor(Math.random()*8);

		// only do the following do while loop if wall ahead and setting 7/8 chance of getting ahead as the direction
		if (robot.look(IRobot.AHEAD) != IRobot.WALL && randdir < 7) {
			// set direction as ahead
			direction = IRobot.AHEAD;
		} else {
			// Select a random number
			do {
				randno = (int) Math.floor(Math.random()*4);

				// Convert this to a direction

				if (randno == 0) {
					direction = IRobot.LEFT;
				} else if (randno == 1) {
					direction = IRobot.RIGHT;
				} else if (randno == 2) {
					direction = IRobot.BEHIND;
				} else {
					direction = IRobot.AHEAD;
				}
			} while (robot.look(direction) == IRobot.WALL) ;
		}

		robot.face(direction); /* Face the robot in this direction */ 

		// state the direction in which the robot travels relative to itself
		if (direction == IRobot.LEFT) {
			robot_direction = "left";
		} else if (direction == IRobot.RIGHT) {
			robot_direction = "right";
		} else if (direction == IRobot.BEHIND) {
			robot_direction = "backwards";
		} else {
			robot_direction = "forwards";
		}

		// count number of walls around robot
		for (int a = 0; a < 4; a++) {
			int dir = IRobot.AHEAD + a;
			if (robot.look(dir) == IRobot.WALL) {
				walls++ ;
			}
		}

		// at a junction, at a deadend, down a corridor
		// crossroads if 0 walls
		// crossroads if 1 wall
		// corridor if 2 walls
		// deadend if 3 walls
		switch (walls) {
			case 0: robot_location = " at a crossroads" ;
				break;
			case 1: robot_location = " at a junction" ;
				break;
			case 2: robot_location = " down a corridor" ;
				break;
			case 3: robot_location = " at a dead end" ;
				break;
		}

		System.out.println("I'm going " + robot_direction + robot_location);
	}
}




























