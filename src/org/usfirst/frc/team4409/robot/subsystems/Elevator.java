package org.usfirst.frc.team4409.robot.subsystems;

import org.usfirst.frc.team4409.robot.RobotMap;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	
	Talon left = RobotMap.elevatorLeft;
	Talon right = RobotMap.elevatorRight;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void raiseLeft() {
    	left.set(0.5);
    }
    
    public void lowerLeft() {
    	left.set(-0.5);
    }
    
    public void raise(){
    	left.set(.5);
    	right.set(-.5);
    }
    public void raiseRight() {
    	right.set(-0.5);
    }

    public void lowerRight() {
    	right.set(0.5);
    }
    
    public void stopRight() {
    	right.set(0);
    }
    
    public void stopLeft() {
    	left.set(0);
    }
    
    public void stop(){
    	left.set(0);
    	right.set(0);
    }
}

