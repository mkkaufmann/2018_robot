package org.usfirst.frc.team4409.robot.autonomous.commands;

import java.util.ArrayList;

public class SeriesCommand extends AutonomousCommand {
	ArrayList<AutonomousCommand> commands;
	int i = 0;
	public SeriesCommand(AutonomousCommand... commands) {
		this.commands = new ArrayList<AutonomousCommand>();
		for(AutonomousCommand command : commands){
			this.commands.add(command);
		}
	}
	
	@Override
	public boolean Run(){
		if(commands.get(i).Run())
			i++;
		return i == commands.size();
	}
}
