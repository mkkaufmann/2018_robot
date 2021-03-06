/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4409.robot;



import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//drive motors
	public static Spark frontLeft = new Spark(4);
	public static Spark backLeft = new Spark(5);
	//public static SpeedControllerGroup Drive_Left = new SpeedControllerGroup(frontLeft,backLeft);
	public static Spark frontRight = new Spark(1);
	public static Spark backRight = new Spark(2);
	//public static SpeedControllerGroup Drive_Right = new SpeedControllerGroup(frontLeft,backLeft);
	
	//drive encoders
	public static Encoder driveRightEnc = new Encoder(6,7);//Right drivetrain encoder
	public static Encoder driveLeftEnc = new Encoder(8,9);//Left drivetrain encoder
	
	public static DoubleSolenoid claw = new DoubleSolenoid(0,1);//Cube grabber solenoid
	public static DoubleSolenoid lock = new DoubleSolenoid(4,5);//Climbing lock solenoid
	public static Talon elevatorLeft = new Talon(3);//Talon SR controlling left elevator motor
	public static Talon elevatorRight = new Talon(0);//Talon SR controlling right elevator motor
	public static Talon intake = new Talon(6);//intake motor 
	
	public static DigitalInput bottomSwitch = new DigitalInput(2);//
	public static DigitalInput topSwitch = new DigitalInput(3);
	
	public static Encoder liftEnc = new Encoder(4,5);
	
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();//gyro for auto turns
	
	public static final double LiftTop = -35000000;//lift encoder limits. Not yet determined. DO NOT USE!
	public static final double LiftBottom = 35000000;
	public static final double EncScale = 0.17578125;
	
	public static int theScale = 0;
	public static final boolean ClawOpen = false;
	public static final boolean ClawClose = true;
	
	//auto constants
	//Y is the long dimension of the field
	public static final double driveToSwitchY = 100;//y-axis distance to drive to switch
	public static final double driveToScaleY = 310*100;//(guess)y-axis distance to drive to scale
	public static final double driveToScaleX = 15;//(guess)x-axis distance to drive to scale
	public static final double driveAtStart = 15;//distance to drive at the start of center-switch, as well as left/right scale autons
	public static final double driveTowardsWall = 35;//(guess) distance to drive to wall after first turn in left/right autons
	public static final double baseline = 105;//distance to drive to cross baseline
	public static final double driveEvenSwitch = 160;
	public static final boolean sendHelp = false;//change to true when we play Stryke Force
}
