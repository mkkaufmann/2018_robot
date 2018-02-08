package org.usfirst.frc.team4409.robot.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team4409.robot.autonomous.commands.AutonomousCommand;

public class Autonomous {
	public ArrayList<AutonomousCommand> commands;
	private int i = 0;
	public Autonomous() {
		commands = new ArrayList<AutonomousCommand>();
	}
	
	public void Run(){
		if(commands.size()-i > 0){
			if(commands.get(i).Run()){
				i++;
			}
		}
	}
}
