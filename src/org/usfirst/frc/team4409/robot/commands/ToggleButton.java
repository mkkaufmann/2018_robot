package org.usfirst.frc.team4409.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.buttons.Button;

public class ToggleButton extends Button{
	private final GenericHID m_joystick;
	  private final int m_buttonNumber;
	  private boolean m_state;

	  /**
	   * Create a joystick button for triggering commands.
	   *
	   * @param joystick     The GenericHID object that has the button (e.g. Joystick, KinectStick,
	   *                     etc)
	   * @param buttonNumber The button number (see {@link GenericHID#getRawButton(int) }
	   */
	  public ToggleButton(GenericHID joystick, int buttonNumber) {
	    m_joystick = joystick;
	    m_buttonNumber = buttonNumber;
	    m_state = false;
	  }

	  /**
	   * Gets the value of the joystick button.
	   *
	   * @return The value of the joystick button
	   */
	  public boolean get() {
	    return m_joystick.getRawButton(m_buttonNumber);
	  }
	  
	  /**
	   * Starts the given command whenever the button is newly pressed.
	   *
	   * @param command the command to start
	   */
	  public void whenPressed(final Command command1, final Command command2) {
		  System.out.println("Toggle state:" + m_state);
		if (m_state == true){
			whenActive(command1);
		}
		else {
			whenActive(command2);
		}
	   m_state = !m_state;
	  }
}
