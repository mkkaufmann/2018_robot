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
	
	public LiftCommand(double _amount) {
		encGoal = _amount;
	}
	
	
	@Override
	public boolean Run(){
		double speed = 0;
		System.out.println(Math.abs(liftEnc.getDistance()*RobotMap.EncScale));
		if(encGoal > 0) {
			if(Math.abs(liftEnc.getDistance()*RobotMap.EncScale)<encGoal){
				speed = 0.3;
			}
		}else {
			if(Math.abs(liftEnc.getDistance()*RobotMap.EncScale)>encGoal){
				speed = -0.3;
			}
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
