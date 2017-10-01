package org.usfirst.frc.team2791.robot.commands;


/**
 *Spins in A circle for 30 seconds
 */
public class ExampleShakerCommandGroup extends ShakerCommandGroup {

	public ExampleShakerCommandGroup() {
		super("Example Command");
    }

	@Override
	public void init() {
    	addSequential(new SpinInACircleFor10Seconds()); 
    	addSequential(new SpinInACircleFor10Seconds()); 
    	addSequential(new SpinInACircleFor10Seconds()); 
	}
}
