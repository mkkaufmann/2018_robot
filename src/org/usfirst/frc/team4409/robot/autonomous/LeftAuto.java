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
			if(canScale){
				ScoreScale();
			}else if(canSwitch){
				ScoreSwitchB();
			}else{
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,15));
			}
//			if(canSwitch){
//				ScoreSwitchB();
//			}else if(canScale){
//				ScoreScale();
//			}else{
//				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,15));
//			}
		}else if(prefrence == 5){
			if(canScale){
				ScoreScale();
			}else{
				ScoreScaleGreedy();
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
//		commands.add(new GyroDriveCommand(0.6,false,210,10));
//		//(double _power, boolean _holdLift,double _period,double degrees){
//		commands.add(new RealGyroTurnCommand(-0.65,true,25,3));
//		commands.add(new LiftCommand(135,0.8,3));
//		commands.add(new LiftCommand(11,0.8,0.75));
//		commands.add(new DriveCommand(26,0.40,true,6));
//		commands.add(new ClawCommand(false));
//		commands.add(new WaitCommand(1));
//		commands.add(new DriveCommand(36,-0.40,true,6));
//		commands.add(new LiftCommand(146,-0.8,3));
//		commands.add(new RealGyroTurnCommand(-0.65,true,30,3));
//		commands.add(new DriveCommand(36,0.40,true,6));
//		commands.add(new RealGyroTurnCommand(-0.65,true,85,3));
//				DriverStation.reportWarning("scale", false);
				//foward short distance
				//90 degree turn to the right
				//drive foward past switch
				//90 degree turn to the left
				SmartDashboard.putBoolean("scale", true);
				//GYRO(double _power, boolean _holdLift, double _distance, double _period)
				commands.add(new GyroDriveCommand(0.6,false,208,10));
				//commands.add(new TurnCommand(300,0.5,false,5));
				commands.add(new RealGyroTurnCommand(0.65,true,22,3));
				commands.add(new LiftCommand(135,0.8,3));
				commands.add(new LiftCommand(11,0.6,0.75));
				commands.add(new DriveCommand(26,0.40,true,6));
				commands.add(new ClawCommand(false));
				commands.add(new WaitCommand(1));
				commands.add(new DriveCommand(30,-0.40,true,6));
				commands.add(new LiftCommand(130,-0.8,3));
				commands.add(new RealGyroTurnCommand(0.65,true,30,3));
				commands.add(new DriveCommand(36,0.40,true,6));

	}
	public void ScoreScaleGreedy(){
				SmartDashboard.putBoolean("scale", true);
				//GYRO(double _power, boolean _holdLift, double _distance, double _period)
				/*SmartDashboard.putNumber("greedy scale forward", 190);
				SmartDashboard.putNumber("greedy scale cross", 120);
				SmartDashboard.putNumber("greedy scale turn 1", 90);
				SmartDashboard.putNumber("greedy scale turn 2", 90);
				SmartDashboard.putNumber("greedy scale forward 2", 30)*/
				commands.add(new GyroDriveCommand(0.3,false,196,10));
				//commands.add(new TurnCommand(300,0.5,false,5));
				commands.add(new RealGyroTurnCommand(0.5,true,80,3));
				commands.add(new GyroDriveCommand(0.6,false,148,10));

				commands.add(new RealGyroTurnCommand(-0.65,true,78,3));
				commands.add(new LiftCommand(135,0.8,3));
				commands.add(new LiftCommand(11,0.75,0.75));
				commands.add(new GyroDriveCommand(0.6,false,20,10));
				commands.add(new ClawCommand(false));
				commands.add(new WaitCommand(1));
				commands.add(new DriveCommand(30,-0.40,true,6));
	}
}