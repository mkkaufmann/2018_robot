package org.usfirst.frc.team4409.robot.subsystems;

import org.usfirst.frc.team4409.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Elevator extends Subsystem {
	
	private DifferentialDrive ElevatorDrive = new DifferentialDrive(RobotMap.elevatorLeft,RobotMap.elevatorRight);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    protected void initDefaultCommand() {
    	setDefaultCommand(new org.usfirst.frc.team4409.robot.commands.ElevatorDrive());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void TeleopDrive(Joystick Driver) {
    	//use hall effect sensors to prevent crazy drivers from destroying the lift.
    	if (!RobotMap.topSwitch.get()){//top switch is activated(yes, false)
    		if (Driver.getY() < -0.01){//only allow lift to go down
    			ElevatorDrive.arcadeDrive(-Driver.getY(),0);
    		}
    		else{
    			ElevatorDrive.arcadeDrive(0, 0);
    		}
    	}
    	else if (!RobotMap.bottomSwitch.get()){//bottom switch is activated
    		if (Driver.getY() > 0.01){//only allow lift to go down
    			ElevatorDrive.arcadeDrive(-Driver.getY(),0);
    		}
    		else{
    			ElevatorDrive.arcadeDrive(0, 0);
    		}
    	}
    	else{
    		ElevatorDrive.arcadeDrive(-Driver.getY(),0);
    	}
    	if (!RobotMap.topSwitch.get() && !RobotMap.bottomSwitch.get()){
    		DriverStation.reportWarning("Both limits triggered. Get help.", false);
    	}
    	
    }
    
    public void stop() {
 	   ElevatorDrive.arcadeDrive(0, 0);
    }
    
    public void setSpeed(double speed, double turning) {
 	   ElevatorDrive.arcadeDrive(speed, turning);
    }
    
}

