package org.usfirst.frc.team2791.robot.commands;

import org.usfirst.frc.team2791.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


/**
 * Just another example of a Command. See if you can figure out what it does!
 * Hint: Unnas's first few tries at an auto
 * Hint 2: It was Wenny's fault once
 *
 */
public class SpinInACircleFor10Seconds extends Command {

	private Timer timer = new Timer();
	private double timeToExpire = 10.0;

	public SpinInACircleFor10Seconds() {

		requires(Robot.drivetrain);
	}

	@Override
	protected void initialize() {
		timer.start();
	}

	@Override
	protected void execute() {
		Robot.drivetrain.setLeftRightMotorOutputs(1.0, -1.0);
	}

	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(timeToExpire);
	}

	@Override
	protected void end() {
		Robot.drivetrain.setLeftRightMotorOutputs(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
