package org.usfirst.frc.team4409.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claw extends Subsystem {
	
	DoubleSolenoid claw = new DoubleSolenoid(0, 1);
	boolean open = false;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	close();
    }
    
    public void open() {
    	claw.set(Value.kForward);
    }
    
    public void close() {
    	claw.set(Value.kReverse);
    }
    
    public boolean getOpen() {
    	return open;
    }
    
    public void setOpen(boolean open) {
		this.open = open;
	}
}

