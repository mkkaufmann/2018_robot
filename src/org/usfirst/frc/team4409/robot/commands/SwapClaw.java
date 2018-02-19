package org.usfirst.frc.team4409.robot.commands;

import org.usfirst.frc.team4409.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwapClaw extends Command {
	boolean finished = false;
	boolean state;

  

    public SwapClaw(boolean m_state) {
		// TODO Auto-generated constructor stub
    	requires(Robot.claw);
    	state = m_state;
    	m_state = !m_state;
	}

	// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.claw.close();
    	Robot.claw.setOpen(false);
    	/*
    	if (state == true){
    		Robot.claw.close();
        	Robot.claw.setOpen(false);
    	}
    	else{
    		Robot.claw.open();
        	Robot.claw.setOpen(true);
    	}
    	*/
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
    	Robot.claw.open();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public void setFinished(boolean finished){
    	this.finished = finished;
    }
   
}
