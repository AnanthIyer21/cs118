/*
* File: DumboController.java
* Created: 17 September 2002, 00:34
* Author: Stephen Jarvis
*/

/*
// Preamble
// Math.random returns a double value with a positive sign >= 0.0 and <= 1.0 - approx with a uniform distribution
// Math.round returns the closest long to the argument
// Multiply Math.round * 3 to get a range of values from 0 - 3
// it would round to 0 for 0 - 0.5
// it would round to 1 for 0.5 - 1.5
// it would round to 2 for 1.5 - 2.5
// it would round to 3 for 2.5 - 3
// probability of 0 and 3 is half the probability of 1 and 2 - 1/6 vs 1/3
// forward = 3, right = 1, back = 2, left = 0, The results show that the number of back is approximately 2* number of forwards and the number of right is approximately 2* number of left. This is similar to the answers for the probabilities calculated.

// I decided to incorporate the 1/8 chance into my design by multiplying the number outputted by Math.random by 8
// This gave me a chance of getting numbers between 0 and 8 but not including 8
// When you floor these you get natural numbers from 0 to 7 - each number has a 1/8 chance of being selected by the Math.random function
// I just had to incorportate a new variable for the random number chosen - this wasnt disruptive
// I then set the direction to ahead for anything less than 7 and if it was 7, the only other option, i chose a random direction
// There is probably a better way to do this by just using a float and checking whether the output from math.random is less than 7/8 (the fraction)

*/




import uk.ac.warwick.dcs.maze.logic.IRobot;

public class Ex2
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




























