package org.usfirst.frc.team4409.robot.subsystems;

import org.usfirst.frc.team4409.robot.RobotMap;
import org.usfirst.frc.team4409.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	
	private RobotDrive ElevatorDrive = new RobotDrive(RobotMap.elevatorLeft,RobotMap.elevatorRight);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    protected void initDefaultCommand() {
    	setDefaultCommand(new org.usfirst.frc.team4409.robot.commands.ElevatorDrive());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void TeleopDrive(Joystick Driver) {
    	ElevatorDrive.arcadeDrive(Driver.getY(),-Driver.getX());
    	
    }
    
    public void stop() {
 	   ElevatorDrive.drive(0, 0);
    }
    
    public void setSpeed(double speed, double turning) {
 	   ElevatorDrive.arcadeDrive(speed, turning);
    }
    
}

