package org.usfirst.frc.team4409.robot.autonomous.commands;

import edu.wpi.first.wpilibj.Timer;

public class WaitCommand extends AutonomousCommand{

	private Timer timer;
	private boolean running = false;
	private double period;
	public WaitCommand(double _seconds) {
		period = _seconds;
		timer = new Timer();
	}

	@Override
	public boolean Run(){
		if(!running){
			timer.start();
			running = true;
		}
		if(timer.hasPeriodPassed(period)){
			return true;
		}
		return false;
	}
}
