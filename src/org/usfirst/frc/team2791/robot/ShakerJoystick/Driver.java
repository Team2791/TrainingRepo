package org.usfirst.frc.team2791.robot.ShakerJoystick;

import org.usfirst.frc.team2791.robot.RobotMap;

/**
 * Robot driver object
 * 
 * Called 'ShakerDriver' in the 2017 code
 */
public class Driver extends OverriddenGamepad {
	private static Driver driverJoystickInstance;
	// this is to account for any slack in the drive train

	public Driver() {
		super(RobotMap.JOYSTICK_DRIVER_PORT);
	}

	public static Driver getInstance() {
		if (driverJoystickInstance == null)
			driverJoystickInstance = new Driver();
		return driverJoystickInstance;
	}

	public double getGtaDriveLeft() {
		return super.getGtaDriveLeft();
	}

	public double getGtaDriveRight() {
		return super.getGtaDriveRight();

	}
}