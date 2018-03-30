package org.usfirst.frc.team4409.robot.autonomous;

import org.usfirst.frc.team4409.robot.autonomous.commands.*;
import org.usfirst.frc.team4409.robot.RobotMap;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4409.robot.*;

public class LeftAuto extends Autonomous{
	int nintyTurn = 520;
	public LeftAuto() {
		super();
		boolean canSwitch;
		boolean canScale;
		int prefrence;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		prefrence = (int) Robot.scalePref.getSelected();
		if (gameData.charAt(0) == 'L'){//where can we score?
			canSwitch = true;
		}else{canSwitch = false;}
		if (gameData.charAt(1) == 'L'){
			canScale = true;
		}else{canScale = false;}
		commands.add(new WaitCommand(SmartDashboard.getNumber("Auto Wait", 0)));
		commands.add(new ClawCommand(true));
		commands.add(new WaitCommand(0.2));
		if (prefrence == 0){
			if(canSwitch){
				ScoreSwitch();
			}
			else if(canScale){
				ScoreScale();
			}
			else{
				//just drive across the base line
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,15));
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
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,15));
			}
		}
		
		else if(prefrence == 2){//Only switch
	
			if(canSwitch){
				//go for switch
				ScoreSwitch();
			}
			else{
				//just drive across the base line
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.35,true,15));
			}
		}
		else if(prefrence == 3){//Only Scale
			if(canScale){
				//go for scale
				ScoreScale(); 
			}
			else if(canSwitch){
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,15));
			}
			else{
				//just drive across the base line
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,15));
			}	
		}else if(prefrence == 4){
			if(canSwitch){
				ScoreSwitchB();
			}else if(canScale){
				ScoreScale();
			}else{
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,15));
			}
		}
	}
	public void ScoreSwitch(){
		//go for switch
		commands.add(new DriveCommand(RobotMap.driveToSwitchY,RobotMap.driveToSwitchY,0.35,true,6));
		commands.add(new WaitCommand(0.3));
		commands.add(new ClawCommand(false));
	}
	public void ScoreSwitchB(){
		DriverStation.reportWarning("switch b", false);
		commands.add(new AllCommand(RobotMap.driveEvenSwitch-20,0.40,true,70,0.65,6));
		commands.add(new WaitCommand(0.3));
		commands.add(new TurnCommand(280,0.35,true,5));
		commands.add(new WaitCommand(0.3));
		commands.add(new DriveCommand(30,30,0.40,true,1));
		commands.add(new ClawCommand(false));
	}
	
	public void ScoreScale(){
		//go for scale
//		commands.add(new DriveCommand(RobotMap.driveToScaleY,RobotMap.driveToScaleY,0.4,true,6));
//		commands.add(new TurnCommand(nintyTurn,-0.4,true,1.5));
//		commands.add(new DriveCommand(RobotMap.driveToScaleX,RobotMap.driveToScaleX,0.4,true,6));
//		commands.add(new WaitCommand(0.3));
//		commands.add(new ClawCommand(false));
		//go for scale
				DriverStation.reportWarning("scale", false);
				//foward short distance
				//90 degree turn to the right
				//drive foward past switch
				//90 degree turn to the left
				SmartDashboard.putBoolean("scale", true);
				//GYRO(double _power, boolean _holdLift, double _distance, double _period)
				commands.add(new GyroDriveCommand(0.6,false,210,10));
				commands.add(new TurnCommand(200,0.35,false,3));
				commands.add(new LiftCommand(135,0.8,3));
				commands.add(new LiftCommand(10,0.6,1));
				commands.add(new DriveCommand(40,0.40,true,6));
				commands.add(new ClawCommand(false));
				commands.add(new WaitCommand(1));
				commands.add(new DriveCommand(24,-0.40,true,6));

	}
}