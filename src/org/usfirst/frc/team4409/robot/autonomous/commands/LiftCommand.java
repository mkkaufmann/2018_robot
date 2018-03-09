package org.usfirst.frc.team4409.robot.autonomous.commands;

import org.usfirst.frc.team4409.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class LiftCommand extends AutonomousCommand{
	
	private static Encoder liftEnc = RobotMap.liftEnc;
	private static Talon elevatorLeft = RobotMap.elevatorLeft;
	private static Talon elevatorRight = RobotMap.elevatorRight;
	private double encGoal;

	private double power;
	private Timer timer;
	private boolean running = false;
	private double period;
	
	public LiftCommand(double _amount,double _power,double _period) {
		encGoal = _amount;
		power = _power;
		period = _period;
		timer = new Timer();
	}
	
	
	@Override
	public boolean Run(){
		double speed = 0;
		if(!running){
			timer.start();
			running = true;
		}
		if(timer.hasPeriodPassed(period)){
			timer.stop();
			DriverStation.reportWarning("timeout expired!(lift)", false);
			return true;
		}
		if(Math.abs(liftEnc.getDistance()*RobotMap.EncScale)<encGoal*19.098593171/*change this to proper number*/){
			speed = power;
		}
		
		if(speed == 0){
			System.out.println("stop lift");
			return true;
		}else{
			elevatorLeft.set(-speed);
			elevatorRight.set(speed);
			System.out.println("lift");
			return false;
		}
	}
} 

