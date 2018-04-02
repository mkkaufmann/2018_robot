package org.usfirst.frc.team4409.robot.autonomous.commands;

import org.usfirst.frc.team4409.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class GyroTurnCommand extends AutonomousCommand{
	private static Spark frontLeft = RobotMap.frontLeft;
	private static Spark frontRight = RobotMap.frontRight;
	private static Spark backLeft = RobotMap.backLeft;
	private static Spark backRight = RobotMap.backRight;
	private static Encoder driveLeftEnc = RobotMap.driveLeftEnc;
	private static Encoder driveRightEnc = RobotMap.driveRightEnc;
	private static Talon elevatorLeft = RobotMap.elevatorLeft;
	private static Talon elevatorRight = RobotMap.elevatorRight;
	private static double degreesToIn = 19.098593171;
	
	private double drivePower;
	private boolean holdLift;
	private double degrees;
	
	private Timer timer;
	private boolean running = false;
	private double period;
	//0.25,true,10,3
	public GyroTurnCommand(double _power, boolean _holdLift,double degrees,double _period){
		this.degrees = degrees;//degrees turned
		drivePower = _power;
		holdLift = _holdLift;
		period = _period;
		timer = new Timer();
	}
	@Override
	public boolean Run(){
		double left = 0;
		double right = 0;
		
		
		if(!running){
			RobotMap.gyro.reset();
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
		if(Math.abs(RobotMap.gyro.getAngle()) < degrees){
			left = drivePower;
			right = drivePower;
		}
		if (right == 0 && left == 0){//target reached
			driveLeftEnc.reset();
			driveRightEnc.reset();
			System.out.println("stop gyro drive");
			return true;
		}
		else{//continue driving
			frontLeft.set(left);
			backLeft.set(left);
			frontRight.set(right);
			backRight.set(right);
			System.out.println("gyro drive");
			return false;
		}
	}
}
