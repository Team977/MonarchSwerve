package frc.robot.Commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap.ArmPositions;
import frc.robot.Subsystems.Arm;

public class ArmMove extends CommandBase{
    private ArmPositions targetPos;

    public ArmMove(ArmPositions position, Arm subsystem) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addRequirements(subsystem);
        targetPos = position;
    }
    @Override
    public void initialize() {
    }
  
    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
      Robot.robotArm.setArmPosition(targetPos);
    }
  
    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
      return Robot.robotArm.getTarget()<10;
      //return (Robot.robotArm.getError() < 10);
    }
 











}