package org.usfirst.frc.team4409.robot.autonomous;

import org.usfirst.frc.team4409.robot.autonomous.commands.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwitchFromCenter extends Autonomous{
	
	public SwitchFromCenter() {
		super();
		String gameData;
		int nintyturn = 400;
		int driveAcross = 45;
		int driveToSwitch = 66;
		double turnSpeed = 0.5;
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();//get the switch position
		commands.add(new WaitCommand(SmartDashboard.getNumber("Auto Wait", 0)));
		commands.add(new ClawCommand(true));
		commands.add(new WaitCommand(0.2));
		commands.add(new LiftCommand(52,0.65,3));
		commands.add(new DriveCommand(15,15,0.55,true,2));
		if(gameData.charAt(0) == 'L')
		  {
			commands.add(new TurnCommand(nintyturn + 40,-turnSpeed,true,1.5));
			commands.add(new DriveCommand(driveAcross + 15,driveAcross,0.55,true,1.5));
			commands.add(new TurnCommand(nintyturn - 15,turnSpeed,true,1.5));
			commands.add(new DriveCommand(driveToSwitch + 8,driveToSwitch,0.55,true,1.5));
			commands.add(new WaitCommand(0.5));
			commands.add(new ClawCommand(false));
		  } else {//R
			commands.add(new TurnCommand(nintyturn,turnSpeed,true,1.5));
			commands.add(new DriveCommand(driveAcross,driveAcross,0.55,true,1.5));
			commands.add(new TurnCommand(nintyturn,-turnSpeed,true,1.5));
			commands.add(new DriveCommand(driveToSwitch,driveToSwitch,0.55,true,1.5));
			commands.add(new WaitCommand(0.5));
			commands.add(new ClawCommand(false));
		  }
		commands.add(new WaitCommand(0.3));
		commands.add(new DriveCommand(25,25,-0.35,false,2));
	}
}