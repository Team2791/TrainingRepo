package org.usfirst.frc.team2791.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2791.robot.ShakerJoystick.Driver;
import org.usfirst.frc.team2791.robot.ShakerJoystick.Operator;
import org.usfirst.frc.team2791.robot.commands.*; //this imports every class in the commands package

/**
 * This class is the double sided tape that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot. 
 * 
 * The DrivingWithJoystick command isn't mapped here b/c it's not like we are saying "When you get an input, activate the command"
 * We are saying "Run this command, and when there is input, change what you are doing"
 * 
 */
public class OI {
	public static Driver driver = new Driver(); 
	public static Operator operator = new Operator();
	
	public OI() {
		
		/******Below is an example of what you would do to map a command to a button********/
		
		//Step 1: make the button that you want to map
		Button driverA = new JoystickButton(driver,1); //1 is the number that corresponds to the A button for the controller. These numbers can be looked up
		
		
		//Step2: send a new command whenever that button is pressed/held/released
		driverA.whenPressed(new SpinInACircleFor10Seconds()); //This is saying, "When you get an input (in this case the A button being hit), activate the command"
		
		
	}
	
}
