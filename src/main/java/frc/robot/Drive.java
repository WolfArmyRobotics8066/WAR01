package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive {

    private final Joystick joystick = new Joystick(0);

    private WPI_VictorSPX victorController0 = new WPI_VictorSPX(0);
    private WPI_VictorSPX victorController1 = new WPI_VictorSPX(1);
    private WPI_VictorSPX victorCont roller2 = new WPI_VictorSPX(2);
    private WPI_VictorSPX victorController3 = new WPI_VictorSPX(3);

    private final Victor victor888_01 = new Victor(0); //Roller
    private Victor victor888_02 = new Victor(1); //Lifter

    private SpeedControllerGroup leftMotor = new SpeedControllerGroup(victorController0, victorController1);
    private SpeedControllerGroup rightMotor = new SpeedControllerGroup(victorController2, victorController3);

    private DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);

    private double maxVelocity = 0.5;

    private double startTime;

    public void LifterPowerCells() {

        //This function causes the power cells lifter to rise and fall.

        if (joystick.getRawButton(9)) {
            victor888_02.setSpeed(1);
        } else if (joystick.getRawButton(10)) {
            victor888_02.setSpeed(-1);
        } else {
            victor888_02.setSpeed(0);
        }

    }

    public void DropPowerCells() {

        /*This function causes the roller to rotate back and forth, making it 
        pick up and release the power cells.*/

        if (joystick.getRawButton(1)) {
            victor888_01.setSpeed(0.8);
        } else if (joystick.getRawButton(4)) {
            victor888_01.setSpeed(-0.8);
        } else {
            victor888_01.setSpeed(0);
        }

    }

    public void MovementDrive() {

        //This function makes the robot move.

        if (joystick.getRawButton(6)) {
            maxVelocity = 0.9;
        } else { maxVelocity = 0.5; }
        
        if (joystick.getRawButton(5) && maxVelocity > 0) {
            maxVelocity = 0.25;
        }

        drive.arcadeDrive((maxVelocity * joystick.getRawAxis(1)) * -1, 
                           maxVelocity * joystick.getRawAxis(0));

        LifterPowerCells();
        DropPowerCells();

    }

    public void AutonomousInit() {
        
        //This function causes a counter to be created, starting from 0

        startTime = Timer.getFPGATimestamp();

    }

    public void Autonomous1() {

        //This function causes the robot to walk for 2 seconds and stop.

        double time = Timer.getFPGATimestamp();

        if ((time - startTime) < 2) {
            drive.arcadeDrive(0.65, 0);
        } else { drive.stopMotor(); }

    }
}
