package org.usfirst.frc.team4409.robot.autonomous;

import org.usfirst.frc.team4409.robot.autonomous.commands.DriveCommand;

public class LeftSwitch extends Autonomous{
	
	public LeftSwitch() {
		super();
		commands.add(new DriveCommand(30,-30,0.35,false,2));
	}
}