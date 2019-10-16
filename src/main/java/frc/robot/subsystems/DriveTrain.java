package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.Robot;
import frc.robot.commands.Drive;
//import frc.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {
	public DifferentialDrive diffDrive; 
	double inputSpeed;
	double inputTurn; 
	boolean flase = false;
	
	//These get drawn in from the correct driver profile
	double maxSpeed;
	double maxTurn;
	boolean arcadeTank = true;	//arcade = true ; tank = false
//	String profileSelected = Robot.selectedProfile;
	String profileSelected = "ethan";
	
	public DriveTrain() {
		super();
		diffDrive = new DifferentialDrive(RobotMap.motorFL, RobotMap.motorFR);
		RobotMap.motorBL.follow(RobotMap.motorFL);
		RobotMap.motorBR.follow(RobotMap.motorFR);
		
		RobotMap.motorFR.setSafetyEnabled(false);
		RobotMap.motorFL.setSafetyEnabled(false);
		RobotMap.motorBR.setSafetyEnabled(false);
		RobotMap.motorBL.setSafetyEnabled(false);
        diffDrive.setSafetyEnabled(false);

				
	}
	public void drive(Joystick joy) {

		if (Robot.arcadeDrive.getBoolean(true)) {
			inputSpeed = joy.getRawAxis(1);
			inputTurn = -joy.getRawAxis(4);
		  } else {
			inputSpeed = joy.getRawAxis(1);
			inputTurn = joy.getRawAxis(5);
		  }
		  drive(inputSpeed, inputTurn);
    }
    

	
	/*public void drive(double left, double right) {
		if (arcadeTank) {
			diffDrive.arcadeDrive(left, right);
		} else {
			diffDrive.tankDrive(left, right);
		}
	}*/
	
	public void drive(double left, double right) {
		if (Robot.arcadeDrive.getBoolean(true)) {
			diffDrive.arcadeDrive(left, right, true);
		  } else {
			diffDrive.tankDrive(left, right, true);
		  }
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
}

