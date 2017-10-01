package org.usfirst.frc.team2791.robot.subsystems;

import org.usfirst.frc.team2791.robot.RobotMap;
import org.usfirst.frc.team2791.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem{

	public Spark left;
	public Spark right;
	
	private double leftOutput = 0;
	private double rightOutput = 0;

	private RobotDrive drivetrain;

	public Drivetrain() {

		//init the speedcontrollers
		left = new Spark(RobotMap.LEFT_DRIVE_PORT);
		right = new Spark(RobotMap.RIGHT_DRIVE_PORT);

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
		 * If a new command starts and requires the drivetrain, that new command will start.
		 * DriveWithJoystick will restart after the new command is done
		 */
		setDefaultCommand(new DriveWithJoystick()); 
	}

	public void setLeftRightMotorOutputs(double left, double right) {
		leftOutput = left;
		rightOutput = right;
		drivetrain.setLeftRightMotorOutputs(leftOutput, rightOutput); //used to set the speeds of the left and right motors
	}

	public void debug() {
		
		SmartDashboard.putNumber("Drivetrain/Left Drive Output", leftOutput);
		SmartDashboard.putNumber("Drivetrain/Right Drive Output", rightOutput);
		
	}

}
