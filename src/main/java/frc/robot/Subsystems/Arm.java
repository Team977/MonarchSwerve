// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.RobotMap.ArmPositions;
import frc.robot.Commands.ManualArmControl;


import edu.wpi.first.wpilibj.DigitalInput;



public class Arm extends SubsystemBase {

  private TalonSRX armTalon;
  private ArmPositions currentPosition = ArmPositions.start;
  public DigitalInput bottomLimit;
  public DigitalInput topLimit;

  final int kTimeoutMs = 30;

  /** Creates a new Arm. */
  public Arm() {

    super();
    armTalon = new TalonSRX(0); //CHANGE VALUE

    armTalon.configFactoryDefault();

    armTalon.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, kTimeoutMs);
    armTalon.setSensorPhase(false);
    armTalon.setInverted(false);
    armTalon.configNominalOutputForward(0, kTimeoutMs);
    armTalon.configNominalOutputReverse(0, kTimeoutMs);
    armTalon.configPeakOutputForward(1, kTimeoutMs);
    armTalon.configPeakOutputReverse(-0.5, kTimeoutMs);
    armTalon.configAllowableClosedloopError(0, 0, kTimeoutMs);

    armTalon.config_kF(0,0,kTimeoutMs);
    armTalon.config_kP(0, 40, kTimeoutMs);
    armTalon.config_kI(0, 0, kTimeoutMs);
    armTalon.config_kD(0, 0, kTimeoutMs);
  }

  public void moveToPos(double position){
    armTalon.set(ControlMode.Position, position);
  }


  public ArmPositions getPosition(){
    return currentPosition;
  }
/* 
   public int getError(){
    return armTalon.getClosedLoopError();
  } */

  public double getTarget(){
    return armTalon.getClosedLoopTarget();
  }
  public void setArmPosition(ArmPositions position){
    switch(position){
      case start:
        armTalon.set(ControlMode.Position, 70);
        break;        
      case loadHatch:
        armTalon.set(ControlMode.Position, 123);
        break;
      case lowHatch:
        armTalon.set(ControlMode.Position, 123);
        break;
      case lowCargo:
        armTalon.set(ControlMode.Position,212);
        break;
      case loadCargo:
        armTalon.set(ControlMode.Position, 330);
        break;
      case midHatch:
        armTalon.set(ControlMode.Position,455);
        break;
      case midCargo:
        armTalon.set(ControlMode.Position,538);
        break;
      case highHatch:
        armTalon.set(ControlMode.Position, 833);
        break;
      case highCargo:
        armTalon.set(ControlMode.Position, 883);
        break;
    }
  }

  public void manualArmControl(double speed){

    armTalon.set(ControlMode.PercentOutput, speed*0.5);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
 /*  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand */
  }

