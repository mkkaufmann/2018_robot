package org.usfirst.frc.team4409.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorDrive extends Command {

	public ElevatorDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(org.usfirst.frc.team4409.robot.Robot.ELEVATOR_DRIVE);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	org.usfirst.frc.team4409.robot.Robot.ELEVATOR_DRIVE.TeleopDrive(org.usfirst.frc.team4409.robot.Robot.m_oi.getSecondJoystick());
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
