package org.usfirst.frc.team2791.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2791.robot.OI;
import org.usfirst.frc.team2791.robot.Robot;
import org.usfirst.frc.team2791.robot.util.GTADrive;

public class DriveWithJoystick extends Command {
		
	public DriveWithJoystick() {
		
		requires(Robot.drivetrain); //this makes sure that if another command also requires the drivetrain, there won't be conflicting messages being sent to the electronics
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double left, right;
		
		//use the GTADrive class to interpret the joystick values into speeds for the left and right side motors
		left = GTADrive.getLeftValue(OI.driver); 
		right = GTADrive.getRightValue(OI.driver);
		
		Robot.drivetrain.setLeftRightMotorOutputs(left, right);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		//it's good to make sure your subsystem ends in a safe manner. Keeps the mentor's blood pressure down :)
		Robot.drivetrain.setLeftRightMotorOutputs(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
