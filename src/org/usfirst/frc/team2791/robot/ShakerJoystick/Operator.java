package org.usfirst.frc.team2791.robot.ShakerJoystick;

import org.usfirst.frc.team2791.robot.RobotMap;

/**
 * Robot operator object
 *  
 * Called 'ShakerOperator' in the 2017 code
 */
public class Operator extends OverriddenGamepad {
    private static Operator operatorJoystickInstance;
    
    public Operator() {
        super(RobotMap.JOYSTICK_OPERATOR_PORT);
    }

    public static Operator getInstance() {
        if (operatorJoystickInstance == null)
            operatorJoystickInstance = new Operator();
        return operatorJoystickInstance;

    }
    public double getGtaDriveLeft() {
        return super.getGtaDriveLeft();
    }

    public double getGtaDriveRight() {
        return super.getGtaDriveRight();
    }
}