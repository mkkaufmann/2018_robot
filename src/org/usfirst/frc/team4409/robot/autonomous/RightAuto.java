package org.usfirst.frc.team4409.robot.autonomous;

import org.usfirst.frc.team4409.robot.autonomous.commands.*;

import edu.wpi.first.wpilibj.DriverStation;

import org.usfirst.frc.team4409.robot.*;

public class RightAuto extends Autonomous{
	
	public RightAuto() {
		super();
		int driveToScale = 200;
		int driveToScale2 = 6;
		int driveToSwitch = 100;
		int nintyTurn = 520;
		int driveToSwitch2 = 24;
		int baseline = 97;
		boolean canSwitch;
		boolean canScale;
		boolean wantSwitch;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'R'){//where can we score?
			canSwitch = true;
		}else{canSwitch = false;}
		if (gameData.charAt(1) == 'R'){
			canScale = true;
		}else{canScale = false;}
		if (RobotMap.theScale == 0){
			wantSwitch = true;
		}else {wantSwitch = false;}
		
		commands.add(new ClawCommand(true));
		commands.add(new WaitCommand(0.2));
		commands.add(new LiftCommand(45,0.45));
		if (wantSwitch){
			if(canSwitch){
				//go for switch
				commands.add(new DriveCommand(driveToSwitch,driveToSwitch,0.4,true));
				commands.add(new TurnCommand(nintyTurn,0.4,true));
				commands.add(new DriveCommand(driveToSwitch2,driveToSwitch2,0.4,true));
				commands.add(new WaitCommand(0.3));
				commands.add(new ClawCommand(false));
			}
			else if(canScale){
				//go for scale
				commands.add(new DriveCommand(driveToScale,driveToScale,0.4,true));
				commands.add(new TurnCommand(nintyTurn,0.4,true));
				commands.add(new DriveCommand(driveToScale2,driveToScale2,0.4,true));
				commands.add(new WaitCommand(0.3));
				commands.add(new ClawCommand(false));
			}
			else{
				//just drive across the base line
				commands.add(new DriveCommand(baseline,baseline,0.4,true));
			}
		}
		else{//want scale
			if(canScale){
				//go for scale
				commands.add(new DriveCommand(driveToScale,driveToScale,0.4,true));
				commands.add(new TurnCommand(nintyTurn,0.4,true));
				commands.add(new DriveCommand(driveToScale2,driveToScale2,0.4,true));
				commands.add(new WaitCommand(0.3));
				commands.add(new ClawCommand(false));
			}
			else if(canSwitch){
				//go for switch
				commands.add(new DriveCommand(driveToSwitch,driveToSwitch,0.4,true));
				commands.add(new TurnCommand(nintyTurn,0.4,true));
				commands.add(new DriveCommand(driveToSwitch2,driveToSwitch2,0.4,true));
				commands.add(new WaitCommand(0.3));
				commands.add(new ClawCommand(false));
			}
			else{
				//just drive across the base line
				commands.add(new DriveCommand(baseline,baseline,0.4,true));
			}
		}
	}
}