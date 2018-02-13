package org.usfirst.frc.team4409.robot.autonomous;

import org.usfirst.frc.team4409.robot.autonomous.commands.*;

public class Baseline extends Autonomous{
	
	public Baseline() {
		super();
		commands.add(new DriveCommand(360,360,0.35));
	}
}