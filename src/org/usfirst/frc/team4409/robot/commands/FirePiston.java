package org.usfirst.frc.team4409.robot.commands;

import org.usfirst.frc.team4409.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class FirePiston extends Command {
	boolean state;
	DoubleSolenoid piston;
	
    public FirePiston(DoubleSolenoid _piston, boolean _state) {
    	requires(Robot.claw);
    	state = _state;
    	piston = _piston;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(piston.get() == Value.kForward){//print piston position to dashboard
    		//SmartDashboard.putString("Piston: ", "Foward");
    	}
    	else{
    		//SmartDashboard.putString("Piston: ", "Back");
    	}
    	if (state == true){//piston foward
    		piston.set(Value.kForward);
    	}
    	else{//piston reverse
    		piston.set(Value.kReverse);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
