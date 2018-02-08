package org.usfirst.frc.team4409.robot.autonomous.commands;

import org.usfirst.frc.team4409.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

public class DriveCommand extends AutonomousCommand{
	private static Spark frontLeft = RobotMap.frontLeft;
	private static Spark frontRight = RobotMap.frontRight;
	private static Spark backLeft = RobotMap.backLeft;
	private static Spark backRight = RobotMap.backRight;
	private static Encoder driveLeftEnc = RobotMap.driveLeftEnc;
	private static Encoder driveRightEnc = RobotMap.driveRightEnc;
	
	private double leftEncGoal;
	private double rightEncGoal;
	
	public DriveCommand(double _left, double _right){
		leftEncGoal = _left;
		rightEncGoal = _right;
	}
	@Override
	public boolean Run(){
		double left = 0;
		double right = 0;
		if(Math.abs(driveLeftEnc.getDistance()*RobotMap.EncScale)<leftEncGoal){
			left = 0.5;
		}
		if(Math.abs(driveRightEnc.getDistance()*RobotMap.EncScale)<rightEncGoal){
			right = 0.5;
		}
		if(left == 0 && right == 0){
			return true;
		}else{
			frontLeft.set(left);
			backLeft.set(left);
			frontRight.set(right);
			backRight.set(right);
			return false;
		}
	}
}
