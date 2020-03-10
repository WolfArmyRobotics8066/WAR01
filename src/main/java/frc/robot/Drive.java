package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.Robot;

public class Drive {

    private WPI_VictorSPX victorController0 = new WPI_VictorSPX(0);
    private WPI_VictorSPX victorController1 = new WPI_VictorSPX(1);
    private WPI_VictorSPX victorController2 = new WPI_VictorSPX(2);
    private WPI_VictorSPX victorController3 = new WPI_VictorSPX(3);

    private SpeedControllerGroup leftMotor = new SpeedControllerGroup(victorController0, victorController1);
    private SpeedControllerGroup rightMotor = new SpeedControllerGroup(victorController2, victorController3);

    private DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);

    private double maxVelocity = 0.5;

    private double startTime;

    public void MovementDrive() {

        //This function makes the robot move.

        if (Robot.controller.joystick.getRawButton(6)) {
            maxVelocity = 0.9;
        } else { maxVelocity = 0.5; }

        drive.arcadeDrive((maxVelocity * Robot.controller.joystick.getRawAxis(1)) * -1, 
                           maxVelocity * Robot.controller.joystick.getRawAxis(0));

    }

    public void AutonomousInit() {
        
        //This function causes a counter to be created, starting from 0

        startTime = Timer.getFPGATimestamp();

    }

    public void Autonomous() {

        //This function causes the robot to walk for 2 seconds and stop.

        double time = Timer.getFPGATimestamp();

        if ((time - startTime) < 2) {
            drive.arcadeDrive(0.65, 0);
        } else { drive.stopMotor(); }

    }
}
