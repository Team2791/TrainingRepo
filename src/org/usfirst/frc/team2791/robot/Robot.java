
package org.usfirst.frc.team2791.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team2791.robot.commands.ExampleShakerCommandGroup;
import org.usfirst.frc.team2791.robot.commands.SpinInACircleFor10Seconds;
import org.usfirst.frc.team2791.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2791.robot.util.CommandChooser;
import org.usfirst.frc.team2791.robot.util.USBCamera;
import org.usfirst.frc.team2791.robot.util.USBCamera.Resolution;

/**
 * This class is the central hub of the code. 
 * This is where the Robot, OI, and subsystems all initalize.
 * There is a method for each phase of activity (Testing Mode, Teleop, Auto, Disabled).
 * This is also where the autonomous command is set with the custom Command Chooser.
 */
public class Robot extends IterativeRobot {

	public static final Drivetrain drivetrain = new Drivetrain();
	public static OI oi;

	Command autonomousCommand;
	CommandChooser<Command> autonChooser;

	boolean lookForAction = true;

	TeamColor teamColor = TeamColor.BLUE;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();

		try {
			USBCamera driverCam1 = new USBCamera("Driver Cam1", 0);
			driverCam1.setResolution(Resolution.k640x360); // make sure that you test this resolution to see if it will actually set it properly

			USBCamera driverCam2 = new USBCamera("Driver Cam2", 1);
			driverCam2.setResolution(Resolution.k640x360, 0.5);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		autonChooser = new CommandChooser<Command>("Auton Command Chooser");
		autonChooser.addCommand(new SpinInACircleFor10Seconds());
		autonChooser.addCommand(new ExampleShakerCommandGroup());
	}

	public void run() {
		debug();
	}
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 */
	@Override
	public void disabledInit() {
		run();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

		/**Logic for Auto Selection w/Gamepad Buttons**/
		if(!(OI.operator.getButtonLB() || OI.operator.getButtonRB()))
			lookForAction = true;

		if(OI.operator.getButtonRB() && lookForAction){
			autonChooser.incrementSelected(); 
			lookForAction = false;
		}
		if(OI.operator.getButtonLB() && lookForAction){
			autonChooser.decrementSelected(); 
			lookForAction = false;
		}

		if(OI.operator.getButtonX()){
			teamColor = TeamColor.BLUE;
		}

		if(OI.operator.getButtonB()){
			teamColor = TeamColor.RED;
		}

		run();

	}

	@Override
	public void autonomousInit() {		

		autonomousCommand = autonChooser.getSelected(); 

		if (autonomousCommand != null)
			System.out.println("***Starting "+teamColor+" "+autonomousCommand.getName()+" AutoMode***");
		autonomousCommand.start();

		run();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		run();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		run();
	}


	public void debug() {
		autonChooser.debug();
	}

	/**
	 * This is an easy way to set variables that change depending on what team you are on.
	 * This also provides a more intuative variable for checking what color you on, compared to a boolean for example
	 *
	 */
	public enum TeamColor{ 
		BLUE("BLUE"), RED("RED");

		private String color;

		TeamColor(String color){
			this.color = color;
		}

		@Override
		public String toString(){
			return color;
		}

	}
}
