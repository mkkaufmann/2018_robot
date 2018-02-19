package org.usfirst.frc.team4409.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4409.robot.RobotMap;
import org.usfirst.frc.team4409.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class DriveTrain extends Subsystem {

   //private RobotDrive MainDrive = new RobotDrive(RobotMap.frontLeft,RobotMap.backLeft,RobotMap.frontRight,RobotMap.backRight);
   private SpeedControllerGroup rightControllerGroup = new SpeedControllerGroup(RobotMap.frontRight,RobotMap.backRight);
   private SpeedControllerGroup leftControllerGroup = new SpeedControllerGroup(RobotMap.frontLeft,RobotMap.backLeft);
   private DifferentialDrive MainDrive = new DifferentialDrive(leftControllerGroup, rightControllerGroup);
   

   @Override
   protected void initDefaultCommand() {
	   setDefaultCommand(new ArcadeDrive());
	   // TODO Auto-generated method stub
	
   }
   
   public void TeleopDrive(Joystick Driver) {
	   MainDrive.arcadeDrive(-Driver.getY(),Driver.getX());
   }
   
   public void stop() {
	   MainDrive.arcadeDrive(0, 0);
   }
   
   public void setSpeed(double speed, double turning) {
	   MainDrive.arcadeDrive(speed, turning);
   }


}

