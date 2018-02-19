package org.usfirst.frc.team4409.robot.autonomous;

import org.usfirst.frc.team4409.robot.RobotMap;
import org.usfirst.frc.team4409.robot.autonomous.commands.*;

public class Baseline extends Autonomous{
	
	public Baseline() {
		super();

		commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.35,false));

	}

}
