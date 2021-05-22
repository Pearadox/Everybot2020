/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DrivetrainConstants {
        public static final int LEFT_MASTER_ID = 10;
        public static final int LEFT_SLAVE_ID = 17;
        public static final int RIGHT_MASTER_ID = 11;
        public static final int RIGHT_SLAVE_ID = 13;

        public static final double MAX_VELOCITY = 3.0; // Meters per second
        public static final double MAX_ANGULAR_VELOCITY = 2.0;
        public static final double TRACK_WIDTH = 1.5;

        public static final DifferentialDriveKinematics KINEMATICS = new DifferentialDriveKinematics(TRACK_WIDTH);
        public static final SimpleMotorFeedforward OPENL_FEEDFORWARD = new SimpleMotorFeedforward(0, 1 / MAX_VELOCITY);
    }

    public static final class IntakeConstants {
        public static final int INTAKE_MOTOR_ID = 4;

        public static final int x = 0;
    }
}
