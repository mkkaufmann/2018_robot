/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4409.robot;

import org.usfirst.frc.team4409.robot.commands.CloseClaw;
import org.usfirst.frc.team4409.robot.commands.LowerLift;
import org.usfirst.frc.team4409.robot.commands.OpenClaw;
import org.usfirst.frc.team4409.robot.commands.RaiseLift;
import org.usfirst.frc.team4409.robot.commands.StopLift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick Driver = new Joystick(0);
	Button trigger = new JoystickButton(Driver, 1);
	Button liftUp = new JoystickButton(Driver, 3);
	Button liftDown = new JoystickButton(Driver, 2);
	
	public OI() {
		trigger.whenPressed(new CloseClaw());
		trigger.whenReleased(new OpenClaw());
		
		liftUp.whenPressed(new RaiseLift());
		liftUp.whenReleased(new StopLift());
		
		liftDown.whenPressed(new LowerLift());
		liftDown.whenReleased(new StopLift());
		
	}
	
	public Joystick getJoystick() {
		return Driver;
	}
			
}
