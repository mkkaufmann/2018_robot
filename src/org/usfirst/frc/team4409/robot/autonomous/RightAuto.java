package org.usfirst.frc.team4409.robot.autonomous;

import org.usfirst.frc.team4409.robot.autonomous.commands.*;
import org.usfirst.frc.team4409.robot.RobotMap;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4409.robot.*;

public class RightAuto extends Autonomous{
	int nintyTurn = 520;
	public RightAuto() {
		super();
		boolean canSwitch;
		boolean canScale;
		int prefrence;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		prefrence = (int) Robot.scalePref.getSelected();
		if (gameData.charAt(0) == 'R'){//where can we score?
			canSwitch = true;
		}else{canSwitch = false;}
		if (gameData.charAt(1) == 'R'){
			canScale = true;
		}else{canScale = false;}
		commands.add(new WaitCommand(SmartDashboard.getNumber("Auto Wait", 0)));
		commands.add(new ClawCommand(true));
		commands.add(new WaitCommand(0.2));
		commands.add(new LiftCommand(45,0.45,3));
		if (prefrence == 0){
			if(canSwitch){
				
				ScoreSwitch();
			}
			else if(canScale){
				
				ScoreScale();
			}
			else{
				//just drive across the base line
				DriverStation.reportWarning("baseline R-1", false);
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,2));
			}
		}
		else if (prefrence == 1){//want scale
			if(canScale){
				//go for scale
				ScoreScale();
			}
			else if(canSwitch){
				//go for switch
				ScoreSwitch();
			}
			else{
				//just drive across the base line
				DriverStation.reportWarning("baseline R-2", false);
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,2));
			}
		}
		
		else if(prefrence == 2){//Only switch
			if(canScale){
				DriverStation.reportWarning("baseline R-3", false);
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,2));
			}
			else if(canSwitch){
				//go for switch
				ScoreSwitch();
			}
			else{
				//just drive across the base line
				DriverStation.reportWarning("baseline R-4", false);
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,2));
			}
		}
		else if(prefrence == 3){//Only Scale
			if(canScale){
				//go for scale
				ScoreScale(); 
			}
			else if(canSwitch){
				DriverStation.reportWarning("baseline R-5", false);
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,2));
			}
			else{
				//just drive across the base line
				DriverStation.reportWarning("baseline R-6", false);
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,2));
			}	
		}
	}
	public void ScoreSwitch(){
		//go for switch
		DriverStation.reportWarning("switch", false);
		commands.add(new DriveCommand(RobotMap.driveToSwitchY,RobotMap.driveToSwitchY,0.4,true,1.5));
		commands.add(new TurnCommand(nintyTurn,0.4,true,1.5));
		commands.add(new DriveCommand(RobotMap.driveToSwitchX,RobotMap.driveToSwitchX,0.4,true,1));
		commands.add(new WaitCommand(0.3));
		commands.add(new ClawCommand(false));
	}
	public void ScoreScale(){
		//go for scale
		DriverStation.reportWarning("scale", false);
		commands.add(new DriveCommand(RobotMap.driveToScaleY,RobotMap.driveToScaleY,0.4,true,3));
		commands.add(new TurnCommand(nintyTurn,0.4,true,1.5));
		commands.add(new DriveCommand(RobotMap.driveToScaleX,RobotMap.driveToScaleX,0.4,true,1));
		commands.add(new WaitCommand(0.3));
		commands.add(new ClawCommand(false));
	}
}