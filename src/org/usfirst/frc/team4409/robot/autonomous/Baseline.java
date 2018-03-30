package org.usfirst.frc.team4409.robot.autonomous;

import org.usfirst.frc.team4409.robot.RobotMap;
import org.usfirst.frc.team4409.robot.autonomous.commands.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Baseline extends Autonomous{
	
	public Baseline() {
		super();
		commands.add(new WaitCommand(SmartDashboard.getNumber("Auto Wait", 0)));
		commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.35,false,15));
		//commands.add(new RealGyroTurnCommand(0.35,true,10,90));//test, delete this
	}

}
