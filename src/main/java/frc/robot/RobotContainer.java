/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain drivetrain = Drivetrain.getInstance();
  private final Intake intake = Intake.getInstance();
  private final Arm arm = new Arm();
  private final Climber climber = new Climber();

  private final GenericHID joystick = new Joystick(0);



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    new PowerDistributionPanel(3).clearStickyFaults();
    // Configure the button bindings
    configureButtonBindings();

    
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@l[]\
   * ][]\ink XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
      Joystick joystick = (Joystick) this.joystick;
      new JoystickButton(joystick, 2).whenPressed(() -> intake.setSpeed(-0.5)).whenReleased(() -> intake.setSpeed(0)); //Intake
      new JoystickButton(joystick, 1).whenPressed(() -> intake.setSpeed(0.5)).whenReleased(() -> intake.setSpeed(0)); //Outtake
      new JoystickButton(joystick, 8).whenPressed(() -> arm.set(-0.3)).whenReleased(() -> arm.set(0)); //Arm Up
      new JoystickButton(joystick, 7).whenPressed(() -> arm.set(0.3)).whenReleased(() -> arm.set(0.2)); //Arm Down
      new JoystickButton(joystick, 12).whenPressed(() -> climber.set(0.5)).whenReleased(() -> climber.set(0)); //Climber Down
      new JoystickButton(joystick, 11).whenPressed(() -> climber.set(-0.5)).whenReleased(() -> climber.set(0)); //Climber Up
      drivetrain.setDefaultCommand(new RunCommand(
      () -> drivetrain.percentArcadeDrive(-joystick.getY(), -joystick.getZ()), drivetrain));
    
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.[\]
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand(() -> arm.set(0.2)).andThen(new RunCommand(() -> drivetrain.setSpeeds(0.3, 0.3), drivetrain).withTimeout(3)
        .andThen(() -> drivetrain.setSpeeds(0, 0)).andThen(new RunCommand(() -> intake.setSpeed(0.5))).withTimeout(2).andThen(
        new InstantCommand(() -> intake.setSpeed(0))));
  }
}

