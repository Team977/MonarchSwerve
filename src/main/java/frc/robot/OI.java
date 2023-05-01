package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotMap.GripStage;
import frc.robot.Commands.CargoButtonPressed;
//import frc.robot.commands.ClimbDown;
//import frc.robot.commands.ClimbDriveUp;
//import frc.robot.commands.ClimbUp;
import frc.robot.Commands.GoHigh;
import frc.robot.Commands.GoLow;
import frc.robot.Commands.GoMid;
import frc.robot.Commands.GripButtonPressed;
import frc.robot.Commands.HatchButtonPressed;
import frc.robot.Commands.RetractClimb;
import frc.robot.Commands.RetractClimb2;
//import frc.robot.commands.SetDefenseMode;
//import frc.robot.commands.StopClimb;
//import frc.robot.commands.StopClimbDrive;
import frc.robot.Commands.setDriveDirection;
//import frc.robot.commands.setGripStage;
import frc.robot.Commands.setPrecisionMode;
import frc.robot.Commands.stopArm;

public class OI {

    Joystick driver = new Joystick(0);
    Joystick operator = new Joystick(1);
    
    Trigger DriverA = new JoystickButton(driver, 1);
    Trigger DriverB = new JoystickButton(driver, 2);
    Trigger DriverX = new JoystickButton(driver, 3);
    Trigger DriverY = new JoystickButton(driver, 4);
    Trigger DriverL1 = new JoystickButton(driver, 5);
    Trigger DriverR1 = new JoystickButton(driver, 6);
    Trigger DriverSelect = new JoystickButton(driver, 7);
    Trigger DriverStart = new JoystickButton(driver, 8);
  
    Trigger hatchButton = new JoystickButton(operator, 2);
    Trigger cargoButton = new JoystickButton(operator, 1);
    Trigger gripButton = new JoystickButton(operator, 5);
    Trigger inButton = new JoystickButton(operator, 7);
    Trigger lowButton = new JoystickButton(operator, 3);
    Trigger midButton = new JoystickButton(operator, 4);
    Trigger hiButton = new JoystickButton(operator, 6);
    Trigger overButton = new JoystickButton(operator, 8);
  
      public OI(){
  
      DriverY.whenPressed(new SetDefenseMode());
      DriverL1.whenPressed(new setPrecisionMode(true));
      DriverL1.whenReleased(new setPrecisionMode(false));
      DriverR1.whenPressed(new setDriveDirection(RobotMap.DriveDirection.reverse));
      DriverR1.whenReleased(new setDriveDirection(RobotMap.DriveDirection.forward));
     
      DriverStart.whileHeld(new ClimbUp());
      DriverStart.whenReleased(new StopClimb());
      DriverX.whileHeld(new ClimbDriveUp());
      DriverX.whenReleased(new StopClimbDrive());
      DriverA.whileHeld(new RetractClimb());
      DriverA.whenReleased(new StopClimb());
      DriverB.whileHeld(new RetractClimb2());    
      DriverB.whenReleased(new StopClimb());
  
      DriverSelect.whileHeld(new ClimbDown());
      DriverSelect.whenReleased(new StopClimb());
  
  
      inButton.whenPressed(new setGripStage(GripStage.cargoDunk));
  
      hatchButton.whenPressed(new HatchButtonPressed());
      cargoButton.whenPressed(new CargoButtonPressed());
      gripButton.whenPressed(new GripButtonPressed());
      lowButton.whenPressed(new GoLow());
      midButton.whenPressed(new GoMid());
      hiButton.whenPressed(new GoHigh());
  
      overButton.whenPressed(new stopArm());
  
      //lowButton.whenPressed(new ArmMove(RobotMap.ArmPositions.start));
      //midButton.whenPressed(new ArmMove(RobotMap.ArmPositions.midCargo));
      //hiButton.whenPressed(new ArmMove(RobotMap.ArmPositions.highHatch));
  
    }
  
    public Joystick getJoystick() {
      return driver;
    }
    
    public Joystick getOperator(){
      return operator;
    }
      
  }