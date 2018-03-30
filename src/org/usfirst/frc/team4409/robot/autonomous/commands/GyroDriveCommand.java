package org.usfirst.frc.team4409.robot.autonomous.commands;

import org.usfirst.frc.team4409.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class GyroDriveCommand extends AutonomousCommand{
	private static Spark frontLeft = RobotMap.frontLeft;
	private static Spark frontRight = RobotMap.frontRight;
	private static Spark backLeft = RobotMap.backLeft;
	private static Spark backRight = RobotMap.backRight;
	private static Encoder driveLeftEnc = RobotMap.driveLeftEnc;
	private static Encoder driveRightEnc = RobotMap.driveRightEnc;
	private static Talon elevatorLeft = RobotMap.elevatorLeft;
	private static Talon elevatorRight = RobotMap.elevatorRight;
	private static double degreesToIn = 19.098593171;
	private static ADXRS450_Gyro gyro = RobotMap.gyro;
	
	private double drivePower;
	private boolean holdLift;
	private double degrees;
	private double distance;
	
	private Timer timer;
	private boolean running = false;
	private double period;

	public GyroDriveCommand(double _power, boolean _holdLift, double _distance, double _period){
		drivePower = _power;
		holdLift = _holdLift;
		period = _period;
		distance = _distance;
		timer = new Timer();
		//gyro reset
	}
	@Override
	public boolean Run(){
		double left = 0;
		double right = 0;
		if(!running){
			gyro.reset();
			timer.start();
			running = true;
		}
		if(timer.hasPeriodPassed(period)){
			timer.stop();
			DriverStation.reportWarning("Timeout expired!(turn)", false);
			return true;
		}
		if (holdLift){
			elevatorLeft.set(-0.08);
			elevatorRight.set(0.08);
		}
		
		if(Math.abs(driveLeftEnc.getDistance()*RobotMap.EncScale)<distance*degreesToIn && Math.abs(driveRightEnc.getDistance()*RobotMap.EncScale)<distance*degreesToIn){
			left = drivePower;
			right = -drivePower;
		}
		
		if(gyro.getAngle()>2){
			left*=0.75;
			right*=1.25;
		}else if(gyro.getAngle()<-2){
			right*=0.8;
			left*=1.2;
		}
		if(left == 0 && right == 0){
			driveLeftEnc.reset();
			driveRightEnc.reset();
			System.out.println("stop drive");
			return true;
		}else{
			frontLeft.set(left);
			backLeft.set(left);
			frontRight.set(right);
			backRight.set(right);
			System.out.println("drive");
			return false;
		}
	}
}
