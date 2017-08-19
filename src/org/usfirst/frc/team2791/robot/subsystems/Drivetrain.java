package org.usfirst.frc.team2791.robot.subsystems;

import org.usfirst.frc.team2791.robot.RobotMap;
import org.usfirst.frc.team2791.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem{

	//Even though we use Sparks, we use the Talon Object.
	/*
	 * Unnas's Note: We had some problems calibrating the Sparks, and it MAY (big MAY) have been because we were using the Talon class here.
	 * So if we are having similar calibration issues, try changing these objects to the PWMSpeedController object from wpilib
	 */

	public Talon left;
	public Talon right;

	private RobotDrive drivetrain;

	public Drivetrain() {

		//init the talons
		left = new Talon(RobotMap.LEFT_DRIVE_PORT);
		right = new Talon(RobotMap.RIGHT_DRIVE_PORT);

		//The RobotDrive is an easy way to set up tank drive
		drivetrain = new RobotDrive(left, right);

		//sometimes the Sparks are inverted ¯\_(ツ)_/¯
		drivetrain.setInvertedMotor(MotorType.kFrontLeft, false);
		drivetrain.setInvertedMotor(MotorType.kFrontRight, false);

	}

	@Override
	protected void initDefaultCommand() {
		/*
		 * this is so the drivetrain will always be looking for joystick values.
		 * If new command starts and requires the drivetrain, that new command will start.
		 * DriveWithJoystick will restart after the new command is done
		 */
		setDefaultCommand(new DriveWithJoystick()); 
	}

	public void setLeftRightMotorOutputs(double left, double right) {
		drivetrain.setLeftRightMotorOutputs(left, right); //used to set the speeds of the left and right motors
	}

	//In the future, you'll need to add sensor logic, PID, and other stuff.

}
