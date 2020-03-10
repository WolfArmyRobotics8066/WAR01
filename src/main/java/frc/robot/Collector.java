package frc.robot;

import edu.wpi.first.wpilibj.Victor;

public class Collector {

    private Victor victor888_01 = new Victor(0); //Roller
    private Victor victor888_02 = new Victor(1); //Lifter

    public void LifterPowerCells() {

        //This function causes the power cells lifter to rise and fall.

        if (Robot.controller.joystick.getRawButton(9)) {
            victor888_02.setSpeed(1);
        } else if (Robot.controller.joystick.getRawButton(10)) {
            victor888_02.setSpeed(-1);
        } else {
            victor888_02.setSpeed(0);
        }

    }

    public void DropPowerCells() {

        /*This function causes the roller to rotate back and forth, making it 
        pick up and release the power cells.*/

        if (Robot.controller.joystick.getRawButton(1)) {
            victor888_01.setSpeed(0.8);
        } else if (Robot.controller.joystick.getRawButton(4)) {
            victor888_01.setSpeed(-0.8);
        } else {
            victor888_01.setSpeed(0);
        }

    }

}
