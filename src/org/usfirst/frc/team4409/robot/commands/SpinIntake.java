package org.usfirst.frc.team4409.robot.commands;

import org.usfirst.frc.team4409.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class SpinIntake extends Command {
	double speed;
	Talon intake;
	
    public SpinIntake(Talon _intake, double _speed) {
    	intake = _intake;
    	speed = _speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intake.set(speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intake.set(speed);
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
