import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import frc.robot.JoystickButtonExample;
import frc.robot.SimpleMotorExample;

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
  JoystickButtonExample.class, 
  SimpleMotorExample.class, 
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
  }

  @Test
  public void testJoystickExample() throws Exception {
    System.out.println("test");
    JoystickButtonExample robot = new JoystickButtonExample();
    robot.robotInit();
    robot.teleopPeriodic();
    Mockito.verify(motorLeft, times(1)).set(0.0);
    Mockito.verify(motorRight, times(1)).set(0.0);
    when(joystick.getRawButton(0)).thenReturn(true);
    robot.teleopPeriodic();
    Mockito.verify(motorLeft, times(1)).set(1.0);
    Mockito.verify(motorRight, times(1)).set(1.0);
    // Mockito.verify(motorRight, never()).set(Mockito.anyDouble());
    robot.close();
  }


  @Test
  public void testSimpleMotorExample() throws Exception {
    SimpleMotorExample robot = new SimpleMotorExample();
    robot.robotInit();
    robot.autonomousPeriodic();
    Mockito.verify(motorLeft, times(1)).set(1.0);
    Mockito.verify(motorRight, times(1)).set(1.0);
    // Mockito.verify(motorRight, never()).set(Mockito.anyDouble());
    robot.close();
  }

}