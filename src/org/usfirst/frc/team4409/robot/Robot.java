/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4409.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.ADXL345_SPI;
//import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4409.robot.autonomous.Baseline;
import org.usfirst.frc.team4409.robot.autonomous.LeftAuto;
import org.usfirst.frc.team4409.robot.autonomous.RightAuto;
import org.usfirst.frc.team4409.robot.autonomous.SwitchFromCenter;
//import org.usfirst.frc.team4409.robot.commands.ElevatorDrive;
import org.usfirst.frc.team4409.robot.subsystems.Claw;
import org.usfirst.frc.team4409.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4409.robot.subsystems.Elevator;
import org.usfirst.frc.team4409.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	
	public static final ExampleSubsystem kExampleSubsystem = new ExampleSubsystem();
	public static final DriveTrain DriveTrain = new DriveTrain();
	public static final Claw claw = new Claw();
	public static OI m_oi;
	public static final Elevator ELEVATOR_DRIVE = new Elevator();
	//public static final ADIS16448_IMU imu = new ADIS16448_IMU();
	Baseline base;
	SwitchFromCenter scoreSwitch;
	RightAuto rightAuto;
	LeftAuto leftAuto;
	Command m_autonomousCommand;
	SendableChooser<Integer> m_chooser = new SendableChooser<Integer>();
	public static SendableChooser<Integer> scalePref = new SendableChooser<Integer>();
	double angle;
	int mode;
	boolean clawState;
	String gameData;
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		m_chooser.addDefault("Baseline", 0);
		//m_chooser.addObject("No auto", 1);
		m_chooser.addDefault("Center", 2);
		m_chooser.addObject("Right", 3);
		m_chooser.addObject("Left", 4);
		
		scalePref.addDefault("Scale Priority w/ Switch", 4);
		scalePref.addObject("Greedy Scale", 5);
		//scalePref.addObject("Switch Only", 2);
		//scalePref.addObject("Scale Only", 3);
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		SmartDashboard.putData("Prioritize Scale or Switch?", scalePref);
		SmartDashboard.putBoolean("Smartdashboard/example_variable", true);
		SmartDashboard.putNumber("Smartdashboard/Lift_Encoder", 0.0);
		SmartDashboard.putNumber("Auto Wait", 0);
		SmartDashboard.putNumber("greedy scale forward", 196);
		SmartDashboard.putNumber("greedy scale cross", 160);
		SmartDashboard.putNumber("greedy scale turn 1", 90);
		SmartDashboard.putNumber("greedy scale turn 2", 90);
		SmartDashboard.putNumber("greedy scale forward 2", 30);
		RobotMap.liftEnc.reset();//Set encoder to 0
		
		CameraServer.getInstance().startAutomaticCapture();//hope camera works
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//m_autonomousCommand = m_chooser.getSelected();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		
		//match 48
		//if (DriverStation.)
		RobotMap.lock.set(Value.kReverse);
		
		RobotMap.driveLeftEnc.reset();
		RobotMap.driveRightEnc.reset();
		
		//check if gamedata exists
		try{
			gameData = DriverStation.getInstance().getGameSpecificMessage();
			
			if (gameData.length() < 1){
				DriverStation.reportWarning("No field data!(null)", false);
				base = new Baseline();
				mode = 0;
			}
			else{//game data is good
				//G
				base = new Baseline();
				scoreSwitch = new SwitchFromCenter();
				rightAuto = new RightAuto();
				leftAuto = new LeftAuto();
				
				mode = (int) m_chooser.getSelected();
			}
		}catch (Exception e){
			DriverStation.reportWarning(e.getMessage(), false);
		}
		
		
		
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		//base.Run();
		DriverStation.reportWarning("Selected auto: " + Integer.toString(mode), false);
		switch(mode){
			case 0:
				SmartDashboard.putString("auto selector", "baseline");
				DriverStation.reportWarning("Selected baseline", false);
				base.Run();
				break;
			case 1:
				SmartDashboard.putString("auto selector", "none");
				DriverStation.reportWarning("Selected no auto", false);
				//don't do anything
				break;
			case 2://center
				SmartDashboard.putString("auto selector", "switch from center");
				DriverStation.reportWarning("Selected switch-from-center", false);
				scoreSwitch.Run();
				break;
			case 3://right
				SmartDashboard.putString("auto selector", "right");
				DriverStation.reportWarning("Selected right auto", false);
				rightAuto.Run();
				break;
			case 4://left
				SmartDashboard.putString("auto selector", "left");
				DriverStation.reportWarning("Selected left auto", false);
				leftAuto.Run();
				break;
			default:
				DriverStation.reportWarning("No auto selection!", false);
				break;
				
		}
		UpdateDash();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		/*
		 * Lift smoothly 
		 * 
		 * x:encoder value as presented
		 * t:max encoder value
		 * b:min encoder value
		 * 
		 * SOLUTION 1:
		 * 
		 * f(x) = -(x-b)(x-t)
		 * 
		 * max: f((b+t)/2)
		 * 
		 * return(multiply f(x) by 1/max)
		 * 
		 * SOLUTION 2:
		 * 
		 * return sin((-pi/(b-t))x-b)
		 * 
		 * SOLUTION 3: 
		 * 
		 * WRITE PIECEWISE FUNCTION HERE
		 * (pic in pictures directory of the LABVIEW pc)
		 * 
		 */
		Scheduler.getInstance().run();//Something with command based
		SmartDashboard.putNumber("/Smartdashboard/drive/navx/yaw",6);//Test code for michael's dashboard
		
		UpdateDash();//put sensor data to the dashboard for debugging
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		/*
		 * This function would be good for testing experimental code. However,
		 * we just put it in the real code and hope the drivers don't hate us after
		 * something goes horribly wrong and breaks the robot after we put code on the robot
		 * without telling them.
		 */
	}
	
	public void UpdateDash(){
		//put sensor values to the dashboard for debuging
		SmartDashboard.putNumber("Right drive encoder",RobotMap.driveRightEnc.getDistance());
		SmartDashboard.putNumber("Left drive encoder", RobotMap.driveLeftEnc.getDistance());
		SmartDashboard.putNumber("Lift encoder", RobotMap.liftEnc.getDistance());
		SmartDashboard.putBoolean("Lift magnet sensor top", RobotMap.topSwitch.get());
		SmartDashboard.putBoolean("Lift magnet sensor bottom", RobotMap.bottomSwitch.get());
		SmartDashboard.getNumber("Auto Wait", 0);
		SmartDashboard.putNumber("Gyro: ", RobotMap.gyro.getAngle());
	}
}
