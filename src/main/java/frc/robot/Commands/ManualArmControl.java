package frc.robot.Commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Subsystems.Arm;

public class ManualArmControl extends CommandBase {
    public ManualArmControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addRequirements(Robot.robotArm);
    }


// Called repeatedly when this Command is scheduled to run
@Override
public void execute() {
  Robot.robotArm.manualArmControl(Robot.m_oi.getOperator().getRawAxis(1));
}

// Make this return true when this Command no longer needs to run execute()
@Override
public boolean isFinished() {
  return false;
}
}
