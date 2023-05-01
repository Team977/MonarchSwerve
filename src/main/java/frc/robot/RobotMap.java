package frc.robot;

public class RobotMap {
    
    public enum DriveDirection {
        forward, reverse;
      }
    
      public enum LoadingMode {
        hatch, cargo, nothing;
      }
      
      public enum ArmPositions {
        start, loadCargo, lowCargo, midCargo, highCargo, loadHatch, lowHatch, midHatch, highHatch;
      }
    
      public enum GripStage {
        hatchStart, hatchGrab, hatchRelease, cargoStart, cargoGrab, cargoRelease, cargoDunk, defense
      }

      
}
