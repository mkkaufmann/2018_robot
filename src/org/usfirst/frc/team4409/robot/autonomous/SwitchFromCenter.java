package org.usfirst.frc.team4409.robot.autonomous;

import org.usfirst.frc.team4409.robot.autonomous.commands.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwitchFromCenter extends Autonomous{
	
	public SwitchFromCenter() {
		super();
		String gameData;
		int nintyturn = 1050;
		int sixtyturn = 600;
		int thirtyturn = 350;
		int driveAcross = 30;
		int driveToSwitch = 82;
		double turnSpeed = 0.34;
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();//get the switch position
		commands.add(new WaitCommand(SmartDashboard.getNumber("Auto Wait", 0)));
		commands.add(new ClawCommand(true));
		commands.add(new WaitCommand(0.2));
		commands.add(new LiftCommand(70,0.65,3));
		commands.add(new DriveCommand(15,15,0.45,true,2));
		commands.add(new WaitCommand(0.2));
		if(gameData.charAt(0) == 'L')
		  {
			commands.add(new TurnCommand(500,-turnSpeed,true,10));
			commands.add(new WaitCommand(0.3));
			commands.add(new DriveCommand(driveAcross + 10,driveAcross + 10,0.55,true,4));//drive further because of exchange
			commands.add(new WaitCommand(0.3));
			commands.add(new TurnCommand(400,turnSpeed,true,10));
			commands.add(new WaitCommand(0.3));
			commands.add(new DriveCommand(driveToSwitch + 10,driveToSwitch+10,0.35,true,3));
			commands.add(new ClawCommand(false));
		  } else {//R
			DriverStation.reportWarning("right",false);
			commands.add(new TurnCommand(thirtyturn,turnSpeed,true,10));
			commands.add(new WaitCommand(0.3));
			commands.add(new DriveCommand(driveAcross+10,driveAcross+10,0.55,true,5));//drive shorter because of exchange
			commands.add(new WaitCommand(0.3));
			commands.add(new TurnCommand(400,-turnSpeed,true,10));
			commands.add(new WaitCommand(0.3));
			commands.add(new DriveCommand(driveToSwitch - 21,driveToSwitch-21,0.4,true,4));
			commands.add(new ClawCommand(false));
		  }
		commands.add(new WaitCommand(0.3));
		commands.add(new DriveCommand(10,10,-0.35,false,2));
	}
}