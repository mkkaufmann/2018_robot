package org.usfirst.frc.team4409.robot.autonomous;

import org.usfirst.frc.team4409.robot.autonomous.commands.*;

import edu.wpi.first.wpilibj.DriverStation;

public class SwitchFromCenter extends Autonomous{
	
	public SwitchFromCenter() {
		super();
		String gameData;
		int nintyturn = 520;
		int driveAcross = 53;
		int driveToSwitch = 50;
		double turnSpeed = 0.5;
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();//get the switch position
		commands.add(new ClawCommand(true));
		commands.add(new WaitCommand(0.2));
		commands.add(new LiftCommand(45,0.45));
		commands.add(new DriveCommand(40,40,0.55,true));
		if(gameData.charAt(0) == 'L')
		  {
			commands.add(new TurnCommand(nintyturn + 80,-turnSpeed,true));
			commands.add(new DriveCommand(driveAcross + 15,driveAcross,0.55,true));
			commands.add(new TurnCommand(nintyturn - 15,turnSpeed,true));
			commands.add(new DriveCommand(driveToSwitch + 8,driveToSwitch,0.55,true));
			commands.add(new WaitCommand(0.5));
			commands.add(new ClawCommand(false));
		  } else {//R
			commands.add(new TurnCommand(nintyturn,turnSpeed,true));
			commands.add(new DriveCommand(driveAcross,driveAcross,0.55,true));
			commands.add(new TurnCommand(nintyturn,-turnSpeed,true));
			commands.add(new DriveCommand(driveToSwitch,driveToSwitch,0.55,true));
			commands.add(new WaitCommand(0.5));
			commands.add(new ClawCommand(false));
		  }
		commands.add(new DriveCommand(25,25,-0.35,false));
	}
}