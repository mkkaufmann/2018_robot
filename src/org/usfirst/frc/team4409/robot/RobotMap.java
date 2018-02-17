/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4409.robot;

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
	public static Encoder driveRightEnc = new Encoder(6,7);
	public static Encoder driveLeftEnc = new Encoder(8,9);
	
	public static DoubleSolenoid claw = new DoubleSolenoid(0,1);
	public static Talon elevatorLeft = new Talon(3);
	public static Talon elevatorRight = new Talon(0);
	
	public static DigitalInput bottomSwitch = new DigitalInput(2);
	public static DigitalInput topSwitch = new DigitalInput(3);
	
	public static Encoder liftEnc = new Encoder(4,5);
	
	public static final double LiftTop = -35000000;//encoder variables
	public static final double LiftBottom = 35000000;
	public static final double EncScale = 0.17578125;
	
	public static int theScale = 0;
	public static final boolean ClawOpen = false;
	public static final boolean ClawClose = true;

}
