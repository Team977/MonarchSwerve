// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class SwerveModule {
  private static final double kWheelRadius = 0.051;
  private static final int kEncoderResolution = 4096;

  private static final double kModuleMaxAngularVelocity = Drivetrain.kMaxAngularSpeed;
  private static final double kModuleMaxAngularAcceleration =
      2 * Math.PI; // radians per second squared

  private final VictorSPX m_driveMotor;
  private final TalonSRX m_turningMotor;

  private final AnalogInput m_turningEncoder;

  // Gains are for example purposes only - must be determined for your own robot!
  private final PIDController m_drivePIDController = new PIDController(1, 0, 0);

  // Gains are for example purposes only - must be determined for your own robot!
  private final ProfiledPIDController m_turningPIDController =
      new ProfiledPIDController(
          1,
          0,
          0,
          new TrapezoidProfile.Constraints(
              kModuleMaxAngularVelocity, kModuleMaxAngularAcceleration));

  // Gains are for example purposes only - must be determined for your own robot!
  private final SimpleMotorFeedforward m_driveFeedforward = new SimpleMotorFeedforward(1, 3);
  private final SimpleMotorFeedforward m_turnFeedforward = new SimpleMotorFeedforward(0, 0);

  /**
   * Constructs a SwerveModule with a drive motor, turning motor, drive encoder and turning encoder.
   *
   * @param driveMotorChannel PWM output for the drive motor.
   * @param turningMotorChannel PWM output for the turning motor.
   * @param turningEncoderChannelA DIO input for the turning encoder channel A
   * @param turningEncoderChannelB DIO input for the turning encoder channel B
   */
  public SwerveModule(
      int driveMotorChannel,
      int turningMotorChannel,
      //int driveEncoderChannelA,
      //int driveEncoderChannelB,
      int turningEncoderChannelA,
      boolean isInverted) {
    m_driveMotor = new VictorSPX(driveMotorChannel);
    m_turningMotor = new TalonSRX(turningMotorChannel);
      m_driveMotor.setInverted(isInverted);
    m_turningEncoder = new AnalogInput(turningEncoderChannelA);

    // Set the distance per pulse for the drive encoder. We can simply use the
    // distance traveled for one rotation of the wheel divided by the encoder
    // resolution.
    //m_driveEncoder.setDistancePerPulse(2 * Math.PI * kWheelRadius / kEncoderResolution);

    // Set the distance (in this case, angle) in radians per pulse for the turning encoder.
    // This is the the angle through an entire rotation (2 * pi) divided by the
    // encoder resolution.
 

    // Limit the PID Controller's input range between -pi and pi and set the input
    // to be continuous.
    m_turningPIDController.enableContinuousInput(-Math.PI, Math.PI);
  }


  /**
   * Sets the desired state for the module.
   *
   * @param desiredState Desired state with speed and angle.
   */
  public void setDesiredState(SwerveModuleState desiredState) {
    // Optimize the reference state to avoid spinning further than 90 degrees
    SwerveModuleState state =
        SwerveModuleState.optimize(desiredState, new Rotation2d(m_turningEncoder.getVoltage()*Math.PI*2/4.95));

    // Calculate the drive output from the drive PID controller.
    final double driveOutput = state.speedMetersPerSecond * 0.1;
        //m_drivePIDController.calculate(m_driveEncoder.getRate(), state.speedMetersPerSecond);

    final double driveFeedforward = m_driveFeedforward.calculate(state.speedMetersPerSecond);

    // Calculate the turning motor output from the turning PID controller.
    final double turnOutput =
        m_turningPIDController.calculate(m_turningEncoder.getVoltage()*Math.PI*2/4.95, state.angle.getRadians());

    final double turnFeedforward =
        m_turnFeedforward.calculate(m_turningPIDController.getSetpoint().velocity);

    m_driveMotor.set(ControlMode.PercentOutput,driveOutput + 0*driveFeedforward);
    m_turningMotor.set(ControlMode.PercentOutput,turnOutput + 0*turnFeedforward);
  }
}
