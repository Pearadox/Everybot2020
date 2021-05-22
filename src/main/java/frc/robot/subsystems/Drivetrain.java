/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  private Drivetrain() {
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);
    rightMaster.setInverted(true);
    rightSlave.setInverted(true);
  }

  private final VictorSPX leftMaster = new VictorSPX(LEFT_MASTER_ID);
  private final VictorSPX leftSlave = new VictorSPX(LEFT_SLAVE_ID);
  private final VictorSPX rightMaster = new VictorSPX(RIGHT_MASTER_ID);
  private final VictorSPX rightSlave = new VictorSPX(RIGHT_SLAVE_ID);

  public void percentArcadeDrive(double throttle, double twist) {
    throttle = Math.copySign(throttle * throttle, throttle);
    twist = Math.copySign(twist * twist, twist);
    setSpeeds(throttle - twist, throttle + twist);
  }

  public void setSpeeds(double left, double right) {
    leftMaster.set(ControlMode.PercentOutput, left);
    rightMaster.set(ControlMode.PercentOutput, right);
  }


  private static final Drivetrain INSTANCE = new Drivetrain();

  public static Drivetrain getInstance() {
    return INSTANCE;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
