package org.usfirst.frc.team4409.robot.autonomous;

import org.usfirst.frc.team4409.robot.autonomous.commands.*;
import org.usfirst.frc.team4409.robot.RobotMap;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4409.robot.*;

public class RightAuto extends Autonomous{
	int nintyTurn = 520;
	static int SWITCH_SIDE = 4;
	public RightAuto() {
		super();
		boolean canSwitch;
		boolean canScale;
		int prefrence;
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		prefrence = (int) Robot.scalePref.getSelected();
		System.out.println(prefrence);
		if (gameData.charAt(0) == 'R'){//where can we score?
			canSwitch = true;
		}else{canSwitch = false;}
		if (gameData.charAt(1) == 'R'){
			canScale = true;
		}else{canScale = false;}
		commands.add(new WaitCommand(SmartDashboard.getNumber("Auto Wait", 0)));
		commands.add(new ClawCommand(true));
		commands.add(new WaitCommand(0.2));
		//commands.add(new LiftCommand(70,0.45,3));
		if (prefrence == 0){//switch
			if(canSwitch){
				
				ScoreSwitchB();
			}
			else if(canScale){
				
				ScoreScale();
			}
			else{
				//just drive across the base line
				DriverStation.reportWarning("baseline R-1", false);
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
				ScoreSwitchB();
			}
			else{
				//just drive across the base line
				DriverStation.reportWarning("baseline R-2", false);
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,15));
			}
		}
		
		else if(prefrence == 2){//Only switch
			System.out.println("Prefrence:" + prefrence + " scale: " + String.valueOf(canScale) + " switch: " + String.valueOf(canSwitch));
			
			if(canSwitch){
				//go for switch
				ScoreSwitch();
			}
			else{
				//just drive across the base line
				DriverStation.reportWarning("baseline R-4", false);
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.35,true,15));
			}
		}
		else if(prefrence == 3){//Only Scale
			if(canScale){
				//go for scale
				ScoreScale(); 
			}
			else if(canSwitch){
				DriverStation.reportWarning("baseline R-5", false);
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,15));
			}
			else{
				//just drive across the base line
				DriverStation.reportWarning("baseline R-6", false);
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,15));
			}	
		}else if(prefrence == SWITCH_SIDE){/*Scale CHANGE THE PRIORITY*/
			SmartDashboard.putString("auto", "scale (b)");
			SmartDashboard.putBoolean("work", true);
//			if(canScale){
//				//go for scale
//				SmartDashboard.putString("input", "please help please help work help please");
//				SmartDashboard.putString("auto", "scale (b) SCALE");
//				ScoreScale(); 
//			}
//			else 
			if(canScale){
				ScoreScale();
			}else if(canSwitch){
				SmartDashboard.putString("auto", "scale (b) SWITCH");
				ScoreSwitchB();
			}else{
				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,15));
			}
//			if(canSwitch){
//				ScoreSwitchB();
//			}else if(canScale){
//				SmartDashboard.putString("auto", "scale (b) SWITCH");
//				ScoreScale();
//			}else{
//				commands.add(new DriveCommand(RobotMap.baseline,RobotMap.baseline,0.4,true,15));
//			}
		}if(prefrence == 5){
			if(canScale){
				ScoreScale();
			}else{
				ScoreScaleGreedy();
			}
		}
	}
	public void ScoreSwitch(){
		//go for switch
		DriverStation.reportWarning("switch", false);
		commands.add(new DriveCommand(RobotMap.driveToSwitchY,RobotMap.driveToSwitchY,0.35,true,6));
		commands.add(new WaitCommand(0.3));
		commands.add(new ClawCommand(false));
	}
	public void ScoreSwitchB(){
		DriverStation.reportWarning("switch b", false);
		//public AllCommand(double _distance, double _power, boolean _holdLift,double _liftAmount,double _liftPower, double _period){
		commands.add(new AllCommand(RobotMap.driveEvenSwitch-20,0.40,true,70,0.65,6));
		//commands.add(new DriveCommand(RobotMap.driveEvenSwitch-20,RobotMap.driveEvenSwitch-20,0.40,true,6));
		commands.add(new WaitCommand(0.3));
		commands.add(new TurnCommand(nintyTurn+120,-0.35,true,5));
		commands.add(new WaitCommand(0.3));
		commands.add(new DriveCommand(30,30,0.40,true,1));
		commands.add(new ClawCommand(false));
		/*
		 * DriverStation.reportWarning("switch b", false);
		commands.add(new AllCommand(RobotMap.driveEvenSwitch-20,0.40,true,70,0.65,6));
		commands.add(new WaitCommand(0.3));
		commands.add(new TurnCommand(280,0.35,true,5));
		commands.add(new WaitCommand(0.3));
		commands.add(new DriveCommand(30,30,0.40,true,1));
		commands.add(new ClawCommand(false));
		 */
	}
	public void ScoreScale(){
		//go for scale
		DriverStation.reportWarning("scale", false);
		//foward short distance
		//90 degree turn to the right
		//drive foward past switch
		//90 degree turn to the left
		SmartDashboard.putBoolean("scale", true);
		//GYRO(double _power, boolean _holdLift, double _distance, double _period)
		commands.add(new GyroDriveCommand(0.6,false,210,10));
		//(double _power, boolean _holdLift,double _period,double degrees){
		commands.add(new GyroTurnCommand(-0.65,true,25,3));
		commands.add(new LiftCommand(135,0.8,3));
		commands.add(new LiftCommand(13,0.9,0.5));
		commands.add(new DriveCommand(26,0.40,true,6));
		commands.add(new ClawCommand(false));
		//commands.add(new WaitCommand(1));
		commands.add(new DriveCommand(60,-0.40,true,6));
//		commands.add(new LiftCommand(146,-0.8,3));
//		commands.add(new RealGyroTurnCommand(-0.65,true,30,3));
//		commands.add(new DriveCommand(36,0.40,true,6));
//		commands.add(new RealGyroTurnCommand(-0.65,true,85,3));

//		
		//left version of this is the same just with the turns inverted
	}
	public void ScoreScaleGreedy(){
		SmartDashboard.putBoolean("scale", true);
		//GYRO(double _power, boolean _holdLift, double _distance, double _period)
		/*SmartDashboard.putNumber("greedy scale forward", 190);
		SmartDashboard.putNumber("greedy scale cross", 120);
		SmartDashboard.putNumber("greedy scale turn 1", 90);
		SmartDashboard.putNumber("greedy scale turn 2", 90);
		SmartDashboard.putNumber("greedy scale forward 2", 30)
		commands.add(new GyroDriveCommand(0.6,false,SmartDashboard.getNumber("greedy scale forward", 196),10));
		//commands.add(new TurnCommand(300,0.5,false,5));
		commands.add(new RealGyroTurnCommand(0.5,true,SmartDashboard.getNumber("greedy scale turn 1", 80),3));
		commands.add(new GyroDriveCommand(0.4,false,SmartDashboard.getNumber("greedy scale cross", 168),10));

		commands.add(new RealGyroTurnCommand(-0.65,true,SmartDashboard.getNumber("greedy scale turn 1", 90),3));
		commands.add(new LiftCommand(135,0.8,3));
		commands.add(new LiftCommand(11,0.6,0.75));
		commands.add(new GyroDriveCommand(0.6,false,SmartDashboard.getNumber("greedy scale forward 2", 30),10));
		commands.add(new ClawCommand(false));
		commands.add(new WaitCommand(1));
		commands.add(new DriveCommand(30,-0.40,true,6));*/
		commands.add(new GyroDriveCommand(0.6,false,196,10));
		//commands.add(new TurnCommand(300,0.5,false,5));
		commands.add(new GyroTurnCommand(-0.5,true,80,3));
		commands.add(new GyroDriveCommand(0.6,false,50,10));
		commands.add(new GyroDriveCommand(0.3,false,50,10));
		commands.add(new GyroTurnCommand(-0.75,true,3,3));
		commands.add(new GyroDriveCommand(0.6,false,50,10));

		commands.add(new GyroTurnCommand(0.75,true,72,3));
		commands.add(new LiftCommand(135,0.8,3));
		commands.add(new LiftCommand(13,0.9,0.5));
		commands.add(new GyroDriveCommand(0.6,false,20,10));
		commands.add(new ClawCommand(false));
		commands.add(new DriveCommand(48,-0.40,true,6));
//		commands.add(new WaitCommand(1));
//		commands.add(new DriveCommand(30,-0.40,true,6));
}
}