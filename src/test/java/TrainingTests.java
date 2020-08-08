import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import frc.robot.RobotToggle;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.PowerMockUtils;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@PrepareForTest({
  PWMTalonSRX.class, 
  RobotToggle.class, 
  Joystick.class
})
@RunWith(PowerMockRunner.class)
public class TrainingTests {
  @Mock
  private PWMTalonSRX motorLeft;

  @Mock
  private PWMTalonSRX motorRight;

  @Mock
  private Joystick joystick;


  @Before
  public void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
    PowerMockito.whenNew(PWMTalonSRX.class)
                .withArguments(0)
                .thenReturn(motorLeft);
    PowerMockito.whenNew(PWMTalonSRX.class)
                .withArguments(1)
                .thenReturn(motorRight);
    PowerMockito.whenNew(Joystick.class)
                .withArguments(0)
                .thenReturn(joystick);
    when(joystick.getRawButton(0)).thenReturn(false);
    when(joystick.getRawAxis(1)).thenReturn(0.0);
    when(joystick.getRawAxis(3)).thenReturn(0.0);
  }

  @Test
  public void testJoystickValues() throws Exception {
    RobotToggle robot = new RobotToggle();
    robot.robotInit();
    for(int i=-100; i<=100; i++) {
      when(joystick.getRawAxis(1)).thenReturn(i*.01);
      when(joystick.getRawAxis(3)).thenReturn(i*.01);  
      robot.teleopPeriodic();
      Mockito.verify(motorLeft, times(1)).set(i*.01);
      Mockito.verify(motorRight, times(1)).set(i*.01);  
    }
    robot.close();
  }

  public void testToggle() throws Exception {
    RobotToggle robot = new RobotToggle();
    robot.robotInit();
    when(joystick.getRawAxis(1)).thenReturn(1.0);
    when(joystick.getRawAxis(3)).thenReturn(1.0);
    
    // Run normally
    robot.teleopPeriodic();
    Mockito.verify(motorLeft, times(1)).set(1.0);
    Mockito.verify(motorRight, times(1)).set(1.0);  

    // Press button
    when(joystick.getRawButton(0)).thenReturn(true);
    robot.teleopPeriodic();
    Mockito.verify(motorLeft, times(1)).set(-1.0);
    Mockito.verify(motorRight, times(1)).set(-1.0);  

    // Hold Button
    robot.teleopPeriodic();
    Mockito.verify(motorLeft, times(2)).set(-1.0);
    Mockito.verify(motorRight, times(2)).set(-1.0);  

    // Release Button
    when(joystick.getRawButton(0)).thenReturn(false);
    robot.teleopPeriodic();
    Mockito.verify(motorLeft, times(3)).set(-1.0);
    Mockito.verify(motorRight, times(3)).set(-1.0);

    // Keep Button Released 
    robot.teleopPeriodic();
    Mockito.verify(motorLeft, times(4)).set(-1.0);
    Mockito.verify(motorRight, times(4)).set(-1.0);

    // Press button again 
    when(joystick.getRawButton(0)).thenReturn(true);
    robot.teleopPeriodic();
    Mockito.verify(motorLeft, times(2)).set(1.0);
    Mockito.verify(motorRight, times(2)).set(1.0);  
    robot.close();
  }



  @Test
  public void testJoystickExample() throws Exception {
    // RobotToggle robot = new RobotToggle();
    // robot.robotInit();
    // robot.teleopPeriodic();
    // Mockito.verify(motorLeft, times(1)).set(0.0);
    // Mockito.verify(motorRight, times(1)).set(0.0);
    // when(joystick.getRawButton(0)).thenReturn(true);
    // robot.teleopPeriodic();
    // Mockito.verify(motorLeft, times(1)).set(1.0);
    // Mockito.verify(motorRight, times(1)).set(1.0);
    // // Mockito.verify(motorRight, never()).set(Mockito.anyDouble());
    // robot.close();
  }
}