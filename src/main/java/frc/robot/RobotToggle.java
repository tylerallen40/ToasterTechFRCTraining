/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class RobotToggle extends TimedRobot {
  /**
   * motorLeft should be motor port 0
   * motorRight should be motor port 1
   * joystick should be joystick port 0
   */
  private PWMTalonSRX motorLeft;
  private PWMTalonSRX motorRight;
  private Joystick joystick;

  /**
   * In this example we will make the motors be driven by joysticks. Pushing button 0
   * will reverse the joystick direction.
   * 
   * Joystick axis 1 will control the left side of the robot.
   * Joystick axis 3 will control the left side of the robot.
   * 
   * Use joystick.getRawButton() to get joystick button values.
   * Use joystick.getRawAxis() to get the joystick values
   */
  @Override
  public void robotInit() {
    motorLeft = new PWMTalonSRX(0);
    motorRight = new PWMTalonSRX(1);
    joystick = new Joystick(0);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
