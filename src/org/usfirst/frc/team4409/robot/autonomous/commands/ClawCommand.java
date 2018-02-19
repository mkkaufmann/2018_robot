package org.usfirst.frc.team4409.robot.autonomous.commands;

import org.usfirst.frc.team4409.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ClawCommand extends AutonomousCommand{

	private boolean closed;
	public ClawCommand(boolean _closed) {
		closed = _closed;
	}

	@Override
	public boolean Run(){
		RobotMap.claw.set(closed?Value.kForward:Value.kReverse);
		return true;
	}
}
