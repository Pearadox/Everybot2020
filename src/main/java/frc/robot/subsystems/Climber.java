/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  private VictorSPX climberMotor = new VictorSPX(31);
  private DigitalInput limitSwitch = new DigitalInput(7);

  /**
   * Creates a new Climber.
   */
  public Climber() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("limit Switch", limitSwitch.get());
    if (!limitSwitch.get()) {
      climberMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  public void set(double speed) {
    if (limitSwitch.get())
      climberMotor.set(ControlMode.PercentOutput, speed);
    else 
      climberMotor.set(ControlMode.PercentOutput, 0);
  }
}
