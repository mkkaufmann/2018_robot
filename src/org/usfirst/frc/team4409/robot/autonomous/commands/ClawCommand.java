package org.usfirst.frc.team4409.robot.autonomous.commands;

import org.usfirst.frc.team4409.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ClawCommand extends AutonomousCommand{

	private boolean open;
	public ClawCommand(boolean _open) {
		open = _open;
	}

	@Override
	public boolean Run(){
		RobotMap.claw.set(open?Value.kForward:Value.kReverse);
		return true;
	}
}
