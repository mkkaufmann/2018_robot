package org.usfirst.frc.team4409.robot.autonomous.commands;

import java.util.LinkedList;

public class ParallelCommand extends AutonomousCommand{
	LinkedList<AutonomousCommand> commands;
	boolean allFinished = false;
	public ParallelCommand(AutonomousCommand... commands) {
		for(AutonomousCommand command : commands){
			this.commands.add(command);
		}
	}
	@Override
	public boolean Run(){
		allFinished = true;
		for(AutonomousCommand command : commands){
			if(command.Run()){
				commands.remove(command);
			}else{
				allFinished = false;
			}
		}
		return allFinished;
	}
}
