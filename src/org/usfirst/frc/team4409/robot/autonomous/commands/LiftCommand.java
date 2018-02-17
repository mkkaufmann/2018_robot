package org.usfirst.frc.team4409.robot.autonomous.commands;

import org.usfirst.frc.team4409.robot.Robot;
import org.usfirst.frc.team4409.robot.RobotMap;
import org.usfirst.frc.team4409.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class LiftCommand extends AutonomousCommand{
	
	private static Encoder liftEnc = RobotMap.liftEnc;
	private static Talon elevatorLeft = RobotMap.elevatorLeft;
	private static Talon elevatorRight = RobotMap.elevatorRight;
	private double encGoal;

	private double power;
	
	public LiftCommand(double _amount,double _power) {
		encGoal = _amount;
		power = _power;
	}
	
	
	@Override
	public boolean Run(){
		double speed = 0;
		if(Math.abs(liftEnc.getDistance()*RobotMap.EncScale)<encGoal*19.098593171/*change this to proper number*/){
			speed = power;
		}
		
		if(speed == 0){
			return true;
		}else{
			elevatorLeft.set(-speed);
			elevatorRight.set(speed);
			return false;
		}
	}
} 

