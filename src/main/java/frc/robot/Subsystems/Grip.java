package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.GripStage;
import frc.robot.RobotMap.LoadingMode;

public class Grip extends SubsystemBase{
    
    private Solenoid hatch;
    private Solenoid cargoArms;
    private Solenoid noodleArms;
    private DoubleSolenoid cargoTop;
    private LoadingMode mode = LoadingMode.hatch;
    private GripStage stage = GripStage.hatchGrab;
    private DigitalInput hatchBumper;
   
}
