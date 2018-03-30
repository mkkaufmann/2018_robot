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
			if(canSwitch){
				SmartDashboard.putString("auto", "scale (b) SWITCH");
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
		DriverStation.reportWarning("switch", false);
		commands.add(new DriveCommand(RobotMap.driveToSwitchY,RobotMap.driveToSwitchY,0.35,true,6));
		commands.add(new WaitCommand(0.3));
		commands.add(new ClawCommand(false));
	}
	public void ScoreSwitchB(){
		DriverStation.reportWarning("switch b", false);
		//public AllCommand(double _distance, double _power, boolean _holdLift,double _liftAmount,double _liftPower, double _period){
		commands.add(new TurnCommand(20,0.35,true,5));
		commands.add(new AllCommand(RobotMap.driveEvenSwitch-20,0.40,true,70,0.65,6));
		//commands.add(new DriveCommand(RobotMap.driveEvenSwitch-20,RobotMap.driveEvenSwitch-20,0.40,true,6));
		commands.add(new WaitCommand(0.3));
		commands.add(new TurnCommand(nintyTurn+120,-0.35,true,5));
		commands.add(new WaitCommand(0.3));
		commands.add(new DriveCommand(30,30,0.40,true,2));
		commands.add(new ClawCommand(false));
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
		//commands.add(new GyroDriveCommand(0.5,false,210,10));
		//commands.add(new TurnCommand(500,-0.35,false,3));
		commands.add(new LiftCommand(135,0.8,3));
		commands.add(new LiftCommand(10,0.6,3));
//		//commands.add(new DriveCommand(36,0.40,true,6));
//		//commands.add(new ClawCommand(false));
//		commands.add(new WaitCommand(1));
		//commands.add(new DriveCommand(24,-0.40,true,6));

//		
		//left version of this is the same just with the turns inverted
	}
}