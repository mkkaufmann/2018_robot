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
		
		//commands.add(new ParallelCommand(new LiftCommand(70,0.65,3),new GyroDriveCommand(0.45,false,15)));
		commands.add(new AllCommand(15,0.45,true,70,0.65,3));
		
		commands.add(new WaitCommand(0.2));
		if(gameData.charAt(0) == 'L')
		  {
			/*Ideal state code
			 * commands.add(new GyroTurnCommand(-0.5,true,45,3));
			 * commands.add(new GyroDriveCommand(0.6,true,driveAcross+20,4));//THIS VALUE NEEDS TWEAKING
			 * commands.add(new GyroTurnCommand(0.5,true,45,3));
			 * commands.add(new GyroDriveCommand(0.6,true,driveToSwitch+10,2.5));//THIS VALUE NEEDS TWEAKING
			 * */
			commands.add(new TurnCommand(450,-turnSpeed*1.3,true,10));
			commands.add(new DriveCommand(driveAcross + 20,driveAcross + 20,0.55,true,4));//drive further because of exchange
			commands.add(new TurnCommand(180,turnSpeed,true,10));
			commands.add(new DriveCommand(driveToSwitch + 10,driveToSwitch+10,0.40,true,2.5));
		  } else {//R
			DriverStation.reportWarning("right",false);
			commands.add(new TurnCommand(thirtyturn-120,turnSpeed,true,10));
			commands.add(new WaitCommand(0.3));
			commands.add(new DriveCommand(driveAcross-6,driveAcross-6,0.55,true,5));//drive shorter because of exchange
			commands.add(new WaitCommand(0.3));
			commands.add(new TurnCommand(550,-turnSpeed,true,10));
			commands.add(new WaitCommand(0.3));
			commands.add(new DriveCommand(driveToSwitch - 21,driveToSwitch-21,0.45,true,3));
		  }
		commands.add(new ClawCommand(false));
		//commands.add(new GyroDriveCommand(-0.6,10,2));
		commands.add(new DriveCommand(10,10,-0.35,false,2));
	}
}