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
import edu.wpi.first.wpilibj.Jaguar;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int
		Drive_Left = 1,
		Drive_Right = 2;
	
	public static DoubleSolenoid claw = new DoubleSolenoid(0,1);
	
	public static Jaguar elevatorLeft = new Jaguar(3);
	public static Jaguar elevatorRight = new Jaguar(4);
	
	public static DigitalInput topLeft = new DigitalInput(0);
	public static DigitalInput bottomLeft = new DigitalInput(1);
	public static DigitalInput bottomRight = new DigitalInput(2);
	public static DigitalInput topRight = new DigitalInput(3);
	
	public static Encoder liftEnc = new Encoder(4,5);
	
	public static double LiftTop = -35000000;//encoder variables
	public static double LiftBottom = 3500000;
	public static double EncScale = 0.17578125;

}
