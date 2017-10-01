package org.usfirst.frc.team2791.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * With normal Command Groups, you have to define each Command in the sequence as soon as you init the Command Group.</br>
 * With Shaker Command Groups, the Command sequence is defined right before you start the Command.
 * That way, You can use Commands that rely on parameters or variables that aren't going to be defined until you start the Command
 */
public abstract class ShakerCommandGroup extends CommandGroup{
	
	public ShakerCommandGroup(String name) {
		super(name);
	}
	
	/**
	 * Initalize each command sequence
	 */
	public abstract void init();
	
	@Override
	public synchronized void start() {
		init();
		super.start();
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
