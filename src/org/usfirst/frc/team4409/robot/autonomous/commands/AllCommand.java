package org.usfirst.frc.team4409.robot.autonomous.commands;

import org.usfirst.frc.team4409.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class AllCommand extends AutonomousCommand{
	private static Spark frontLeft = RobotMap.frontLeft;
	private static Spark frontRight = RobotMap.frontRight;
	private static Spark backLeft = RobotMap.backLeft;
	private static Spark backRight = RobotMap.backRight;
	private static Encoder driveLeftEnc = RobotMap.driveLeftEnc;
	private static Encoder driveRightEnc = RobotMap.driveRightEnc;
	private static Talon elevatorLeft = RobotMap.elevatorLeft;
	private static Talon elevatorRight = RobotMap.elevatorRight;
	private static double degreesToIn = 19.098593171;
	private static Encoder liftEnc = RobotMap.liftEnc;
	
	private double leftEncGoal;
	private double rightEncGoal;
	private double drivePower;
	private boolean holdLift;
	private double period;
	private Timer timer;
	private boolean running = false;
	private boolean finishedDriving = false;
	private boolean finishedLifting = false;
	private double liftAmount;
	private double liftPower;
	
	public AllCommand(double _left, double _right, double _power, boolean _holdLift,double _liftAmount,double _liftPower, double _period){
		leftEncGoal = _left;
		rightEncGoal = _right;
		drivePower = _power;
		holdLift = _holdLift;
		period = _period;
		liftAmount = _liftAmount;
		liftPower = _liftPower;
		timer = new Timer();
	}
	public AllCommand(double _distance, double _power, boolean _holdLift,double _liftAmount,double _liftPower, double _period){
		leftEncGoal = _distance;
		rightEncGoal = _distance;
		drivePower = _power;
		holdLift = _holdLift;
		period = _period;
		liftAmount = _liftAmount;
		liftPower = _liftPower;
		timer = new Timer();
	}
	@Override
	public boolean Run(){
		double left = 0;
		double right = 0;
		double liftSpeed = 0;
		//timer
		if(!running){
			timer.start();
			running = true;
		}
		if(timer.hasPeriodPassed(period)){
			DriverStation.reportWarning("timeout expired!(drive)", false);
			timer.stop();
			return true;
		}
		
		//hold the lift up (ONLY IF DONE LIFTING)
		if (holdLift && finishedLifting){
			elevatorLeft.set(-0.08);
			elevatorRight.set(0.08);

		}
		
		//drive
		if(Math.abs(driveLeftEnc.getDistance()*RobotMap.EncScale)<leftEncGoal*degreesToIn && Math.abs(driveRightEnc.getDistance()*RobotMap.EncScale)<rightEncGoal*degreesToIn){
			left = drivePower;
			right = -drivePower;
		}
		
		
		if(left == 0 && right == 0){
			driveLeftEnc.reset();
			driveRightEnc.reset();
			System.out.println("stop drive");
			finishedDriving = true;
		}else{
			frontLeft.set(left);
			backLeft.set(left);
			frontRight.set(right);
			backRight.set(right);
			System.out.println("drive");
		}
		
		//
		if(Math.abs(liftEnc.getDistance()*RobotMap.EncScale)<liftAmount*degreesToIn){
			liftSpeed = liftPower;
		}
		
		if(liftSpeed == 0){
			System.out.println("stop lift");
			finishedLifting = true;
		}else{
			elevatorLeft.set(-liftSpeed);
			elevatorRight.set(liftSpeed);
			System.out.println("lift");
		}
		return (finishedDriving && finishedLifting);
	}
}

